/***/
package org.eclipse.ui.branding;

/**
* These constants define the set of properties that the UI expects to
* be available via <code>IBundleGroup.getProperty(String)</code>.
*
* @since 3.0
* @see org.eclipse.core.runtime.IBundleGroup#getProperty(String)
*/
public interface IBundleGroupConstants {

    /**
* The text to show in an "about features" dialog.
*/
    //$NON-NLS-1$
    public static final String ABOUT_TEXT = "aboutText";

    /**
* An image which can be shown in an "about features" dialog (32x32).
* <p>
* The value is a fully qualified valid URL.
* </p>
*/
    //$NON-NLS-1$
    public static final String FEATURE_IMAGE = "featureImage";

    /**
* A help reference for the feature's tips and tricks page (optional).
*/
    //$NON-NLS-1$
    public static final String TIPS_AND_TRICKS_HREF = "tipsAndTricksHref";

    /**
* The feature's welcome page (special XML-based format).
* <p>
* The value is a fully qualified valid URL.
* </p>
* Products designed to run "headless" typically would not have such a page.
*/
    //$NON-NLS-1$
    public static final String WELCOME_PAGE = "welcomePage";

    /**
* The id of a perspective in which to show the welcome page
* (optional).
*/
    //$NON-NLS-1$
    public static final String WELCOME_PERSPECTIVE = "welcomePerspective";

    /**
* The URL of the license page for the feature (optional).
* <p>
* The value is a fully qualified valid URL.
* </p>
*/
    //$NON-NLS-1$
    public static final String LICENSE_HREF = "licenseHref";

    /**
* The feature's branding bundle id (optional).
*
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String BRANDING_BUNDLE_ID = "brandingBundleId";

    /**
* The feature's branding bundle version (optional).
*
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String BRANDING_BUNDLE_VERSION = "brandingBundleVersion";
}
