/***/
package org.eclipse.core.tests.internal.databinding.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.beans.IBeanProperty;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.jface.databinding.conformance.MutableObservableListContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.CurrentRealm;
import org.eclipse.jface.databinding.conformance.util.ListChangeEventTracker;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;
import org.eclipse.swt.widgets.Display;

/**
* @since 1.1
*/
public class JavaBeanObservableArrayBasedListTest extends AbstractDefaultRealmTestCase {

    private IObservableList list;

    private IBeanObservable beanObservable;

    private PropertyDescriptor propertyDescriptor;

    private Bean bean;

    private String propertyName;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        propertyName = "array";
        propertyDescriptor = ((IBeanProperty) BeanProperties.list(Bean.class, propertyName)).getPropertyDescriptor();
        bean = new Bean(new Object[0]);
        list = BeansObservables.observeList(DisplayRealm.getRealm(Display.getDefault()), bean, propertyName);
        beanObservable = (IBeanObservable) list;
    }

    public void testGetObserved() throws Exception {
        assertSame(bean, beanObservable.getObserved());
    }

    public void testGetPropertyDescriptor() throws Exception {
        assertEquals(propertyDescriptor, beanObservable.getPropertyDescriptor());
    }

    public void testRegistersListenerAfterFirstListenerIsAdded() throws Exception {
        assertFalse(bean.changeSupport.hasListeners(propertyName));
        list.addListChangeListener(new ListChangeEventTracker());
        assertTrue(bean.changeSupport.hasListeners(propertyName));
    }

    public void testRemovesListenerAfterLastListenerIsRemoved() throws Exception {
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertTrue(bean.changeSupport.hasListeners(propertyName));
        list.removeListChangeListener(listener);
        assertFalse(bean.changeSupport.hasListeners(propertyName));
    }

    public void testFiresListChangeEvents() throws Exception {
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        bean.setArray(new Bean[] { new Bean() });
        assertEquals(1, listener.count);
    }

    public void testAddAddsElement() throws Exception {
        int count = list.size();
        String element = "1";
        assertEquals(0, count);
        list.add(element);
        assertEquals(count + 1, list.size());
        assertEquals(element, bean.getArray()[count]);
    }

    public void testAddListChangeEvent() throws Exception {
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        String element = "1";
        list.add(element);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Collections.EMPTY_LIST, Collections.singletonList("1"));
    }

    public void testAdd_FiresPropertyChangeEvent() throws Exception {
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.add("0");
            }
        });
    }

    public void testAddWithIndex() throws Exception {
        String element = "1";
        assertEquals(0, list.size());
        list.add(0, element);
        assertEquals(element, bean.getArray()[0]);
    }

    public void testAddAtIndexListChangeEvent() throws Exception {
        String element = "1";
        assertEquals(0, list.size());
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        list.add(0, element);
        ListChangeEvent event = listener.event;
        assertDiff(event.diff, Collections.EMPTY_LIST, Collections.singletonList("1"));
    }

    public void testAddAtIndexPropertyChangeEvent() throws Exception {
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.add(0, "0");
            }
        });
    }

    public void testRemove() throws Exception {
        String element = "1";
        list.add(element);
        assertEquals(1, bean.getArray().length);
        list.remove(element);
        assertEquals(0, bean.getArray().length);
    }

    public void testRemoveListChangeEvent() throws Exception {
        String element = "1";
        list.add(element);
        assertEquals(1, list.size());
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        list.remove(element);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Collections.singletonList("1"), Collections.EMPTY_LIST);
    }

    public void testRemovePropertyChangeEvent() throws Exception {
        list.add("0");
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.remove("0");
            }
        });
    }

    public void testRemoveAtIndex() throws Exception {
        String element = "1";
        list.add(element);
        assertEquals(element, bean.getArray()[0]);
        list.remove(0);
        assertEquals(0, bean.getArray().length);
    }

    public void testRemoveAtIndexListChangeEvent() throws Exception {
        String element = "1";
        list.add(element);
        assertEquals(1, list.size());
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        list.remove(0);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Collections.singletonList(element), Collections.EMPTY_LIST);
    }

    public void testRemoveAtIndexPropertyChangeEvent() throws Exception {
        list.add("0");
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.remove(0);
            }
        });
    }

    public void testAddAll() throws Exception {
        Collection elements = Arrays.asList(new String[] { "1", "2" });
        assertEquals(0, list.size());
        list.addAll(elements);
        assertEquals(2, bean.getArray().length);
    }

    public void testAddAllListChangEvent() throws Exception {
        List elements = Arrays.asList(new String[] { "1", "2" });
        assertEquals(0, list.size());
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        list.addAll(elements);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Collections.EMPTY_LIST, Arrays.asList(new String[] { "1", "2" }));
    }

    public void testAddAllPropertyChangeEvent() throws Exception {
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.addAll(Arrays.asList(new String[] { "0", "1" }));
            }
        });
    }

    public void testAddAllAtIndex() throws Exception {
        List elements = Arrays.asList(new String[] { "1", "2" });
        list.addAll(elements);
        assertEquals(2, list.size());
        list.addAll(2, elements);
        assertEquals(4, bean.getArray().length);
        assertEquals(elements.get(0), bean.getArray()[0]);
        assertEquals(elements.get(1), bean.getArray()[1]);
    }

    public void testAddAllAtIndexListChangeEvent() throws Exception {
        List elements = Arrays.asList(new String[] { "1", "2" });
        list.addAll(elements);
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        list.addAll(2, elements);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Arrays.asList(new Object[] { "1", "2" }), Arrays.asList(new Object[] { "1", "2", "1", "2" }));
    }

    public void testAddAllAtIndexPropertyChangeEvent() throws Exception {
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.addAll(0, Arrays.asList(new String[] { "1", "2" }));
            }
        });
    }

    public void testRemoveAll() throws Exception {
        list.addAll(Arrays.asList(new String[] { "1", "2", "3", "4" }));
        assertEquals(4, bean.getArray().length);
        list.removeAll(Arrays.asList(new String[] { "2", "4" }));
        assertEquals(2, bean.getArray().length);
        assertEquals("1", bean.getArray()[0]);
        assertEquals("3", bean.getArray()[1]);
    }

    public void testRemoveAllListChangeEvent() throws Exception {
        List elements = Arrays.asList(new String[] { "1", "2" });
        list.addAll(elements);
        list.addAll(elements);
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        list.removeAll(elements);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Arrays.asList(new Object[] { "1", "2", "1", "2" }), Collections.EMPTY_LIST);
    }

    public void testRemoveAllPropertyChangeEvent() throws Exception {
        list.add("0");
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.removeAll(Arrays.asList(new String[] { "0" }));
            }
        });
    }

    public void testRetainAll() throws Exception {
        List elements = Arrays.asList(new String[] { "0", "1", "2", "3" });
        list.addAll(elements);
        assertEquals(4, bean.getArray().length);
        list.retainAll(elements.subList(0, 2));
        assertEquals(2, bean.getArray().length);
        assertEquals(elements.get(0), bean.getArray()[0]);
        assertEquals(elements.get(1), bean.getArray()[1]);
    }

    public void testRetainAllListChangeEvent() throws Exception {
        List elements = Arrays.asList(new String[] { "0", "1", "2", "3" });
        list.addAll(elements);
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        list.retainAll(elements.subList(0, 2));
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Arrays.asList(new Object[] { "0", "1", "2", "3" }), Arrays.asList(new Object[] { "0", "1" }));
    }

    public void testRetainAllPropertyChangeEvent() throws Exception {
        list.addAll(Arrays.asList(new String[] { "0", "1" }));
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.retainAll(Arrays.asList(new String[] { "0" }));
            }
        });
    }

    public void testSet() throws Exception {
        String oldElement = "old";
        String newElement = "new";
        list.add(oldElement);
        assertEquals(oldElement, bean.getArray()[0]);
        list.set(0, newElement);
        assertEquals(newElement, bean.getArray()[0]);
    }

    public void testMove() throws Exception {
        String element0 = "element0";
        String element1 = "element1";
        list.add(element0);
        list.add(element1);
        assertEquals(2, bean.getArray().length);
        assertEquals(element0, bean.getArray()[0]);
        assertEquals(element1, bean.getArray()[1]);
        list.move(0, 1);
        assertEquals(2, bean.getArray().length);
        assertEquals(element1, bean.getArray()[0]);
        assertEquals(element0, bean.getArray()[1]);
    }

    public void testSetListChangeEvent() throws Exception {
        String oldElement = "old";
        String newElement = "new";
        list.add(oldElement);
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        list.set(0, newElement);
        assertEquals(1, listener.count);
        ListChangeEvent event = listener.event;
        assertSame(list, event.getObservableList());
        assertDiff(event.diff, Collections.singletonList(oldElement), Collections.singletonList(newElement));
    }

    public void testSetPropertyChangeEvent() throws Exception {
        list.add("0");
        assertPropertyChangeEvent(bean, new Runnable() {

            @Override
            public void run() {
                list.set(0, "1");
            }
        });
    }

    public void testListChangeEventFiresWhenNewListIsSet() throws Exception {
        Bean[] elements = new Bean[] { new Bean(), new Bean() };
        ListChangeEventTracker listener = new ListChangeEventTracker();
        list.addListChangeListener(listener);
        assertEquals(0, listener.count);
        bean.setArray(elements);
        assertEquals(1, listener.count);
    }

    public void testSetBeanProperty_CorrectForNullOldAndNewValues() {
        // The java bean spec allows the old and new values in a
        // PropertyChangeEvent to be null, which indicates that an unknown
        // change occured.
        // This test ensures that JavaBeanObservableValue fires the correct
        // value diff even if the bean implementor is lazy :-P
        Bean bean = new AnnoyingBean();
        bean.setArray(new Object[] { "old" });
        IObservableList observable = BeansObservables.observeList(new CurrentRealm(true), bean, "array");
        ListChangeEventTracker tracker = ListChangeEventTracker.observe(observable);
        bean.setArray(new Object[] { "new" });
        assertEquals(1, tracker.count);
        List list = new ArrayList();
        list.add("old");
        tracker.event.diff.applyTo(list);
        assertEquals(Collections.singletonList("new"), list);
    }

    public void testModifyObservableList_FiresListChange() {
        Bean bean = new Bean(new Object[] { "old" });
        IObservableList observable = BeansObservables.observeList(bean, "array");
        ListChangeEventTracker tracker = ListChangeEventTracker.observe(observable);
        observable.set(0, "new");
        assertEquals(1, tracker.count);
        assertDiff(tracker.event.diff, Collections.singletonList("old"), Collections.singletonList("new"));
    }

    public void testSetBeanPropertyOutsideRealm_FiresEventInsideRealm() {
        Bean bean = new Bean(new Object[0]);
        CurrentRealm realm = new CurrentRealm(true);
        IObservableList observable = BeansObservables.observeList(realm, bean, "array");
        ListChangeEventTracker tracker = ListChangeEventTracker.observe(observable);
        realm.setCurrent(false);
        bean.setArray(new Object[] { "element" });
        assertEquals(0, tracker.count);
        realm.setCurrent(true);
        assertEquals(1, tracker.count);
        assertDiff(tracker.event.diff, Collections.EMPTY_LIST, Collections.singletonList("element"));
    }

    private static void assertDiff(ListDiff diff, List oldList, List newList) {
        // defensive copy in case arg is
        oldList = new ArrayList(oldList);
        // unmodifiable
        diff.applyTo(oldList);
        assertEquals("applying diff to list did not produce expected result", newList, oldList);
    }

    private static void assertPropertyChangeEvent(Bean bean, Runnable runnable) {
        PropertyChangeTracker listener = new PropertyChangeTracker();
        bean.addPropertyChangeListener(listener);
        Object[] old = bean.getArray();
        assertEquals(0, listener.count);
        runnable.run();
        PropertyChangeEvent event = listener.evt;
        assertEquals("event did not fire", 1, listener.count);
        assertEquals("array", event.getPropertyName());
        assertTrue("old value", Arrays.equals(old, (Object[]) event.getOldValue()));
        assertTrue("new value", Arrays.equals(bean.getArray(), (Object[]) event.getNewValue()));
        assertFalse("lists are equal", Arrays.equals(bean.getArray(), old));
    }

    private static class PropertyChangeTracker implements PropertyChangeListener {

        int count;

        PropertyChangeEvent evt;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            count++;
            this.evt = evt;
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(JavaBeanObservableArrayBasedListTest.class.getName());
        suite.addTestSuite(JavaBeanObservableArrayBasedListTest.class);
        suite.addTest(MutableObservableListContractTest.suite(new Delegate()));
        return suite;
    }

    static class Delegate extends AbstractObservableCollectionContractDelegate {

        @Override
        public IObservableCollection createObservableCollection(Realm realm, int elementCount) {
            String propertyName = "array";
            Object bean = new Bean(new Object[0]);
            IObservableList list = BeansObservables.observeList(realm, bean, propertyName, String.class);
            for (int i = 0; i < elementCount; i++) list.add(createElement(list));
            return list;
        }

        @Override
        public Object createElement(IObservableCollection collection) {
            return new Object();
        }

        @Override
        public Object getElementType(IObservableCollection collection) {
            return String.class;
        }

        @Override
        public void change(IObservable observable) {
            IObservableList list = (IObservableList) observable;
            list.add(createElement(list));
        }
    }
}
