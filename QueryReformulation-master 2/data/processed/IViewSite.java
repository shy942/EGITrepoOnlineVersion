/***/
package org.eclipse.ui;

/**
* The primary interface between a view part and the workbench.
* <p>
* The workbench exposes its implemention of view part sites via this interface,
* which is not intended to be implemented or extended by clients.
* </p>
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IViewSite extends IWorkbenchPartSite {

    /**
* Returns the action bars for this part site.
* Views have exclusive use of their site's action bars.
*
* @return the action bars
*/
    public IActionBars getActionBars();

    /**
* Returns the secondary id for this part site's part, or <code>null</code>
* if it has none.
*
* @return the secondary id for this part site's part or <code>null</code>
*         if it has none
* @see IWorkbenchPage#showView(String, String, int)
* @since 3.0
*/
    public String getSecondaryId();
}
