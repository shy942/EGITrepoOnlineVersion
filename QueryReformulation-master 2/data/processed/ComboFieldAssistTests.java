/***/
package org.eclipse.jface.tests.fieldassist;

/**
* @since 3.6
*
*/
public class ComboFieldAssistTests extends FieldAssistTestCase {

    @Override
    protected AbstractFieldAssistWindow createFieldAssistWindow() {
        return new ComboFieldAssistWindow();
    }
}
