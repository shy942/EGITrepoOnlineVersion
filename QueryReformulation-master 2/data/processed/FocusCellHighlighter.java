/***/
package org.eclipse.jface.viewers;

/**
* @since 3.3
*
*/
public abstract class FocusCellHighlighter {

    private ColumnViewer viewer;

    private SWTFocusCellManager mgr;

    /**
* @param viewer
*/
    public  FocusCellHighlighter(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    void setMgr(SWTFocusCellManager mgr) {
        this.mgr = mgr;
    }

    /**
* @return the focus cell
*/
    public ViewerCell getFocusCell() {
        // to the SWTFocusCellManager instance
        if (mgr != null) {
            // cell update (which might cause scrolling) happens
            return mgr._getFocusCell();
        }
        return viewer.getColumnViewerEditor().getFocusCell();
    }

    /**
* Called by the framework when the focus cell has changed. Subclasses may
* extend.
*
* @param cell
*            the new focus cell or <code>null</code> if no new cell
*            receives the focus
* @deprecated use {@link #focusCellChanged(ViewerCell, ViewerCell)} instead
*/
    @Deprecated
    protected void focusCellChanged(ViewerCell cell) {
    }

    /**
* Called by the framework when the focus cell has changed. Subclasses may
* extend.
* <p>
* <b>The default implementation for this method calls
* focusCellChanged(ViewerCell). Subclasses should override this method
* rather than {@link #focusCellChanged(ViewerCell)} .</b>
*
* @param newCell
*            the new focus cell or <code>null</code> if no new cell
*            receives the focus
* @param oldCell
*            the old focus cell or <code>null</code> if no cell has been
*            focused before
* @since 3.4
*/
    protected void focusCellChanged(ViewerCell newCell, ViewerCell oldCell) {
        focusCellChanged(newCell);
    }

    /**
* This method is called by the framework to initialize this cell
* highlighter object. Subclasses may extend.
*/
    protected void init() {
    }
}
