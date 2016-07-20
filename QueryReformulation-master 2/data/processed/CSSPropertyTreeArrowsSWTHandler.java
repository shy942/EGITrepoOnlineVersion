/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.dom.TreeElement;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Tree;
import org.w3c.dom.css.CSSValue;

/**
* A handler which will set the foreground color to paint the tree items.
*/
public class CSSPropertyTreeArrowsSWTHandler implements ICSSPropertyHandler {

    //$NON-NLS-1$
    private static final String SWT_TREE_ARROWS_COLOR = "swt-tree-arrows-color";

    //$NON-NLS-1$
    private static final String SWT_TREE_ARROWS_MODE = "swt-tree-arrows-mode";

    @Override
    public boolean applyCSSProperty(Object element, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (element instanceof TreeElement) {
            TreeElement treeElement = (TreeElement) element;
            Tree tree = treeElement.getTree();
            if (SWT_TREE_ARROWS_COLOR.equals(property)) {
                if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
                    Color newColor = (Color) engine.convert(value, Color.class, tree.getDisplay());
                    treeElement.setTreeArrowsForegroundColor(newColor);
                }
            } else if (SWT_TREE_ARROWS_MODE.equals(property)) {
                // Note: windows-only
                treeElement.setTreeArrowsMode(value.getCssText());
            }
        }
        return false;
    }

    @Override
    public String retrieveCSSProperty(Object element, String property, String pseudo, CSSEngine engine) throws Exception {
        return null;
    }
}
