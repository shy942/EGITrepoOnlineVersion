/***/
package org.eclipse.jface.viewers;

import java.util.EventObject;

/**
* This event is fired when an editor deactivated
*
* @since 3.3
* @noextend This class is not intended to be subclassed by clients.
*
*/
public class ColumnViewerEditorDeactivationEvent extends EventObject {

    /**
*
*/
    private static final long serialVersionUID = 1L;

    /**
* The event type
* @since 3.4
*/
    public int eventType;

    /**
* Event when editor is canceled
* @since 3.4
*/
    public static final int EDITOR_CANCELED = 1;

    /**
* Event when editor is saved
* @since 3.4
*/
    public static final int EDITOR_SAVED = 2;

    /**
* @param source
*/
    public  ColumnViewerEditorDeactivationEvent(Object source) {
        super(source);
    }
}
