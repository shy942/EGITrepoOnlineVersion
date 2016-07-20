/***/
package org.eclipse.ui.internal.forms.widgets;

import java.util.Hashtable;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

/**
* This segment serves as break within a paragraph. It has no data -
* just starts a new line and resets the locator.
*/
public class BreakSegment extends ParagraphSegment {

    @Override
    public boolean advanceLocator(GC gc, int wHint, Locator locator, Hashtable<String, Object> objectTable, boolean computeHeightOnly) {
        if (locator.rowHeight == 0) {
            FontMetrics fm = gc.getFontMetrics();
            locator.rowHeight = fm.getHeight();
        }
        if (computeHeightOnly)
            locator.collectHeights();
        locator.resetCaret();
        locator.width = locator.x;
        locator.y += locator.rowHeight;
        locator.rowHeight = 0;
        locator.leading = 0;
        return true;
    }

    @Override
    public void paint(GC gc, boolean hover, Hashtable<String, Object> resourceTable, boolean selected, SelectionData selData, Rectangle repaintRegion) {
    //nothing to paint
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return false;
    }

    @Override
    public void layout(GC gc, int width, Locator locator, Hashtable<String, Object> ResourceTable, boolean selected) {
        locator.resetCaret();
        if (locator.rowHeight == 0) {
            FontMetrics fm = gc.getFontMetrics();
            locator.rowHeight = fm.getHeight();
        }
        locator.y += locator.rowHeight;
        locator.rowHeight = 0;
        locator.rowCounter++;
    }

    @Override
    public void computeSelection(GC gc, Hashtable<String, Object> resourceTable, SelectionData selData) {
        selData.markNewLine();
    }
}
