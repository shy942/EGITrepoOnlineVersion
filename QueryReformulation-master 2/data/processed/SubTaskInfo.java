/***/
package org.eclipse.ui.internal.progress;

/**
* SubTaskInfo is the class that displays a subtask in the tree.
*/
class SubTaskInfo extends JobTreeElement {

    protected String taskName;

    JobInfo jobInfo;

    /**
* Create a new instance of the receiver.
*
* @param parentJob
* @param name
*/
     SubTaskInfo(JobInfo parentJob, String name) {
        taskName = name;
        jobInfo = parentJob;
    }

    @Override
    Object[] getChildren() {
        return ProgressManagerUtil.EMPTY_OBJECT_ARRAY;
    }

    @Override
    String getDisplayString() {
        if (taskName == null) {
            return ProgressMessages.SubTaskInfo_UndefinedTaskName;
        }
        return taskName;
    }

    @Override
    boolean hasChildren() {
        return false;
    }

    /**
* Set the taskName of the receiver.
*
* @param name
*/
    void setTaskName(String name) {
        if (name == null)
            taskName = ProgressMessages.SubTaskInfo_UndefinedTaskName;
        else
            this.taskName = name;
    }

    /**
* Returns the taskName of the receiver.
*/
    String getTaskName() {
        return taskName;
    }

    @Override
    public Object getParent() {
        return jobInfo;
    }

    @Override
    boolean isJobInfo() {
        return false;
    }

    @Override
    boolean isActive() {
        return jobInfo.isActive();
    }
}
