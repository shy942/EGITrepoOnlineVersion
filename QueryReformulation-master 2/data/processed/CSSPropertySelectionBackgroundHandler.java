/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.ElementAdapter;
import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.dom.ISelectionBackgroundCustomizationElement;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Widget;
import org.w3c.dom.css.CSSValue;

/**
* A handler which will set the selection/hot background and border colors.
*/
public class CSSPropertySelectionBackgroundHandler implements ICSSPropertyHandler {

    //$NON-NLS-1$
    private static final String SWT_SELECTION_FOREGROUND_COLOR = "swt-selection-foreground-color";

    //$NON-NLS-1$
    private static final String SWT_SELECTION_BACKGROUND_COLOR = "swt-selection-background-color";

    //$NON-NLS-1$
    private static final String SWT_SELECTION_BORDER_COLOR = "swt-selection-border-color";

    //$NON-NLS-1$
    private static final String SWT_HOT_BACKGROUND_COLOR = "swt-hot-background-color";

    //$NON-NLS-1$
    private static final String SWT_HOT_BORDER_COLOR = "swt-hot-border-color";

    @Override
    public boolean applyCSSProperty(Object element, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (element instanceof ISelectionBackgroundCustomizationElement && element instanceof ElementAdapter) {
            ElementAdapter elementAdapter = (ElementAdapter) element;
            Object nativeWidget = elementAdapter.getNativeWidget();
            if (nativeWidget instanceof Widget) {
                Widget widget = (Widget) nativeWidget;
                ISelectionBackgroundCustomizationElement treeElement = (ISelectionBackgroundCustomizationElement) element;
                if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
                    if (SWT_SELECTION_FOREGROUND_COLOR.equals(property)) {
                        Color newColor = (Color) engine.convert(value, Color.class, widget.getDisplay());
                        treeElement.setSelectionForegroundColor(newColor);
                    } else if (SWT_SELECTION_BACKGROUND_COLOR.equals(property)) {
                        Color newColor = (Color) engine.convert(value, Color.class, widget.getDisplay());
                        treeElement.setSelectionBackgroundColor(newColor);
                    } else if (SWT_SELECTION_BORDER_COLOR.equals(property)) {
                        Color newColor = (Color) engine.convert(value, Color.class, widget.getDisplay());
                        treeElement.setSelectionBorderColor(newColor);
                    } else if (SWT_HOT_BACKGROUND_COLOR.equals(property)) {
                        Color newColor = (Color) engine.convert(value, Color.class, widget.getDisplay());
                        treeElement.setHotBackgroundColor(newColor);
                    } else if (SWT_HOT_BORDER_COLOR.equals(property)) {
                        Color newColor = (Color) engine.convert(value, Color.class, widget.getDisplay());
                        treeElement.setHotBorderColor(newColor);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String retrieveCSSProperty(Object element, String property, String pseudo, CSSEngine engine) throws Exception {
        return null;
    }
}
