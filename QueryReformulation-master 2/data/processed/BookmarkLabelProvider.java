/***/
package org.eclipse.ui.views.bookmarkexplorer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.views.bookmarkexplorer.BookmarkMessages;

/**
* Provides labels for the bookmark navigator table
*/
class BookmarkLabelProvider extends LabelProvider implements ITableLabelProvider {

    private Image image;

    private ImageDescriptor desc;

    static final int COLUMN_ICON = 0;

    static final int COLUMN_DESCRIPTION = 1;

    static final int COLUMN_RESOURCE = 2;

    static final int COLUMN_FOLDER = 3;

    static final int COLUMN_LOCATION = 4;

    public  BookmarkLabelProvider(BookmarkNavigator view) {
        //$NON-NLS-1$
        desc = IDEWorkbenchPlugin.getIDEImageDescriptor("obj16/bkmrk_tsk.png");
        image = JFaceResources.getResources().createImageWithDefault(desc);
    }

    @Override
    public void dispose() {
        if (image != null) {
            JFaceResources.getResources().destroyImage(desc);
            image = null;
        }
    }

    @Override
    public Image getImage(Object element) {
        return image;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        if (!(element instanceof IMarker)) {
            //$NON-NLS-1$
            return "";
        }
        IMarker marker = (IMarker) element;
        switch(columnIndex) {
            case COLUMN_DESCRIPTION:
                //$NON-NLS-1$
                return marker.getAttribute(IMarker.MESSAGE, "");
            case COLUMN_RESOURCE:
                return marker.getResource().getName();
            case COLUMN_FOLDER:
                return getContainerName(marker);
            case COLUMN_LOCATION:
                {
                    int line = marker.getAttribute(IMarker.LINE_NUMBER, -1);
                    if (line == -1) {
                        //$NON-NLS-1$
                        return "";
                    }
                    return NLS.bind(BookmarkMessages.LineIndicator_text, String.valueOf(line));
                }
        }
        //$NON-NLS-1$
        return "";
    }

    @Override
    public Image getColumnImage(Object element, int index) {
        if (index == COLUMN_ICON) {
            return image;
        }
        return null;
    }

    /**
* Returns the container name if it is defined, or empty string if not.
*/
    public static String getContainerName(IMarker marker) {
        IPath path = marker.getResource().getFullPath();
        int n = path.segmentCount() - 1;
        // n is the number of segments in container, not path
        if (n <= 0) {
            //$NON-NLS-1$
            return "";
        }
        int len = 0;
        for (int i = 0; i < n; ++i) {
            len += path.segment(i).length();
        }
        // account for /'s
        if (n > 1) {
            len += n - 1;
        }
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                sb.append('/');
            }
            sb.append(path.segment(i));
        }
        return sb.toString();
    }
}
