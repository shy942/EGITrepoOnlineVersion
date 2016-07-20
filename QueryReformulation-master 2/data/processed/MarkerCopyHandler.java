/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.MarkerTransfer;
import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerViewHandler;

/**
* MarkerCopyHandler is the handler for the copy action when the markers view is
* selected.
*
* @since 3.4
*
*/
public class MarkerCopyHandler extends MarkerViewHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        ExtendedMarkersView view = getView(event);
        if (view == null)
            return null;
        setClipboard(view);
        return this;
    }

    /**
* Set the workbench clipboard for the markers.
*
* @param view
*/
    private void setClipboard(ExtendedMarkersView view) {
        IMarker[] markers = view.getSelectedMarkers();
        String markerReport = createMarkerReport(view, markers);
        // Place the markers on the clipboard
        Object[] data;
        Transfer[] transferTypes;
        if (markerReport == null) {
            data = new Object[] { markers };
            transferTypes = new Transfer[] { MarkerTransfer.getInstance() };
        } else {
            data = new Object[] { markers, markerReport };
            transferTypes = new Transfer[] { MarkerTransfer.getInstance(), TextTransfer.getInstance() };
        }
        view.getClipboard().setContents(data, transferTypes);
    }

    /**
* Creates a plain-text report of the selected markers based on predefined
* properties.
*
* @param view
*            the view being copied
* @param markers
* @return the marker report
*/
    static String createMarkerReport(ExtendedMarkersView view, IMarker[] markers) {
        StringBuffer report = new StringBuffer();
        MarkerField[] fields = view.getVisibleFields();
        //$NON-NLS-1$
        final String NEWLINE = System.getProperty("line.separator");
        final char DELIMITER = '\t';
        // create header
        for (int i = 0; i < fields.length; i++) {
            report.append(fields[i].getColumnHeaderText());
            if (i == fields.length - 1) {
                report.append(NEWLINE);
            } else {
                report.append(DELIMITER);
            }
        }
        for (int i = 0; i < markers.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                report.append(fields[j].getValue(MarkerSupportInternalUtilities.newMarkerItem(markers[i])));
                if (j == fields.length - 1) {
                    report.append(NEWLINE);
                } else {
                    report.append(DELIMITER);
                }
            }
        }
        return report.toString();
    }
}
