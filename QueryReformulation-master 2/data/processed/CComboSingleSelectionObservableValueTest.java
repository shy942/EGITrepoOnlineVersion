/***/
package org.eclipse.jface.tests.internal.databinding.swt;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.swt.SWTMutableObservableValueContractTest;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.tests.databinding.AbstractSWTTestCase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Shell;

/**
* @since 3.2
*/
public class CComboSingleSelectionObservableValueTest extends AbstractSWTTestCase {

    public void testSetValue() throws Exception {
        CCombo combo = new CCombo(getShell(), SWT.NONE);
        IObservableValue observableValue = SWTObservables.observeSingleSelectionIndex(combo);
        combo.add("Item1");
        combo.add("Item2");
        assertEquals(-1, combo.getSelectionIndex());
        assertEquals(-1, ((Integer) observableValue.getValue()).intValue());
        Integer value = Integer.valueOf(1);
        observableValue.setValue(value);
        assertEquals("combo selection index", value.intValue(), combo.getSelectionIndex());
        assertEquals("observable value", value, observableValue.getValue());
        assertEquals("Item2", combo.getText());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CComboSingleSelectionObservableValueTest.class.getName());
        suite.addTestSuite(CComboSingleSelectionObservableValueTest.class);
        suite.addTest(SWTMutableObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    /* package */
    static class Delegate extends AbstractObservableValueContractDelegate {

        private CCombo combo;

        private Shell shell;

        @Override
        public void setUp() {
            shell = new Shell();
            combo = new CCombo(shell, SWT.NONE);
            combo.add("0");
            combo.add("1");
        }

        @Override
        public void tearDown() {
            shell.dispose();
        }

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            return WidgetProperties.singleSelectionIndex().observe(realm, combo);
        }

        @Override
        public void change(IObservable observable) {
            IObservableValue value = (IObservableValue) observable;
            value.setValue(createValue(value));
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return Integer.TYPE;
        }

        @Override
        public Object createValue(IObservableValue observable) {
            return Integer.valueOf(_createValue(observable));
        }

        private int _createValue(IObservableValue observable) {
            int value = Math.max(0, combo.getSelectionIndex());
            // returns either 0 or 1 depending upon current value
            return Math.abs(value - 1);
        }
    }
}
