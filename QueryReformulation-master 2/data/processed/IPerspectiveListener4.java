/***/
package org.eclipse.ui;

/**
* Extension interface to <code>IPerspectiveListener</code> which adds support
* for listening to perspective pre-deactivate events.
* <p>
* This interface may be implemented by clients.
* </p>
*
* @see IPageService#addPerspectiveListener(IPerspectiveListener)
* @see PerspectiveAdapter
* @since 3.2
*
*/
public interface IPerspectiveListener4 extends IPerspectiveListener3 {

    /**
* <p>
* Notifies this listener that a perspective in the given page is about to
* be deactivated.
* </p>
* <p>
* Note: This does not have the ability to veto a perspective deactivation.
* </p>
*
* @param page
*            the page containing the deactivated perspective
* @param perspective
*            the perspective descriptor that was deactivated
* @see IWorkbenchPage#setPerspective(IPerspectiveDescriptor)
*/
    public void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor perspective);
}
