/***/
package org.eclipse.ui.internal.registry;

import org.eclipse.ui.PlatformUI;

/**
* Interface containing various registry constants (tag and attribute names).
*
* @since 3.1
*/
public interface IWorkbenchRegistryConstants {

    /**
* Accelerator attribute. Value <code>accelerator</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ACCELERATOR = "accelerator";

    /**
* Adaptable attribute. Value <code>adaptable</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ADAPTABLE = "adaptable";

    /**
* Advisor id attribute. Value <code>triggerPointAdvisorId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ADVISORID = "triggerPointAdvisorId";

    /**
* Allow label update attribute. Value <code>allowLabelUpdate</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ALLOW_LABEL_UPDATE = "allowLabelUpdate";

    /**
* View multiple attribute. Value <code>allowMultiple</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ALLOW_MULTIPLE = "allowMultiple";

    /**
* Attribute that specifies whether a view gets restored upon workbench restart. Value <code>restorable</code>.
*/
    //$NON-NLS-1$
    public static String ATT_RESTORABLE = "restorable";

    /**
* Attribute that specifies whether a wizard is immediately capable of
* finishing. Value <code>canFinishEarly</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CAN_FINISH_EARLY = "canFinishEarly";

    /**
* The name of the category attribute, which appears on a command
* definition.
*/
    //$NON-NLS-1$
    public static String ATT_CATEGORY = "category";

    /**
* Category id attribute. Value <code>categoryId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CATEGORY_ID = "categoryId";

    /**
* The name of the attribute storing checkEnabled for the visibleWhen
* element. Value <code>checkEnabled</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CHECK_ENABLED = "checkEnabled";

    /**
* Class attribute. Value <code>class</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CLASS = "class";

    /**
* Sticky view closable attribute. Value <code>closable</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CLOSEABLE = "closeable";

    /**
* Color factory attribute. Value <code>colorFactory</code>.
*/
    //$NON-NLS-1$
    public static String ATT_COLORFACTORY = "colorFactory";

    /**
* Editor command attribute. Value <code>command</code>.
*/
    //$NON-NLS-1$
    public static String ATT_COMMAND = "command";

    /**
* The name of the attribute storing the command id.
*/
    //$NON-NLS-1$
    public static String ATT_COMMAND_ID = "commandId";

    /**
* The name of the configuration attribute storing the scheme id for a
* binding.
*/
    //$NON-NLS-1$
    public static String ATT_CONFIGURATION = "configuration";

    /**
* Intro content detector class attribute (optional). Value <code>contentDetector</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CONTENT_DETECTOR = "contentDetector";

    /**
* Editor content type id binding attribute. Value
* <code>contentTypeId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CONTENT_TYPE_ID = "contentTypeId";

    /**
* The name of the attribute storing the context id for a binding.
*/
    //$NON-NLS-1$
    public static String ATT_CONTEXT_ID = "contextId";

    /**
* Editor contributor class attribute. Value <code>contributorClass</code>.
*/
    //$NON-NLS-1$
    public static String ATT_CONTRIBUTOR_CLASS = "contributorClass";

    /**
* The name of the attribute storing the AbstractParameterValueConverter for
* a commandParameterType.
*/
    //$NON-NLS-1$
    public static String ATT_CONVERTER = "converter";

    /**
* Perspective default attribute. Value <code>default</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DEFAULT = "default";

    /**
* The name of the default handler attribute, which appears on a command
* definition.
*/
    //$NON-NLS-1$
    public static String ATT_DEFAULT_HANDLER = "defaultHandler";

    /**
* Defaults-to attribute. Value <code>defaultsTo</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DEFAULTS_TO = "defaultsTo";

    /**
* Action definition id attribute. Value <code>definitionId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DEFINITION_ID = "definitionId";

    /**
* Resembles a deactivated SYSTEM binding. Value <code>deleted</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DELETED = "deleted";

    /**
* The name of the description attribute, which appears on named handle
* objects.
*/
    //$NON-NLS-1$
    public static String ATT_DESCRIPTION = "description";

    /**
* Description image attribute. Value <code>descriptionImage</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DESCRIPTION_IMAGE = "descriptionImage";

    /**
* Disabled icon attribute. Value <code>disabledIcon</code>.
*/
    //$NON-NLS-1$
    public static String ATT_DISABLEDICON = "disabledIcon";

    /**
* Enables-for attribute. Value <code>enablesFor</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ENABLES_FOR = "enablesFor";

    /**
* Editor extensions attribute. Value <code>extensions</code>.
*/
    //$NON-NLS-1$
    public static String ATT_EXTENSIONS = "extensions";

    /**
* Editor filenames attribute. Value <code>filenames</code>.
*/
    //$NON-NLS-1$
    public static String ATT_FILENAMES = "filenames";

    /**
* Trim fill major attribute. Value <code>fillMajor</code>.
*/
    //$NON-NLS-1$
    public static String ATT_FILL_MAJOR = "fillMajor";

    /**
* Trim fill minor attribute. Value <code>fillMinor</code>.
*/
    //$NON-NLS-1$
    public static String ATT_FILL_MINOR = "fillMinor";

    /**
* Perspective fixed attribute. Value <code>fixed</code>.
*/
    //$NON-NLS-1$
    public static String ATT_FIXED = "fixed";

