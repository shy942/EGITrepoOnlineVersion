/***/
package org.eclipse.ui.views.properties;

import org.eclipse.ui.internal.views.properties.IDEPropertiesMessages;

/**
* This interface documents the property constants used by the resource
* property source.
*/
public interface IResourcePropertyConstants {

    /**
* The <code>IResource</code> property key for name.
*/
    public static final String P_LABEL_RES = IDEPropertiesMessages.IResourcePropertyConstants_name;

    /**
* The <code>IResource</code> property key for path.
*/
    //$NON-NLS-1$
    public static final String P_PATH_RES = "org.eclipse.ui.path";

    /**
* The <code>IResource</code> property key for display path.
*/
    public static final String P_DISPLAYPATH_RES = IDEPropertiesMessages.IResourcePropertyConstants_path;

    /**
* The <code>IResource</code> property key for read-only.
*/
    //$NON-NLS-1$
    public static final String P_EDITABLE_RES = "org.eclipse.ui.editable";

    /**
* The <code>IResource</code> property key for display read-only.
*/
    public static final String P_DISPLAYEDITABLE_RES = IDEPropertiesMessages.IResourcePropertyConstants_editable;

    /**
* The <code>IResource</code> property key for read-only.
*/
    //$NON-NLS-1$
    public static final String P_DERIVED_RES = "org.eclipse.ui.derived";

    /**
* The <code>IResource</code> property key for display read-only.
*/
    public static final String P_DISPLAYDERIVED_RES = IDEPropertiesMessages.IResourcePropertyConstants_derived;

    /**
* The <code>IResource</code> property key for location.
*/
    //$NON-NLS-1$
    public static final String P_LOCATION_RES = "org.eclipse.ui.location";

    /**
* The <code>IResource</code> property key for display location.
*/
    public static final String P_DISPLAYLOCATION_RES = IDEPropertiesMessages.IResourcePropertyConstants_location;

    /**
* The <code>IResource</code> property key for resolved location.
*/
    //$NON-NLS-1$,
    public static final String P_RESOLVED_LOCATION_RES = "org.eclipse.ui.resolvedLocation";

    /**
* The <code>IResource</code> property key for display resolved location.
*/
    public static final String P_DISPLAYRESOLVED_LOCATION_RES = IDEPropertiesMessages.IResourcePropertyConstants_resolvedLocation;

    /**
* The <code>IResource</code> property key for linked.
*/
    //$NON-NLS-1$,
    public static final String P_LINKED_RES = "org.eclipse.ui.linked";

    /**
* The <code>IResource</code> property key for display linked.
*/
    public static final String P_DISPLAYLINKED_RES = IDEPropertiesMessages.IResourcePropertyConstants_linked;

    /**
* The <code>IResource</code> category for the base values
*/
    public static final String P_FILE_SYSTEM_CATEGORY = IDEPropertiesMessages.IResourcePropertyConstants_info;

    /**
* The <code>IResource</code> property key for path.
*/
    //$NON-NLS-1$
    public static final String P_SIZE_RES = "org.eclipse.ui.size";

    /**
* The <code>IResource</code> property key for displaying size
*/
    public static final String P_DISPLAY_SIZE = IDEPropertiesMessages.IResourcePropertyConstants_size;

    /**
* The <code>IResource</code> property key for path.
*/
    //$NON-NLS-1$
    public static final String P_LAST_MODIFIED_RES = "org.eclipse.ui.lastmodified";

    /**
* The <code>IResource</code> category for last modified
*/
    public static final String P_DISPLAY_LAST_MODIFIED = IDEPropertiesMessages.IResourcePropertyConstants_lastModified;
}
