/***/
package org.eclipse.jface.preference;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
* A field editor for an integer type preference.
*/
public class IntegerFieldEditor extends StringFieldEditor {

    private int minValidValue = 0;

    private int maxValidValue = Integer.MAX_VALUE;

    private static final int DEFAULT_TEXT_LIMIT = 10;

    /**
* Creates a new integer field editor
*/
    protected  IntegerFieldEditor() {
    }

    /**
* Creates an integer field editor.
*
* @param name the name of the preference this field editor works on
* @param labelText the label text of the field editor
* @param parent the parent of the field editor's control
*/
    public  IntegerFieldEditor(String name, String labelText, Composite parent) {
        this(name, labelText, parent, DEFAULT_TEXT_LIMIT);
    }

    /**
* Creates an integer field editor.
*
* @param name the name of the preference this field editor works on
* @param labelText the label text of the field editor
* @param parent the parent of the field editor's control
* @param textLimit the maximum number of characters in the text.
*/
    public  IntegerFieldEditor(String name, String labelText, Composite parent, int textLimit) {
        init(name, labelText);
        setTextLimit(textLimit);
        setEmptyStringAllowed(false);
        setErrorMessage(JFaceResources.getString(//$NON-NLS-1$
        "IntegerFieldEditor.errorMessage"));
        createControl(parent);
    }

    /**
* Sets the range of valid values for this field.
*
* @param min the minimum allowed value (inclusive)
* @param max the maximum allowed value (inclusive)
*/
    public void setValidRange(int min, int max) {
        minValidValue = min;
        maxValidValue = max;
        setErrorMessage(//$NON-NLS-1$
        JFaceResources.format(//$NON-NLS-1$
        "IntegerFieldEditor.errorMessageRange", Integer.valueOf(min), Integer.valueOf(max)));
    }

    @Override
    protected boolean checkState() {
        Text text = getTextControl();
        if (text == null) {
            return false;
        }
        String numberString = text.getText();
        try {
            int number = Integer.valueOf(numberString).intValue();
            if (number >= minValidValue && number <= maxValidValue) {
                clearErrorMessage();
                return true;
            }
            showErrorMessage();
            return false;
        } catch (NumberFormatException e1) {
            showErrorMessage();
        }
        return false;
    }

    @Override
    protected void doLoad() {
        Text text = getTextControl();
        if (text != null) {
            int value = getPreferenceStore().getInt(getPreferenceName());
            //$NON-NLS-1$
            text.setText("" + value);
            //$NON-NLS-1$
            oldValue = "" + value;
        }
    }

    @Override
    protected void doLoadDefault() {
        Text text = getTextControl();
        if (text != null) {
            int value = getPreferenceStore().getDefaultInt(getPreferenceName());
            //$NON-NLS-1$
            text.setText("" + value);
        }
        valueChanged();
    }

    @Override
    protected void doStore() {
        Text text = getTextControl();
        if (text != null) {
            Integer i = Integer.valueOf(text.getText());
            getPreferenceStore().setValue(getPreferenceName(), i.intValue());
        }
    }

    /**
* Returns this field editor's current value as an integer.
*
* @return the value
* @exception NumberFormatException if the <code>String</code> does not
*   contain a parsable integer
*/
    public int getIntValue() throws NumberFormatException {
        return Integer.valueOf(getStringValue()).intValue();
    }
}
