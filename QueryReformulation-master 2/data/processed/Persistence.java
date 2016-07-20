/***/
package org.eclipse.ui.internal.activities;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.util.ConfigurationElementMemento;

public final class Persistence {

    //$NON-NLS-1$
    static final String PACKAGE_BASE = "activities";

    //$NON-NLS-1$
    static final String PACKAGE_FULL = "org.eclipse.ui.activities";

    //$NON-NLS-1$
    static final String PACKAGE_PREFIX = "org.eclipse.ui";

    //$NON-NLS-1$
    static final String TAG_ACTIVITY = "activity";

    //$NON-NLS-1$
    static final String TAG_ACTIVITY_REQUIREMENT_BINDING = "activityRequirementBinding";

    //$NON-NLS-1$
    static final String TAG_DEFAULT_ENABLEMENT = "defaultEnablement";

    //$NON-NLS-1$
    static final String TAG_ACTIVITY_ID = "activityId";

    //$NON-NLS-1$
    static final String TAG_ACTIVITY_PATTERN_BINDING = "activityPatternBinding";

    //$NON-NLS-1$
    static final String TAG_CATEGORY = "category";

    //$NON-NLS-1$
    static final String TAG_CATEGORY_ACTIVITY_BINDING = "categoryActivityBinding";

    //$NON-NLS-1$
    static final String TAG_CATEGORY_ID = "categoryId";

    //$NON-NLS-1$
    static final String TAG_REQUIRED_ACTIVITY_ID = "requiredActivityId";

    //$NON-NLS-1$
    static final String TAG_ID = "id";

    //$NON-NLS-1$
    static final String TAG_NAME = "name";

    //$NON-NLS-1$
    static final String TAG_PATTERN = "pattern";

    //$NON-NLS-1$
    static final String TAG_IS_EQUALITY_PATTERN = "isEqualityPattern";

    //$NON-NLS-1$
    static final String TAG_SOURCE_ID = "sourceId";

    //$NON-NLS-1$
    static final String TAG_DESCRIPTION = "description";

    // Used only in error messages addressed to plug-in developers
    //$NON-NLS-1$
    public static final String ACTIVITY_REQUIREMENT_BINDING_DESC = "Invalid activity requirement binding";

    //$NON-NLS-1$
    public static final String ACTIVITY_DESC = "Invalid activity";

    //$NON-NLS-1$
    public static final String ACTIVITY_PATTERN_BINDING_DESC = "Invalid activity pattern binding";

    //$NON-NLS-1$
    public static final String CATEGORY_ACTIVITY_BINDING_DESC = "Invalid category activity binding";

    //$NON-NLS-1$
    public static final String CATEGORY_DESC = "Invalid category description";

    //$NON-NLS-1$
    public static final String ACTIVITY_IMAGE_BINDING_DESC = "Invalid activity image binding";

    //$NON-NLS-1$
    public static final String ACTIVITY_TRIGGER_DESC = "Invalid trigger point";

    //$NON-NLS-1$
    public static final String ACTIVITY_TRIGGER_HINT_DESC = "Invalid trigger point hint";

    // Non-translatable error messages for plug-in developers
    //$NON-NLS-1$;
    public static final String shortContextTemplate = " (contributed by ''{0}'')";

    //$NON-NLS-1$;
    public static final String fullContextTemplate = " (contributed by ''{0}'', extension ID ''{1}'')";

