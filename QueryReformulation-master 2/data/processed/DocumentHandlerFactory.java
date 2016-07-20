/***/
package org.eclipse.e4.ui.css.core.sac;

import org.eclipse.e4.ui.css.core.impl.sac.DocumentHandlerFactoryImpl;

/**
* Factory to get instance of {@link DocumentHandlerFactory}.
*/
public abstract class DocumentHandlerFactory implements IDocumentHandlerFactory {

    /**
* Return instance of {@link DocumentHandlerFactory}.
*
* @return
*/
    public static DocumentHandlerFactory newInstance() {
        return new DocumentHandlerFactoryImpl();
    }
}
