/***/
package org.eclipse.ui.internal.keys;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.eclipse.ui.internal.util.Util;
import org.eclipse.ui.keys.CharacterKey;
import org.eclipse.ui.keys.Key;
import org.eclipse.ui.keys.KeySequence;
import org.eclipse.ui.keys.KeyStroke;
import org.eclipse.ui.keys.ModifierKey;
import org.eclipse.ui.keys.SpecialKey;

/**
* Formats the key sequences and key strokes into the native human-readable
* format. This is typically what you would see on the menus for the given
* platform and locale.
*
* @since 3.0
*/
public class NativeKeyFormatter extends AbstractKeyFormatter {

    /**
* The key into the internationalization resource bundle for the delimiter
* to use between keys (on the Carbon platform).
*/
    //$NON-NLS-1$
    private static final String CARBON_KEY_DELIMITER_KEY = "CARBON_KEY_DELIMITER";

    /**
* A look-up table for the string representations of various carbon keys.
*/
    private static final HashMap CARBON_KEY_LOOK_UP = new HashMap();

    /**
* A comparator to sort modifier keys in the order that they would be
* displayed to a user. This comparator is platform-specific.
*/
    private static final Comparator MODIFIER_KEY_COMPARATOR = new NativeModifierKeyComparator();

    /**
* The resource bundle used by <code>format()</code> to translate formal
* string representations by locale.
*/
    private static final ResourceBundle RESOURCE_BUNDLE;

    /**
* The key into the internationalization resource bundle for the delimiter
* to use between key strokes (on the Win32 platform).
*/
    //$NON-NLS-1$
    private static final String WIN32_KEY_STROKE_DELIMITER_KEY = "WIN32_KEY_STROKE_DELIMITER";

    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(NativeKeyFormatter.class.getName());
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(CharacterKey.BS.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(CharacterKey.CR.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(CharacterKey.DEL.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(CharacterKey.SPACE.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(ModifierKey.ALT.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(ModifierKey.COMMAND.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(ModifierKey.CTRL.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(ModifierKey.SHIFT.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.ARROW_DOWN.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.ARROW_LEFT.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.ARROW_RIGHT.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.ARROW_UP.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.END.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.NUMPAD_ENTER.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.HOME.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.PAGE_DOWN.toString(), "?");
        //$NON-NLS-1$
        CARBON_KEY_LOOK_UP.put(SpecialKey.PAGE_UP.toString(), "?");
    }

    /**
* Formats an individual key into a human readable format. This uses an
* internationalization resource bundle to look up the key. This does the
* platform-specific formatting for Carbon.
*
* @param key
*            The key to format; must not be <code>null</code>.
* @return The key formatted as a string; should not be <code>null</code>.
*/
    @Override
    public String format(Key key) {
        String name = key.toString();
        // TODO consider platform-specific resource bundles
        if (org.eclipse.jface.util.Util.isMac()) {
            String formattedName = (String) CARBON_KEY_LOOK_UP.get(name);
            if (formattedName != null) {
                return formattedName;
            }
        }
        return super.format(key);
    }

    @Override
    protected String getKeyDelimiter() {
        // We must do the look up every time, as our locale might change.
        if (org.eclipse.jface.util.Util.isMac()) {
            return Util.translateString(RESOURCE_BUNDLE, CARBON_KEY_DELIMITER_KEY, Util.ZERO_LENGTH_STRING, false, false);
        } else {
            return Util.translateString(RESOURCE_BUNDLE, KEY_DELIMITER_KEY, KeyStroke.KEY_DELIMITER, false, false);
        }
    }

    @Override
    protected String getKeyStrokeDelimiter() {
        // We must do the look up every time, as our locale might change.
        if (org.eclipse.jface.util.Util.isWindows()) {
            return Util.translateString(RESOURCE_BUNDLE, WIN32_KEY_STROKE_DELIMITER_KEY, KeySequence.KEY_STROKE_DELIMITER, false, false);
        } else {
            return Util.translateString(RESOURCE_BUNDLE, KEY_STROKE_DELIMITER_KEY, KeySequence.KEY_STROKE_DELIMITER, false, false);
        }
    }

    @Override
    protected Comparator getModifierKeyComparator() {
        return MODIFIER_KEY_COMPARATOR;
    }
}
