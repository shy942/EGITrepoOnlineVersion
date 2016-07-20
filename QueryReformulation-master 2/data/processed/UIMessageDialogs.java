/***/
package org.eclipse.ui.tests.dialogs;

import java.text.MessageFormat;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.views.navigator.ResourceNavigatorMessages;
import org.eclipse.ui.tests.harness.util.DialogCheck;
import junit.framework.TestCase;

public class UIMessageDialogs extends TestCase {

    private static final String DUMMY_RESOURCE = "Dummy.resource";

    private static final String DUMMY_PROJECT = "DummyProject";

    private static final String DUMMY_ABSOLUTE_PATH = "C:\\Dummypath\\Dummy.resource";

    private static final String DUMMY_RELATIVE_PATH = "\\" + DUMMY_PROJECT + "\\" + DUMMY_RESOURCE;

    public  UIMessageDialogs(String name) {
        super(name);
    }

    private Shell getShell() {
        return DialogCheck.getShell();
    }

    private MessageDialog getInformationDialog(String title, String message) {
        return new MessageDialog(getShell(), title, null, message, MessageDialog.INFORMATION, 0, IDialogConstants.OK_LABEL);
    }

    private MessageDialog getQuestionDialog(String title, String message) {
        return new MessageDialog(getShell(), title, null, message, MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL);
    }

    private MessageDialog getWarningDialog(String title, String message) {
        return new MessageDialog(getShell(), title, null, message, MessageDialog.WARNING, 0, IDialogConstants.OK_LABEL);
    }

    public void testAbortPageFlipping() {
        Dialog dialog = getWarningDialog(JFaceResources.getString("AbortPageFlippingDialog.title"), JFaceResources.getString("AbortPageFlippingDialog.message"));
        DialogCheck.assertDialog(dialog, this);
    }

    public void testCopyOverwrite() {
        Dialog dialog = getQuestionDialog("Exists", "Overwrite?");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testDeleteProject() {
        String title = "Delete Project";
        String msg = NLS.bind("Delete", (new Object[] { DUMMY_PROJECT, DUMMY_ABSOLUTE_PATH }));
        Dialog dialog = new MessageDialog(getShell(), title, null, msg, MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testDeleteReadOnlyCheck() {
        Dialog dialog = new MessageDialog(getShell(), "Delete?", null, "This?", MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.YES_TO_ALL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testDeleteResource() {
        Dialog dialog = getQuestionDialog("Delete", "Delete?");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testDeleteResources() {
        Dialog dialog = getQuestionDialog("Delete", "OK?");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testDropOverwrite() {
        Dialog dialog = new MessageDialog(getShell(), ResourceNavigatorMessages.DropAdapter_question, null, MessageFormat.format(ResourceNavigatorMessages.DropAdapter_overwriteQuery, new Object[] { DUMMY_RELATIVE_PATH }), MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.YES_TO_ALL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testErrorClosing() {
        Dialog dialog = getQuestionDialog(WorkbenchMessages.Error, WorkbenchMessages.ErrorClosingNoArg);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testFileExtensionEmpty() {
        Dialog dialog = getInformationDialog("", "");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testFileNameWrong() {
        Dialog dialog = getInformationDialog("Invalid", "Invalid file");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testFileTypeExists() {
        Dialog dialog = getInformationDialog("Exists", "Already Exists");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testInvalidType_1() {
        Dialog dialog = getWarningDialog("Invalid?", "Is this invalid?");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testInvalidType_2() {
        Dialog dialog = getWarningDialog("Invalid", "Is this invalid?");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testMoveReadOnlyCheck() {
        Dialog dialog = new MessageDialog(getShell(), "Move", null, "OK to move", MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.YES_TO_ALL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testNoPropertyPage() {
        Dialog dialog = getInformationDialog(WorkbenchMessages.PropertyDialog_messageTitle, NLS.bind(WorkbenchMessages.PropertyDialog_noPropertyMessage, (new Object[] { "DummyPropertyPage" })));
        DialogCheck.assertDialog(dialog, this);
    }

    public void testOperationNotAvailable() {
        Dialog dialog = getInformationDialog(WorkbenchMessages.Information, "Not available");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testOverwritePerspective() {
        Dialog dialog = new MessageDialog(getShell(), WorkbenchMessages.SavePerspective_overwriteTitle, null, NLS.bind(WorkbenchMessages.SavePerspective_overwriteQuestion, (new Object[] { "Dummy Perspective" })), MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testRefreshDeleteProject() {
        Dialog dialog = new MessageDialog(getShell(), "Refresh", null, NLS.bind("deleted location", (new Object[] { DUMMY_PROJECT, "c:\\dummypath\\" + DUMMY_PROJECT })), MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL);
        DialogCheck.assertDialogTexts(dialog, this);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testRenameOverwrite() {
        Dialog dialog = getQuestionDialog("Exists", "Overwrite");
        DialogCheck.assertDialog(dialog, this);
    }

    public void testResetPerspective() {
        Dialog dialog = new MessageDialog(getShell(), WorkbenchMessages.ResetPerspective_title, null, NLS.bind(WorkbenchMessages.ResetPerspective_message, (new Object[] { "Dummy Perspective" })), MessageDialog.QUESTION, 0, IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testSaveAsOverwrite() {
        Dialog dialog = new MessageDialog(getShell(), "OK?", null, "Overwrite?", MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testSaveChanges() {
        Dialog dialog = new MessageDialog(getShell(), WorkbenchMessages.Save_Resource, null, NLS.bind(WorkbenchMessages.EditorManager_saveChangesQuestion, (new Object[] { DUMMY_RESOURCE })), MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testWizardClosing() {
        Dialog dialog = new MessageDialog(getShell(), JFaceResources.getString("WizardClosingDialog.title"), null, JFaceResources.getString("WizardClosingDialog.message"), MessageDialog.QUESTION, 0, IDialogConstants.OK_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }

    public void testWizardOverwrite() {
        Dialog dialog = new MessageDialog(getShell(), "OK?", null, "Exists", MessageDialog.QUESTION, 0, IDialogConstants.YES_LABEL, IDialogConstants.YES_TO_ALL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL);
        DialogCheck.assertDialog(dialog, this);
    }
}
