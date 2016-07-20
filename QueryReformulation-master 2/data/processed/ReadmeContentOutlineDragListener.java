/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.PluginTransferData;

/**
* A drag listener for the readme editor's content outline page.
* Allows dragging of content segments into views that support
* the <code>TextTransfer</code> or <code>PluginTransfer</code> transfer types.
*/
public class ReadmeContentOutlineDragListener extends DragSourceAdapter {

    private ReadmeContentOutlinePage page;

    /**
* Creates a new drag listener for the given page.
*/
    public  ReadmeContentOutlineDragListener(ReadmeContentOutlinePage page) {
        this.page = page;
    }

    @Override
    public void dragSetData(DragSourceEvent event) {
        if (PluginTransfer.getInstance().isSupportedType(event.dataType)) {
            byte[] segmentData = getSegmentText().getBytes();
            event.data = new PluginTransferData(ReadmeDropActionDelegate.ID, segmentData);
            return;
        }
        if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
            event.data = getSegmentText();
            return;
        }
    }

    /**
* Returns the text of the currently selected readme segment.
*/
    private String getSegmentText() {
        StringBuffer result = new StringBuffer();
        ISelection selection = page.getSelection();
        if (selection instanceof org.eclipse.jface.viewers.IStructuredSelection) {
            Object[] selected = ((IStructuredSelection) selection).toArray();
            //$NON-NLS-1$
            result.append("\n");
            for (int i = 0; i < selected.length; i++) {
                if (selected[i] instanceof MarkElement) {
                    result.append(((MarkElement) selected[i]).getLabel(selected[i]));
                    //$NON-NLS-1$
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }
}
