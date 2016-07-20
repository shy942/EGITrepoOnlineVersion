/***/
package org.eclipse.ui.internal.navigator;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.part.IPageSite;

/**
* Provides a delegate implementation of {@link ICommonViewerSite}.
*
* @since 3.2
*
*/
public class CommonViewerSiteIPageSiteDelegate implements ICommonViewerSite {

    private IPageSite pageSite;

    private String viewerId;

    /**
*
* @param aViewerId
* @param aPageSite
*/
    public  CommonViewerSiteIPageSiteDelegate(String aViewerId, IPageSite aPageSite) {
        viewerId = aViewerId;
        pageSite = aPageSite;
    }

    @Override
    public String getId() {
        return viewerId;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return Adapters.adapt(pageSite, adapter);
    }

    @Override
    public ISelectionProvider getSelectionProvider() {
        return pageSite.getSelectionProvider();
    }

    @Override
    public void setSelectionProvider(ISelectionProvider aSelectionProvider) {
        pageSite.setSelectionProvider(aSelectionProvider);
    }

    @Override
    public Shell getShell() {
        return pageSite.getShell();
    }
}
