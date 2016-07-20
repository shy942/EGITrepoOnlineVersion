/***/
package org.eclipse.ui.internal.activities.ws;

/**
* @since 3.1
*/
public interface WorkbenchTriggerPoints {

    /**
* New wizard trigger point identifier.  Value <code>org.eclipse.ui.newWizards</code>.
*/
    //$NON-NLS-1$
    public static final String NEW_WIZARDS = "org.eclipse.ui.newWizards";

    /**
* Perspective opening trigger point identifier.  Value <code>org.eclipse.ui.openPerspectiveDialog</code>.
*/
    //$NON-NLS-1$
    public static final String OPEN_PERSPECITVE_DIALOG = "org.eclipse.ui.openPerspectiveDialog";

    /**
* Import wizards trigger point identifier.  Value <code>org.eclipse.ui.importWizards</code>.
*/
    //$NON-NLS-1$
    public static final String IMPORT_WIZARDS = "org.eclipse.ui.importWizards";

    /**
* Export wizards trigger point identifier.  Value <code>org.eclipse.ui.exportWizards</code>.
*/
    //$NON-NLS-1$
    public static final String EXPORT_WIZARDS = "org.eclipse.ui.exportWizards";
}
