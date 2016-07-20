/***/
package org.eclipse.ui.internal.themes;

import java.util.Map;

/**
* Interface for the Theme descriptors
*
* @since 3.0
*/
public interface IThemeDescriptor extends IThemeElementDefinition {

    //$NON-NLS-1$
    public static final String TAB_BORDER_STYLE = "TAB_BORDER_STYLE";

    /**
* Returns the color overrides for this theme.
* @return ColorDefinition []
*/
    public ColorDefinition[] getColors();

    /**
* Returns the font overrides for this theme.
* @return GradientDefinition []
*/
    public FontDefinition[] getFonts();

    /**
* Returns the data map for this theme.
*
* @return the data map.  This will be read only.
*/
    public Map getData();
}
