/***/
package org.eclipse.ui;

/**
* Listener for events fired by implementers of {@link ISaveablesSource}.
*
* <p>
* This service can be acquired from a part's service locator:
*
* <pre>
* ISaveablesLifecycleListener listener = (ISaveablesLifecycleListener) getSite()
* 		.getService(ISaveablesLifecycleListener.class);
* </pre>
*
* or, in the case of implementers of {@link ISaveablesSource} that are not a
* part, from the workbench:
*
* <pre>
* ISaveablesLifecycleListener listener = (ISaveablesLifecycleListener) workbench
* 		.getService(ISaveablesLifecycleListener.class);
* </pre>
*
* <ul>
* <li>This service is available globally.</li>
* </ul>
* </p>
*
* @since 3.2
*/
public interface ISaveablesLifecycleListener {

    /**
* Handle the given event. This method must be called on the UI thread.
*
* @param event
*/
    public void handleLifecycleEvent(SaveablesLifecycleEvent event);
}
