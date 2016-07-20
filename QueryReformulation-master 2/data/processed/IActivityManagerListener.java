/***/
package org.eclipse.ui.activities;

/**
* An instance of this interface can be used by clients to receive notification
* of changes to one or more instances of <code>IActivityManager</code>.
* <p>
* This interface may be implemented by clients.
* </p>
*
* @since 3.0
* @see IActivityManager#addActivityManagerListener(IActivityManagerListener)
* @see IActivityManager#removeActivityManagerListener(IActivityManagerListener)
*/
public interface IActivityManagerListener {

    /**
* Notifies that one or more properties of an instance of <code>IActivityManager</code>
* have changed. Specific details are described in the <code>ActivityManagerEvent</code>.
*
* @param activityManagerEvent
*            the activity manager event. Guaranteed not to be <code>null</code>.
*/
    void activityManagerChanged(ActivityManagerEvent activityManagerEvent);
}
