/***/
package org.eclipse.jface.tests.internal.databinding.swt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.swt.SWTMutableObservableValueContractTest;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
* @since 3.2
*/
public class TextObservableValueModifyTest extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(TextObservableValueModifyTest.class.toString());
        suite.addTest(SWTMutableObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    /* package */
    static class Delegate extends AbstractObservableValueContractDelegate {

        private Shell shell;

        private Text text;

        @Override
        public void setUp() {
            shell = new Shell();
            text = new Text(shell, SWT.NONE);
        }

        @Override
        public void tearDown() {
            shell.dispose();
        }

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            return WidgetProperties.text(SWT.Modify).observe(realm, text);
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return String.class;
        }

        @Override
        public void change(IObservable observable) {
            text.setFocus();
            IObservableValue observableValue = (IObservableValue) observable;
            text.setText((String) createValue(observableValue));
        }

        @Override
        public Object createValue(IObservableValue observable) {
            String value = (String) observable.getValue();
            return value + "a";
        }
    }
}
