/***/
package org.eclipse.e4.ui.css.core.impl.engine;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.e4.ui.css.core.dom.CSSStylableElement;
import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSElementContext;
import org.w3c.dom.Element;

public class CSSElementContextImpl implements CSSElementContext {

    private Element element;

    private Map<Object, Object> datas;

    private IElementProvider elementProvider;

    public  CSSElementContextImpl() {
    }

    @Override
    public void setData(Object key, Object value) {
        if (datas == null) {
            datas = new HashMap();
        }
        datas.put(key, value);
    }

    @Override
    public Object getData(Object key) {
        if (datas == null) {
            return null;
        }
        return datas.get(key);
    }

    @Override
    public Element getElement() {
        return element;
    }

    @Override
    public void setElement(Element newElement) {
        if (element != null && element instanceof CSSStylableElement && newElement instanceof CSSStylableElement) {
            ((CSSStylableElement) newElement).copyDefaultStyleDeclarations(((CSSStylableElement) element));
        }
        this.element = newElement;
    }

    @Override
    public boolean elementMustBeRefreshed(IElementProvider elementProvider) {
        if (this.elementProvider == null) {
            return (elementProvider != null);
        }
        return !this.elementProvider.equals(elementProvider);
    }

    @Override
    public void setElementProvider(IElementProvider elementProvider) {
        this.elementProvider = elementProvider;
    }
}
