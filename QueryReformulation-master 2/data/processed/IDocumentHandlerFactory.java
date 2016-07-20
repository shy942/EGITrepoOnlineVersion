/***/
package org.eclipse.e4.ui.css.core.sac;

/**
* Factory interface to get instance of {@link ExtendedDocumentHandler}.
*/
public interface IDocumentHandlerFactory {

    /**
* Return default instance of {@link ExtendedDocumentHandler}.
*
* @return
*/
    public ExtendedDocumentHandler makeDocumentHandler();
}
