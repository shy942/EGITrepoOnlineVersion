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
import org.eclipse.jface.databinding.conformance.util.ValueChangeEventTracker;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
* @since 3.2
*/
public class CComboObservableValueTextTest extends TestCase {

    private Delegate delegate;

    private CCombo combo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        delegate = new Delegate();
        delegate.setUp();
        combo = delegate.combo;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        delegate.tearDown();
    }

    public void testModify_NotifiesObservable() throws Exception {
        IObservableValue observable = delegate.createObservableValue(DisplayRealm.getRealm(Display.getDefault()));
        ValueChangeEventTracker listener = ValueChangeEventTracker.observe(observable);
        combo.setText((String) delegate.createValue(observable));
        assertEquals("Observable was not notified.", 1, listener.count);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CComboObservableValueTextTest.class.getName());
        suite.addTestSuite(CComboObservableValueTextTest.class);
        suite.addTest(SWTMutableObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    /* package */
    static class Delegate extends AbstractObservableValueContractDelegate {

        /* package */
        CCombo combo;

        private Shell shell;

        @Override
        public void setUp() {
            shell = new Shell();
            combo = new CCombo(shell, SWT.NONE);
        }

        @Override
        public void tearDown() {
            shell.dispose();
        }

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            return WidgetProperties.text().observe(realm, combo);
        }

        @Override
        public void change(IObservable observable) {
            IObservableValue ov = (IObservableValue) observable;
            ov.setValue(createValue(ov));
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return String.class;
        }

        @Override
        public Object createValue(IObservableValue observable) {
            return observable.getValue() + "a";
        }
    }
}
