/***/
package org.eclipse.jface.bindings.keys;

/**
* <p>
* A facilitiy for converting the formal representation for key strokes
* (i.e., used in persistence) into real key stroke instances.
* </p>
*
* @since 3.1
*/
public interface IKeyLookup {

    /**
* The formal name of the 'Alt' key.
*/
    //$NON-NLS-1$
    public static final String ALT_NAME = "ALT";

    /**
* The formal name of the 'Arrow Down' key.
*/
    //$NON-NLS-1$
    public static final String ARROW_DOWN_NAME = "ARROW_DOWN";

    /**
* The formal name of the 'Arrow Left' key.
*/
    //$NON-NLS-1$
    public static final String ARROW_LEFT_NAME = "ARROW_LEFT";

    /**
* The formal name of the 'Arrow Right' key.
*/
    //$NON-NLS-1$
    public static final String ARROW_RIGHT_NAME = "ARROW_RIGHT";

    /**
* The formal name of the 'Arrow Up' key.
*/
    //$NON-NLS-1$
    public static final String ARROW_UP_NAME = "ARROW_UP";

    /**
* An alternate name for the backspace key.
*/
    //$NON-NLS-1$
    public static final String BACKSPACE_NAME = "BACKSPACE";

    /**
* The formal name for the 'Break' key.
*/
    //$NON-NLS-1$
    public static final String BREAK_NAME = "BREAK";

    /**
* The formal name of the backspace key.
*/
    //$NON-NLS-1$
    public static final String BS_NAME = "BS";

    /**
* The formal name for the 'Caps Lock' key.
*/
    //$NON-NLS-1$
    public static final String CAPS_LOCK_NAME = "CAPS_LOCK";

    /**
* The formal name of the 'Command' key.
*/
    //$NON-NLS-1$
    public static final String COMMAND_NAME = "COMMAND";

    /**
* The formal name of the carriage return (U+000D)
*/
    //$NON-NLS-1$
    public static final String CR_NAME = "CR";

    /**
* The formal name of the 'Ctrl' key.
*/
    //$NON-NLS-1$
    public static final String CTRL_NAME = "CTRL";

    /**
* The formal name of the delete (U+007F) key
*/
    //$NON-NLS-1$
    public static final String DEL_NAME = "DEL";

    /**
* An alternative name for the delete key.
*/
    //$NON-NLS-1$
    public static final String DELETE_NAME = "DELETE";

    /**
* The formal name of the 'End' key.
*/
    //$NON-NLS-1$
    public static final String END_NAME = "END";

    /**
* An alternative name for the enter key.
*/
    //$NON-NLS-1$
    public static final String ENTER_NAME = "ENTER";

    /**
* The formal name of the escape (U+001B) key.
*/
    //$NON-NLS-1$
    public static final String ESC_NAME = "ESC";

    /**
* An alternative name for the escape key.
*/
    //$NON-NLS-1$
    public static final String ESCAPE_NAME = "ESCAPE";

    /**
* The formal name of the 'F1' key.
*/
    //$NON-NLS-1$
    public static final String F1_NAME = "F1";

    /**
* The formal name of the 'F10' key.
*/
    //$NON-NLS-1$
    public static final String F10_NAME = "F10";

    /**
* The formal name of the 'F11' key.
*/
    //$NON-NLS-1$
    public static final String F11_NAME = "F11";

    /**
* The formal name of the 'F12' key.
*/
    //$NON-NLS-1$
    public static final String F12_NAME = "F12";

    /**
* The formal name of the 'F13' key.
*/
    //$NON-NLS-1$
    public static final String F13_NAME = "F13";

    /**
* The formal name of the 'F14' key.
*/
    //$NON-NLS-1$
    public static final String F14_NAME = "F14";

    /**
* The formal name of the 'F15' key.
*/
    //$NON-NLS-1$
    public static final String F15_NAME = "F15";

