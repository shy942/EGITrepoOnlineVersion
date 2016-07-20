/***/
package org.eclipse.core.tests.databinding.observable.value;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.conformance.MutableObservableValueContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;

/**
* @since 3.2
*
*/
public class DecoratingObservableValueTest {

    public static Test suite() {
        TestSuite suite = new TestSuite(DecoratingObservableValueTest.class.getName());
        suite.addTest(MutableObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    static class Delegate extends AbstractObservableValueContractDelegate {

        private Object valueType = Object.class;

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            IObservableValue decorated = new WritableValue(realm, new Object(), valueType);
            return new DecoratingObservableValueStub(decorated);
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return valueType;
        }

        @Override
        public void change(IObservable observable) {
            ((DecoratingObservableValueStub) observable).decorated.setValue(new Object());
        }
    }

    static class DecoratingObservableValueStub extends DecoratingObservableValue {

        IObservableValue decorated;

         DecoratingObservableValueStub(IObservableValue decorated) {
            super(decorated, true);
            this.decorated = decorated;
        }
    }
}