    /**
* Attribute that specifies whether a wizard has any pages. Value
* <code>hasPages</code>.
*/
    //$NON-NLS-1$
    public static String ATT_HAS_PAGES = "hasPages";

    /**
* Help context id attribute. Value <code>helpContextId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_HELP_CONTEXT_ID = "helpContextId";

    /**
* Help url attribute. Value <code>helpHref</code>.
*/
    //$NON-NLS-1$
    public static String ATT_HELP_HREF = "helpHref";

    /**
* Hover icon attribute. Value <code>hoverIcon</code>.
*/
    //$NON-NLS-1$
    public static String ATT_HOVERICON = "hoverIcon";

    /**
* Icon attribute. Value <code>icon</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ICON = "icon";

    /**
* Id attribute. Value <code>id</code>.
*/
    //$NON-NLS-1$
    public static String ATT_ID = "id";

    /**
* The name of the image style attribute, which is used on location elements
* in the menus extension point.
*/
    //$NON-NLS-1$
    public static String ATT_IMAGE_STYLE = "imageStyle";

    /**
* Is-editable attribute. Value <code>isEditable</code>.
*/
    //$NON-NLS-1$
    public static String ATT_IS_EDITABLE = "isEditable";

    /**
* Keys attribute. Value <code>keys</code>.
*/
    //$NON-NLS-1$
    public static String ATT_KEY = "key";

    /**
* The name of the attribute storing the identifier for the active key
* configuration identifier. This provides legacy support for the
* <code>activeKeyConfiguration</code> element in the commands extension
* point.
*/
    //$NON-NLS-1$
    public static String ATT_KEY_CONFIGURATION_ID = "keyConfigurationId";

    /**
* The name of the attribute storing the trigger sequence for a binding.
* This is called a 'keySequence' for legacy reasons.
*/
    //$NON-NLS-1$
    public static String ATT_KEY_SEQUENCE = "keySequence";

    /**
* Label attribute. Value <code>label</code>.
*/
    //$NON-NLS-1$
    public static String ATT_LABEL = "label";

    /**
* Editor launcher attribute. Value <code>launcher</code>.
*/
    //$NON-NLS-1$
    public static String ATT_LAUNCHER = "launcher";

    /**
* Lightweight decorator tag. Value <code>lightweight</code>.
*/
    //$NON-NLS-1$
    public static String ATT_LIGHTWEIGHT = "lightweight";

    /**
* The name of the attribute storing the locale for a binding.
*/
    //$NON-NLS-1$
    public static String ATT_LOCALE = "locale";

    /**
* Sticky view location attribute. Value <code>location</code>.
*/
    //$NON-NLS-1$
    public static String ATT_LOCATION = "location";

    /**
* Editor management strategy attribute. Value <code>matchingStrategy</code>.
*/
    //$NON-NLS-1$
    public static String ATT_MATCHING_STRATEGY = "matchingStrategy";

    /**
* The name of the menu identifier attribute, which appears on items.
*/
    //$NON-NLS-1$
    public static String ATT_MENU_ID = "menuId";

    /**
* Menubar path attribute. Value <code>menubarPath</code>.
*/
    //$NON-NLS-1$
    public static String ATT_MENUBAR_PATH = "menubarPath";

    /**
* The name of the mnemonic attribute, which appears on locations.
*/
    //$NON-NLS-1$
    public static String ATT_MNEMONIC = "mnemonic";

    /**
* The name of the minimized attribute, which appears
* when adding a view in a perspectiveExtension.
*/
    //$NON-NLS-1$
    public static String ATT_MINIMIZED = "minimized";

    /**
* Sticky view moveable attribute. Value <code>moveable</code>.
*/
    //$NON-NLS-1$
    public static String ATT_MOVEABLE = "moveable";

    /**
* Name attribute. Value <code>name</code>.
*/
    //$NON-NLS-1$
    public static String ATT_NAME = "name";

    /**
* Match type attribute. Value <code>match</code>.
*/
    //$NON-NLS-1$
    public static String ATT_MATCH_TYPE = "match";

    /**
* Name filter attribute. Value <code>nameFilter</code>.
*/
    //$NON-NLS-1$
    public static String ATT_NAME_FILTER = "nameFilter";

    /**
* Node attribute. Value <code>node</code>.
*/
    //$NON-NLS-1$
    public static String ATT_NODE = "node";

    /**
* Object class attribute. Value <code>objectClass</code>.
*/
    //$NON-NLS-1$
    public static String ATT_OBJECTCLASS = "objectClass";

    /**
* The name of the optional attribute, which appears on parameter
* definitions.
*/
    //$NON-NLS-1$
    public static String ATT_OPTIONAL = "optional";

    /**
* Operating system attribute. Value <code>os</code>.
*/
    //$NON-NLS-1$
    public static String ATT_OS = "os";

    /**
* The name of the deprecated parent attribute, which appears on scheme
* definitions.
*/
    //$NON-NLS-1$
    public static String ATT_PARENT = "parent";

    /**
* View parent category attribute. Value <code>parentCategory</code>.
*/
    //$NON-NLS-1$
    public static String ATT_PARENT_CATEGORY = "parentCategory";

    /**
* Parent id attribute. Value <code>parentId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_PARENT_ID = "parentId";

    /**
* The name of the deprecated parent scope attribute, which appears on
* contexts definitions.
*/
    //$NON-NLS-1$
    public static String ATT_PARENT_SCOPE = "parentScope";

