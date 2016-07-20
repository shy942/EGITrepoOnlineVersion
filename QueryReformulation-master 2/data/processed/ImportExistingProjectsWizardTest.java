/***/
package org.eclipse.ui.tests.datatransfer;

import static org.eclipse.jface.dialogs.IMessageProvider.WARNING;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.tests.harness.FileSystemHelper;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.dialogs.ImportExportWizard;
import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
import org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage;
import org.eclipse.ui.internal.wizards.datatransfer.WizardProjectsImportPage.ProjectRecord;
import org.eclipse.ui.tests.TestPlugin;
import org.eclipse.ui.tests.harness.util.DialogCheck;
import org.eclipse.ui.tests.harness.util.FileTool;
import org.eclipse.ui.tests.harness.util.FileUtil;
import org.eclipse.ui.tests.harness.util.UITestCase;
import org.eclipse.ui.wizards.datatransfer.ExternalProjectImportWizard;
import junit.framework.TestSuite;

public class ImportExistingProjectsWizardTest extends UITestCase {

    private static final String DATA_PATH_PREFIX = "data/org.eclipse.datatransferArchives/";

    private static final String WS_DATA_PREFIX = "data/workspaces";

    private static final String WS_DATA_LOCATION = "importExistingFromDirTest";

    private static final String WS_NESTED_DATA_LOCATION = "importExistingNestedTest";

    private static final String ARCHIVE_HELLOWORLD = "helloworld";

    private static final String ARCHIVE_FILE_WITH_EMPTY_FOLDER = "EmptyFolderInArchive";

    private static final String PROJECTS_ARCHIVE = "ProjectsArchive";

    private static final String CORRUPT_PROJECTS_ARCHIVE = "CorruptProjectsArchive";

    private static final String[] FILE_LIST = new String[] { "test-file-1.txt", "test-file-2.doc", ".project" };

    private static final String[] ARCHIVE_FILE_EMPTY_FOLDER_LIST = new String[] { "empty", "folder" };

    private String dataLocation = null;

    private String zipLocation = null;

    private boolean originalRefreshSetting;

    public static TestSuite suite() {
        TestSuite ts = new TestSuite();
        ts.addTest(new ImportExistingProjectsWizardTest("testFindSingleZip"));
        ts.addTest(new ImportExistingProjectsWizardTest("testFindSingleTar"));
        ts.addTest(new ImportExistingProjectsWizardTest("testFindSingleDirectory"));
        ts.addTest(new ImportExistingProjectsWizardTest("testDoNotShowProjectWithSameName"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportSingleZip"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportZipWithEmptyFolder"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportSingleTar"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportTarWithEmptyFolder"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportSingleDirectory"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportSingleDirectoryWithCopy"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportSingleDirectoryWithCopyDeleteProjectKeepContents"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportZipDeleteContentsImportAgain"));
        ts.addTest(new ImportExistingProjectsWizardTest("testInitialValue"));
        ts.addTest(new ImportExistingProjectsWizardTest("testImportArchiveMultiProject"));
        ts.addTest(new ImportExistingProjectsWizardTest("testGetProjectRecords"));
        ts.addTest(new ImportExistingProjectsWizardTest("testGetProjectRecordsShouldHandleCorruptProjects"));
        ts.addTest(new ImportExistingProjectsWizardTest("testGetProjectRecordsShouldHandleCorruptAndConflictingProjects"));
        return ts;
    }

    public  ImportExistingProjectsWizardTest(String testName) {
        super(testName);
    }

