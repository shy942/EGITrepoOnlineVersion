/***/
package org.eclipse.ui.internal.themes;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.e4.ui.internal.css.swt.definition.IThemeElementDefinitionOverridable;
import org.eclipse.e4.ui.internal.css.swt.definition.IThemesExtension;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
* @since 3.5
*
*/
public class ThemesExtension implements IThemesExtension {

    //$NON-NLS-1$
    public static final String DEFAULT_CATEGORY_ID = "org.eclipse.ui.themes.CssTheme";

    private List<IThemeElementDefinitionOverridable<?>> definitions = new ArrayList();

    @Override
    public void addFontDefinition(String symbolicName) {
        FontDefinition definition = new FontDefinition(formatDefaultName(FontDefinition.class, symbolicName), symbolicName, null, null, DEFAULT_CATEGORY_ID, true, null);
        definition.appendState(ThemeElementDefinition.State.ADDED_BY_CSS);
        definitions.add(definition);
    }

    @Override
    public void addColorDefinition(String symbolicName) {
        ColorDefinition definition = new ColorDefinition(formatDefaultName(ColorDefinition.class, symbolicName), symbolicName, null, null, DEFAULT_CATEGORY_ID, true, null, getPluginId());
        definition.appendState(ThemeElementDefinition.State.ADDED_BY_CSS);
        definitions.add(definition);
    }

    private String getPluginId() {
        return WorkbenchPlugin.getDefault().getBundle().getSymbolicName();
    }

    public List<IThemeElementDefinitionOverridable<?>> getDefinitions() {
        return definitions;
    }

    private String formatDefaultName(Class<?> cls, String symbolicName) {
        //$NON-NLS-1$
        return String.format("%s #%s", cls.getSimpleName(), symbolicName);
    }
}
