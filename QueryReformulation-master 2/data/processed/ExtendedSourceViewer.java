/***/
package org.eclipse.ui.tests.internal;

import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class ExtendedSourceViewer extends SourceViewer {

    /**
* Constructs a new source viewer. The vertical ruler is initially visible.
* The viewer has not yet been initialized with a source viewer configuration.
*
* @param parent the parent of the viewer's control
* @param ruler the vertical ruler used by this source viewer
* @param styles the SWT style bits
*/
    public  ExtendedSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
        super(parent, ruler, styles);
    }

    /*
* @see Viewer#getSelection()
*/
    @Override
    public ISelection getSelection() {
        Point p = getSelectedRange();
        if (p.x == -1 || p.y == -1) {
            return TextSelection.emptySelection();
        }
        return new ExtendedTextSelection(getDocument(), p.x, p.y);
    }

    /**
* Sends out a selection changed event to all registered listeners.
*
* @param offset the offset of the newly selected range
* @param length the length of the newly selected range
*/
    @Override
    protected void selectionChanged(int offset, int length) {
        ISelection selection = new ExtendedTextSelection(getDocument(), offset, length);
        SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
        fireSelectionChanged(event);
    }
}