    /**
* Path attribute. Value <code>path</code>.
*/
    //$NON-NLS-1$
    public static String ATT_PATH = "path";

    /**
* The name of the attribute storing the platform for a binding.
*/
    //$NON-NLS-1$
    public static String ATT_PLATFORM = "platform";

    /**
* The name of the position attribute, which appears on order elements.
*/
    //$NON-NLS-1$
    public static String ATT_POSITION = "position";

    /**
* Presentation id attribute. Value <code>presentationId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_PRESENTATIONID = "presentationId";

    /**
* Product id attribute. Value <code>productId</code>.
*/
    //$NON-NLS-1$
    public static String ATT_PRODUCTID = "productId";

    /**
* Project attribute. Value <code>project</code>.
*/
    // @issue project-specific attribute and behavior
    //$NON-NLS-1$	/**
    public static String ATT_PROJECT = "project";

    /**
* The name of the pulldown attribute, which indicates whether the class is
* a pulldown delegate.
*/
    //$NON-NLS-1$
    public static String ATT_PULLDOWN = "pulldown";

    /**
* View ratio attribute. Value <code>ratio</code>.
*/
    //$NON-NLS-1$
    public static String ATT_RATIO = "ratio";

    /**
* Relationship attribute. Value <code>relationship</code>.
*/
    //$NON-NLS-1$
    public static String ATT_RELATIONSHIP = "relationship";

    /**
* Relative attribute. Value <code>relative</code>.
*/
    //$NON-NLS-1$
    public static String ATT_RELATIVE = "relative";

    /**
* The name of the relativeTo attribute, which appears on order elements.
*/
    //$NON-NLS-1$
    public static String ATT_RELATIVE_TO = "relativeTo";

    /**
* Retarget attribute. Value <code>retarget</code>.
*/
    //$NON-NLS-1$
    public static String ATT_RETARGET = "retarget";

    /**
* The name of the returnTypeId attribute, which appears on command
* elements.
*/
    //$NON-NLS-1$
    public static String ATT_RETURN_TYPE_ID = "returnTypeId";

    /**
* The name of the attribute storing the identifier for the active scheme.
* This is called a 'keyConfigurationId' for legacy reasons.
*/
    //$NON-NLS-1$
    public static String ATT_SCHEME_ID = "schemeId";

    /**
* Scope attribute. Value <code>scope</code>.
*/
    //$NON-NLS-1$
    public static String ATT_SCOPE = "scope";

    /**
* The name of the separatorsVisible attribute, which appears on group
* elements.
*/
    //$NON-NLS-1$
    public static String ATT_SEPARATORS_VISIBLE = "separatorsVisible";

    /**
* The name of the sequence attribute for a key binding.
*/
    //$NON-NLS-1$
    public static String ATT_SEQUENCE = "sequence";

    /**
* Show title attribute. Value <code>showTitle</code>.
*/
    //$NON-NLS-1$
    public static String ATT_SHOW_TITLE = "showTitle";

    /**
* Perspective singleton attribute. Value <code>singleton</code>.
*/
    //$NON-NLS-1$
    public static String ATT_SINGLETON = "singleton";

    /**
* Splash id attribute.  Value <code>splashId</code>.
*
* @since 3.3
*/
    //$NON-NLS-1$
    public static String ATT_SPLASH_ID = "splashId";

    /**
* Standalone attribute. Value <code>standalone</code>.
*/
    //$NON-NLS-1$
    public static String ATT_STANDALONE = "standalone";

    /**
* Action state attribute. Value <code>state</code>.
*/
    //$NON-NLS-1$
    public static String ATT_STATE = "state";

    /**
* The name of the string attribute (key sequence) for a binding in the
* commands extension point.
*/
    //$NON-NLS-1$
    public static String ATT_STRING = "string";

    /**
* Action style attribute. Value <code>style</code>.
*/
    //$NON-NLS-1$
    public static String ATT_STYLE = "style";

    /**
* Target attribute. Value <code>targetID</code>.
*/
    //$NON-NLS-1$
    public static String ATT_TARGET_ID = "targetID";

    /**
* Toolbar path attribute. Value <code>toolbarPath</code>.
*/
    //$NON-NLS-1$
    public static String ATT_TOOLBAR_PATH = "toolbarPath";

    /**
* Tooltip attribute. Value <code>tooltip</code>.
*/
    //$NON-NLS-1$
    public static String ATT_TOOLTIP = "tooltip";

    /**
* The name of the type attribute, which appears on bar elements and
* commandParameterType elments.
*/
    //$NON-NLS-1$
    public static String ATT_TYPE = "type";

    /**
* The name of the typeId attribute, which appears on commandParameter
* elements.
*/
    //$NON-NLS-1$
    public static String ATT_TYPE_ID = "typeId";

    /**
* Value attribute. Value <code>value</code>.
*/
    //$NON-NLS-1$
    public static String ATT_VALUE = "value";

    /**
* Visible attribute. Value <code>visible</code>.
*/
    // ATT_VISIBLE added by dan_rubel@instantiations.com
    //$NON-NLS-1$
    public static String ATT_VISIBLE = "visible";

    /**
* Windowing system attribute. Value <code>ws</code>.
*/
    //$NON-NLS-1$
    public static String ATT_WS = "ws";

