/***/
package org.eclipse.ui.tests.api;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

/**
* Simple factory implementation that will fail on command. This is used to test
* that working set restoration does not die if one of the factories dies.
*
* @since 3.4
*
*/
public class BadElementFactory implements IElementFactory {

    /**
* Set to cause the factory to fail.
*/
    public static boolean fail = false;

    /**
* Set to true when {@link #createElement(IMemento)} is called while fail is true.
*/
    public static boolean failAttempted = false;

    public static class BadElementInstance implements IAdaptable, IPersistableElement {

        /**
* Set to cause save to fail.
*/
        public static boolean fail = false;

        /**
* Set to true when {@link #saveState(IMemento)} is called while fail is true.
*/
        public static boolean failAttempted = false;

        @Override
        public Object getAdapter(Class adapter) {
            if (adapter.equals(IPersistableElement.class)) {
                return this;
            }
            return null;
        }

        @Override
        public String getFactoryId() {
            return "org.eclipse.ui.tests.badFactory";
        }

        @Override
        public void saveState(IMemento memento) {
            if (fail) {
                failAttempted = true;
                throw new RuntimeException();
            }
        }
    }

    ;

    @Override
    public IAdaptable createElement(IMemento memento) {
        if (fail) {
            failAttempted = true;
            throw new RuntimeException();
        }
        return new BadElementInstance();
    }
}
