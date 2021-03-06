/***/
package org.eclipse.ui.part;

/**
* This interface must be provided by Show In targets (parts listed
* in the Show In prompter).
* The part can either directly implement this interface, or provide it
* via <code>IAdaptable.getAdapter(IShowInTarget.class)</code>.
*
* @see org.eclipse.ui.IPageLayout#addShowInPart
*
* @since 2.1
*/
public interface IShowInTarget {

    /**
* Shows the given context in this target.
* The target should check the context's selection for elements
* to show.  If there are no relevant elements in the selection,
* then it should check the context's input.
*
* @param context the context to show
* @return <code>true</code> if the context could be shown,
*   <code>false</code> otherwise
*/
    public boolean show(ShowInContext context);
}
