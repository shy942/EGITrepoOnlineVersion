/***/
package org.eclipse.ui.tests.dialogs;

import junit.framework.TestCase;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.YesNoCancelListSelectionDialog;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;
import org.eclipse.ui.tests.harness.util.DialogCheck;

public class DeprecatedUIDialogsAuto extends TestCase {

    public  DeprecatedUIDialogsAuto(String name) {
        super(name);
    }

    private Shell getShell() {
        return DialogCheck.getShell();
    }

    public void testSaveAll() {
        YesNoCancelListSelectionDialog dialog = new YesNoCancelListSelectionDialog(getShell(), new AdaptableList(), new WorkbenchContentProvider(), new WorkbenchPartLabelProvider(), WorkbenchMessages.EditorManager_saveResourcesMessage);
        dialog.setTitle(WorkbenchMessages.EditorManager_saveResourcesTitle);
        DialogCheck.assertDialogTexts(dialog, this);
    }
}
