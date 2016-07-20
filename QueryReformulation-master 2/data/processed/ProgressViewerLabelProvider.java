/***/
package org.eclipse.ui.internal.progress;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Control;

/**
* The ProgressViewerLabelProvider is the label provider for progress viewers.
*/
public class ProgressViewerLabelProvider extends LabelProvider {

    private Control control;

    @Override
    public String getText(Object element) {
        JobTreeElement info = (JobTreeElement) element;
        return ProgressManagerUtil.shortenText(info.getCondensedDisplayString(), control);
    }

    /**
* Create a new instance of the receiver within the control.
*
* @param progressControl The control that the label is
* being created for.
*/
    public  ProgressViewerLabelProvider(Control progressControl) {
        super();
        control = progressControl;
    }
}
