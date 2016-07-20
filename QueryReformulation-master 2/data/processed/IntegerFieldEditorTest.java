/***/
package org.eclipse.jface.tests.preferences;

import junit.framework.TestCase;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class IntegerFieldEditorTest extends TestCase {

    private Shell shell;

    private IntegerFieldEditor integerFieldEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        shell = new Shell();
        integerFieldEditor = new IntegerFieldEditor("name", "label", shell);
        integerFieldEditor.setValidRange(0, 500);
        integerFieldEditor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
    }

    public void testLoad() {
        PreferenceStore myPreferenceStore = new PreferenceStore();
        integerFieldEditor.setPreferenceName("name");
        integerFieldEditor.setPreferenceStore(myPreferenceStore);
        myPreferenceStore.setDefault("name", 5);
        integerFieldEditor.load();
        assertEquals(integerFieldEditor.getIntValue(), 5);
        myPreferenceStore.setDefault("name", 5);
        myPreferenceStore.setValue("name", 6);
        integerFieldEditor.load();
        assertEquals(integerFieldEditor.getIntValue(), 6);
    }

    public void testLoadDefault() {
        PreferenceStore myPreferenceStore = new PreferenceStore();
        integerFieldEditor.setPreferenceName("name");
        integerFieldEditor.setPreferenceStore(myPreferenceStore);
        myPreferenceStore.setDefault("name", 5);
        myPreferenceStore.setValue("name", 6);
        integerFieldEditor.loadDefault();
        assertEquals(integerFieldEditor.getIntValue(), 5);
    }

    public void testSetValueInWidget() {
        PreferenceStore myPreferenceStore = new PreferenceStore();
        integerFieldEditor.setPreferenceName("name");
        integerFieldEditor.setPreferenceStore(myPreferenceStore);
        myPreferenceStore.setValue("name", 5);
        integerFieldEditor.load();
        assertEquals(integerFieldEditor.getIntValue(), 5);
        Text text = integerFieldEditor.getTextControl(shell);
        text.setText("6");
        assertEquals(integerFieldEditor.getIntValue(), 6);
    }

    public void testSetValueInEditor() {
        PreferenceStore myPreferenceStore = new PreferenceStore();
        integerFieldEditor.setPreferenceName("name");
        integerFieldEditor.setPreferenceStore(myPreferenceStore);
        myPreferenceStore.setValue("name", 5);
        integerFieldEditor.load();
        assertEquals(integerFieldEditor.getIntValue(), 5);
        integerFieldEditor.setStringValue("6");
        Text text = integerFieldEditor.getTextControl(shell);
        assertEquals(text.getText(), "6");
        assertEquals(integerFieldEditor.getIntValue(), 6);
    }

    public void testValidate() {
        PreferenceStore myPreferenceStore = new PreferenceStore();
        integerFieldEditor.setPreferenceName("name");
        integerFieldEditor.setPreferenceStore(myPreferenceStore);
        myPreferenceStore.setValue("name", 5000);
        integerFieldEditor.load();
        assertFalse(integerFieldEditor.isValid());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
