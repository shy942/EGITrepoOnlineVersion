/***/
package org.eclipse.ui.tests.views.properties.tabbed.override;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;

/**
* The selection provider for the override tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class OverrideTestsSelectionProvider implements ISelectionProvider {

    private ListenerList selectionChangedListeners = new ListenerList();

    private final TableViewer viewer;

    /**
* Constructor for OverrideTestsSelectionProvider
*
* @param aViewer
*            the viewer in the OverrideTestsView.
*/
     OverrideTestsSelectionProvider(TableViewer aViewer) {
        this.viewer = aViewer;
    }

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.add(listener);
    }

    @Override
    public ISelection getSelection() {
        ISelection selection = viewer.getSelection();
        IStructuredSelection structuredSelection = (IStructuredSelection) selection;
        if (structuredSelection.isEmpty()) {
            return new OverrideTestsSelection(null);
        }
        Element element = (Element) structuredSelection.getFirstElement();
        return new OverrideTestsSelection(element);
    }

    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.remove(listener);
    }

    /**
* Notify the selection changed listeners that a selection change has
* occurred.
*
* @param event
*            the selection change event.
*/
    public void selectionChanged(final SelectionChangedEvent event) {
        // pass on the notification to listeners
        Object[] listeners = selectionChangedListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
            l.selectionChanged(event);
        }
    }

    @Override
    public void setSelection(ISelection selection) {
        viewer.setSelection(selection);
    }
}
