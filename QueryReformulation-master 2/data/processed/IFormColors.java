/***/
package org.eclipse.ui.forms;

/**
* A place to hold all the color constants used in the forms package.
*
* @since 3.3
*/
public interface IFormColors {

    /**
* A prefix for all the keys.
*/
    //$NON-NLS-1$
    String PREFIX = "org.eclipse.ui.forms.";

    /**
* Key for the form title foreground color.
*/
    //$NON-NLS-1$
    String TITLE = PREFIX + "TITLE";

    /**
* A prefix for the header color constants.
*/
    //$NON-NLS-1$
    String H_PREFIX = PREFIX + "H_";

    /*
* A prefix for the section title bar color constants.
*/
    //$NON-NLS-1$
    String TB_PREFIX = PREFIX + "TB_";

    /**
* Key for the form header background gradient ending color.
*/
    //$NON-NLS-1$
    String H_GRADIENT_END = H_PREFIX + "GRADIENT_END";

    /**
* Key for the form header background gradient starting color.
*
*/
    //$NON-NLS-1$
    String H_GRADIENT_START = H_PREFIX + "GRADIENT_START";

    /**
* Key for the form header bottom keyline 1 color.
*
*/
    //$NON-NLS-1$
    String H_BOTTOM_KEYLINE1 = H_PREFIX + "BOTTOM_KEYLINE1";

    /**
* Key for the form header bottom keyline 2 color.
*
*/
    //$NON-NLS-1$
    String H_BOTTOM_KEYLINE2 = H_PREFIX + "BOTTOM_KEYLINE2";

    /**
* Key for the form header light hover color.
*
*/
    //$NON-NLS-1$
    String H_HOVER_LIGHT = H_PREFIX + "H_HOVER_LIGHT";

    /**
* Key for the form header full hover color.
*
*/
    //$NON-NLS-1$
    String H_HOVER_FULL = H_PREFIX + "H_HOVER_FULL";

    /**
* Key for the tree/table border color.
*/
    //$NON-NLS-1$
    String BORDER = PREFIX + "BORDER";

    /**
* Key for the section separator color.
*/
    //$NON-NLS-1$
    String SEPARATOR = PREFIX + "SEPARATOR";

    /**
* Key for the section title bar background.
*/
    //$NON-NLS-1$
    String TB_BG = TB_PREFIX + "BG";

    /**
* Key for the section title bar foreground.
*/
    //$NON-NLS-1$
    String TB_FG = TB_PREFIX + "FG";

    /**
* Key for the section title bar gradient.
* @deprecated Since 3.3, this color is not used any more. The
* tool bar gradient is created starting from {@link #TB_BG} to
* the section background color.
*/
    @Deprecated
    String TB_GBG = TB_BG;

    /**
* Key for the section title bar border.
*/
    //$NON-NLS-1$
    String TB_BORDER = TB_PREFIX + "BORDER";

    /**
* Key for the section toggle color. Since 3.1, this color is used for all
* section styles.
*/
    //$NON-NLS-1$
    String TB_TOGGLE = TB_PREFIX + "TOGGLE";

    /**
* Key for the section toggle hover color.
*
*/
    //$NON-NLS-1$
    String TB_TOGGLE_HOVER = TB_PREFIX + "TOGGLE_HOVER";
}
