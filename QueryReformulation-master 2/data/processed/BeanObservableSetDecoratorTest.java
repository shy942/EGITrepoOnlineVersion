/***/
package org.eclipse.core.tests.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import junit.framework.TestCase;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.internal.databinding.beans.BeanObservableSetDecorator;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.swt.widgets.Display;

/**
* @since 3.3
*/
public class BeanObservableSetDecoratorTest extends TestCase {

    private PropertyDescriptor propertyDescriptor;

    private IObservableSet observableSet;

    private BeanObservableSetDecorator decorator;

    private Bean bean;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bean = new Bean();
        propertyDescriptor = new PropertyDescriptor("set", Bean.class);
        observableSet = BeansObservables.observeSet(DisplayRealm.getRealm(Display.getDefault()), bean, "set");
        decorator = new BeanObservableSetDecorator(observableSet, propertyDescriptor);
    }

    public void testGetDecorated() throws Exception {
        assertSame(observableSet, decorator.getDecorated());
    }

    public void testGetObserved() throws Exception {
        assertSame(bean, decorator.getObserved());
    }

    public void testGetPropertyDescriptor() throws Exception {
        assertSame(propertyDescriptor, decorator.getPropertyDescriptor());
    }
}
