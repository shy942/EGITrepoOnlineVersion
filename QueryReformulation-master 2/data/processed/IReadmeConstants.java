/***/
package org.eclipse.ui.examples.readmetool;

/**
* This interface contains constants for use only within the
* Readme example.
*/
public interface IReadmeConstants {

    //$NON-NLS-1$
    public static final String PLUGIN_ID = "org.eclipse.ui.examples.readmetool";

    //$NON-NLS-1$
    public static final String PREFIX = PLUGIN_ID + ".";

    //$NON-NLS-1$
    public static final String P_CONTENT_OUTLINE = PREFIX + "content_outline";

    //$NON-NLS-1$
    public static final String P_SECTIONS = PREFIX + "sections";

    //$NON-NLS-1$
    public static final String EXTENSION = "readme";

    //$NON-NLS-1$
    public static final String TAG_PARSER = "parser";

    //$NON-NLS-1$
    public static final String ATT_CLASS = "class";

    //$NON-NLS-1$
    public static final String PP_SECTION_PARSER = "sectionParser";

    // Global actions
    //$NON-NLS-1$
    public static final String RETARGET2 = PREFIX + "retarget2";

    //$NON-NLS-1$
    public static final String LABELRETARGET3 = PREFIX + "labelretarget3";

    //$NON-NLS-1$
    public static final String ACTION_SET_RETARGET4 = "org_eclipse_ui_examples_readmetool_readmeRetargetAction";

    //$NON-NLS-1$
    public static final String ACTION_SET_LABELRETARGET5 = "org_eclipse_ui_examples_readmetool_readmeRelabelRetargetAction";

    // Preference constants
    //$NON-NLS-1$
    public static final String PRE_CHECK1 = PREFIX + "check1";

    //$NON-NLS-1$
    public static final String PRE_CHECK2 = PREFIX + "check2";

    //$NON-NLS-1$
    public static final String PRE_CHECK3 = PREFIX + "check3";

    //$NON-NLS-1$
    public static final String PRE_RADIO_CHOICE = PREFIX + "radio_choice";

    //$NON-NLS-1$
    public static final String PRE_TEXT = PREFIX + "text";

    // Help context ids
    public static final String EDITOR_ACTION1_CONTEXT = PREFIX + //$NON-NLS-1$
    "editor_action1_context";

    public static final String EDITOR_ACTION2_CONTEXT = PREFIX + //$NON-NLS-1$
    "editor_action2_context";

    public static final String EDITOR_ACTION3_CONTEXT = PREFIX + //$NON-NLS-1$
    "editor_action3_context";

    public static final String SECTIONS_VIEW_CONTEXT = PREFIX + //$NON-NLS-1$
    "sections_view_context";

    public static final String PREFERENCE_PAGE_CONTEXT = PREFIX + //$NON-NLS-1$
    "preference_page_context";

    public static final String PROPERTY_PAGE_CONTEXT = PREFIX + //$NON-NLS-1$
    "property_page_context";

    public static final String PROPERTY_PAGE2_CONTEXT = PREFIX + //$NON-NLS-1$
    "property_page2_context";

    //$NON-NLS-1$
    public static final String EDITOR_CONTEXT = PREFIX + "editor_context";

    public static final String SECTIONS_DIALOG_CONTEXT = PREFIX + //$NON-NLS-1$
    "sections_dialog_context";

    public static final String CONTENT_OUTLINE_PAGE_CONTEXT = PREFIX + //$NON-NLS-1$
    "content_outline_page_context";

    public static final String CREATION_WIZARD_PAGE_CONTEXT = PREFIX + //$NON-NLS-1$
    "creation_wizard_page_context";

    // Marker attributes
    //$NON-NLS-1$
    public static final String MARKER_ATT_ID = PREFIX + "id";

    //$NON-NLS-1$
    public static final String MARKER_ATT_LEVEL = PREFIX + "level";

    //$NON-NLS-1$
    public static final String MARKER_ATT_DEPT = PREFIX + "department";

    //$NON-NLS-1$
    public static final String MARKER_ATT_CODE = PREFIX + "code";

    //$NON-NLS-1$
    public static final String MARKER_ATT_LANG = PREFIX + "language";
}
