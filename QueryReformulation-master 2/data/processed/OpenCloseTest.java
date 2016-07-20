/***/
package org.eclipse.ui.tests.stress;

import java.util.HashMap;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.tests.harness.util.FileUtil;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Test opening and closing of items.
*/
public class OpenCloseTest extends UITestCase {

    /**
*
*/
    private static final String ORG_ECLIPSE_JDT_UI_JAVA_PERSPECTIVE = "org.eclipse.jdt.ui.JavaPerspective";

    private static int index;

    private static final int numIterations = 10;

    private WorkbenchWindow workbenchWindow;

    /**
* Constructor.
*
* @param testName
*            Test's name.
*/
    public  OpenCloseTest(String testName) {
        super(testName);
        workbenchWindow = (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    }

    /**
* Test the opening and closing of a file.
*
*/
    public void testOpenCloseFile() {
        IWorkbenchPage page = workbenchWindow.getActivePage();
        try {
            FileUtil.createProject("TestProject");
            IProject testProject = ResourcesPlugin.getWorkspace().getRoot().getProject(//$NON-NLS-1$
            "TestProject");
            FileUtil.createFile("tempFile.txt", testProject);
            testProject.open(null);
            IEditorInput editorInput = new FileEditorInput(testProject.getFile("tempFile.txt"));
            IEditorPart editorPart = null;
            for (index = 0; index < numIterations; index++) {
                editorPart = page.openEditor(editorInput, //$NON-NLS-1$
                "org.eclipse.ui.DefaultTextEditor");
                page.closeEditor(editorPart, false);
            }
            FileUtil.deleteProject(testProject);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
* Test opening and closing of workbench window.
*
*/
    public void testOpenCloseWorkbenchWindow() {
        IWorkbenchWindow secondWorkbenchWindow = null;
        try {
            for (index = 0; index < numIterations; index++) {
                secondWorkbenchWindow = PlatformUI.getWorkbench().openWorkbenchWindow(getPageInput());
                secondWorkbenchWindow.close();
            }
        } catch (WorkbenchException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
* Test open and close of perspective.
*
*/
    public void testOpenClosePerspective() {
        ICommandService commandService = fWorkbench.getService(ICommandService.class);
        Command command = commandService.getCommand("org.eclipse.ui.window.closePerspective");
        HashMap parameters = new HashMap();
        parameters.put(IWorkbenchCommandConstants.WINDOW_CLOSE_PERSPECTIVE_PARM_ID, ORG_ECLIPSE_JDT_UI_JAVA_PERSPECTIVE);
        ParameterizedCommand pCommand = ParameterizedCommand.generateCommand(command, parameters);
        IHandlerService handlerService = (IHandlerService) workbenchWindow.getService(IHandlerService.class);
        for (index = 0; index < numIterations; index++) {
            try {
                PlatformUI.getWorkbench().showPerspective(ORG_ECLIPSE_JDT_UI_JAVA_PERSPECTIVE, workbenchWindow);
                try {
                    handlerService.executeCommand(pCommand, null);
                } catch (ExecutionException e1) {
                } catch (NotDefinedException e1) {
                } catch (NotEnabledException e1) {
                } catch (NotHandledException e1) {
                }
            } catch (WorkbenchException e) {
                e.printStackTrace();
            }
        }
    }

    /**
* Test open and close of view.
*
*/
    public void testOpenCloseView() {
        IViewPart consoleView = null;
        try {
            IWorkbenchPage page = PlatformUI.getWorkbench().showPerspective(ORG_ECLIPSE_JDT_UI_JAVA_PERSPECTIVE, workbenchWindow);
            for (index = 0; index < numIterations; index++) {
                consoleView = page.showView("org.eclipse.ui.views.ResourceNavigator");
                page.hideView(consoleView);
            }
        } catch (WorkbenchException e) {
            e.printStackTrace();
        }
    }

    /**
* Test open and close intro.
*
*/
    public void testOpenCloseIntro() {
        IIntroPart introPart = null;
        for (index = 0; index < numIterations; index++) {
            introPart = PlatformUI.getWorkbench().getIntroManager().showIntro(workbenchWindow, false);
            PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
        }
    }
}