    /**
* The formal name of the 'F16' key.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String F16_NAME = "F16";

    /**
* The formal name of the 'F17' key.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String F17_NAME = "F17";

    /**
* The formal name of the 'F18' key.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String F18_NAME = "F18";

    /**
* The formal name of the 'F19' key.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String F19_NAME = "F19";

    /**
* The formal name of the 'F20' key.
*
* @since 3.6
*/
    //$NON-NLS-1$
    public static final String F20_NAME = "F20";

    /**
* The formal name of the 'F2' key.
*/
    //$NON-NLS-1$
    public static final String F2_NAME = "F2";

    /**
* The formal name of the 'F3' key.
*/
    //$NON-NLS-1$
    public static final String F3_NAME = "F3";

    /**
* The formal name of the 'F4' key.
*/
    //$NON-NLS-1$
    public static final String F4_NAME = "F4";

    /**
* The formal name of the 'F5' key.
*/
    //$NON-NLS-1$
    public static final String F5_NAME = "F5";

    /**
* The formal name of the 'F6' key.
*/
    //$NON-NLS-1$
    public static final String F6_NAME = "F6";

    /**
* The formal name of the 'F7' key.
*/
    //$NON-NLS-1$
    public static final String F7_NAME = "F7";

    /**
* The formal name of the 'F8' key.
*/
    //$NON-NLS-1$
    public static final String F8_NAME = "F8";

    /**
* The formal name of the 'F9' key.
*/
    //$NON-NLS-1$
    public static final String F9_NAME = "F9";

    /**
* The formal name of the form feed (U+000C) key.
*/
    //$NON-NLS-1$
    public static final String FF_NAME = "FF";

    /**
* The formal name of the 'Home' key.
*/
    //$NON-NLS-1$
    public static final String HOME_NAME = "HOME";

    /**
* The formal name of the 'Insert' key.
*/
    //$NON-NLS-1$
    public static final String INSERT_NAME = "INSERT";

    /**
* The formal name of the line feed (U+000A) key.
*/
    //$NON-NLS-1$
    public static final String LF_NAME = "LF";

    /**
* The formal name of the 'M1' key.
*/
    //$NON-NLS-1$
    public static final String M1_NAME = "M1";

    /**
* The formal name of the 'M2' key.
*/
    //$NON-NLS-1$
    public static final String M2_NAME = "M2";

    /**
* The formal name of the 'M3' key.
*/
    //$NON-NLS-1$
    public static final String M3_NAME = "M3";

    /**
* The formal name of the 'M4' key.
*/
    //$NON-NLS-1$
    public static final String M4_NAME = "M4";

    /**
* The formal name of the null (U+0000) key.
*/
    //$NON-NLS-1$
    public static final String NUL_NAME = "NUL";

    /**
* The formal name of the 'NumLock' key.
*/
    //$NON-NLS-1$
    public static final String NUM_LOCK_NAME = "NUM_LOCK";

    /**
* The formal name of the '0' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_0_NAME = "NUMPAD_0";

    /**
* The formal name of the '1' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_1_NAME = "NUMPAD_1";

    /**
* The formal name of the '2' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_2_NAME = "NUMPAD_2";

    /**
* The formal name of the '3' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_3_NAME = "NUMPAD_3";

    /**
* The formal name of the '4' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_4_NAME = "NUMPAD_4";

    /**
* The formal name of the '5' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_5_NAME = "NUMPAD_5";

    /**
* The formal name of the '6' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_6_NAME = "NUMPAD_6";

    /**
* The formal name of the '7' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_7_NAME = "NUMPAD_7";

    /**
* The formal name of the '8' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_8_NAME = "NUMPAD_8";

    /**
* The formal name of the '9' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_9_NAME = "NUMPAD_9";

    /**
* The formal name of the 'Add' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_ADD_NAME = "NUMPAD_ADD";

    /**
* The formal name of the 'Decimal' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_DECIMAL_NAME = "NUMPAD_DECIMAL";

    /**
* The formal name of the 'Divide' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_DIVIDE_NAME = "NUMPAD_DIVIDE";

    /**
* The formal name of the 'Enter' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_ENTER_NAME = "NUMPAD_ENTER";

    /**
* The formal name of the '=' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_EQUAL_NAME = "NUMPAD_EQUAL";

    /**
* The formal name of the 'Multiply' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_MULTIPLY_NAME = "NUMPAD_MULTIPLY";

    /**
* The formal name of the 'Subtract' key on the numpad.
*/
    //$NON-NLS-1$
    public static final String NUMPAD_SUBTRACT_NAME = "NUMPAD_SUBTRACT";

