/***/
package org.eclipse.ui.forms.events;

/**
* Classes that implement this interface will be notified before and after the
* expandable control's expansion state changes.
*
* @since 3.0
*/
public interface IExpansionListener {

    /**
* Notifies the listener that the expandable control is about to change its
* expansion state. The state provided by the event is the new state.
*
* @param e
*            the expansion event
*/
    void expansionStateChanging(ExpansionEvent e);

    /**
* Notifies the listener after the expandable control has changed its
* expansion state. The state provided by the event is the new state.
*
* @param e
*            the expansion event
*/
    void expansionStateChanged(ExpansionEvent e);
}
