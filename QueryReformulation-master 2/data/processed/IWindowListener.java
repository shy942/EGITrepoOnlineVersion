/***/
package org.eclipse.ui;

/**
* Interface for listening to window lifecycle events.
* <p>
* This interface may be implemented by clients.
* </p>
*/
public interface IWindowListener {

    /**
* Notifies this listener that the given window has been activated.
* <p>
* <b>Note:</b> This event is not fired if no perspective is
* open (the window is empty).
* </p>
*
* @param window the window that was activated
*/
    public void windowActivated(IWorkbenchWindow window);

    /**
* Notifies this listener that the given window has been deactivated.
* <p>
* <b>Note:</b> This event is not fired if no perspective is
* open (the window is empty).
* </p>
*
* @param window the window that was activated
*/
    public void windowDeactivated(IWorkbenchWindow window);

    /**
* Notifies this listener that the given window has been closed.
*
* @param window the window that was closed
* @see IWorkbenchWindow#close
*/
    public void windowClosed(IWorkbenchWindow window);

    /**
* Notifies this listener that the given window has been opened.
*
* @param window the window that was opened
* @see IWorkbench#openWorkbenchWindow
*/
    public void windowOpened(IWorkbenchWindow window);
}
