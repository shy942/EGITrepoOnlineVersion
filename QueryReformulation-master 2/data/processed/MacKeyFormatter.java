/***/
package org.eclipse.ui.internal.keys;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ResourceBundle;
import org.eclipse.ui.internal.util.Util;
import org.eclipse.ui.keys.CharacterKey;
import org.eclipse.ui.keys.Key;
import org.eclipse.ui.keys.KeySequence;
import org.eclipse.ui.keys.ModifierKey;
import org.eclipse.ui.keys.SpecialKey;

public final class MacKeyFormatter extends AbstractKeyFormatter {

    private static final class MacModifierKeyComparator extends AbstractModifierKeyComparator {

        @Override
        protected int rank(ModifierKey modifierKey) {
            if (ModifierKey.SHIFT.equals(modifierKey)) {
                return 0;
            }
            if (ModifierKey.CTRL.equals(modifierKey)) {
                return 1;
            }
            if (ModifierKey.ALT.equals(modifierKey)) {
                return 2;
            }
            if (ModifierKey.COMMAND.equals(modifierKey)) {
                return 3;
            }
            return Integer.MAX_VALUE;
        }
    }

    private static final HashMap KEY_LOOKUP = new HashMap();

    private static final Comparator MODIFIER_KEY_COMPARATOR = new MacModifierKeyComparator();

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MacKeyFormatter.class.getName());

    static {
        KEY_LOOKUP.put(CharacterKey.BS.toString(), //$NON-NLS-1$
        "?");
        KEY_LOOKUP.put(CharacterKey.CR.toString(), //$NON-NLS-1$
        "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(CharacterKey.DEL.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(CharacterKey.SPACE.toString(), "?");
        KEY_LOOKUP.put(ModifierKey.ALT.toString(), //$NON-NLS-1$
        "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(ModifierKey.COMMAND.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(ModifierKey.CTRL.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(ModifierKey.SHIFT.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.ARROW_DOWN.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.ARROW_LEFT.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.ARROW_RIGHT.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.ARROW_UP.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.END.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.NUMPAD_ENTER.toString(), "?");
        KEY_LOOKUP.put(SpecialKey.HOME.toString(), //$NON-NLS-1$
        "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.PAGE_DOWN.toString(), "?");
        //$NON-NLS-1$
        KEY_LOOKUP.put(SpecialKey.PAGE_UP.toString(), "?");
    }

    @Override
    public String format(Key key) {
        String string = (String) KEY_LOOKUP.get(key.toString());
        return string != null ? string : super.format(key);
    }

    @Override
    protected String getKeyDelimiter() {
        return Util.translateString(RESOURCE_BUNDLE, KEY_DELIMITER_KEY, Util.ZERO_LENGTH_STRING, false, false);
    }

    @Override
    protected String getKeyStrokeDelimiter() {
        return Util.translateString(RESOURCE_BUNDLE, KEY_STROKE_DELIMITER_KEY, KeySequence.KEY_STROKE_DELIMITER, false, false);
    }

    @Override
    protected Comparator getModifierKeyComparator() {
        return MODIFIER_KEY_COMPARATOR;
    }
}
