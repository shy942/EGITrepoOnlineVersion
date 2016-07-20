/***/
package org.eclipse.core.tests.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.internal.databinding.beans.BeanObservableListDecorator;
import org.eclipse.jface.databinding.conformance.MutableObservableListContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.swt.widgets.Display;

/**
* @since 3.3
*/
public class BeanObservableListDecoratorTest extends TestCase {

    private Bean bean;

    private PropertyDescriptor propertyDescriptor;

    private IObservableList observableList;

    private BeanObservableListDecorator decorator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bean = new Bean();
        propertyDescriptor = new PropertyDescriptor("list", Bean.class, "getList", "setList");
        observableList = BeansObservables.observeList(DisplayRealm.getRealm(Display.getDefault()), bean, "list");
        decorator = new BeanObservableListDecorator(observableList, propertyDescriptor);
    }

    public void testGetDelegate() throws Exception {
        assertSame(observableList, decorator.getDecorated());
    }

    public void testGetObserved() throws Exception {
        assertSame(bean, decorator.getObserved());
    }

    public void testGetPropertyDescriptor() throws Exception {
        assertSame(propertyDescriptor, decorator.getPropertyDescriptor());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BeanObservableListDecoratorTest.class.getName());
        suite.addTestSuite(BeanObservableListDecoratorTest.class);
        suite.addTest(MutableObservableListContractTest.suite(new Delegate()));
        return suite;
    }

    static class Delegate extends AbstractObservableCollectionContractDelegate {

        @Override
        public IObservableCollection createObservableCollection(Realm realm, int elementCount) {
            final WritableList delegate = new WritableList(realm);
            for (int i = 0; i < elementCount; i++) delegate.add(createElement(delegate));
            return new BeanObservableListDecorator(delegate, null);
        }

        private int counter;

        @Override
        public Object createElement(IObservableCollection collection) {
            return Integer.toString(counter++);
        }

        @Override
        public void change(IObservable observable) {
            IObservableList list = (IObservableList) observable;
            list.add(createElement(list));
        }
    }
}
