/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.eclipse.e4.ui.css.core.exceptions.DOMExceptionImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.stylesheets.MediaList;
import org.w3c.dom.stylesheets.StyleSheet;

public class CSSStyleSheetImpl extends AbstractCSSNode implements CSSStyleSheet {

    private CSSRuleList rules = null;

    public  CSSStyleSheetImpl() {
        super();
    }

    // W3C CSSStyleSheet API methods
    @Override
    public void deleteRule(int position) throws DOMException {
        try {
            ((CSSRuleListImpl) rules).remove(position);
        } catch (IndexOutOfBoundsException ex) {
            throw new DOMExceptionImpl(DOMException.INDEX_SIZE_ERR, DOMExceptionImpl.ARRAY_OUT_OF_BOUNDS, ex.getMessage());
        }
    }

    @Override
    public CSSRuleList getCssRules() {
        return rules;
    }

    @Override
    public CSSRule getOwnerRule() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public int insertRule(String arg0, int arg1) throws DOMException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    // org.w3c.dom.stylesheet.StyleSheet API methods
    @Override
    public boolean getDisabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public String getHref() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public MediaList getMedia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public Node getOwnerNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public StyleSheet getParentStyleSheet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public void setDisabled(boolean disabled) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    // Additional
    public void setRuleList(CSSRuleList rules) {
        this.rules = rules;
    }
}
