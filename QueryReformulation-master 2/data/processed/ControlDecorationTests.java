/***/
package org.eclipse.jface.tests.fieldassist;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ControlDecorationTests extends AbstractFieldAssistTestCase {

    private Text anotherControl;

    public void testDecorationIsVisible() {
        AbstractFieldAssistWindow window = getFieldAssistWindow();
        window.open();
        ControlDecoration decoration = new ControlDecoration(getFieldAssistWindow().getFieldAssistControl(), SWT.RIGHT);
        decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        decoration.setDescriptionText("foo");
        window.open();
        assertTrue("1.0", decoration.isVisible());
        decoration.hide();
        assertFalse("1.1", decoration.isVisible());
        decoration.show();
        assertTrue("1.2", decoration.isVisible());
        window.getFieldAssistControl().setVisible(false);
        assertFalse("1.3", decoration.isVisible());
        window.getFieldAssistControl().setVisible(true);
        assertTrue("1.4", decoration.isVisible());
        // focus related tests.  Comment out for now.
        // see bug 275393
        decoration.setShowOnlyOnFocus(true);
        anotherControl.setFocus();
        spinEventLoop();
    /*
assertFalse("1.5", decoration.isVisible());
window.getFieldAssistControl().setFocus();
spinEventLoop();
assertTrue("1.6", decoration.isVisible());
decoration.setShowOnlyOnFocus(false);
*/
    }

    public void testHoverVisibility() {
        AbstractFieldAssistWindow window = getFieldAssistWindow();
        window.open();
        ControlDecoration decoration = new ControlDecoration(window.getFieldAssistControl(), SWT.RIGHT);
        decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        decoration.setDescriptionText("foo");
        assertTrue("1.0", decoration.isVisible());
        assertOneShellUp();
        decoration.hide();
        decoration.showHoverText("Show me");
        // because the decoration is hidden;
        assertOneShellUp();
        decoration.show();
        getFieldAssistWindow().getFieldAssistControl().setVisible(false);
        decoration.showHoverText("Show me");
        // because the control is hidden, bug 295386
        assertOneShellUp();
        getFieldAssistWindow().getFieldAssistControl().setVisible(true);
        decoration.showHoverText("Show me");
        assertTwoShellsUp();
    }

    public void XXXtestBug418420() {
        AbstractFieldAssistWindow window = getFieldAssistWindow();
        window.open();
        ControlDecoration decoration = new ControlDecoration(window.getFieldAssistControl(), SWT.RIGHT);
        decoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        decoration.setDescriptionText("foo");
        decoration.setShowOnlyOnFocus(true);
        // focus related tests.  Comment out for now.
        // see 418420 and bug 275393
        anotherControl.forceFocus();
        decoration.showHoverText("Show me");
        assertOneShellUp();
    }

    @Override
    protected AbstractFieldAssistWindow createFieldAssistWindow() {
        return new TextFieldAssistWindow() {

            @Override
            protected void createExtraControls(Composite parent) {
                anotherControl = new Text(parent, SWT.DEFAULT);
            }
        };
    }
}
