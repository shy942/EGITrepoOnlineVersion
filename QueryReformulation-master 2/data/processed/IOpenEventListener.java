/***/
package org.eclipse.jface.util;

import org.eclipse.swt.events.SelectionEvent;

/**
* Listener for open events which are generated on selection
* of default selection depending on the user preferences.
*
* <p>
* Usage:
* <pre>
*	OpenStrategy handler = new OpenStrategy(control);
*	handler.addOpenListener(new IOpenEventListener() {
*		public void handleOpen(SelectionEvent e) {
*			... // code to handle the open event.
*		}
*	});
* </pre>
* </p>
*
* @see OpenStrategy
*/
@FunctionalInterface
public interface IOpenEventListener {

    /**
* Called when a selection or default selection occurs
* depending on the user preference.
* @param e the selection event
*/
    public void handleOpen(SelectionEvent e);
}
