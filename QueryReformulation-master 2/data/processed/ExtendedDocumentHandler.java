/***/
package org.eclipse.e4.ui.css.core.sac;

import java.util.Stack;
import org.w3c.css.sac.DocumentHandler;

/**
* Extends {@link DocumentHandler} to get the root node.
*/
public interface ExtendedDocumentHandler extends DocumentHandler {

    /**
* Return root node.
*
* @return
*/
    public Object getNodeRoot();

    /**
* Set node stack.
*
* @param statck
*/
    public void setNodeStack(Stack<Object> statck);
}