    static ActivityRequirementBindingDefinition readActivityRequirementBindingDefinition(IMemento memento, String sourceIdOverride) {
        //, IStatus status) {
        String childActivityId = memento.getString(TAG_REQUIRED_ACTIVITY_ID);
        if (childActivityId == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_REQUIREMENT_BINDING_DESC, "missing ID of the required activity");
            return null;
        }
        String parentActivityId = memento.getString(TAG_ACTIVITY_ID);
        if (parentActivityId == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_REQUIREMENT_BINDING_DESC, "missing ID of the activity to bind");
            return null;
        }
        String sourceId = sourceIdOverride != null ? sourceIdOverride : memento.getString(TAG_SOURCE_ID);
        return new ActivityRequirementBindingDefinition(childActivityId, parentActivityId, sourceId);
    }

    static String readDefaultEnablement(IMemento memento) {
        return memento.getString(TAG_ID);
    }

    static ActivityDefinition readActivityDefinition(IMemento memento, String sourceIdOverride) {
        String id = memento.getString(TAG_ID);
        if (id == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_DESC, "missing a unique identifier");
            return null;
        }
        String name = memento.getString(TAG_NAME);
        if (name == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_DESC, "missing a translatable name");
            return null;
        }
        String description = memento.getString(TAG_DESCRIPTION);
        if (description == null) {
            //$NON-NLS-1$
            description = "";
        }
        String sourceId = sourceIdOverride != null ? sourceIdOverride : memento.getString(TAG_SOURCE_ID);
        return new ActivityDefinition(id, name, sourceId, description);
    }

    static ActivityPatternBindingDefinition readActivityPatternBindingDefinition(IMemento memento, String sourceIdOverride) {
        String activityId = memento.getString(TAG_ACTIVITY_ID);
        if (activityId == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_PATTERN_BINDING_DESC, "missing an ID of the activity to bind");
            return null;
        }
        String pattern = memento.getString(TAG_PATTERN);
        if (pattern == null) {
            //$NON-NLS-1$
            log(memento, ACTIVITY_PATTERN_BINDING_DESC, "missing the pattern to be bound");
            return null;
        }
        String sourceId = sourceIdOverride != null ? sourceIdOverride : memento.getString(TAG_SOURCE_ID);
        final String isEqualityPatternStr = memento.getString(TAG_IS_EQUALITY_PATTERN);
        final boolean isEqualityPattern = (isEqualityPatternStr != null && isEqualityPatternStr.equals(//$NON-NLS-1$
        "true"));
        return new ActivityPatternBindingDefinition(activityId, pattern, sourceId, isEqualityPattern);
    }

    static CategoryActivityBindingDefinition readCategoryActivityBindingDefinition(IMemento memento, String sourceIdOverride) {
        String activityId = memento.getString(TAG_ACTIVITY_ID);
        if (activityId == null) {
            //$NON-NLS-1$
            log(memento, CATEGORY_ACTIVITY_BINDING_DESC, "missing the ID of the activity to bind");
            return null;
        }
        String categoryId = memento.getString(TAG_CATEGORY_ID);
        if (categoryId == null) {
            //$NON-NLS-1$
            log(memento, CATEGORY_ACTIVITY_BINDING_DESC, "missing the ID of the category to bind");
            return null;
        }
        String sourceId = sourceIdOverride != null ? sourceIdOverride : memento.getString(TAG_SOURCE_ID);
        return new CategoryActivityBindingDefinition(activityId, categoryId, sourceId);
    }

    static CategoryDefinition readCategoryDefinition(IMemento memento, String sourceIdOverride) {
        String id = memento.getString(TAG_ID);
        if (id == null) {
            //$NON-NLS-1$
            log(memento, CATEGORY_DESC, "has no ID");
            return null;
        }
        String name = memento.getString(TAG_NAME);
        if (name == null) {
            //$NON-NLS-1$
            log(memento, CATEGORY_DESC, "missing a translatable name");
            return null;
        }
        String description = memento.getString(TAG_DESCRIPTION);
        if (description == null) {
            //$NON-NLS-1$
            description = "";
        }
        String sourceId = sourceIdOverride != null ? sourceIdOverride : memento.getString(TAG_SOURCE_ID);
        return new CategoryDefinition(id, name, sourceId, description);
    }

    private  Persistence() {
    //no-op
    }

    public static void log(IMemento memento, String elementName, String msg) {
        if (memento instanceof ConfigurationElementMemento) {
            ConfigurationElementMemento cMemento = (ConfigurationElementMemento) memento;
            log(elementName, msg, cMemento.getContributorName(), cMemento.getExtensionID());
        } else
            log(elementName, msg, null, null);
    }

    public static void log(IConfigurationElement element, String elementName, String msg) {
        String contributorName = element.getContributor().getName();
        String extensionID = element.getDeclaringExtension().getUniqueIdentifier();
        log(elementName, msg, contributorName, extensionID);
    }

    public static void log(String elementName, String msg, String contributorName, String extensionID) {
        //$NON-NLS-1$;
        String msgInContext = elementName + ": " + msg;
        if (contributorName != null && extensionID != null)
            msgInContext += NLS.bind(fullContextTemplate, contributorName, extensionID);
        else if (contributorName != null)
            msgInContext += NLS.bind(shortContextTemplate, contributorName);
        WorkbenchPlugin.log(msgInContext);
    }
}
