/***/
package org.eclipse.ui.statushandlers;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.ui.PlatformUI;

/**
* This interface contains common constants important for
* <code>StatusAdapter</code>.
*
* <p>
* This interface should not be implemented or extended by clients.
* </p>
*
* @since 3.4
*/
public interface IStatusAdapterConstants {

    /**
* Common prefix for properties defined in this interface.
*/
    static final String PROPERTY_PREFIX = PlatformUI.PLUGIN_ID + //$NON-NLS-1$
    ".workbench.statusHandlers.adapters";

    /**
* This property is used to add title to the adapter. If the adapter is
* shown in a dialog, this property is used to create title of the dialog.
*
* <p>
* The property must be of type <code>String</code>.
* </p>
*/
    public static final QualifiedName TITLE_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "title");

    /**
* This property is used to add a timestamp to the adapter. If the adapter
* is shown in the UI, this property can be used for sorting and showing
* information about the status creation time.
*
* <p>
* The property must be of type <code>Long</code>.
* </p>
*/
    public static final QualifiedName TIMESTAMP_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "timestamp");

    /**
* This property is used to add an explanation to the adapter. If the
* adapter is shown in the UI, this property should be used to present
* additional explanation for the status.
*
* <p>
* The property must be of type <code>String</code>.
* </p>
*/
    public static final QualifiedName EXPLANATION_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "explanation");

    /**
* This property is used to add a hint to the adapter. If the adapter is
* shown in the UI, this property should be used to present suggested
* actions that have to be performed by the user.
*
* <p>
* The property must be of type <code>String</code>.
* </p>
*/
    public static final QualifiedName HINT_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "suggestedAction");
}
