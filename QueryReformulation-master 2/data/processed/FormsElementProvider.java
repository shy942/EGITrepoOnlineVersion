/***/
package org.eclipse.ui.internal.forms.css.dom;

import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.Section;
import org.w3c.dom.Element;

/**
* Returns the CSS class which is responsible for styling a forms widget
*
* Registered via the "org.eclipse.e4.ui.css.core.elementProvider" extension
* point for the widgets
*
* {@link IElementProvider} SWT implementation to retrieve w3c Element
*
*/
public class FormsElementProvider implements IElementProvider {

    public static final IElementProvider INSTANCE = new FormsElementProvider();

    @Override
    public Element getElement(Object element, CSSEngine engine) {
        if (element instanceof Section) {
            return new SectionElement((Section) element, engine);
        }
        if (element instanceof Form) {
            return new FormElement((Form) element, engine);
        }
        return null;
    }
}
