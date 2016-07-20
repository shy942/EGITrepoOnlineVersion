/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.UIJob;

/**
* The LazyVirtualTableView is the VirtualTableView with lazy content.
*/
public class LazyDeferredVirtualTableView extends VirtualTableView {

    /**
* Create a new instance of the receiver.
*/
    public  LazyDeferredVirtualTableView() {
        super();
    }

    @Override
    protected IContentProvider getContentProvider() {
        return new ILazyContentProvider() {

            int rangeStart = -1;

            int rangeEnd = -1;

            UIJob updateJob = new UIJob("Update") {

                @Override
                public IStatus runInUIThread(IProgressMonitor monitor) {
                    if (viewer.getControl().isDisposed()) {
                        return Status.CANCEL_STATUS;
                    }
                    int rangeLength = rangeEnd - rangeStart;
                    for (int i = 0; i <= rangeLength; i++) {
                        int index = i + rangeStart;
                        viewer.replace("Element " + String.valueOf(index), index);
                    }
                    return Status.OK_STATUS;
                }
            };

            @Override
            public void updateElement(int index) {
                int begin = Math.max(0, index - 50);
                int end = Math.min(begin + 50, 9999);
                // Initial case
                if (rangeStart == -1 || rangeEnd == -1) {
                    rangeStart = begin;
                    rangeEnd = end;
                    updateJob.schedule(1000);
                    return;
                }
                // Are we in the range already being worked on?
                if (index >= rangeStart && index <= rangeEnd) {
                    return;
                }
                // Are we outside of the old range?
                if (begin > rangeEnd || end < rangeStart) {
                    viewer.getTable().clear(rangeStart, rangeEnd);
                    rangeStart = begin;
                    rangeEnd = end;
                    updateJob.schedule(1000);
                    return;
                }
                // Shift if it is before
                if (begin < rangeStart) {
                    rangeStart = begin;
                    int oldEnd = rangeEnd;
                    rangeEnd = end;
                    viewer.getTable().clear(end + 1, oldEnd);
                    updateJob.schedule(1000);
                    return;
                }
                // Shift if it is after
                if (end > rangeEnd) {
                    rangeEnd = end;
                    int oldStart = rangeStart;
                    rangeStart = begin;
                    viewer.getTable().clear(oldStart, rangeStart - 1);
                    updateJob.schedule(1000);
                    return;
                }
            }

            @Override
            public void dispose() {
            // Do Nothing
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Do nothing.
            }
        };
    }

    @Override
    protected void resetInput() {
        viewer.setItemCount(itemCount);
        super.resetInput();
    }
}
