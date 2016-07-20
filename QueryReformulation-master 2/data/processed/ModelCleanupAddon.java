/***/
package org.eclipse.ui.internal.ide.application.addons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.URIHelper;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.UIEvents.UILifeCycle;
import org.eclipse.ui.internal.registry.ViewRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.namespace.HostNamespace;
import org.osgi.framework.namespace.IdentityNamespace;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.framework.wiring.FrameworkWiring;
import org.osgi.resource.Namespace;
import org.osgi.resource.Requirement;
import org.osgi.resource.Resource;
import org.osgi.service.event.Event;

/**
* The model-addon searches for model contributions in the runtime application
* and removes elements for which the classes cannot be accessed anymore.
* Currently it only covered part descriptors but it is planned to extend this
* addon to also remove other broken model contributions
*/
@SuppressWarnings("restriction")
public class ModelCleanupAddon {

    /**
* See URIHelper#BUNDLECLASS_SCHEMA constant.
*/
    private static final int BUNDLECLASS_SCHEMA_LENGTH = 14;

    //$NON-NLS-1$
    private static String COMPATIBILITY_EDITOR_URI = "bundleclass://org.eclipse.ui.workbench/org.eclipse.ui.internal.e4.compatibility.CompatibilityEditor";

    //$NON-NLS-1$
    private static String COMPATIBILITY_VIEW_URI = "bundleclass://org.eclipse.ui.workbench/org.eclipse.ui.internal.e4.compatibility.CompatibilityView";

    @Inject
    @Optional
    private MApplication application;

    @Inject
    @Optional
    private Logger logger;

    /**
* This addon listens to the {@link UILifeCycle#APP_STARTUP_COMPLETE} event.
*
* @param event
*            {@link Event}
*/
    @Inject
    @Optional
    public void applicationStartUp(@EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
        List<MPartDescriptor> descriptors = application.getDescriptors();
        Bundle bundle = FrameworkUtil.getBundle(getClass());
        for (Iterator<MPartDescriptor> iterator = descriptors.iterator(); iterator.hasNext(); ) {
            MPartDescriptor partDescriptor = iterator.next();
            boolean validPartDescriptor = isValidPartDescriptor(bundle, partDescriptor);
            if (!validPartDescriptor) {
                logger.warn(//$NON-NLS-1$
                "Removing part descriptor with the '" + partDescriptor.getElementId() + "' id and the '" + //$NON-NLS-1$
                partDescriptor.getLocalizedLabel() + "' description. Points to the invalid '" + partDescriptor.getContributionURI() + //$NON-NLS-1$ //$NON-NLS-2$
                "' class.");
                iterator.remove();
            }
        }
    }

    private boolean isValidPartDescriptor(Bundle bundle, MPartDescriptor partDescriptor) {
        String contributionURI = partDescriptor.getContributionURI();
        if (!URIHelper.isBundleClassUri(contributionURI)) {
            return false;
        }
        String originalCompatibilityViewClass = partDescriptor.getPersistedState().get(ViewRegistry.ORIGINAL_COMPATIBILITY_VIEW_CLASS);
        // See createDescriptor method of the ViewRegistry
        if (COMPATIBILITY_VIEW_URI.equals(contributionURI) && originalCompatibilityViewClass != null) {
            String originalCompatibilityViewBundle = partDescriptor.getPersistedState().get(ViewRegistry.ORIGINAL_COMPATIBILITY_VIEW_BUNDLE);
            return checkPartDescriptorByBundleSymbolicNameAndClass(bundle, originalCompatibilityViewBundle, originalCompatibilityViewClass);
        } else if (!COMPATIBILITY_EDITOR_URI.equals(contributionURI)) {
            // check for e4views and usual MPartDescriptors
            //$NON-NLS-1$
            String[] bundleClass = contributionURI.substring(BUNDLECLASS_SCHEMA_LENGTH).split("/");
            String bundleSymbolicName = bundleClass[0];
            String className = bundleClass[1];
            return checkPartDescriptorByBundleSymbolicNameAndClass(bundle, bundleSymbolicName, className);
        }
        return true;
    }

    private boolean checkPartDescriptorByBundleSymbolicNameAndClass(Bundle bundle, String bundleSymbolicName, String className) {
        Collection<BundleWiring> wirings = findWirings(bundleSymbolicName, bundle.getBundleContext());
        if (!isPartDescriptorClassAvailable(wirings, className)) {
            // or if the class cannot be found
            return false;
        }
        return true;
    }

    private boolean isPartDescriptorClassAvailable(Collection<BundleWiring> wirings, String className) {
        if (wirings.isEmpty()) {
            return false;
        }
        String classPackageName;
        String classResourceName;
        int indexLastDot = className.lastIndexOf('.');
        if (indexLastDot < 0) {
            //$NON-NLS-1$
            classPackageName = "/";
            classResourceName = className;
        } else {
            classPackageName = '/' + className.substring(0, indexLastDot).replace('.', '/');
            //$NON-NLS-1$
            classResourceName = className.substring(indexLastDot + 1) + ".class";
        }
        for (BundleWiring bundleWiring : wirings) {
            if (!checkClassResource(classPackageName, classResourceName, bundleWiring)) {
                return false;
            }
        }
        return true;
    }

    private Collection<BundleWiring> findWirings(final String bundleSymbolicName, BundleContext bundleContext) {
        Requirement req = new Requirement() {

            @Override
            public Resource getResource() {
                // no resource
                return null;
            }

            @Override
            public String getNamespace() {
                return IdentityNamespace.IDENTITY_NAMESPACE;
            }

            @Override
            public Map<String, String> getDirectives() {
                return Collections.singletonMap(Namespace.REQUIREMENT_FILTER_DIRECTIVE, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                "(" + IdentityNamespace.IDENTITY_NAMESPACE + "=" + bundleSymbolicName + ")");
            }

            @Override
            public Map<String, Object> getAttributes() {
                return Collections.emptyMap();
            }
        };
        Collection<BundleCapability> identities = bundleContext.getBundle(Constants.SYSTEM_BUNDLE_LOCATION).adapt(FrameworkWiring.class).findProviders(req);
        // normally
        Collection<BundleWiring> result = new ArrayList<BundleWiring>(1);
        // one
        for (BundleCapability identity : identities) {
            BundleRevision revision = identity.getRevision();
            BundleWiring wiring = revision.getWiring();
            if (wiring != null) {
                if ((revision.getTypes() & BundleRevision.TYPE_FRAGMENT) != 0) {
                    // fragment case; need to get the host wiring
                    wiring = wiring.getRequiredWires(HostNamespace.HOST_NAMESPACE).get(0).getProviderWiring();
                }
                result.add(wiring);
            }
        }
        return result;
    }

    private boolean checkClassResource(String classPackageName, String classFileName, BundleWiring wiring) {
        if (wiring == null) {
            return false;
        }
        if ((wiring.getRevision().getTypes() & BundleRevision.TYPE_FRAGMENT) != 0) {
            // fragment case; need to get the host wiring
            wiring = wiring.getRequiredWires(HostNamespace.HOST_NAMESPACE).get(0).getProviderWiring();
        }
        Collection<String> classResourcePaths = wiring.listResources(classPackageName, classFileName, 0);
        return classResourcePaths != null && !classResourcePaths.isEmpty();
    }
}
