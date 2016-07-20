/***/
package org.eclipse.e4.ui.css.swt.helpers;

public class ThemeElementDefinitionHelper {

    public static String escapeId(String id) {
        return id.replace('.', '-');
    }

    public static String normalizeId(String id) {
        return id.replace('-', '.');
    }
}
