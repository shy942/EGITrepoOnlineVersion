/***/
package org.eclipse.e4.ui.css.xml.properties.css2;

import org.eclipse.e4.ui.css.core.dom.properties.css2.AbstractCSSPropertyFontHandler;
import org.eclipse.e4.ui.css.core.dom.properties.css2.ICSSPropertyFontHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
*
*/
public class CSSPropertyFontXMLHandler extends AbstractCSSPropertyFontHandler {

    public static final ICSSPropertyFontHandler INSTANCE = new CSSPropertyFontXMLHandler();

    @Override
    public boolean applyCSSProperty(Object node, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (node instanceof Element && ((Node) node).getOwnerDocument() != null) {
            super.applyCSSProperty(node, property, value, pseudo, engine);
            return true;
        }
        return false;
    }

    @Override
    public void applyCSSPropertyFontSize(Object node, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
            Element element = (Element) node;
            // Add size attribute
            int size = (int) ((CSSPrimitiveValue) value).getFloatValue(CSSPrimitiveValue.CSS_PT);
            element.setAttribute("size", size + "");
        }
    }

    @Override
    public void applyCSSPropertyFontWeight(Object node, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
            Element element = (Element) node;
            String weight = ((CSSPrimitiveValue) value).getStringValue();
            if ("bold".equals(weight.toLowerCase())) {
                insertElement(element, "b");
            }
        }
    }

    @Override
    public void applyCSSPropertyFontStyle(Object node, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) {
            Element element = (Element) node;
            String style = ((CSSPrimitiveValue) value).getStringValue();
            if ("italic".equals(style)) {
                insertElement(element, "i");
            }
        }
    }

    @Override
    public String retrieveCSSPropertyFontAdjust(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontFamily(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontSize(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontStretch(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontStyle(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontVariant(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String retrieveCSSPropertyFontWeight(Object element, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    private void insertElement(Element element, String elementName) {
        Element b = element.getOwnerDocument().createElement(elementName);
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            b.appendChild(node.cloneNode(true));
            node.getParentNode().removeChild(node);
        }
        element.appendChild(b);
    }
}
