/***/
package org.eclipse.ui.views.properties;

import org.eclipse.ui.PlatformUI;

/*package*/
interface IPropertiesHelpContextIds {

    //$NON-NLS-1$
    public static final String PREFIX = PlatformUI.PLUGIN_ID + ".";

    // Actions
    public static final String CATEGORIES_ACTION = PREFIX + //$NON-NLS-1$
    "properties_categories_action_context";

    public static final String DEFAULTS_ACTION = PREFIX + //$NON-NLS-1$
    "properties_defaults_action_context";

    public static final String FILTER_ACTION = PREFIX + //$NON-NLS-1$
    "properties_filter_action_context";

    public static final String COPY_PROPERTY_ACTION = PREFIX + //$NON-NLS-1$
    "properties_copy_action_context";

    public static final String PIN_ACTION = PREFIX + //$NON-NLS-1$;
    "properties_pin_action_context";

    // Views
    public static final String PROPERTY_SHEET_VIEW = PREFIX + //$NON-NLS-1$
    "property_sheet_view_context";
}
