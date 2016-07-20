/***/
package org.eclipse.ui.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.services.IDisposable;

/**
* @since 3.4
*
*/
public class SlaveSelectionService implements ISelectionService, IDisposable {

    private ListenerList<ISelectionListener> postListeners = new ListenerList(ListenerList.IDENTITY);

    private ListenerList<ISelectionListener> listeners = new ListenerList(ListenerList.IDENTITY);

    private Map<ISelectionListener, String> listenersToPartId = new HashMap();

    private Map<ISelectionListener, String> postListenersToPartId = new HashMap();

    private ISelectionService parentSelectionService;

    /**
* @param parentSelectionService
*/
    public  SlaveSelectionService(ISelectionService parentSelectionService) {
        if (parentSelectionService == null) {
            throw new IllegalArgumentException(//$NON-NLS-1$
            "The parent selection service cannot be null");
        }
        this.parentSelectionService = parentSelectionService;
    }

    @Override
    public void addPostSelectionListener(ISelectionListener listener) {
        postListeners.add(listener);
        parentSelectionService.addPostSelectionListener(listener);
    }

    @Override
    public void addPostSelectionListener(String partId, ISelectionListener listener) {
        listenersToPartId.put(listener, partId);
        parentSelectionService.addPostSelectionListener(partId, listener);
    }

    @Override
    public void addSelectionListener(ISelectionListener listener) {
        listeners.add(listener);
        parentSelectionService.addSelectionListener(listener);
    }

    @Override
    public void addSelectionListener(String partId, ISelectionListener listener) {
        postListenersToPartId.put(listener, partId);
        parentSelectionService.addPostSelectionListener(partId, listener);
    }

    @Override
    public ISelection getSelection() {
        return parentSelectionService.getSelection();
    }

    @Override
    public ISelection getSelection(String partId) {
        return parentSelectionService.getSelection(partId);
    }

    @Override
    public void removePostSelectionListener(ISelectionListener listener) {
        postListeners.remove(listener);
        parentSelectionService.removePostSelectionListener(listener);
    }

    @Override
    public void removePostSelectionListener(String partId, ISelectionListener listener) {
        postListenersToPartId.remove(listener);
        parentSelectionService.removePostSelectionListener(partId, listener);
    }

    @Override
    public void removeSelectionListener(ISelectionListener listener) {
        listeners.remove(listener);
        parentSelectionService.removeSelectionListener(listener);
    }

    @Override
    public void removeSelectionListener(String partId, ISelectionListener listener) {
        listenersToPartId.remove(listener);
        parentSelectionService.removeSelectionListener(partId, listener);
    }

    @Override
    public void dispose() {
        Object list[] = listeners.getListeners();
        for (int i = 0; i < list.length; i++) {
            parentSelectionService.removeSelectionListener((ISelectionListener) list[i]);
        }
        listeners.clear();
        list = postListeners.getListeners();
        for (int i = 0; i < list.length; i++) {
            parentSelectionService.removePostSelectionListener((ISelectionListener) list[i]);
        }
        postListeners.clear();
        for (Entry<ISelectionListener, String> entry : listenersToPartId.entrySet()) {
            parentSelectionService.removeSelectionListener(entry.getValue(), entry.getKey());
        }
        listenersToPartId.clear();
        for (Entry<ISelectionListener, String> entry : postListenersToPartId.entrySet()) {
            parentSelectionService.removePostSelectionListener(entry.getValue(), entry.getKey());
        }
        postListenersToPartId.clear();
    }
}
