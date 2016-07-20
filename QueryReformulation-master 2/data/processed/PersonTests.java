/***/
package org.eclipse.jface.tests.examples.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import junit.framework.TestCase;
import org.eclipse.jface.examples.databinding.model.SimplePerson;

/**
* @since 3.2
*
*/
public class PersonTests extends TestCase {

    public void testSetName() {
        SimplePerson person = new SimplePerson();
        Listener listener = new Listener();
        person.addPropertyChangeListener(listener);
        assertEquals(0, listener.count);
        assertNull(listener.lastEvent);
        //$NON-NLS-1$
        person.setState("new state");
        assertEquals(1, listener.count);
        assertNotNull(listener.lastEvent);
        //$NON-NLS-1$
        assertEquals("state", listener.lastEvent.getPropertyName());
        assertEquals("", listener.lastEvent.getOldValue());
        //$NON-NLS-1$
        assertEquals("new state", listener.lastEvent.getNewValue());
        assertEquals("new state", person.getState());
    }

    private class Listener implements PropertyChangeListener {

        private int count;

        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            count++;
            this.lastEvent = evt;
        }
    }
}
