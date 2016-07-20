/***/
package org.eclipse.ui.internal.activities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.activities.IIdentifierListener;
import org.eclipse.ui.activities.IdentifierEvent;
import org.eclipse.ui.internal.util.Util;

final class Identifier implements IIdentifier {

    private static final int HASH_FACTOR = 89;

    private static final int HASH_INITIAL = Identifier.class.getName().hashCode();

    private static final Set<Identifier> strongReferences = new HashSet();

    private Set<String> activityIds = Collections.emptySet();

    private transient String[] activityIdsAsArray = {};

    private boolean enabled;

    private transient int hashCode = HASH_INITIAL;

    private String id;

    private ListenerList identifierListeners;

    private transient String string;

     Identifier(String id) {
        if (id == null) {
            throw new NullPointerException();
        }
        this.id = id;
    }

    @Override
    public void addIdentifierListener(IIdentifierListener identifierListener) {
        if (identifierListener == null) {
            throw new NullPointerException();
        }
        if (identifierListeners == null) {
            identifierListeners = new ListenerList(ListenerList.IDENTITY);
        }
        identifierListeners.add(identifierListener);
        strongReferences.add(this);
    }

    @Override
    public int compareTo(Object object) {
        Identifier castedObject = (Identifier) object;
        int compareTo = Util.compare(activityIdsAsArray, castedObject.activityIdsAsArray);
        if (compareTo == 0) {
            compareTo = Util.compare(enabled, castedObject.enabled);
            if (compareTo == 0) {
                compareTo = Util.compare(id, castedObject.id);
            }
        }
        return compareTo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Identifier)) {
            return false;
        }
        final Identifier castedObject = (Identifier) object;
        if (!Util.equals(activityIds, castedObject.activityIds)) {
            return false;
        }
        if (!Util.equals(enabled, castedObject.enabled)) {
            return false;
        }
        return Util.equals(id, castedObject.id);
    }

    void fireIdentifierChanged(IdentifierEvent identifierEvent) {
        if (identifierEvent == null) {
            throw new NullPointerException();
        }
        if (identifierListeners != null) {
            Object[] listeners = identifierListeners.getListeners();
            for (int i = 0; i < listeners.length; i++) {
                Object listener = listeners[i];
                ((IIdentifierListener) listener).identifierChanged(identifierEvent);
            }
        }
    }

    @Override
    public Set<String> getActivityIds() {
        return activityIds;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        if (hashCode == HASH_INITIAL) {
            hashCode = hashCode * HASH_FACTOR + Util.hashCode(activityIds);
            hashCode = hashCode * HASH_FACTOR + Util.hashCode(enabled);
            hashCode = hashCode * HASH_FACTOR + Util.hashCode(id);
            if (hashCode == HASH_INITIAL) {
                hashCode++;
            }
        }
        return hashCode;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void removeIdentifierListener(IIdentifierListener identifierListener) {
        if (identifierListener == null) {
            throw new NullPointerException();
        }
        if (identifierListeners != null) {
            identifierListeners.remove(identifierListener);
            if (identifierListeners.isEmpty()) {
                strongReferences.remove(this);
            }
        }
    }

    boolean setActivityIds(Set<String> activityIds) {
        activityIds = Util.safeCopy(activityIds, String.class);
        if (!Util.equals(activityIds, this.activityIds)) {
            this.activityIds = activityIds;
            this.activityIdsAsArray = this.activityIds.toArray(new String[this.activityIds.size()]);
            hashCode = HASH_INITIAL;
            string = null;
            return true;
        }
        return false;
    }

    boolean setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            this.enabled = enabled;
            hashCode = HASH_INITIAL;
            string = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (string == null) {
            final StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append('[');
            stringBuffer.append(activityIds);
            stringBuffer.append(',');
            stringBuffer.append(enabled);
            stringBuffer.append(',');
            stringBuffer.append(id);
            stringBuffer.append(']');
            string = stringBuffer.toString();
        }
        return string;
    }
}