    /**
* The prefix that all auto-generated identifiers start with. This makes the
* identifier recognizable as auto-generated, and further helps ensure that
* it does not conflict with existing identifiers.
*/
    //$NON-NLS-1$
    public static String AUTOGENERATED_PREFIX = "AUTOGEN:::";

    /**
* The legacy extension point (2.1.x and earlier) for specifying a key
* binding scheme.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_ACCELERATOR_CONFIGURATIONS = "acceleratorConfigurations";

    /**
* The legacy extension point (2.1.x and earlier) for specifying a context.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_ACCELERATOR_SCOPES = "acceleratorScopes";

    /**
* The legacy extension point (2.1.x and earlier) for specifying a command.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_ACTION_DEFINITIONS = "actionDefinitions";

    //$NON-NLS-1$
    public static String PL_ACTION_SET_PART_ASSOCIATIONS = "actionSetPartAssociations";

    //$NON-NLS-1$
    public static String PL_ACTION_SETS = "actionSets";

    //$NON-NLS-1$
    public static String PL_ACTIVITIES = "activities";

    //$NON-NLS-1$
    public static String PL_ACTIVITYSUPPORT = "activitySupport";

    /**
* The extension point (3.1 and later) for specifying bindings, such as
* keyboard shortcuts.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_BINDINGS = "bindings";

    //$NON-NLS-1$
    public static String PL_BROWSER_SUPPORT = "browserSupport";

    //$NON-NLS-1$
    public static String PL_COLOR_DEFINITIONS = "colorDefinitions";

    /**
* The extension point (3.2 and later) for associating images with commands.
*
* @since 3.2
*/
    //$NON-NLS-1$
    public static String PL_COMMAND_IMAGES = "commandImages";

    /**
* The extension point (2.1.x and later) for specifying a command. A lot of
* other things have appeared first in this extension point and then been
* moved to their own extension point.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_COMMANDS = "commands";

    /**
* The extension point (3.0 and later) for specifying a context.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_CONTEXTS = "contexts";

    //$NON-NLS-1$
    public static String PL_DECORATORS = "decorators";

    //$NON-NLS-1$
    public static String PL_DROP_ACTIONS = "dropActions";

    //$NON-NLS-1$
    public static String PL_EDITOR = "editors";

    //$NON-NLS-1$
    public static String PL_EDITOR_ACTIONS = "editorActions";

    //$NON-NLS-1$
    public static String PL_ELEMENT_FACTORY = "elementFactories";

    /**
* The extension point for encoding definitions.
*/
    //$NON-NLS-1$
    public static String PL_ENCODINGS = "encodings";

    //$NON-NLS-1$
    public static String PL_EXPORT = "exportWizards";

    //$NON-NLS-1$
    public static String PL_FONT_DEFINITIONS = "fontDefinitions";

    /**
* The extension point (3.1 and later) for specifying handlers.
*
* @since 3.1.1
*/
    //$NON-NLS-1$
    public static String PL_HANDLERS = "handlers";

    //$NON-NLS-1$
    public static String PL_HELPSUPPORT = "helpSupport";

    //$NON-NLS-1$
    public static String PL_IMPORT = "importWizards";

    //$NON-NLS-1$
    public static String PL_INTRO = "intro";

    /**
* The extension point for keyword definitions.
*
* @since 3.1
*/
    //$NON-NLS-1$
    public static String PL_KEYWORDS = "keywords";

    /**
* The extension point (3.2 and later) for specifying menu contributions.
*
* @since 3.2
*/
    //$NON-NLS-1$
    public static String PL_MENUS = "menus";

    /**
* The extension point (3.3 and later) for specifying menu contributions.
*
* @since 3.3
*/
    //$NON-NLS-1$
    public static String PL_MENU_CONTRIBUTION = "menuContribution";

    //$NON-NLS-1$
    public static String PL_NEW = "newWizards";

    //$NON-NLS-1$
    public static String PL_PERSPECTIVE_EXTENSIONS = "perspectiveExtensions";

    //$NON-NLS-1$
    public static String PL_PERSPECTIVES = "perspectives";

    //$NON-NLS-1$
    public static String PL_POPUP_MENU = "popupMenus";

    //$NON-NLS-1$
    public static String PL_PREFERENCE_TRANSFER = "preferenceTransfer";

    //$NON-NLS-1$
    public static String PL_PREFERENCES = "preferencePages";

    //$NON-NLS-1$
    public static String PL_PROPERTY_PAGES = "propertyPages";

    //$NON-NLS-1$
    public static String PL_STARTUP = "startup";

    /**
* @since 3.3
*/
    //$NON-NLS-1$
    public static String PL_SPLASH_HANDLERS = "splashHandlers";

    //$NON-NLS-1$
    public static String PL_SYSTEM_SUMMARY_SECTIONS = "systemSummarySections";

    //$NON-NLS-1$
    public static String PL_THEMES = "themes";

    //$NON-NLS-1$
    public static String PL_VIEW_ACTIONS = "viewActions";

    //$NON-NLS-1$
    public static String PL_VIEWS = "views";

    //$NON-NLS-1$
    public static String PL_WORKINGSETS = "workingSets";

    /**
* The name of the deprecated accelerator configurations extension point.
*/
    public static String EXTENSION_ACCELERATOR_CONFIGURATIONS = PlatformUI.PLUGIN_ID + '.' + PL_ACCELERATOR_CONFIGURATIONS;

