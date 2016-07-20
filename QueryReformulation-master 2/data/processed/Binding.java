/***/
package org.eclipse.ui.internal.navigator.extensions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.internal.navigator.CommonNavigatorMessages;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.internal.navigator.Policy;

class Binding {

    private final Set<Pattern> rootPatterns = new HashSet<Pattern>();

    private final Set<Pattern> includePatterns = new HashSet<Pattern>();

    private final Set<Pattern> excludePatterns = new HashSet<Pattern>();

    private final String TAG_EXTENSION;

    private final Map<String, Boolean> knownIds = new HashMap<String, Boolean>();

    private final Map<String, Boolean> knownRootIds = new HashMap<String, Boolean>();

    protected  Binding(String tagExtension) {
        TAG_EXTENSION = tagExtension;
    }

    boolean isVisibleExtension(String anExtensionId) {
        // Have we seen this pattern before?
        if (knownIds.containsKey(anExtensionId)) {
            // we have, don't recompute
            return knownIds.get(anExtensionId).booleanValue();
        }
        for (Iterator<Pattern> itr = excludePatterns.iterator(); itr.hasNext(); ) {
            Pattern pattern = itr.next();
            if (pattern.matcher(anExtensionId).matches()) {
                knownIds.put(anExtensionId, Boolean.FALSE);
                if (Policy.DEBUG_RESOLUTION) {
                    System.out.println(//$NON-NLS-1$
                    "Viewer Binding: EXCLUDED: " + TAG_EXTENSION + " to: " + //$NON-NLS-1$
                    anExtensionId);
                }
                return false;
            }
        }
        for (Iterator<Pattern> itr = includePatterns.iterator(); itr.hasNext(); ) {
            Pattern pattern = itr.next();
            if (pattern.matcher(anExtensionId).matches()) {
                // keep track of the result for next time
                knownIds.put(anExtensionId, Boolean.TRUE);
                if (Policy.DEBUG_RESOLUTION) {
                    System.out.println(//$NON-NLS-1$
                    "Viewer Binding: " + TAG_EXTENSION + " to: " + //$NON-NLS-1$
                    anExtensionId);
                }
                return true;
            }
        }
        if (Policy.DEBUG_RESOLUTION) {
            System.out.println(//$NON-NLS-1$
            "Viewer Binding: NOT FOUND: " + TAG_EXTENSION + " to: " + //$NON-NLS-1$
            anExtensionId);
        }
        knownIds.put(anExtensionId, Boolean.FALSE);
        return false;
    }

    boolean isRootExtension(String anExtensionId) {
        if (rootPatterns.size() == 0) {
            return false;
        }
        // Have we seen this pattern before?
        if (knownRootIds.containsKey(anExtensionId)) {
            // we have, don't recompute
            return knownRootIds.get(anExtensionId).booleanValue();
        }
        Pattern pattern = null;
        for (Iterator<Pattern> itr = rootPatterns.iterator(); itr.hasNext(); ) {
            pattern = itr.next();
            if (pattern.matcher(anExtensionId).matches()) {
                knownRootIds.put(anExtensionId, Boolean.TRUE);
                return true;
            }
        }
        knownRootIds.put(anExtensionId, Boolean.FALSE);
        return false;
    }

    boolean hasOverriddenRootExtensions() {
        return rootPatterns.size() > 0;
    }

    void consumeIncludes(IConfigurationElement element, boolean toRespectRoots) {
        Assert.isTrue(NavigatorViewerDescriptor.TAG_INCLUDES.equals(element.getName()));
        IConfigurationElement[] contentExtensionPatterns = element.getChildren(TAG_EXTENSION);
        String isRootString = null;
        boolean isRoot = false;
        String patternString = null;
        Pattern compiledPattern = null;
        for (int i = 0; i < contentExtensionPatterns.length; i++) {
            if (toRespectRoots) {
                isRootString = contentExtensionPatterns[i].getAttribute(NavigatorViewerDescriptor.ATT_IS_ROOT);
                isRoot = (isRootString != null) ? Boolean.valueOf(isRootString.trim()).booleanValue() : false;
            }
            patternString = contentExtensionPatterns[i].getAttribute(NavigatorViewerDescriptor.ATT_PATTERN);
            if (patternString == null) {
                NavigatorPlugin.logError(0, NLS.bind(CommonNavigatorMessages.Attribute_Missing_Warning, new Object[] { NavigatorViewerDescriptor.ATT_PATTERN, element.getDeclaringExtension().getUniqueIdentifier(), element.getDeclaringExtension().getNamespaceIdentifier() }), null);
            } else {
                compiledPattern = Pattern.compile(patternString);
                includePatterns.add(compiledPattern);
                // Cache is now invlaid
                knownIds.clear();
                if (toRespectRoots && isRoot) {
                    rootPatterns.add(compiledPattern);
                }
            }
        }
    }

    void consumeExcludes(IConfigurationElement element) {
        Assert.isTrue(NavigatorViewerDescriptor.TAG_EXCLUDES.equals(element.getName()));
        IConfigurationElement[] contentExtensionPatterns = element.getChildren(TAG_EXTENSION);
        String patternString = null;
        Pattern compiledPattern = null;
        for (int i = 0; i < contentExtensionPatterns.length; i++) {
            patternString = contentExtensionPatterns[i].getAttribute(NavigatorViewerDescriptor.ATT_PATTERN);
            if (patternString == null) {
                NavigatorPlugin.logError(0, NLS.bind(CommonNavigatorMessages.Attribute_Missing_Warning, new Object[] { NavigatorViewerDescriptor.ATT_PATTERN, element.getDeclaringExtension().getUniqueIdentifier(), element.getDeclaringExtension().getNamespaceIdentifier() }), null);
            } else {
                compiledPattern = Pattern.compile(patternString);
                excludePatterns.add(compiledPattern);
                // Clear the cache
                knownIds.clear();
            }
        }
    }

    void addBinding(Binding otherBinding) {
        includePatterns.addAll(otherBinding.includePatterns);
        excludePatterns.addAll(otherBinding.excludePatterns);
        rootPatterns.addAll(otherBinding.rootPatterns);
    }
}
