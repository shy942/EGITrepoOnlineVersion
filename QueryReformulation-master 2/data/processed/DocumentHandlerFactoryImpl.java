/***/
package org.eclipse.e4.ui.css.core.impl.sac;

import org.eclipse.e4.ui.css.core.sac.DocumentHandlerFactory;
import org.eclipse.e4.ui.css.core.sac.ExtendedDocumentHandler;

/**
* This class implements the
* {@link org.eclipse.e4.ui.css.core.sac.IDocumentHandlerFactory} interface.
*/
public class DocumentHandlerFactoryImpl extends DocumentHandlerFactory {

    @Override
    public ExtendedDocumentHandler makeDocumentHandler() {
        return new CSSDocumentHandlerImpl();
    }
}
