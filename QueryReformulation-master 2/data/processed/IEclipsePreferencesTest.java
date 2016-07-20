/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import org.eclipse.core.internal.preferences.EclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.junit.Test;

public class IEclipsePreferencesTest extends CSSSWTTestCase {

    @Test
    public void testIEclipsePreferences() {
        // given
        IEclipsePreferences preferences = new EclipsePreferences(null, "org.eclipse.jdt.ui") {
        };
        engine = createEngine("IEclipsePreferences#org-eclipse-jdt-ui{preferences:" + "'semanticHighlighting.abstractClass.color=128,255,0'," + "'java_bracket=0,255,255'," + "'java_bracket_italic=true'," + "'java_bracket_underline='" + "}", display);
        // when
        engine.applyStyles(preferences, false);
        // then
        assertEquals("128,255,0", preferences.get("semanticHighlighting.abstractClass.color", null));
        assertEquals("0,255,255", preferences.get("java_bracket", null));
        assertEquals("true", preferences.get("java_bracket_italic", null));
        assertEquals("", preferences.get("java_bracket_underline", null));
    }
}