    /**
* The name of the accelerator scopes extension point.
*/
    public static String EXTENSION_ACCELERATOR_SCOPES = PlatformUI.PLUGIN_ID + '.' + PL_ACCELERATOR_SCOPES;

    /**
* The name of the action definitions extension point.
*/
    public static String EXTENSION_ACTION_DEFINITIONS = PlatformUI.PLUGIN_ID + '.' + PL_ACTION_DEFINITIONS;

    /**
* The name of the <code>org.eclipse.ui.actionSets</code> extension point.
*/
    public static String EXTENSION_ACTION_SETS = PlatformUI.PLUGIN_ID + '.' + IWorkbenchRegistryConstants.PL_ACTION_SETS;

    /**
* The name of the bindings extension point.
*/
    public static String EXTENSION_BINDINGS = PlatformUI.PLUGIN_ID + '.' + PL_BINDINGS;

    /**
* The name of the commands extension point.
*/
    public static String EXTENSION_COMMAND_IMAGES = PlatformUI.PLUGIN_ID + '.' + PL_COMMAND_IMAGES;

    /**
* The name of the commands extension point, and the name of the key for the
* commands preferences.
*/
    public static String EXTENSION_COMMANDS = PlatformUI.PLUGIN_ID + '.' + PL_COMMANDS;

    /**
* The name of the contexts extension point.
*/
    public static String EXTENSION_CONTEXTS = PlatformUI.PLUGIN_ID + '.' + PL_CONTEXTS;

    /**
* The name of the <code>org.eclipse.ui.editorActions</code> extension
* point.
*/
    public static String EXTENSION_EDITOR_ACTIONS = PlatformUI.PLUGIN_ID + '.' + PL_EDITOR_ACTIONS;

    /**
* The name of the commands extension point.
*/
    public static String EXTENSION_HANDLERS = PlatformUI.PLUGIN_ID + '.' + PL_HANDLERS;

    /**
* The name of the <code>org.eclipse.ui.menus</code> extension point.
*/
    public static String EXTENSION_MENUS = PlatformUI.PLUGIN_ID + '.' + PL_MENUS;

    /**
* The name of the <code>org.eclipse.ui.menus2</code> extension point.
*/
    public static String COMMON_MENU_ADDITIONS = PlatformUI.PLUGIN_ID + '.' + PL_MENUS + '2';

    /**
* The name of the <code>org.eclipse.ui.popupMenus</code> extension point.
*/
    public static String EXTENSION_POPUP_MENUS = PlatformUI.PLUGIN_ID + '.' + PL_POPUP_MENU;

    /**
* The name of the <code>org.eclipse.ui.viewActions</code> extension
* point.
*/
    public static String EXTENSION_VIEW_ACTIONS = PlatformUI.PLUGIN_ID + '.' + PL_VIEW_ACTIONS;

    /**
* The constant for the position attribute corresponding to
* {@link SOrder#POSITION_AFTER}.
*/
    //$NON-NLS-1$
    public static String POSITION_AFTER = "after";

    /**
* The constant for the position attribute corresponding to
* {@link SOrder#POSITION_BEFORE}.
*/
    //$NON-NLS-1$
    public static String POSITION_BEFORE = "before";

    /**
* The constant for the position attribute corresponding to
* {@link SOrder#POSITION_END}.
*/
    //$NON-NLS-1$
    public static String POSITION_END = "end";

    /**
* The constant for the position attribute corresponding to
* {@link SOrder#POSITION_START}.
*/
    //$NON-NLS-1$
    public static String POSITION_START = "start";

    /**
* The action style for drop-down menus.
*/
    //$NON-NLS-1$
    public static String STYLE_PULLDOWN = "pulldown";

    /**
* The action style for radio buttons.
*/
    //$NON-NLS-1$
    public static String STYLE_RADIO = "radio";

    /**
* The action style for check boxes.
*/
    //$NON-NLS-1$
    public static String STYLE_TOGGLE = "toggle";

    /**
* The name of the deprecated accelerator configuration element. This
* element was used in 2.1.x and earlier to define groups of what are now
* called schemes.
*/
    //$NON-NLS-1$
    public static String TAG_ACCELERATOR_CONFIGURATION = "acceleratorConfiguration";

    /**
* The name of the element storing a deprecated accelerator scope.
*/
    //$NON-NLS-1$
    public static String TAG_ACCELERATOR_SCOPE = "acceleratorScope";

    /**
* Action tag. Value <code>action</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ACTION = "action";

    /**
* The name of the element storing an action definition. This element only
* existed in
*/
    //$NON-NLS-1$
    public static String TAG_ACTION_DEFINITION = "actionDefinition";

    /**
* Action set tag. Value <code>actionSet</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ACTION_SET = "actionSet";

    /**
* Part association tag. Value <code>actionSetPartAssociation</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ACTION_SET_PART_ASSOCIATION = "actionSetPartAssociation";

    /**
* The name of the element storing the active key configuration from the
* commands extension point.
*/
    //$NON-NLS-1$
    public static String TAG_ACTIVE_KEY_CONFIGURATION = "activeKeyConfiguration";

    //$NON-NLS-1$
    public static String TAG_SEQUENCE_MODIFIER = "sequenceModifier";