    /**
* The formal name of the 'Page Down' key.
*/
    //$NON-NLS-1$
    public static final String PAGE_DOWN_NAME = "PAGE_DOWN";

    /**
* The formal name of the 'Page Up' key.
*/
    //$NON-NLS-1$
    public static final String PAGE_UP_NAME = "PAGE_UP";

    /**
* The formal name for the 'Pause' key.
*/
    //$NON-NLS-1$
    public static final String PAUSE_NAME = "PAUSE";

    /**
* The formal name for the 'Print Screen' key.
*/
    //$NON-NLS-1$
    public static final String PRINT_SCREEN_NAME = "PRINT_SCREEN";

    /**
* An alternative name for the enter key.
*/
    //$NON-NLS-1$
    public static final String RETURN_NAME = "RETURN";

    /**
* The formal name for the 'Scroll Lock' key.
*/
    //$NON-NLS-1$
    public static final String SCROLL_LOCK_NAME = "SCROLL_LOCK";

    /**
* The formal name of the 'Shift' key.
*/
    //$NON-NLS-1$
    public static final String SHIFT_NAME = "SHIFT";

    /**
* The formal name of the space (U+0020) key.
*/
    //$NON-NLS-1$
    public static final String SPACE_NAME = "SPACE";

    /**
* The formal name of the tab (U+0009) key.
*/
    //$NON-NLS-1$
    public static final String TAB_NAME = "TAB";

    /**
* The formal name of the vertical tab (U+000B) key.
*/
    //$NON-NLS-1$
    public static final String VT_NAME = "VT";

    /**
* Looks up a single natural key by its formal name, and returns the integer
* representation for this natural key
*
* @param name
*            The formal name of the natural key to look-up; must not be
*            <code>null</code>.
* @return The integer representation of this key. If the natural key cannot
*         be found, then this method returns <code>0</code>.
*/
    public int formalKeyLookup(String name);

    /**
* Looks up a single natural key by its formal name, and returns the integer
* representation for this natural key
*
* @param name
*            The formal name of the natural key to look-up; must not be
*            <code>null</code>.
* @return The integer representation of this key. If the natural key cannot
*         be found, then this method returns <code>0</code>.
*/
    public Integer formalKeyLookupInteger(String name);

    /**
* Looks up a single modifier key by its formal name, and returns the integer
* representation for this modifier key
*
* @param name
*            The formal name of the modifier key to look-up; must not be
*            <code>null</code>.
* @return The integer representation of this key. If the modifier key
*         cannot be found, then this method returns <code>0</code>.
*/
    public int formalModifierLookup(String name);

    /**
* Looks up a key value, and returns the formal string representation for
* that key
*
* @param key
*            The key to look-up.
* @return The formal string representation of this key. If this key cannot
*         be found, then it is simply the character corresponding to that
*         integer value.
*/
    public String formalNameLookup(int key);

    /**
* Returns the integer representation of the ALT key.
*
* @return The ALT key
*/
    public int getAlt();

    /**
* Returns the integer representation of the COMMAND key.
*
* @return The COMMAND key
*/
    public int getCommand();

    /**
* Returns the integer representation of the CTRL key.
*
* @return The CTRL key
*/
    public int getCtrl();

    /**
* Returns the integer representation of the SHIFT key.
*
* @return The SHIFT key
*/
    public int getShift();

    /**
* Returns whether the given key is a modifier key.
*
* @param key
*            The integer value of the key to check.
* @return <code>true</code> if the key is one of the modifier keys;
*         <code>false</code> otherwise.
*/
    public boolean isModifierKey(int key);
}