    private Shell getShell() {
        return DialogCheck.getShell();
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        originalRefreshSetting = ResourcesPlugin.getPlugin().getPluginPreferences().getBoolean(ResourcesPlugin.PREF_AUTO_REFRESH);
        ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_AUTO_REFRESH, true);
    }

    @Override
    protected void doTearDown() throws Exception {
        super.doTearDown();
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        IProject[] projects = wsRoot.getProjects();
        for (int i = projects.length - 1; i >= 0; i--) {
            FileUtil.deleteProject(projects[i]);
        }
        // clean up any data directories created
        if (dataLocation != null) {
            File root = new File(dataLocation);
            if (root.exists()) {
                FileSystemHelper.clear(root);
            }
        }
        // reset for next test
        dataLocation = null;
        // do same for zip location
        if (zipLocation != null) {
            File root = new File(zipLocation);
            if (root.exists()) {
                // zipLocation is the zip file, not the temp directory it was
                // copied to
                FileSystemHelper.clear(root.getParentFile());
            }
        }
        // reset for next test
        zipLocation = null;
        ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_AUTO_REFRESH, originalRefreshSetting);
    }

    private void waitForRefresh() {
        try {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InterruptedException {
                    Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
                }
            });
        } catch (InvocationTargetException e) {
            fail(e.getLocalizedMessage());
        } catch (InterruptedException e) {
            fail(e.getLocalizedMessage());
        }
    }

    public void testFindSingleZip() {
        try {
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".zip")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                assertFalse(selectedProject.hasConflicts());
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public void testFindSingleTar() {
        try {
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".tar")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                assertFalse(selectedProject.hasConflicts());
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in tar", projectNames.containsAll(projects));
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public void testFindSingleDirectory() {
        try {
            dataLocation = copyDataLocation(WS_DATA_LOCATION);
            IPath wsPath = new Path(dataLocation).append("HelloWorld");
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We're importing a directory
            wpip.getProjectFromDirectoryRadio().setSelection((true));
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                assertFalse(selectedProject.hasConflicts());
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in directory", projectNames.containsAll(projects));
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    public void testDoNotShowProjectWithSameName() {
        try {
            dataLocation = copyDataLocation(WS_DATA_LOCATION);
            IPath wsPath = new Path(dataLocation);
            FileUtil.createProject("HelloWorld");
            WizardProjectsImportPage wpip = getNewWizard();
            // We're importing a directory
            wpip.getProjectFromDirectoryRadio().setSelection((true));
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            for (ProjectRecord selectedProject : selectedProjects) {
                if (selectedProject.getProjectName().equals("HelloWorld")) {
                    assertTrue(selectedProject.hasConflicts());
                }
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    public void testImportSingleZip() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".zip")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                assertFalse(selectedProject.hasConflicts());
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportZipWithEmptyFolder() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_FILE_WITH_EMPTY_FOLDER + ".zip")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("A");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], ARCHIVE_FILE_EMPTY_FOLDER_LIST, false);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportSingleTar() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".tar")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in tar", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportTarWithEmptyFolder() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_FILE_WITH_EMPTY_FOLDER + ".tar")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("A");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in tar", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported: Expected=1, Actual=" + workspaceProjects.length);
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("A");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], ARCHIVE_FILE_EMPTY_FOLDER_LIST, false);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportSingleDirectory() {
        IPath wsPath = null;
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            dataLocation = copyDataLocation(WS_DATA_LOCATION);
            wsPath = new Path(dataLocation).append("HelloWorld");
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            wpip.getProjectFromDirectoryRadio().setSelection((true));
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in directory", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(false, workspaceProjects[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportSingleDirectoryWithCopy() {
        IPath wsPath = null;
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            dataLocation = copyDataLocation(WS_DATA_LOCATION);
            wsPath = new Path(dataLocation).append("HelloWorld");
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            wpip.getProjectFromDirectoryRadio().setSelection((true));
            wpip.getCopyCheckbox().setSelection(true);
            wpip.saveWidgetValues();
            wpip.restoreWidgetValues();
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportSingleDirectoryWithCopyDeleteProjectKeepContents() {
        IPath wsPath = null;
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            dataLocation = copyDataLocation(WS_DATA_LOCATION);
            wsPath = new Path(dataLocation).append("HelloWorld");
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            wpip.getProjectFromDirectoryRadio().setSelection((true));
            wpip.getCopyCheckbox().setSelection(true);
            wpip.saveWidgetValues();
            wpip.restoreWidgetValues();
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported: " + workspaceProjects.length + " projects in workspace.");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
            // delete projects in workspace but not contents
            for (IProject workspaceProject : workspaceProjects) {
                workspaceProject.delete(false, true, null);
            }
            assertTrue("Test project not deleted successfully.", root.getProjects().length == 0);
            // perform same test again, but this time import from this workspace
            final WizardProjectsImportPage wpip2 = getNewWizard();
            HashSet projects2 = new HashSet();
            projects2.add("HelloWorld");
            wpip2.getProjectFromDirectoryRadio().setSelection((true));
            wpip2.getCopyCheckbox().setSelection(true);
            wpip2.saveWidgetValues();
            wpip2.restoreWidgetValues();
            wpip2.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects2 = wpip2.getProjectRecords();
            assertTrue("Not all projects were found correctly in zip (2).", selectedProjects2.length == 1);
            ArrayList projectNames2 = new ArrayList();
            for (ProjectRecord element : selectedProjects2) {
                projectNames2.add(element.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip (2)", projectNames2.containsAll(projects2));
            CheckboxTreeViewer projects2List = wpip2.getProjectsList();
            projects2List.setCheckedElements(selectedProjects2);
            // Try importing all the projects we found
            wpip2.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself (2)");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportZipDeleteContentsImportAgain() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            URL archiveFile = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".zip")));
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(archiveFile.getPath());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 1) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("Project was imported as a folder into itself");
            }
            verifyProjectInWorkspace(true, workspaceProjects[0], FILE_LIST, true);
            // delete projects in workspace but not contents
            for (IProject workspaceProject : workspaceProjects) {
                workspaceProject.delete(true, true, null);
            }
            assertTrue("Test project not deleted successfully.", root.getProjects().length == 0);
            // import again
            IProject[] workspaceProjects2 = root.getProjects();
            for (IProject element : workspaceProjects2) {
                FileUtil.deleteProject(element);
            }
            URL archiveFile2 = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + ARCHIVE_HELLOWORLD + ".zip")));
            WizardProjectsImportPage wpip2 = getNewWizard();
            HashSet projects2 = new HashSet();
            projects2.add("HelloWorld");
            // We
            wpip2.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip2.updateProjectsList(archiveFile2.getPath());
            ProjectRecord[] selectedProjects2 = wpip2.getProjectRecords();
            ArrayList projectNames2 = new ArrayList();
            for (ProjectRecord element : selectedProjects2) {
                projectNames2.add(element.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip (2)", projectNames2.containsAll(projects2));
            CheckboxTreeViewer projectsList2 = wpip2.getProjectsList();
            projectsList2.setCheckedElements(selectedProjects2);
            // Try importing all the projects we found
            wpip2.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects2 = root.getProjects();
            if (workspaceProjects2.length != 1) {
                fail("Incorrect Number of projects imported (2)");
            }
            IFolder helloFolder2 = workspaceProjects2[0].getFolder("HelloWorld");
            if (helloFolder2.exists()) {
                fail("Project was imported as a folder into itself (2)");
            }
            verifyProjectInWorkspace(true, workspaceProjects2[0], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportDirectoryNested() {
        IPath wsPath = null;
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            dataLocation = copyDataLocation(WS_NESTED_DATA_LOCATION);
            wsPath = new Path(dataLocation).append("A");
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("A");
            projects.add("B");
            projects.add("C");
            wpip.getProjectFromDirectoryRadio().setSelection(true);
            wpip.getNestedProjectsCheckbox().setSelection(true);
            wpip.getCopyCheckbox().setSelection(false);
            wpip.saveWidgetValues();
            wpip.restoreWidgetValues();
            wpip.updateProjectsList(wsPath.toOSString());
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in directory", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "A", "B", and "C" should be the only projects in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 3) {
                fail("Incorrect number of projects imported");
            }
            IFolder aFolder = workspaceProjects[0].getFolder("A");
            if (aFolder.exists()) {
                fail("Project A was imported as a folder into itself");
            }
            IFolder bFolder = workspaceProjects[1].getFolder("B");
            if (bFolder.exists()) {
                fail("Project B was imported as a folder into itself");
            }
            IFolder cFolder = workspaceProjects[2].getFolder("C");
            if (cFolder.exists()) {
                fail("Project C was imported as a folder into itself");
            }
            workspaceProjects[0].refreshLocal(IResource.DEPTH_INFINITE, null);
            verifyProjectInWorkspace(false, workspaceProjects[0], FILE_LIST, true);
            verifyProjectInWorkspace(false, workspaceProjects[1], FILE_LIST, true);
            verifyProjectInWorkspace(false, workspaceProjects[2], FILE_LIST, true);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testInitialValue() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            zipLocation = copyZipLocation(WS_DATA_LOCATION);
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            WizardProjectsImportPage wpip = getExternalImportWizard(zipLocation);
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            projects.add("WorldHello");
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            // no initial value, no projects
            wpip = getExternalImportWizard(null);
            selectedProjects = wpip.getProjectRecords();
            assertEquals(0, selectedProjects.length);
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    public void testImportArchiveMultiProject() {
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            zipLocation = copyZipLocation(WS_DATA_LOCATION);
            IProject[] workspaceProjects = root.getProjects();
            for (IProject workspaceProject : workspaceProjects) {
                FileUtil.deleteProject(workspaceProject);
            }
            WizardProjectsImportPage wpip = getNewWizard();
            HashSet projects = new HashSet();
            projects.add("HelloWorld");
            projects.add("WorldHello");
            // We
            wpip.getProjectFromDirectoryRadio().setSelection((false));
            // want
            // the
            // other
            // one
            // selected
            wpip.updateProjectsList(zipLocation);
            ProjectRecord[] selectedProjects = wpip.getProjectRecords();
            ArrayList projectNames = new ArrayList();
            for (ProjectRecord selectedProject : selectedProjects) {
                projectNames.add(selectedProject.getProjectName());
            }
            assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(projects));
            CheckboxTreeViewer projectsList = wpip.getProjectsList();
            projectsList.setCheckedElements(selectedProjects);
            // Try importing all the projects we found
            wpip.createProjects();
            waitForRefresh();
            // "HelloWorld" should be the only project in the workspace
            workspaceProjects = root.getProjects();
            if (workspaceProjects.length != 2) {
                fail("Incorrect Number of projects imported");
            }
            IFolder helloFolder = workspaceProjects[0].getFolder("HelloWorld");
            if (helloFolder.exists()) {
                fail("HelloWorld was imported as a folder into itself");
            }
            IFolder folder2 = workspaceProjects[0].getFolder("WorldHello");
            if (folder2.exists()) {
                fail("WorldHello was imported as a folder into itself");
            }
            for (IProject workspaceProject : workspaceProjects) {
                verifyProjectInWorkspace(true, workspaceProject, FILE_LIST, true);
            }
        } catch (IOException e) {
            fail(e.toString());
        } catch (CoreException e) {
            fail(e.toString());
        }
    }

    /**
* Verify whether or not the imported project is in the current workspace
* location (i.e. copy projects was true) or in another workspace location
* (i.e. copy projects was false).
*
* @param inWorkspace
* @param project
*/
    private void verifyProjectInWorkspace(final boolean inWorkspace, final IProject project, String[] fileList, boolean isListFiles) {
        IPath rootLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
        IPath projectLocation = project.getLocation();
        boolean isProjectInWorkspace = rootLocation.isPrefixOf(projectLocation);
        if (inWorkspace) {
            if (!isProjectInWorkspace) {
                fail(project.getName() + " should be in the workspace location: " + rootLocation.toOSString());
            }
        } else {
            if (isProjectInWorkspace) {
                fail(project.getName() + " should not be in the workspace location: " + rootLocation.toOSString());
            }
        }
        StringBuffer filesNotImported = new StringBuffer();
        // make sure the files in the project were imported
        for (String element : fileList) {
            IResource res = isListFiles ? (IResource) project.getFile(element) : (IResource) project.getFolder(element);
            if (!res.exists()) {
                filesNotImported.append(res.getName() + ", ");
            }
        }
        assertTrue("Files expected but not in workspace for project \"" + project.getName() + "\": " + filesNotImported.toString(), filesNotImported.length() == 0);
    }

    /**
* Copies the data to a temporary directory and returns the new location.
*
* @return the location
*/
    private String copyDataLocation(String dataLocation) throws IOException {
        TestPlugin plugin = TestPlugin.getDefault();
        if (plugin == null) {
            throw new IllegalStateException("TestPlugin default reference is null");
        }
        URL fullPathString = plugin.getBundle().getResource("/" + WS_DATA_PREFIX + "/" + dataLocation + ".zip");
        URI fileURI = null;
        try {
            fileURI = FileLocator.resolve(fullPathString).toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException();
        }
        File origin = new File(fileURI);
        if (!origin.exists()) {
            throw new IllegalArgumentException();
        }
        ZipFile zFile = new ZipFile(origin);
        File destination = new File(FileSystemHelper.getRandomLocation(FileSystemHelper.getTempDir()).toOSString());
        FileTool.unzip(zFile, destination);
        return destination.getAbsolutePath();
    }

    private String copyZipLocation(String zipLocation) throws IOException {
        TestPlugin plugin = TestPlugin.getDefault();
        if (plugin == null) {
            throw new IllegalStateException("TestPlugin default reference is null");
        }
        URL fullPathString = plugin.getBundle().getResource(WS_DATA_PREFIX + "/" + zipLocation + ".zip");
        URI fileURI = null;
        try {
            fileURI = FileLocator.resolve(fullPathString).toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException();
        }
        File origin = new File(fileURI);
        if (!origin.exists()) {
            throw new IllegalArgumentException();
        }
        File destination = new File(FileSystemHelper.getRandomLocation(FileSystemHelper.getTempDir()).toOSString() + File.separator + ARCHIVE_HELLOWORLD + ".zip");
        FileTool.copy(origin, destination);
        return destination.getAbsolutePath();
    }

    private WizardProjectsImportPage getNewWizard() {
        ImportExportWizard wizard = new ImportExportWizard(ImportExportWizard.IMPORT);
        wizard.init(getWorkbench(), null);
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings wizardSettings = workbenchSettings.getSection("ImportExportTests");
        if (wizardSettings == null) {
            wizardSettings = workbenchSettings.addNewSection("ImportExportTests");
        }
        wizard.setDialogSettings(wizardSettings);
        wizard.setForcePreviousAndNextButtons(true);
        WizardProjectsImportPage wpip = new WizardProjectsImportPage();
        Shell shell = getShell();
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.create();
        dialog.getShell().setSize(Math.max(100, dialog.getShell().getSize().x), 100);
        Composite parent = new Composite(shell, SWT.NONE);
        parent.setLayout(new GridLayout());
        wpip.setWizard(dialog.getCurrentPage().getWizard());
        wpip.createControl(parent);
        return wpip;
    }

    public void testGetProjectRecords() throws Exception {
        HashSet expectedNames = new HashSet();
        expectedNames.add("Project1");
        expectedNames.add("Project2");
        expectedNames.add("Project3");
        expectedNames.add("Project4");
        expectedNames.add("Project5");
        URL projectsArchive = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + PROJECTS_ARCHIVE + ".zip")));
        List projectNames = getNonConflictingProjectsFromArchive(projectsArchive);
        assertTrue("Not all projects were found correctly in zip", projectNames.containsAll(expectedNames));
        FileUtil.createProject("Project1");
        projectNames = getNonConflictingProjectsFromArchive(projectsArchive);
        assertFalse("Conflict flag is not set on the projects correctly", projectNames.contains("Project1"));
    }

    private List getNonConflictingProjectsFromArchive(URL projectsArchive) {
        WizardProjectsImportPage newWizard = getNewWizard();
        newWizard.getProjectFromDirectoryRadio().setSelection(false);
        newWizard.updateProjectsList(projectsArchive.getPath());
        ProjectRecord[] projectRecords = newWizard.getProjectRecords();
        List projectNames = new ArrayList();
        for (int i = 0; i < projectRecords.length; i++) {
            if (!projectRecords[i].hasConflicts()) {
                projectNames.add(projectRecords[i].getProjectName());
            }
        }
        return projectNames;
    }

    public void testGetProjectRecordsShouldHandleCorruptProjects() throws Exception {
        URL projectsArchive = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + CORRUPT_PROJECTS_ARCHIVE + ".zip")));
        WizardProjectsImportPage newWizard = spy(getNewWizard());
        ProjectRecord[] projectRecords = getProjectsFromArchive(newWizard, projectsArchive);
        verify(newWizard).setMessage(DataTransferMessages.WizardProjectsImportPage_projectsInvalid, WARNING);
        List<String> invalidProjectNames = getInvalidProjects(projectRecords);
        assertEquals("Expected to find invalid projects", 2, invalidProjectNames.size());
        assertEquals("Expected pseudo name for first invalid project", DataTransferMessages.WizardProjectsImportPage_invalidProjectName, invalidProjectNames.get(0));
        assertEquals("Expected pseudo name for second invalid project", DataTransferMessages.WizardProjectsImportPage_invalidProjectName, invalidProjectNames.get(1));
        List<String> validProjectNames = getValidProjects(projectRecords);
        assertEquals("Expected to find only one valid project", 1, validProjectNames.size());
        assertEquals("Expected to find valid project", "Project1", validProjectNames.get(0));
    }

    public void testGetProjectRecordsShouldHandleCorruptAndConflictingProjects() throws Exception {
        URL projectsArchive = Platform.asLocalURL(Platform.find(TestPlugin.getDefault().getBundle(), new Path(DATA_PATH_PREFIX + CORRUPT_PROJECTS_ARCHIVE + ".zip")));
        WizardProjectsImportPage newWizard = spy(getNewWizard());
        FileUtil.createProject("Project1");
        ProjectRecord[] projectRecords = getProjectsFromArchive(newWizard, projectsArchive);
        verify(newWizard).setMessage(DataTransferMessages.WizardProjectsImportPage_projectsInWorkspaceAndInvalid, WARNING);
        List<String> invalidProjectNames = getInvalidProjects(projectRecords);
        assertEquals("Expected to find invalid projects", 2, invalidProjectNames.size());
        List<String> validProjectNames = getValidProjects(projectRecords);
        assertEquals("Expected to find one valid project", 1, validProjectNames.size());
        assertEquals("Expected to find valid project", "Project1", validProjectNames.get(0));
        List<String> conflictingProjectNames = getProjectsWithConflicts(projectRecords);
        assertEquals("Expected to find one existing project", 1, conflictingProjectNames.size());
        assertEquals("Expected to find existing project", "Project1", conflictingProjectNames.get(0));
    }

    private ProjectRecord[] getProjectsFromArchive(WizardProjectsImportPage newWizard, URL projectsArchive) {
        newWizard.getProjectFromDirectoryRadio().setSelection(false);
        newWizard.updateProjectsList(projectsArchive.getPath());
        ProjectRecord[] projectRecords = newWizard.getProjectRecords();
        return projectRecords;
    }

    private List<String> getValidProjects(ProjectRecord[] projectRecords) {
        List<String> projectNames = new ArrayList<String>();
        for (int i = 0; i < projectRecords.length; i++) {
            if (!projectRecords[i].isInvalidProject()) {
                projectNames.add(projectRecords[i].getProjectName());
            }
        }
        return projectNames;
    }

    private List<String> getInvalidProjects(ProjectRecord[] projectRecords) {
        List<String> projectNames = new ArrayList<String>();
        for (ProjectRecord projectRecord : projectRecords) {
            if (projectRecord.isInvalidProject()) {
                projectNames.add(projectRecord.getProjectName());
            }
        }
        return projectNames;
    }

    private List<String> getProjectsWithConflicts(ProjectRecord[] projectRecords) {
        List<String> projectNames = new ArrayList<String>();
        for (ProjectRecord projectRecord : projectRecords) {
            if (projectRecord.hasConflicts()) {
                projectNames.add(projectRecord.getProjectName());
            }
        }
        return projectNames;
    }

    private WizardProjectsImportPage getExternalImportWizard(String initialPath) {
        ExternalProjectImportWizard wizard = new ExternalProjectImportWizard(initialPath);
        wizard.init(getWorkbench(), null);
        WizardDialog dialog = new WizardDialog(getShell(), wizard);
        dialog.create();
        dialog.getShell().setSize(Math.max(100, dialog.getShell().getSize().x), 100);
        WizardProjectsImportPage wpip = (WizardProjectsImportPage) wizard.getPage("wizardExternalProjectsPage");
        return wpip;
    }
}
