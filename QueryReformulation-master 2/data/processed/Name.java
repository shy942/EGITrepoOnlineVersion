/***/
package org.eclipse.ui.examples.propertysheet;

import java.util.Vector;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
* Example IPropertySource whose value as well as its children are editable.
*/
public class Name implements IPropertySource {

    //$NON-NLS-1$
    private String firstName = "";

    //$NON-NLS-1$
    private String lastName = "";

    //$NON-NLS-1$
    private String initial = "";

    // property unique keys
    //$NON-NLS-1$
    public static String P_ID_FIRSTNAME = "Name.FirstName";

    //$NON-NLS-1$
    public static String P_ID_LASTNAME = "Name.LastName";

    //$NON-NLS-1$
    public static String P_ID_MIDDLENAME = "Name.Middle";

    // property display keys
    //$NON-NLS-1$
    public static String P_FIRSTNAME = MessageUtil.getString("FirstName");

    //$NON-NLS-1$
    public static String P_LASTNAME = MessageUtil.getString("LastName");

    //$NON-NLS-1$
    public static String P_MIDDLENAME = MessageUtil.getString("Middle");

    // default values
    //
    private static final String FIRSTNAME_DEFAULT = null;

    private static final String LASTNAME_DEFAULT = null;

    private static final String MIDDLENAME_DEFAULT = null;

    //$NON-NLS-1$
    public static final String P_DESCRIPTORS = "properties";

    private static Vector<TextPropertyDescriptor> descriptors;

    static {
        descriptors = new Vector();
        descriptors.addElement(new TextPropertyDescriptor(P_ID_FIRSTNAME, P_FIRSTNAME));
        descriptors.addElement(new TextPropertyDescriptor(P_ID_LASTNAME, P_LASTNAME));
        descriptors.addElement(new TextPropertyDescriptor(P_ID_MIDDLENAME, P_MIDDLENAME));
    }

    /**
* Creates a new Name.
* @param name String in the form "firstname initial lastname"
*/
    public  Name(String name) {
        int index1, index2;
        index1 = name.indexOf(' ');
        if (index1 < 0)
            index1 = name.length();
        index2 = name.lastIndexOf(' ');
        if (index2 > 0)
            lastName = name.substring(index2 + 1);
        firstName = name.substring(0, index1);
        if (index1 < index2)
            initial = name.substring(index1 + 1, index2);
    }

    /**
* Returns the descriptors
*/
    private static Vector<TextPropertyDescriptor> getDescriptors() {
        return descriptors;
    }

    @Override
    public Object getEditableValue() {
        return this.toString();
    }

    /**
* Returns the first name
*/
    private String getFirstName() {
        return firstName;
    }

    /**
* Returns the initial
*/
    private String getInitial() {
        return initial;
    }

    /**
* Returns the last name
*/
    private String getLastName() {
        return lastName;
    }

    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return getDescriptors().toArray(new IPropertyDescriptor[getDescriptors().size()]);
    }

    /**
* The <code>Name</code> implementation of this
* <code>IPropertySource</code> method returns the following properties
*
* 	1) P_FIRSTNAME returns String, firstname
* 	2) P_LASTNAME returns String, lastname
*  3) P_MIDDLENAME returns String, middle
*/
    @Override
    public Object getPropertyValue(Object propKey) {
        if (P_ID_FIRSTNAME.equals(propKey))
            return getFirstName();
        if (P_ID_LASTNAME.equals(propKey))
            return getLastName();
        if (P_ID_MIDDLENAME.equals(propKey))
            return getInitial();
        return null;
    }

    @Override
    public boolean isPropertySet(Object key) {
        if (key.equals(P_ID_FIRSTNAME))
            return getFirstName() != FIRSTNAME_DEFAULT;
        if (key.equals(P_ID_LASTNAME))
            return getLastName() != LASTNAME_DEFAULT;
        if (key.equals(P_ID_MIDDLENAME))
            return getInitial() != MIDDLENAME_DEFAULT;
        return false;
    }

    /**
* Implemented as part of IPropertySource framework. Sets the specified property
* to its default value.
*
* @see 	IPropertySource#resetPropertyValue(Object)
* @param 	property 	The property to reset.
*/
    @Override
    public void resetPropertyValue(Object property) {
        if (P_ID_FIRSTNAME.equals(property)) {
            setFirstName(FIRSTNAME_DEFAULT);
            return;
        }
        if (P_ID_LASTNAME.equals(property)) {
            setLastName(LASTNAME_DEFAULT);
            return;
        }
        if (P_ID_MIDDLENAME.equals(property)) {
            setInitial(MIDDLENAME_DEFAULT);
            return;
        }
    }

    /**
* Sets the first name
*/
    private void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    /**
* Sets the initial
*/
    private void setInitial(String newInitial) {
        initial = newInitial;
    }

    /**
* Sets the last name
*/
    private void setLastName(String newLastName) {
        lastName = newLastName;
    }

    /**
* The <code>Name</code> implementation of this
* <code>IPropertySource</code> method
* defines the following Setable properties
*
*	1) P_FIRST, expects String, sets the firstname of this OrganizationElement
*  2) P_MIDDLENAME, expects String, sets middlename of this OrganizationElement
*  3) P_LASTNAME, expects String, sets lastname of this OrganizationElement
*/
    @Override
    public void setPropertyValue(Object propName, Object val) {
        if (P_ID_FIRSTNAME.equals(propName)) {
            setFirstName((String) val);
            return;
        }
        if (P_ID_LASTNAME.equals(propName)) {
            setLastName((String) val);
            return;
        }
        if (P_ID_MIDDLENAME.equals(propName)) {
            setInitial((String) val);
            return;
        }
    }

    /**
* The value as displayed in the Property Sheet. Will not print default values
* @return java.lang.String
*/
    @Override
    public String toString() {
        StringBuffer outStringBuffer = new StringBuffer();
        if (getFirstName() != FIRSTNAME_DEFAULT) {
            outStringBuffer.append(getFirstName());
            //$NON-NLS-1$
            outStringBuffer.append(" ");
        }
        if (getInitial() != MIDDLENAME_DEFAULT) {
            outStringBuffer.append(getInitial());
            //$NON-NLS-1$
            outStringBuffer.append(" ");
        }
        if (getLastName() != LASTNAME_DEFAULT) {
            outStringBuffer.append(getLastName());
        }
        return outStringBuffer.toString();
    }
}
