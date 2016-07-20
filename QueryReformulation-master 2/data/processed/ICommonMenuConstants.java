/***/
/*
* Created on Feb 5, 2004
*
* To change the template for this generated file go to
* Window - Preferences - Java - Code Generation - Code and Comments
*/
package org.eclipse.ui.navigator;

import org.eclipse.ui.IWorkbenchActionConstants;

/**
* Defines strings used for menu insertion points.
*
* @since 3.2
* @noextend This interface is not intended to be extended by clients.
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface ICommonMenuConstants {

    /**
* Pop-up menu: name of group for the top of the menu (value
* <code>"group.top"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_TOP = "group.top";

    /**
* Pop-up menu: name of group for goto actions (value
* <code>"group.goto"</code>).
* <p>
* Examples for open actions are:
* <ul>
* <li>Go Into</li>
* <li>Go To</li>
* </ul>
* </p>
*/
    //$NON-NLS-1$
    public static final String GROUP_GOTO = "group.goto";

    /**
* Pop-up menu: name of group for show actions (value
* <code>"group.show"</code>).
* <p>
* Examples for show actions are:
* <ul>
* <li>Show in Navigator</li>
* <li>Show in Type Hierarchy</li>
* </ul>
* </p>
*/
    //$NON-NLS-1$
    public static final String GROUP_SHOW = "group.show";

    /**
* Pop-up menu: name of group for new actions (value
* <code>"group.new"</code>).
* <p>
* Examples for new actions are:
* <ul>
* <li>Create new class</li>
* <li>Create new interface</li>
* </ul>
* </p>
*/
    //$NON-NLS-1$
    public static final String GROUP_NEW = "group.new";

    /**
* Pop-up menu: name of group for open actions (value
* <code>"group.open"</code>).
* <p>
* Examples for open actions are:
* <ul>
* <li>Open To</li>
* <li>Open With</li>
* </ul>
* </p>
*
* @see #GROUP_OPEN_WITH
*/
    //$NON-NLS-1$
    public static final String GROUP_OPEN = "group.open";

    /**
* Pop-up menu: name of group for open actions (value
* <code>"group.openWith"</code>).
* <p>
* Examples for open actions are:
* <ul>
* <li>Open With</li>
* </ul>
* </p>
*/
    //$NON-NLS-1$
    public static final String GROUP_OPEN_WITH = "group.openWith";

    /**
* Pop-up menu: name of group for porting actions (value
* <code>"group.port"</code>).
* <p>
* Examples for open actions are:
* <ul>
* <li>Import</li>
* <li>Export</li>
* </ul>
* </p>
*/
    //$NON-NLS-1$
    public static final String GROUP_PORT = "group.port";

    /**
* Pop-up menu: name of group for properties actions (value
* <code>"group.edit"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_EDIT = "group.edit";

    /**
* Pop-up menu: name of group for build actions (value
* <code>"group.build"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_BUILD = "group.build";

    /**
* Pop-up menu: name of group for reorganize actions (value
* <code>"group.reorganize"</code>).
*/
    public static final String GROUP_REORGANIZE = IWorkbenchActionConstants.GROUP_REORGANIZE;

    /**
* Pop-up menu: name of group for code generation actions ( value
* <code>"group.generate"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_GENERATE = "group.generate";

    /**
* Pop-up menu: name of group for source actions. This is an alias for
* <code>GROUP_GENERATE</code> to be more consistent with main menu bar
* structure.
*
*/
    public static final String GROUP_SOURCE = GROUP_GENERATE;

    /**
* Pop-up menu: name of group for search actions (value
* <code>"group.search"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_SEARCH = "group.search";

    /**
* Pop-up menu: name of group for additional actions (value
* <code>"additions"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_ADDITIONS = "additions";

    /**
* Pop-up menu: name of group for viewer setup actions (value
* <code>"group.viewerSetup"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_VIEWER_SETUP = "group.viewerSetup";

    /**
* Pop-up menu: name of group for properties actions (value
* <code>"group.properties"</code>).
*/
    //$NON-NLS-1$
    public static final String GROUP_PROPERTIES = "group.properties";
}
