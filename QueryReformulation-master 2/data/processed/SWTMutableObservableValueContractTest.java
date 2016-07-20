/***/
package org.eclipse.jface.databinding.conformance.swt;

import junit.framework.Test;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.jface.databinding.conformance.MutableObservableValueContractTest;
import org.eclipse.jface.databinding.conformance.delegate.IObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.util.DelegatingRealm;
import org.eclipse.jface.databinding.conformance.util.SuiteBuilder;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.swt.widgets.Display;

/**
* Mutability tests for IObservableValue for a SWT widget.
*
* <p>
* This class is experimental and can change at any time. It is recommended to
* not subclass or assume the test names will not change. The only API that is
* guaranteed to not change are the constructors. The tests will remain public
* and not final in order to allow for consumers to turn off a test if needed by
* subclassing.
* </p>
*
* @since 3.2
*/
public class SWTMutableObservableValueContractTest extends MutableObservableValueContractTest {

    private IObservableValueContractDelegate delegate;

    public  SWTMutableObservableValueContractTest(IObservableValueContractDelegate delegate) {
        this(null, delegate);
    }

    /**
* @param testName
* @param delegate
*/
    public  SWTMutableObservableValueContractTest(String testName, IObservableValueContractDelegate delegate) {
        super(testName, delegate);
        this.delegate = delegate;
    }

    /**
* Creates a new observable passing the realm for the current display.
*
* @return observable
*/
    @Override
    protected IObservable doCreateObservable() {
        Display display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
        DelegatingRealm delegateRealm = new DelegatingRealm(DisplayRealm.getRealm(display));
        delegateRealm.setCurrent(true);
        return delegate.createObservable(delegateRealm);
    }

    public static Test suite(IObservableValueContractDelegate delegate) {
        return new SuiteBuilder().addObservableContractTest(SWTMutableObservableValueContractTest.class, delegate).addObservableContractTest(SWTObservableValueContractTest.class, delegate).build();
    }
}