    /**
* The name of the active when element, which appears on a handler
* definition.
*/
    //$NON-NLS-1$
    public static String TAG_ACTIVE_WHEN = "activeWhen";

    /**
* Activity image binding tag. Value <code>activityImageBindingw</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ACTIVITY_IMAGE_BINDING = "activityImageBinding";

    /**
* Advisor to product binding element. Value
* <code>triggerPointAdvisorProductBinding</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ADVISORPRODUCTBINDING = "triggerPointAdvisorProductBinding";

    /**
* The name of the bar element, which appears in a location definition.
*/
    //$NON-NLS-1$
    public static String TAG_BAR = "bar";

    /**
* Category tag. Value <code>category</code>.
*/
    //$NON-NLS-1$
    public static String TAG_CATEGORY = "category";

    /**
* Category image binding tag. Value <code>categoryImageBinding</code>.
*/
    //$NON-NLS-1$
    public static String TAG_CATEGORY_IMAGE_BINDING = "categoryImageBinding";

    /**
* Element category tag. Value <code>themeElementCategory</code>.
*/
    //$NON-NLS-1$
    public static String TAG_CATEGORYDEFINITION = "themeElementCategory";

    /**
* Category presentation tag. Value <code>categoryPresentationBinding</code>
* .
*
* @deprecated used by the removal presentation API
*/
    @Deprecated
    public static String //$NON-NLS-1$
    TAG_CATEGORYPRESENTATIONBINDING = "categoryPresentationBinding";

    /**
* The name of the class element, which appears on an executable extension.
*/
    public static String TAG_CLASS = ATT_CLASS;

    /**
* Color definition tag. Value <code>colorDefinition</code>.
*/
    //$NON-NLS-1$
    public static String TAG_COLORDEFINITION = "colorDefinition";

    /**
* Color override tag. Value <code>colorOverride</code>.
*/
    //$NON-NLS-1$
    public static String TAG_COLOROVERRIDE = "colorOverride";

    /**
* Color value tag. Value <code>colorValue</code>.
*/
    //$NON-NLS-1$
    public static String TAG_COLORVALUE = "colorValue";

    /**
* The name of the element storing a command.
*/
    //$NON-NLS-1$
    public static String TAG_COMMAND = "command";

    /**
* The name of the element storing a parameter.
*/
    //$NON-NLS-1$
    public static String TAG_COMMAND_PARAMETER = "commandParameter";

    /**
* The name of the element storing a parameter type.
*/
    //$NON-NLS-1$
    public static String TAG_COMMAND_PARAMETER_TYPE = "commandParameterType";

    /**
* Editor content type binding tag. Value <code>contentTypeBinding</code>.
*/
    //$NON-NLS-1$
    public static String TAG_CONTENT_TYPE_BINDING = "contentTypeBinding";

    /**
* The name of the element storing a context.
*/
    //$NON-NLS-1$
    public static String TAG_CONTEXT = "context";

    /**
* Data tag. Value <code>data</code>.
*/
    //$NON-NLS-1$
    public static String TAG_DATA = "data";

    /**
* The name of the default handler element, which appears on a command
* definition.
*/
    public static String TAG_DEFAULT_HANDLER = ATT_DEFAULT_HANDLER;

    /**
* Description element. Value <code>description</code>.
*/
    //$NON-NLS-1$
    public static String TAG_DESCRIPTION = "description";

    /**
* The name of the dynamic menu element, which appears in a group or menu
* definition.
*/
    //$NON-NLS-1$
    public static String TAG_DYNAMIC = "dynamic";

    /**
* Editor tag. Value <code>editor</code>.
*/
    //$NON-NLS-1$
    public static String TAG_EDITOR = "editor";

    /**
* The name of the deprecated editorContribution element. This is used for
* contributing actions to the top-level menus and tool bars when particular
* editors are visible.
*/
    //$NON-NLS-1$
    public static String TAG_EDITOR_CONTRIBUTION = "editorContribution";

    /**
* The name of the enabled when element, which appears on a handler
* definition.
*/
    //$NON-NLS-1$
    public static String TAG_ENABLED_WHEN = "enabledWhen";

    /**
* Enablement tag. Value <code>enablement</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ENABLEMENT = "enablement";

    /**
* Entry tag. Value <code>entry</code>.
*/
    //$NON-NLS-1$
    public static String TAG_ENTRY = "entry";

    /**
* Filter tag. Value <code>filter</code>.
*/
    //$NON-NLS-1$
    public static String TAG_FILTER = "filter";

    /***************************************************************************
* Font definition tag. Value <code>fontDefinition</code>.
*/
    //$NON-NLS-1$
    public static String TAG_FONTDEFINITION = "fontDefinition";

    /**
* Font override tag. Value <code>fontOverride</code>.
*/
    //$NON-NLS-1$
    public static String TAG_FONTOVERRIDE = "fontOverride";

    /**
* Font value tag. Value <code>fontValue</code>.
*/
    //$NON-NLS-1$
    public static String TAG_FONTVALUE = "fontValue";

    /**
* The name of the element storing a group.
*/
    //$NON-NLS-1$
    public static String TAG_GROUP = "group";

    /**
* Group marker tag. Value <code>groupMarker</code>.
*/
    //$NON-NLS-1$
    public static String TAG_GROUP_MARKER = "groupMarker";

