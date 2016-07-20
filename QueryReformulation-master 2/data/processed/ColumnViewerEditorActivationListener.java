/***/
package org.eclipse.jface.viewers;

/**
* Parties interested in activation and deactivation of editors extend this
* class and implement any or all of the methods
*
* @since 3.3
*
*/
public abstract class ColumnViewerEditorActivationListener {

    /**
* Called before an editor is activated
*
* @param event
*            the event
*/
    public abstract void beforeEditorActivated(ColumnViewerEditorActivationEvent event);

    /**
* Called after an editor has been activated
*
* @param event the event
*/
    public abstract void afterEditorActivated(ColumnViewerEditorActivationEvent event);

    /**
* Called before an editor is deactivated
*
* @param event
*            the event
*/
    public abstract void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event);

    /**
* Called after an editor is deactivated
*
* @param event the event
*/
    public abstract void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event);
}
