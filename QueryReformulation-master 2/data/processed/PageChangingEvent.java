/***/
package org.eclipse.jface.dialogs;

import java.util.EventObject;
import org.eclipse.core.runtime.Assert;

/**
* Event object describing an <code>IDialogPage</code> in the midst of changing.
*
* @see IPageChangingListener
* @since 3.3
*/
public class PageChangingEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    private Object currentPage;

    private Object targetPage;

    /**
* Public field that dictates if the page change will successfully change.
*
* Set this field to <code>false</code> to prevent the page from changing.
*
* Default value is <code>true</code>.
*/
    public boolean doit = true;

    /**
* Creates a new event for the given source, selected (current) page and
* direction.
*
* @param source
*            the page changing provider (the source of this event)
* @param currentPage
*            the current page. In the JFace provided dialogs this will be
*            an <code>IDialogPage</code>.
* @param targetPage
*            the target page. In the JFace provided dialogs this will be an
*            <code>IDialogPage</code>.
*/
    public  PageChangingEvent(Object source, Object currentPage, Object targetPage) {
        super(source);
        Assert.isNotNull(currentPage);
        Assert.isNotNull(targetPage);
        this.currentPage = currentPage;
        this.targetPage = targetPage;
    }

    /**
* Returns the current page from which the page change originates.
*
* @return the current page. In dialogs implemented by JFace,
* 		this will be an <code>IDialogPage</code>.
*/
    public Object getCurrentPage() {
        return currentPage;
    }

    /**
* Returns the target page to change to.
*
* @return the target page. In dialogs implemented by JFace,
* 		this will be an <code>IDialogPage</code>.
*/
    public Object getTargetPage() {
        return targetPage;
    }
}
