/***/
package org.eclipse.ui.tests.navigator.extension;

import java.text.Collator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TestSorterData extends ViewerSorter {

    public static String _sorterProperty;

    public static Object _sorterElement;

    public boolean _forward = true;

    public static void resetTest() {
        _sorterProperty = null;
        _sorterElement = null;
    }

    public  TestSorterData() {
        super();
    }

    public  TestSorterData(Collator collator) {
        super(collator);
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof TestExtensionTreeData) {
            if (e2 instanceof TestExtensionTreeData) {
                TestExtensionTreeData lvalue = (TestExtensionTreeData) e1;
                TestExtensionTreeData rvalue = (TestExtensionTreeData) e2;
                if (_forward)
                    return lvalue.getName().compareTo(rvalue.getName());
                return rvalue.getName().compareTo(lvalue.getName());
            }
            return -1;
        } else if (e2 instanceof TestExtensionTreeData) {
            return +1;
        }
        return super.compare(viewer, e1, e2);
    }

    @Override
    public boolean isSorterProperty(Object element, String property) {
        _sorterProperty = property;
        _sorterElement = element;
        return false;
    }
}
