/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.eclipse.e4.ui.css.core.exceptions.DOMExceptionImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleSheet;

public abstract class CSSRuleImpl extends AbstractCSSNode implements CSSRule {

    // null allowed
    private CSSStyleSheet parentStyleSheet = null;

    // null allowed
    private CSSRule parentRule = null;

    private boolean readOnly;

    //TODO who sets readOnly?  Seems should be ViewCSSImpl.getComputedStyle(Element,String)
    public  CSSRuleImpl(CSSStyleSheet parentStyleSheet, CSSRule parentRule) {
        super();
        this.parentStyleSheet = parentStyleSheet;
        this.parentRule = parentRule;
    }

    // W3C CSSRule API methods
    @Override
    public String getCssText() {
        // TODO Auto-generated constructor stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public CSSStyleSheet getParentStyleSheet() {
        return parentStyleSheet;
    }

    @Override
    public CSSRule getParentRule() {
        return parentRule;
    }

    @Override
    public abstract short getType();

    @Override
    public void setCssText(String cssText) throws DOMException {
        if (readOnly)
            throw new DOMExceptionImpl(DOMException.NO_MODIFICATION_ALLOWED_ERR, DOMExceptionImpl.NO_MODIFICATION_ALLOWED_ERROR);
        // TODO throws HIERARCHY_REQUEST_ERR: Raised if the rule cannot be inserted at this point in the style sheet.
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }
}
