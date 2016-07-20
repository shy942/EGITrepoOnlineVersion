/***/
package org.eclipse.ui.internal;

/**
* Preference constants for the heap status.
*
* @since 3.1
*/
public interface IHeapStatusConstants {

    /**
* Preference key for the update interval (value in milliseconds).
*/
    //$NON-NLS-1$
    String PREF_UPDATE_INTERVAL = "HeapStatus.updateInterval";

    /**
* Preference key for whether to show max heap, if available (value is boolean).
*/
    //$NON-NLS-1$
    String PREF_SHOW_MAX = "HeapStatus.showMax";
}
