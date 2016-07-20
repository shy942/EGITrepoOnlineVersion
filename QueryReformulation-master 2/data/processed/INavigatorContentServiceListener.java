/***/
package org.eclipse.ui.navigator;

/**
* <p>
* Used by clients who would like to listen for the load event of am
* {@link INavigatorContentExtension}.
*
* @since 3.2
*/
public interface INavigatorContentServiceListener {

    /**
* Notifies the listener that the given extension
* has now been loaded. Extensions are loaded as
* needed as the user expands nodes in the tree viewer.
*
* @param anExtension The extension that was loaded.
*/
    void onLoad(INavigatorContentExtension anExtension);
}
