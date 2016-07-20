/***/
package org.eclipse.jface.tests.internal.databinding.swt;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.conformance.ObservableDelegateTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.swt.SWTMutableObservableValueContractTest;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

/**
* @since 3.2
*/
public class ScaleObservableValueMinTest extends ObservableDelegateTest {

    private Delegate delegate;

    private Scale scale;

    private IObservableValue observable;

    public  ScaleObservableValueMinTest() {
        this(null);
    }

    public  ScaleObservableValueMinTest(String testName) {
        super(testName, new Delegate());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        delegate = (Delegate) getObservableContractDelegate();
        observable = (IObservableValue) getObservable();
        scale = delegate.scale;
    }

    @Override
    protected IObservable doCreateObservable() {
        return getObservableContractDelegate().createObservable(DisplayRealm.getRealm(Display.getDefault()));
    }

    public void testGetValue() throws Exception {
        int min = 100;
        scale.setMinimum(min);
        assertEquals(Integer.valueOf(min), observable.getValue());
    }

    public void testSetValue() throws Exception {
        int min = 100;
        observable.setValue(Integer.valueOf(min));
        assertEquals(min, scale.getMinimum());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ScaleObservableValueMinTest.class.toString());
        suite.addTestSuite(ScaleObservableValueMinTest.class);
        suite.addTest(SWTMutableObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    /* package */
    static class Delegate extends AbstractObservableValueContractDelegate {

        private Shell shell;

        Scale scale;

        @Override
        public void setUp() {
            shell = new Shell();
            scale = new Scale(shell, SWT.NONE);
            scale.setMaximum(1000);
        }

        @Override
        public void tearDown() {
            shell.dispose();
        }

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            return WidgetProperties.minimum().observe(realm, scale);
        }

        @Override
        public void change(IObservable observable) {
            IObservableValue observableValue = (IObservableValue) observable;
            observableValue.setValue(createValue(observableValue));
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return Integer.TYPE;
        }

        @Override
        public Object createValue(IObservableValue observable) {
            return createIntegerValue(observable);
        }

        private Integer createIntegerValue(IObservableValue observable) {
            return Integer.valueOf(((Integer) observable.getValue()).intValue() + 1);
        }
    }
}
