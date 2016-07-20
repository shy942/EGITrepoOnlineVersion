/***/
package org.eclipse.ui;

/**
* Should be implemented by editors that wish to contribute to the
* navigation history. The message <code>createNavigationLocation</code>
* will be sent when a new location is marked in the history.
*
* @since 2.1
*/
public interface INavigationLocationProvider {

    /**
* Creates an empty navigation location. The message <code>restoreState</code>
* will be sent to the location to restore its state.
*
* @return INavigationLocation
*/
    public INavigationLocation createEmptyNavigationLocation();

    /**
* Creates a navigation location describing the current state.
*
* @return INavigationLocation
*/
    public INavigationLocation createNavigationLocation();
}
