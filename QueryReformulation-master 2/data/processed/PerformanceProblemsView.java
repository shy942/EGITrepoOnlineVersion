/***/
package org.eclipse.ui.tests.performance.parts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.internal.views.markers.ExtendedMarkersView;
import org.eclipse.ui.internal.views.markers.ProblemsView;

/**
* PerformanceProblemsView is a problems view created
* for the performance tests.
* @since 3.2
*
*/
public class PerformanceProblemsView extends ProblemsView {

    public Tree getTreeWidget() {
        TreeViewer viewer;
        try {
            Method m = ExtendedMarkersView.class.getDeclaredMethod("getViewer");
            m.setAccessible(true);
            viewer = (TreeViewer) m.invoke(this);
            return viewer.getTree();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
