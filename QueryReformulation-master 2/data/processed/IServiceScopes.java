/***/
package org.eclipse.ui.services;

/**
* Different levels of service locators supported by the workbench.
*
* @since 3.3
* @noextend This interface is not intended to be extended by clients.
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IServiceScopes {

    /**
* The global service locator scope.
*/
    //$NON-NLS-1$
    public static final String WORKBENCH_SCOPE = "org.eclipse.ui.services.IWorkbench";

    /**
* A sub-scope to the global scope that is not the workbench window.
*
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String DIALOG_SCOPE = "org.eclipse.ui.services.IDialog";

    /**
* A workbench window service locator scope.
*/
    //$NON-NLS-1$
    public static final String WINDOW_SCOPE = "org.eclipse.ui.IWorkbenchWindow";

    /**
* A part site service locator scope.  Found in editors and views.
*/
    //$NON-NLS-1$
    public static final String PARTSITE_SCOPE = "org.eclipse.ui.part.IWorkbenchPartSite";

    /**
* A page site service locator scope.  Found in pages in a PageBookView.
*/
    //$NON-NLS-1$
    public static final String PAGESITE_SCOPE = "org.eclipse.ui.part.PageSite";

    /**
* An editor site within a MultiPageEditorPart.
*/
    //$NON-NLS-1$
    public static final String MPESITE_SCOPE = "org.eclipse.ui.part.MultiPageEditorSite";
}
