/***/
package org.eclipse.ui.views.tasklist;

/**
* This interface defines constants used for marker properties
* and attributes.
*
* @see org.eclipse.core.resources.IMarker
*/
interface IMarkerConstants {

    //$NON-NLS-1$
    public static final String PREFIX = "org.eclipse.ui.tasklist.";

    //$NON-NLS-1$
    public static final String P_PRIORITY_IMAGE = PREFIX + "priorityImage";

    //$NON-NLS-1$
    public static final String P_COMPLETE_IMAGE = PREFIX + "completeImage";

    //$NON-NLS-1$
    public static final String P_RESOURCE_NAME = PREFIX + "resourceName";

    //$NON-NLS-1$
    public static final String P_CONTAINER_NAME = PREFIX + "containerName";

    //$NON-NLS-1$
    public static final String P_LINE_AND_LOCATION = PREFIX + "lineAndLocation";
}
