/***/
package org.eclipse.e4.ui.css.swt.dom;

import org.eclipse.e4.ui.css.core.dom.CSSStylableElement;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.swt.widgets.Item;

/**
* {@link CSSStylableElement} implementation which wrap SWT {@link Item}.
*
*/
public class ItemElement extends WidgetElement {

    public  ItemElement(Item item, CSSEngine engine) {
        super(item, engine);
    }
}
