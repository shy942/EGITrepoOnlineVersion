/***/
package org.eclipse.ui.examples.jobs.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

public class SlowElementAdapter implements IDeferredWorkbenchAdapter {

    private static boolean serializeFetching = false;

    private static boolean batchFetchedChildren = false;

    final ISchedulingRule mutexRule = new ISchedulingRule() {

        @Override
        public boolean isConflicting(ISchedulingRule rule) {
            return rule == mutexRule;
        }

        @Override
        public boolean contains(ISchedulingRule rule) {
            return rule == mutexRule;
        }
    };

    @Override
    public void fetchDeferredChildren(Object object, IElementCollector collector, IProgressMonitor monitor) {
        if (object instanceof SlowElement) {
            Object[] children = ((SlowElement) object).getChildren();
            if (isBatchFetchedChildren()) {
                sleep(4000);
                collector.add(children, monitor);
            } else {
                for (int i = 0; i < children.length; i++) {
                    if (monitor.isCanceled()) {
                        return;
                    }
                    collector.add(children[i], monitor);
                    sleep(4000);
                }
            }
        }
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public ISchedulingRule getRule(final Object object) {
        if (isSerializeFetching())
            return mutexRule;
        // Allow several SlowElement parents to fetch children concurrently
        return null;
    }

    @Override
    public Object[] getChildren(Object object) {
        if (object instanceof SlowElement) {
            return ((SlowElement) object).getChildren();
        }
        return new Object[0];
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel(Object o) {
        if (o instanceof SlowElement) {
            return ((SlowElement) o).getName();
        }
        //$NON-NLS-1$
        return "unknown";
    }

    @Override
    public Object getParent(Object o) {
        if (o instanceof SlowElement) {
            return ((SlowElement) o).getParent();
        }
        return null;
    }

    /**
* @return Returns the batchFetchedChildren.
*/
    public static boolean isBatchFetchedChildren() {
        return batchFetchedChildren;
    }

    /**
* @param batchFetchedChildren
*                   The batchFetchedChildren to set.
*/
    public static void setBatchFetchedChildren(boolean batchFetchedChildren) {
        SlowElementAdapter.batchFetchedChildren = batchFetchedChildren;
    }

    /**
* @return Returns the serializeFetching.
*/
    public static boolean isSerializeFetching() {
        return serializeFetching;
    }

    /**
* @param serializeFetching
*                   The serializeFetching to set.
*/
    public static void setSerializeFetching(boolean serializeFetching) {
        SlowElementAdapter.serializeFetching = serializeFetching;
    }
}
