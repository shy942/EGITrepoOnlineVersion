/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.helpers.SWTElementHelpers;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyShowCloseHandler extends CTabETabHelper implements ICSSPropertyHandler {

    public static final ICSSPropertyHandler INSTANCE = new CSSPropertyShowCloseHandler();

    //$NON-NLS-1$
    private static final String CSS_CTABITEM_SELECTED_SHOW_CLOSE_LISTENER_KEY = "CSS_CTABFOLDER_SELECTED_SHOW_CLOSE_LISTENER_KEY";

    @Override
    public boolean applyCSSProperty(Object element, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        Widget widget = SWTElementHelpers.getWidget(element);
        if (widget instanceof CTabItem) {
            Item item = (Item) widget;
            boolean showClose = ((Boolean) engine.convert(value, Boolean.class, null)).booleanValue();
            if ("selected".equals(pseudo)) {
                Control parent = getParent(widget);
                ShowCloseSelectionListener listener = (ShowCloseSelectionListener) parent.getData(CSS_CTABITEM_SELECTED_SHOW_CLOSE_LISTENER_KEY);
                if (listener == null) {
                    listener = new ShowCloseSelectionListener(engine);
                    parent.addListener(SWT.Paint, listener);
                    parent.setData(CSS_CTABITEM_SELECTED_SHOW_CLOSE_LISTENER_KEY, listener);
                } else {
                    listener.setEngine(engine);
                }
                item = getSelection(getParent(widget));
                listener.setSelection(item);
                if (item != null) {
                    setShowClose(item, showClose);
                }
            } else {
                setShowClose(item, showClose);
            }
            return true;
        }
        return false;
    }

    @Override
    public String retrieveCSSProperty(Object element, String property, String pseudo, CSSEngine engine) throws Exception {
        Widget widget = SWTElementHelpers.getWidget(element);
        if (widget instanceof CTabItem) {
            CTabItem item = (CTabItem) widget;
            return Boolean.toString(item.getShowClose());
        }
        return null;
    }

    private class ShowCloseSelectionListener implements Listener {

        private CSSEngine engine;

        private Item selection;

        public  ShowCloseSelectionListener(CSSEngine engine) {
            this.engine = engine;
        }

        public void setSelection(Item selection) {
            this.selection = selection;
        }

        public void setEngine(CSSEngine engine) {
            this.engine = engine;
        }

        @Override
        public void handleEvent(Event e) {
            Item selection = getSelection(e.widget);
            if (selection == null || selection.isDisposed() || this.selection == selection) {
                return;
            }
            Item[] items = getItems(e.widget);
            int selectionIndex = getSelectionIndex(e.widget);
            boolean selectionSet = false;
            CSSStyleDeclaration selectedStyle = engine.getViewCSS().getComputedStyle(engine.getElement(selection), "selected");
            if (selectedStyle != null) {
                CSSValue value = selectedStyle.getPropertyCSSValue("show-close");
                if (value != null) {
                    setShowClose(selection, Boolean.parseBoolean(value.getCssText()));
                    selectionSet = true;
                }
            }
            CSSStyleDeclaration unselectedStyle = engine.getViewCSS().getComputedStyle(engine.getElement(selection), null);
            if (unselectedStyle == null) {
                for (int i = 0; i < items.length; i++) {
                    if (selectionSet && i != selectionIndex) {
                        setShowClose(items[i], false);
                    }
                }
            } else {
                CSSValue value = unselectedStyle.getPropertyCSSValue("show-close");
                boolean unselectedShowClose = value == null ? false : Boolean.parseBoolean(value.getCssText());
                for (int i = 0; i < items.length; i++) {
                    if (selectionSet && i != selectionIndex) {
                        setShowClose(items[i], unselectedShowClose);
                    }
                }
            }
            this.selection = selection;
        }
    }
}
