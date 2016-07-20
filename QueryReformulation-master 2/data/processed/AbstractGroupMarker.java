/***/
package org.eclipse.jface.action;

import org.eclipse.core.runtime.Assert;

/**
* Abstract superclass for group marker classes.
* <p>
* This class is not intended to be subclassed outside the framework.
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public abstract class AbstractGroupMarker extends ContributionItem {

    /**
* Constructor for use by subclasses.
*/
    protected  AbstractGroupMarker() {
    }

    /**
* Create a new group marker with the given name.
* The group name must not be <code>null</code> or the empty string.
* The group name is also used as the item id.
*
* @param groupName the name of the group
*/
    protected  AbstractGroupMarker(String groupName) {
        super(groupName);
        Assert.isTrue(groupName != null && groupName.length() > 0);
    }

    /**
* Returns the group name.
*
* @return the group name
*/
    public String getGroupName() {
        return getId();
    }

    /**
* The <code>AbstractGroupMarker</code> implementation of this <code>IContributionItem</code>
* method returns <code>true</code> iff the id is not <code>null</code>. Subclasses may override.
*/
    @Override
    public boolean isGroupMarker() {
        return getId() != null;
    }
}
