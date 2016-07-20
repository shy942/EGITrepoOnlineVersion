/***/
package org.eclipse.jface.window;

import org.eclipse.swt.widgets.Shell;

/**
* Interface for objects that can return a shell. This is normally used for
* opening child windows. An object that wants to open child shells can take
* an IShellProvider in its constructor, and the object that implements IShellProvider
* can dynamically choose where child shells should be opened.
*
* @since 3.1
*/
public interface IShellProvider {

    /**
* Returns the current shell (or null if none). This return value may
* change over time, and should not be cached.
*
* @return the current shell or null if none
*/
    Shell getShell();
}