    /**
* The name of the element storing a handler.
*/
    //$NON-NLS-1$
    public static String TAG_HANDLER = "handler";

    /**
* The name of the element storing a handler submission.
*/
    //$NON-NLS-1$
    public static String TAG_HANDLER_SUBMISSION = "handlerSubmission";

    /**
* The name of the element storing the id of a menu item to hide
*/
    //$NON-NLS-1$
    public static String TAG_HIDDEN_MENU_ITEM = "hiddenMenuItem";

    /**
* The name of the element storing the id of a toolbar item to hide
*/
    //$NON-NLS-1$
    public static String TAG_HIDDEN_TOOLBAR_ITEM = "hiddenToolBarItem";

    /**
* Trigger point hint tag. Value <code>hint</code>.
*/
    //$NON-NLS-1$
    public static String TAG_HINT = "hint";

    /**
* The name of the element storing an image.
*/
    //$NON-NLS-1$
    public static String TAG_IMAGE = "image";

    /**
* The name of the element storing a key binding.
*/
    //$NON-NLS-1$
    public static String TAG_KEY = "key";

    /**
* The name of the key binding element in the commands extension point.
*/
    //$NON-NLS-1$
    public static String TAG_KEY_BINDING = "keyBinding";

    /**
* The name of the deprecated key configuration element in the commands
* extension point. This element has been replaced with the scheme element
* in the bindings extension point.
*/
    //$NON-NLS-1$
    public static String TAG_KEY_CONFIGURATION = "keyConfiguration";

    /**
* The name of the element storing a location.
*/
    //$NON-NLS-1$
    public static String TAG_LOCATION = "location";

    /**
* The name of the element defining the insertion point for menu
* additions.
*
* @since 3.3
*/
    //$NON-NLS-1$
    public static String TAG_LOCATION_URI = "locationURI";

    /**
* The name of the element storing trim layout info for a widget.
*/
    //$NON-NLS-1$
    public static String TAG_LAYOUT = "layout";

    /**
* Mapping tag. Value <code>mapping</code>.
*/
    //$NON-NLS-1$
    public static String TAG_MAPPING = "mapping";

    /**
* Menu tag. Value <code>menu</code>.
*/
    //$NON-NLS-1$
    public static String TAG_MENU = "menu";

    /**
* Wizard shortcut tag. Value <code>newWizardShortcut</code>.
*/
    //$NON-NLS-1$
    public static String TAG_NEW_WIZARD_SHORTCUT = "newWizardShortcut";

    /**
* Object contribution tag. Value <code>objectContribution</code>.
*/
    //$NON-NLS-1$
    public static String TAG_OBJECT_CONTRIBUTION = "objectContribution";

    /**
* The name of the element storing the ordering information.
*/
    //$NON-NLS-1$
    public static String TAG_ORDER = "order";

    /**
* The name of the element storing a parameter.
*/
    //$NON-NLS-1$
    public static String TAG_PARAMETER = "parameter";

    /**
* Part tag. Value <code>part</code>.
*/
    //$NON-NLS-1$
    public static String TAG_PART = "part";

    /**
* Perspective shortcut tag. Value <code>perspectiveShortcut</code>.
*/
    //$NON-NLS-1$
    public static String TAG_PERSP_SHORTCUT = "perspectiveShortcut";

    /**
* Perspective tag. Value <code>perspective</code>.
*/
    //$NON-NLS-1$
    public static String TAG_PERSPECTIVE = "perspective";

    /**
* Perspective extension tag. Value <code>perspectiveExtension</code>.
*/
    //$NON-NLS-1$
    public static String TAG_PERSPECTIVE_EXTENSION = "perspectiveExtension";

    /**
* Primary wizard tag. Value <code>primaryWizard</code>.
*/
    //$NON-NLS-1$
    public static String TAG_PRIMARYWIZARD = "primaryWizard";

    /**
* The name of the element storing the a menu element reference.
*/
    //$NON-NLS-1$
    public static String TAG_REFERENCE = "reference";

    /**
* The name of the scheme element in the bindings extension point.
*/
    //$NON-NLS-1$
    public static String TAG_SCHEME = "scheme";

    /**
* The name of the element storing a deprecated scope.
*/
    //$NON-NLS-1$
    public static String TAG_SCOPE = "scope";

    /**
* Selectiont tag. Value <code>selection</code>.
*/
    //$NON-NLS-1$
    public static String TAG_SELECTION = "selection";

    /**
* Separator tag. Value <code>separator</code>.
*/
    //$NON-NLS-1$
    public static String TAG_SEPARATOR = "separator";

    /**
* Tag for the settings transfer entry.
*/
    //$NON-NLS-1$
    public static String TAG_SETTINGS_TRANSFER = "settingsTransfer";

    /**
* Show in part tag. Value <code>showInPart</code>.
*/
    //$NON-NLS-1$
    public static String TAG_SHOW_IN_PART = "showInPart";

    /**
* The name of the element storing some state.
*/
    //$NON-NLS-1$
    public static String TAG_STATE = "state";

    /**
* The name of the element describing splash handlers. Value
* <code>splashHandler</code>.
* @since 3.3
*/
    //$NON-NLS-1$
    public static String TAG_SPLASH_HANDLER = "splashHandler";

