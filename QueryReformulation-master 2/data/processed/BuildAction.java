/***/
package org.eclipse.ui.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEInternalPreferences;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.actions.BuildUtilities;
import org.eclipse.ui.progress.IProgressConstants2;

/**
* Standard actions for full and incremental builds of the selected project(s)
* and their references project build configurations.
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class BuildAction extends WorkspaceAction {

    /**
* The id of an incremental build action.
*/
    //$NON-NLS-1$
    public static final String ID_BUILD = PlatformUI.PLUGIN_ID + ".BuildAction";

    /**
* The id of a rebuild all action.
*/
    //$NON-NLS-1$
    public static final String ID_REBUILD_ALL = PlatformUI.PLUGIN_ID + ".RebuildAllAction";

    private int buildType;

    /**
* The list of IProjects to build (computed lazily). This is computed from the
* list of project build configurations that are to be built.
*/
    private List<IProject> projectsToBuild;

    /**
* The list of {@link IBuildConfiguration} to build (computed lazily).
*/
    private List<IBuildConfiguration> projectConfigsToBuild;

    /**
* Creates a new action of the appropriate type. The action id is
* <code>ID_BUILD</code> for incremental builds and <code>ID_REBUILD_ALL</code>
* for full builds.
*
* @param shell the shell for any dialogs
* @param type the type of build; one of
*  <code>IncrementalProjectBuilder.INCREMENTAL_BUILD</code> or
*  <code>IncrementalProjectBuilder.FULL_BUILD</code>
* @deprecated See {@link #BuildAction(IShellProvider, int)}
*/
    @Deprecated
    public  BuildAction(Shell shell, int type) {
        //$NON-NLS-1$
        super(shell, "");
        initAction(type);
    }

    /**
* Creates a new action of the appropriate type. The action id is
* <code>ID_BUILD</code> for incremental builds and
* <code>ID_REBUILD_ALL</code> for full builds.
*
* @param provider
*            the shell provider for any dialogs
* @param type
*            the type of build; one of
*            <code>IncrementalProjectBuilder.INCREMENTAL_BUILD</code> or
*            <code>IncrementalProjectBuilder.FULL_BUILD</code>
* @since 3.4
*/
    public  BuildAction(IShellProvider provider, int type) {
        //$NON-NLS-1$
        super(provider, "");
        initAction(type);
    }

    /**
* @param type
*/
    private void initAction(int type) {
        if (type == IncrementalProjectBuilder.INCREMENTAL_BUILD) {
            setText(IDEWorkbenchMessages.BuildAction_text);
            setToolTipText(IDEWorkbenchMessages.BuildAction_toolTip);
            setId(ID_BUILD);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.INCREMENTAL_BUILD_ACTION);
        } else {
            setText(IDEWorkbenchMessages.RebuildAction_text);
            setToolTipText(IDEWorkbenchMessages.RebuildAction_tooltip);
            setId(ID_REBUILD_ALL);
            PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IIDEHelpContextIds.FULL_BUILD_ACTION);
        }
        this.buildType = type;
    }

    @Override
    protected List<? extends IResource> getActionResources() {
        return getProjectsToBuild();
    }

    @Override
    protected String getOperationMessage() {
        return IDEWorkbenchMessages.BuildAction_operationMessage;
    }

    @Override
    protected String getProblemsMessage() {
        return IDEWorkbenchMessages.BuildAction_problemMessage;
    }

    @Override
    protected String getProblemsTitle() {
        return IDEWorkbenchMessages.BuildAction_problemTitle;
    }

    /**
* Returns the projects to build.
* This contains the set of projects which have builders, across all selected resources.
*/
    List<IProject> getProjectsToBuild() {
        if (projectsToBuild == null) {
            Set<IProject> projects = new HashSet(3);
            List<? extends IBuildConfiguration> configurations = getBuildConfigurationsToBuild();
            for (IBuildConfiguration buildConfiguration : configurations) {
                projects.add(buildConfiguration.getProject());
            }
            projectsToBuild = new ArrayList(projects);
        }
        return projectsToBuild;
    }

    /**
* This collection of project build configs, derived from the selected
* resources, is passed to the workspace for building.  The Workspace
* is responsible for resolving references.
* @return List of project build configurations to build.
* @since 3.7
*/
    protected List<? extends IBuildConfiguration> getBuildConfigurationsToBuild() {
        if (projectConfigsToBuild == null) {
            Set<IBuildConfiguration> configs = new HashSet(3);
            for (IResource resource : getSelectedResources()) {
                IProject project = resource.getProject();
                if (project != null && hasBuilder(project)) {
                    try {
                        configs.add(project.getActiveBuildConfig());
                    } catch (CoreException e) {
                    }
                }
            }
            projectConfigsToBuild = new ArrayList(configs);
        }
        return projectConfigsToBuild;
    }

    /**
* Returns whether there are builders configured on the given project.
*
* @return <code>true</code> if it has builders,
*   <code>false</code> if not, or if this couldn't be determined
*/
    boolean hasBuilder(IProject project) {
        if (!project.isAccessible())
            return false;
        try {
            ICommand[] commands = project.getDescription().getBuildSpec();
            if (commands.length > 0) {
                return true;
            }
        } catch (CoreException e) {
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        //update enablement based on active window and part
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            selectionChanged(new StructuredSelection(BuildUtilities.findSelectedProjects(window)));
        }
        return super.isEnabled();
    }

    /**
* Returns whether the user's preference is set to automatically save modified
* resources before a manual build is done.
*
* @return <code>true</code> if Save All Before Build is enabled
*/
    public static boolean isSaveAllSet() {
        IPreferenceStore store = IDEWorkbenchPlugin.getDefault().getPreferenceStore();
        return store.getBoolean(IDEInternalPreferences.SAVE_ALL_BEFORE_BUILD);
    }

    /*
* This override allows the user to save the contents of selected open
* editors so that the updated contents will be used for building. The build
* is run as a background job.
*/
    @Override
    public void run() {
        final List<?> buildConfigurations = getBuildConfigurationsToBuild();
        if (buildConfigurations == null || buildConfigurations.isEmpty()) {
            return;
        }
        // Save all resources prior to doing build
        BuildUtilities.saveEditors(getProjectsToBuild());
        runInBackground(null, ResourcesPlugin.FAMILY_MANUAL_BUILD);
    }

    @Override
    public void runInBackground(ISchedulingRule rule, Object[] jobFamilies) {
        // Get immutable copies of the build settings
        final int kind = buildType;
        List<? extends IBuildConfiguration> buildConfigurations = getBuildConfigurationsToBuild();
        if (buildConfigurations == null || buildConfigurations.isEmpty()) {
            return;
        }
        final IBuildConfiguration[] configs = buildConfigurations.toArray(new IBuildConfiguration[buildConfigurations.size()]);
        // Schedule a Workspace Job to perform the build
        Job job = new WorkspaceJob(removeMnemonics(getText())) {

            @Override
            public boolean belongsTo(Object family) {
                return ResourcesPlugin.FAMILY_MANUAL_BUILD.equals(family);
            }

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) {
                IStatus status = null;
                SubMonitor progress = SubMonitor.convert(monitor, 1);
                progress.setTaskName(getOperationMessage());
                try {
                    // Backwards compatibility: check shouldPerformResourcePruning().
                    // Previously if this returned true, the full reference graph is built, otherwise just build the selected configurations
                    ResourcesPlugin.getWorkspace().build(configs, kind, shouldPerformResourcePruning(), progress.split(1));
                } catch (CoreException e) {
                    status = e.getStatus();
                }
                return status == null ? Status.OK_STATUS : status;
            }
        };
        job.setProperty(IProgressConstants2.SHOW_IN_TASKBAR_ICON_PROPERTY, Boolean.TRUE);
        job.setUser(true);
        job.schedule();
    }

    @Override
    protected boolean shouldPerformResourcePruning() {
        return true;
    }

    /**
* The <code>BuildAction</code> implementation of this
* <code>SelectionListenerAction</code> method ensures that this action is
* enabled only if all of the selected resources have buildable projects.
*/
    @Override
    protected boolean updateSelection(IStructuredSelection s) {
        projectConfigsToBuild = null;
        projectsToBuild = null;
        IProject[] projects = getProjectsToBuild().toArray(new IProject[0]);
        return BuildUtilities.isEnabled(projects, IncrementalProjectBuilder.INCREMENTAL_BUILD);
    }
}