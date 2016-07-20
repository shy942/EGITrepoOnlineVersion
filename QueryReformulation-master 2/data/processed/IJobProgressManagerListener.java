/***/
package org.eclipse.ui.internal.progress;

/**
* The IJobProgressManagerListener is a class that listeners to the JobProgressManager.
*/
interface IJobProgressManagerListener {

    /**
* Refresh the viewer as a result of an addition of info.
* @param info
*/
    void addJob(final JobInfo info);

    /**
* Refresh the viewer as a result of an addition of group.
* @param info
*/
    void addGroup(final GroupInfo info);

    /**
* Refresh the IJobProgressManagerListeners as a result of a change in info.
* @param info
*/
    public void refreshJobInfo(JobInfo info);

    /**
* Refresh the IJobProgressManagerListeners as a result of a change in groups.
* @param info
*/
    public void refreshGroup(GroupInfo info);

    /**
* Refresh the viewer for all jobs.
* @param info
*/
    void refreshAll();

    /**
* Refresh the viewer as a result of a removal of info.
* @param info
*/
    void removeJob(final JobInfo info);

    /**
* Refresh the viewer as a result of a removal of group.
* @param info
*/
    void removeGroup(final GroupInfo group);

    /**
* Return whether or not this listener shows debug information.
* @return boolean
*/
    boolean showsDebug();
}
