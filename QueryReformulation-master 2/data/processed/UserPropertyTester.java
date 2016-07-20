/***/
package org.eclipse.ui.examples.contributions.model;

import org.eclipse.core.expressions.PropertyTester;

/**
* Test properties of a Person object provided as a variable.
*
* @since 3.4
*/
public class UserPropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final String IS_ADMIN = "isAdmin";

    //$NON-NLS-1$
    private static final String ID = "id";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof Person)) {
            return false;
        }
        Person person = (Person) receiver;
        if (property.equals(IS_ADMIN) && expectedValue instanceof Boolean) {
            boolean value = ((Boolean) expectedValue).booleanValue();
            return person.hasAdminRights() == value;
        }
        if (property.equals(ID) && expectedValue instanceof Integer) {
            int value = ((Integer) expectedValue).intValue();
            return person.getId() == value;
        }
        return false;
    }
}
