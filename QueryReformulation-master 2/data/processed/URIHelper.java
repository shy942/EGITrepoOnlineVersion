/***/
package org.eclipse.e4.ui.internal.workbench;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.service.log.LogService;

/**
* Collection of URI-related utilities
*/
public class URIHelper {

    /**
* The schema identifier used for Eclipse platform references
*/
    //$NON-NLS-1$
    private static final String PLATFORM_SCHEMA = "platform:/";

    /**
* The schema identifier used for Eclipse bundlesclass reference
*/
    //$NON-NLS-1$
    private static final String BUNDLECLASS_SCHEMA = "bundleclass://";

    /**
* The schema identifier used for EMF platform references
*/
    //$NON-NLS-1$
    private static final String PLATFORM_SCHEMA_EMF = "platform";

    /**
* The segment used to specify location in a plugin
*/
    //$NON-NLS-1$
    private static final String PLUGIN_SEGMENT = "plugin/";

    /**
* The segment used to specify location in a fragment
*/
    //$NON-NLS-1$
    private static final String FRAGMENT_SEGMENT = "fragment/";

    public static String constructPlatformURI(Bundle bundle) {
        BundleRevision bundleRevision = bundle.adapt(BundleRevision.class);
        if (bundleRevision == null)
            return null;
        StringBuffer tmp = new StringBuffer();
        tmp.append(PLATFORM_SCHEMA);
        if ((bundleRevision.getTypes() & BundleRevision.TYPE_FRAGMENT) == BundleRevision.TYPE_FRAGMENT)
            tmp.append(FRAGMENT_SEGMENT);
        else
            tmp.append(PLUGIN_SEGMENT);
        tmp.append(bundle.getSymbolicName());
        return tmp.toString();
    }

    public static String constructPlatformURI(IContributor contributor) {
        // registry contributors are singletons
        String bundleName;
        if (contributor instanceof RegistryContributor)
            bundleName = ((RegistryContributor) contributor).getActualName();
        else
            // should not happen for the standard registry, but try to make a best guess
            bundleName = contributor.getName();
        Bundle bundle = Activator.getDefault().getBundleForName(bundleName);
        return constructPlatformURI(bundle);
    }

    public static Bundle getBundle(String contributorURI) {
        URI uri;
        try {
            uri = new URI(contributorURI);
        } catch (URISyntaxException e) {
            Activator.log(LogService.LOG_ERROR, "Invalid contributor URI: " + contributorURI);
            return null;
        }
        if (!PLATFORM_SCHEMA.equals(uri.getScheme()))
            // not implemented
            return null;
        return Activator.getDefault().getBundleForName(uri.getPath());
    }

    public static String EMFtoPlatform(org.eclipse.emf.common.util.URI uri) {
        if (!PLATFORM_SCHEMA_EMF.equals(uri.scheme()))
            return null;
        // remove all segments but first two - only need bundle/fragment name
        int segments = uri.segmentCount();
        // segments: { "plugin", "org.eclipse.platform", "myDir", "model.e4xmi" }
        if (segments > 2)
            uri = uri.trimSegments(segments - 2);
        return uri.toString();
    }

    /**
* Helper method which checks if given String represents a Platform URI.
*
* @param uri
*            a possible Platform URI
* @return true if the given string is not {@code null} and starts with
*         {@value #PLATFORM_SCHEMA}; false otherwise
*/
    public static boolean isPlatformURI(String uri) {
        return uri != null && uri.startsWith(PLATFORM_SCHEMA);
    }

    /**
* Helper method which checks if given String represents a Bundleclass URI.
*
* @param uri
*            a possible Bundleclass URI
* @return true if the given string is not {@code null} and starts with
*         {@value #BUNDLECLASS_SCHEMA}; false otherwise
*/
    public static boolean isBundleClassUri(String uri) {
        if (uri != null && uri.startsWith(BUNDLECLASS_SCHEMA)) {
            //$NON-NLS-1$
            String[] split = uri.substring(BUNDLECLASS_SCHEMA.length()).split("/");
            // segments: { "bundle-symbolic-name", "fully qualified classname"}
            if (split.length == 2) {
                return true;
            }
        }
        return false;
    }
}
