/***/
package org.eclipse.e4.ui.workbench;

import org.eclipse.emf.common.util.URI;

/**
* This interface describes a utility that is used to load ImageDesc's from {@link URI}s
*
* @param <ImageDesc>
*
* @noimplement This interface is not intended to be implemented by clients.
* @since 1.0
*/
public interface IResourceUtilities<ImageDesc> {

    /**
* Loads an ImageDesc from the given {@link URI}.
*
* @param iconPath
* @return the ImageDesc from the given URI or <code>null</code>
*/
    public ImageDesc imageDescriptorFromURI(URI iconPath);
}