    /**
* The name of the element describing splash handler product bindings. Value
* <code>splashHandlerProductBinding</code>.
* @since 3.3
*/
    //$NON-NLS-1$
    public static String TAG_SPLASH_HANDLER_PRODUCT_BINDING = "splashHandlerProductBinding";

    /**
* Sticky view tag. Value <code>stickyView</code>.
*/
    //$NON-NLS-1$
    public static String TAG_STICKYVIEW = "stickyView";

    /**
* Browser support tag. Value <code>support</code>.
*/
    //$NON-NLS-1$
    public static String TAG_SUPPORT = "support";

    /**
* Theme tag. Value <code>theme</code>.
*/
    //$NON-NLS-1$
    public static String TAG_THEME = "theme";

    /**
* Transfer tag. Value <code>transfer</code>.
*/
    //$NON-NLS-1$
    public static String TAG_TRANSFER = "transfer";

    /**
* Trigger point tag. Value <code>triggerPoint</code>.
*/
    //$NON-NLS-1$
    public static String TAG_TRIGGERPOINT = "triggerPoint";

    /**
* Advisor tag. Value <code>triggerPointAdvisor</code>.
*/
    //$NON-NLS-1$
    public static String TAG_TRIGGERPOINTADVISOR = "triggerPointAdvisor";

    /**
* View tag. Value <code>view</code>.
*/
    //$NON-NLS-1$
    public static String TAG_VIEW = "view";

    /**
* E4 view tag, used in the <code>org.eclipse.ui.view</code> extension point
* to point to a POJO class. Value <code>e4view</code>.
*/
    //$NON-NLS-1$
    public static String TAG_E4VIEW = "e4view";

    /**
* View shortcut tag. Value <code>viewShortcut</code>.
*/
    //$NON-NLS-1$
    public static String TAG_VIEW_SHORTCUT = "viewShortcut";

    /**
* The name of the element storing a view contribution.
*/
    //$NON-NLS-1$
    public static String TAG_VIEW_CONTRIBUTION = "viewContribution";

    /**
* Viewer contribution tag. Value <code>viewerContribution</code>.
*/
    //$NON-NLS-1$
    public static String TAG_VIEWER_CONTRIBUTION = "viewerContribution";

    /**
* Visibility tag. Value <code>visibility</code>.
*/
    //$NON-NLS-1$
    public static String TAG_VISIBILITY = "visibility";

    /**
* The name of the element storing the visible when condition.
*/
    //$NON-NLS-1$
    public static String TAG_VISIBLE_WHEN = "visibleWhen";

    /**
* The name of the element storing a widget.
*/
    //$NON-NLS-1$
    public static String TAG_WIDGET = "widget";

    /**
* The name of the element storing a control hosted in a ToolBar.
*/
    //$NON-NLS-1$
    public static String TAG_CONTROL = "control";

    /**
* Wizard tag. Value <code>wizard</code>.
*/
    //$NON-NLS-1$
    public static String TAG_WIZARD = "wizard";

    /**
* Working set tag. Value <code>workingSet</code>.
*/
    //$NON-NLS-1$
    public static String TAG_WORKING_SET = "workingSet";

    /**
* The type of reference which refers to a group.
*/
    //$NON-NLS-1$
    public static String TYPE_GROUP = "group";

    /**
* The type of reference which refers to an item.
*/
    //$NON-NLS-1$
    public static String TYPE_ITEM = "item";

    /**
* The type of reference which refers to an menu.
*/
    //$NON-NLS-1$
    public static String TYPE_MENU = "menu";

    /**
* The type of reference which refers to the widget.
*/
    //$NON-NLS-1$
    public static String TYPE_WIDGET = "widget";

    //$NON-NLS-1$
    public static String TAG_TOOLBAR = "toolbar";

    //$NON-NLS-1$
    public static String TAG_SERVICE_FACTORY = "serviceFactory";

    //$NON-NLS-1$
    public static String TAG_SERVICE = "service";

    //$NON-NLS-1$
    public static final String ATTR_FACTORY_CLASS = "factoryClass";

    //$NON-NLS-1$
    public static final String ATTR_SERVICE_CLASS = "serviceClass";

    //$NON-NLS-1$
    public static final String TAG_SOURCE_PROVIDER = "sourceProvider";

    //$NON-NLS-1$
    public static final String ATTR_PROVIDER = "provider";

    //$NON-NLS-1$
    public static final String TAG_VARIABLE = "variable";

    //$NON-NLS-1$
    public static final String ATT_PRIORITY_LEVEL = "priorityLevel";

    //$NON-NLS-1$
    public static final String ATT_MODE = "mode";

    //$NON-NLS-1$
    public static final String ATT_PLATFORMS = "platforms";

    //$NON-NLS-1$
    public static final String ATT_REPLACE = "replace";

    //$NON-NLS-1$
    public static final String ATT_FIND = "find";

    //$NON-NLS-1$
    public static final String TAG_KEYWORD_REFERENCE = "keywordReference";

    //$NON-NLS-1$
    public static final String ATT_THEME_ASSOCIATION = "themeAssociation";

    //$NON-NLS-1$
    public static final String ATT_THEME_ID = "themeId";

    //$NON-NLS-1$
    public static final String ATT_COLOR_AND_FONT_ID = "colorAndFontId";

    //$NON-NLS-1$
    public static final String ATT_OS_VERSION = "os_version";
}
