/***/
package org.eclipse.ui.examples.propertysheet;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
* Validator for email addresses
*/
public class EmailAddressValidator implements ICellEditorValidator {

    /**
* The <code>EmailAddressValidator</code> implementation of this
* <code>ICellEditorValidator</code> method
* determines if the value is a valid email address.
* (check to see if it is non-null and contains an @)
*/
    @Override
    public String isValid(Object value) {
        if (value == null) {
            //$NON-NLS-1$
            return MessageUtil.getString("email_address_is_incomplete");
        }
        String emailAddress = (String) value;
        int index = emailAddress.indexOf('@');
        int length = emailAddress.length();
        if (index > 0 && index < length) {
            return null;
        }
        return MessageUtil.getString(//$NON-NLS-1$
        "email_address_does_not_have_a_valid_format");
    }
}
