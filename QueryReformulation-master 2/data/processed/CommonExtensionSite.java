/***/
package org.eclipse.ui.internal.navigator.extensions;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
*
* Provides a common base class for {@link ICommonContentExtensionSite} and
* {@link ICommonActionExtensionSite}.
*
* @since 3.2
*
*/
public class CommonExtensionSite {

    private final INavigatorContentService contentService;

    private IExtensionStateModel extensionStateModel;

    protected  CommonExtensionSite(INavigatorContentService aContentService, String anExtensionId) {
        Assert.isNotNull(aContentService);
        contentService = aContentService;
        if (anExtensionId != null) {
            extensionStateModel = aContentService.findStateModel(anExtensionId);
        }
    }

    /**
*
* @return The content service used to create this extension site
*/
    public final INavigatorContentService getContentService() {
        return contentService;
    }

    /**
* By default, the extension state model returned is for the associated
* content extension (if this is NOT a top-level action provider).
* Otherwise, clients may use
* {@link INavigatorContentService#findStateModel(String)} to locate the
* state model of another content extension.
*
* @return The extension state model of the associated extension.
* @see IExtensionStateModel
*/
    public final IExtensionStateModel getExtensionStateModel() {
        return extensionStateModel;
    }
}
