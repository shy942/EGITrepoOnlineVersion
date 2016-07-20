/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IStickyViewDescriptor;

/**
* Utility class that will test various view properties.
*
* @since 3.0
*/
public final class ViewUtils {

    public static boolean findInStack(IViewPart[] stack, IViewPart target) {
        for (IViewPart element : stack) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }

    /**
* @param part the view part
*/
    public static boolean isCloseable(IViewPart part) {
        // FIXME: Facade claimed closeable perspectives where not supported
        return false;
    }

    /**
* @param part the view part
*/
    public static boolean isMoveable(IViewPart part) {
        // FIXME: Facade claimed moveable perspectives where not supported
        return false;
    }

    public static boolean isSticky(IViewPart part) {
        String id = part.getSite().getId();
        IStickyViewDescriptor[] descs = PlatformUI.getWorkbench().getViewRegistry().getStickyViews();
        for (IStickyViewDescriptor desc : descs) {
            if (desc.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
*
*/
    protected  ViewUtils() {
    //no-op
    }
}
