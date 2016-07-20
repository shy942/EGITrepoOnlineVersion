/***/
package org.eclipse.ui.examples.propertysheet;

import java.util.Vector;
import org.eclipse.swt.graphics.RGB;

/**
* A Group Element
*/
public class GroupElement extends OrganizationElement {

    //$NON-NLS-1$
    public static String P_USERS = "users";

    //$NON-NLS-1$
    public static String P_SUBGROUPS = "subgroups";

    //$NON-NLS-1$
    public static String P_CONTENTS = "contents";

    private Vector<OrganizationElement> subGroups;

    private Vector<OrganizationElement> users;

    // must be synchronized to contain both the references of subGroups and users
    private Vector<OrganizationElement> contents;

    /**
* Constructor.
* Creates a new GroupElement within the passed parent GroupElement,
* gives it the passed name property, sets Icon.
*
* @param name the name
* @param parent the parent
*/
    public  GroupElement(String name, GroupElement parent) {
        super(name, parent);
    }

    /**
* Adds an OrganizationElement to this GroupElement.
*
* @param userGroup The Organization Element
*/
    public void add(OrganizationElement userGroup) {
        if (userGroup.isUser() || userGroup.isGroup()) {
            getContents().add(userGroup);
        }
        if (userGroup.isGroup()) {
            getSubGroups().add(userGroup);
            // synchronizes backpointer of userGroup: defensive
            userGroup.setParent(this);
        }
        if (userGroup.isUser()) {
            getUsers().add(userGroup);
            // synchronizes backpointer of userGroup: defensive
            userGroup.setParent(this);
        }
    }

    /**
* Creates a new <code>GroupElement</code>
* nested in this <code>GroupElement<code>
*
* @param name the name of the sub group
*/
    public GroupElement createSubGroup(String name) {
        GroupElement newGroup = new GroupElement(name, this);
        add(newGroup);
        return newGroup;
    }

    /**
* Creates a new <code>UserElement</code>
*
* @param the name of the user element
*/
    public UserElement createUser(String name) {
        UserElement newUser = new UserElement(name, this);
        add(newUser);
        return newUser;
    }

    /**
* Deletes an OrganizationElement from this GroupElement.
*
* @param userGroup the Organization Element
*/
    public void delete(OrganizationElement userGroup) {
        if (userGroup.isUser() || userGroup.isGroup()) {
            getContents().remove(userGroup);
        }
        if (userGroup.isGroup()) {
            getSubGroups().remove(userGroup);
        // synchronizes backpointer of userGroup: defensive
        }
        if (userGroup.isUser()) {
            getUsers().remove(userGroup);
        // synchronizes backpointer of userGroup: defensive
        }
    }

    @Override
    public Object[] getChildren(Object o) {
        return getContents().toArray();
    }

    /**
* Returns the content
*/
    private Vector<OrganizationElement> getContents() {
        if (contents == null)
            contents = new Vector();
        return contents;
    }

    @Override
    public Object getEditableValue() {
        return this.toString();
    }

    /**
* Returns the error message
*/
    public String getErrorMessage() {
        return null;
    }

    /**
* Returns the subgroups
*/
    private Vector<OrganizationElement> getSubGroups() {
        if (subGroups == null)
            subGroups = new Vector();
        return subGroups;
    }

    /**
* Returns the users
*/
    private Vector<OrganizationElement> getUsers() {
        if (users == null)
            users = new Vector();
        return users;
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    public RGB getForeground(Object element) {
        return null;
    }

    public RGB getBackground(Object element) {
        return null;
    }
}
