/***/
package org.eclipse.ui.internal.activities;

import org.eclipse.ui.activities.IActivityRequirementBinding;
import org.eclipse.ui.internal.util.Util;

public final class ActivityRequirementBinding implements IActivityRequirementBinding {

    private static final int HASH_FACTOR = 89;

    private static final int HASH_INITIAL = ActivityRequirementBinding.class.getName().hashCode();

    private String requiredActivityId;

    private transient int hashCode = HASH_INITIAL;

    private String activityId;

    private transient String string;

    public  ActivityRequirementBinding(String requiredActivityId, String activityId) {
        if (requiredActivityId == null || activityId == null) {
            throw new NullPointerException();
        }
        this.requiredActivityId = requiredActivityId;
        this.activityId = activityId;
    }

    @Override
    public int compareTo(Object object) {
        ActivityRequirementBinding castedObject = (ActivityRequirementBinding) object;
        int compareTo = Util.compare(requiredActivityId, castedObject.requiredActivityId);
        if (compareTo == 0) {
            compareTo = Util.compare(activityId, castedObject.activityId);
        }
        return compareTo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ActivityRequirementBinding)) {
            return false;
        }
        final ActivityRequirementBinding castedObject = (ActivityRequirementBinding) object;
        if (!Util.equals(requiredActivityId, castedObject.requiredActivityId)) {
            return false;
        }
        return Util.equals(activityId, castedObject.activityId);
    }

    @Override
    public String getRequiredActivityId() {
        return requiredActivityId;
    }

    @Override
    public String getActivityId() {
        return activityId;
    }

    @Override
    public int hashCode() {
        if (hashCode == HASH_INITIAL) {
            hashCode = hashCode * HASH_FACTOR + Util.hashCode(requiredActivityId);
            hashCode = hashCode * HASH_FACTOR + Util.hashCode(activityId);
            if (hashCode == HASH_INITIAL) {
                hashCode++;
            }
        }
        return hashCode;
    }

    @Override
    public String toString() {
        if (string == null) {
            final StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append('[');
            stringBuffer.append(requiredActivityId);
            stringBuffer.append(',');
            stringBuffer.append(activityId);
            stringBuffer.append(']');
            string = stringBuffer.toString();
        }
        return string;
    }
}
