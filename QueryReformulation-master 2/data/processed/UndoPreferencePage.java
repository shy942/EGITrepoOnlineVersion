/***/
package org.eclipse.ui.examples.undo.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.examples.undo.UndoExampleMessages;
import org.eclipse.ui.examples.undo.UndoPlugin;

/**
* This class is used to define preferences that control how
* undo occurs.  It demonstrates the use of preferences to configure
* the operation history in different ways.
*/
public class UndoPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public  UndoPreferencePage() {
        super(GRID);
        setPreferenceStore(UndoPlugin.getDefault().getPreferenceStore());
        setDescription(UndoExampleMessages.UndoPreferences_Description);
    }

    /**
* Creates the field editors.
*/
    @Override
    public void createFieldEditors() {
        addField(new IntegerFieldEditor(PreferenceConstants.PREF_UNDOLIMIT, UndoExampleMessages.UndoPreferences_HistoryLimit, getFieldEditorParent()));
        addField(new BooleanFieldEditor(PreferenceConstants.PREF_SHOWDEBUG, UndoExampleMessages.UndoPreferences_ShowDebug, getFieldEditorParent()));
        addField(new BooleanFieldEditor(PreferenceConstants.PREF_CONFIRMUNDO, UndoExampleMessages.UndoPreferences_ConfirmUndo, getFieldEditorParent()));
    }

    @Override
    public void init(IWorkbench workbench) {
    }
}
