/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.ui.views.markers.FilterConfigurationArea;

/**
* GroupFilterConfigurationArea is the FilterConfigurationArea for the special case
* group level settings for a {@link MarkerFieldFilterGroup}
* @since 3.4
*
*/
abstract class GroupFilterConfigurationArea extends FilterConfigurationArea {

    /**
* Apply to the group
* @param group
*/
    public abstract void applyToGroup(MarkerFieldFilterGroup group);

    /**
* Initialise from the group
* @param group
*/
    public abstract void initializeFromGroup(MarkerFieldFilterGroup group);
}
