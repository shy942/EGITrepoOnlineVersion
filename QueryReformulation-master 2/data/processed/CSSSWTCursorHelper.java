/***/
package org.eclipse.e4.ui.css.swt.helpers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public class CSSSWTCursorHelper {

    private static final String DEFAULT_CURSOR = "defaultCursor";

    /**
* @see http://www.w3schools.com/css/pr_class_cursor.asp
*/
    public static Cursor getSWTCursor(CSSValue value, Display display) {
        if (!(value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE)) {
            return null;
        }
        int i = getSWTCursorId((CSSPrimitiveValue) value);
        if (i == SWT.NONE) {
            return null;
        }
        Cursor cursor = new Cursor(display, i);
        return cursor;
    }

    public static String getCSSCursor(Cursor cursor) {
        if (cursor == null) {
            return "auto";
        }
        // }
        return "auto";
    }

    public static int getSWTCursorId(CSSPrimitiveValue value) {
        String cursorName = value.getStringValue();
        if ("default".equals(cursorName)) {
            // The default cursor (often an arrow)
            return SWT.NONE;
        }
        if ("auto".equals(cursorName)) {
            // Default. The browser sets a cursor
            return SWT.NONE;
        }
        if ("crosshair".equals(cursorName)) {
            // The cursor render as a crosshair
            return SWT.CURSOR_CROSS;
        }
        if ("pointer".equals(cursorName)) {
            // The cursor render as a pointer (a hand) that indicates a link
            return SWT.CURSOR_HAND;
        }
        if ("move".equals(cursorName)) {
            // The cursor indicates something that should be moved
            return SWT.CURSOR_UPARROW;
        }
        if ("e-resize".equals(cursorName)) {
            // (east)
            return SWT.CURSOR_SIZEE;
        }
        if ("ne-resize".equals(cursorName)) {
            // right (north/east)
            return SWT.CURSOR_SIZENE;
        }
        if ("nw-resize".equals(cursorName)) {
            // left (north/west)
            return SWT.CURSOR_SIZENW;
        }
        if ("n-resize".equals(cursorName)) {
            // (north)
            return SWT.CURSOR_SIZEN;
        }
        if ("se-resize".equals(cursorName)) {
            // and right (south/east)
            return SWT.CURSOR_SIZESE;
        }
        if ("sw-resize".equals(cursorName)) {
            // and left (south/west)
            return SWT.CURSOR_SIZESW;
        }
        if ("s-resize".equals(cursorName)) {
            // (south)
            return SWT.CURSOR_SIZES;
        }
        if ("w-resize".equals(cursorName)) {
            // (west)
            return SWT.CURSOR_SIZEW;
        }
        if ("text".equals(cursorName)) {
            // The cursor indicates text
            return SWT.CURSOR_UPARROW;
        }
        if ("wait".equals(cursorName)) {
            // an hourglass)
            return SWT.CURSOR_WAIT;
        }
        if ("help".equals(cursorName)) {
            // mark or a balloon)
            return SWT.CURSOR_HELP;
        }
        return SWT.NONE;
    }

    public static void storeDefaultCursor(Control control) {
        if (control.getData(DEFAULT_CURSOR) == null) {
            control.setData(DEFAULT_CURSOR, control.getCursor());
        }
    }

    public static void restoreDefaultCursor(Control control) {
        Cursor defaultCursor = (Cursor) control.getData(DEFAULT_CURSOR);
        if (defaultCursor != null) {
            control.setCursor(defaultCursor.isDisposed() ? control.getDisplay().getSystemCursor(SWT.ARROW) : defaultCursor);
        }
    }
}
