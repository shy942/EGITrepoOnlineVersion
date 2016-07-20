/***/
package org.eclipse.ui.model;

// can't use ICU, public API
import java.text.Collator;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.ViewerSorter;

/**
* A viewer sorter that sorts elements with registered workbench adapters by their text property.
* Note that capitalization differences are not considered by this
* sorter, so a &gt; B &gt; c
*
* @see IWorkbenchAdapter
* @deprecated as of 3.3, use {@link WorkbenchViewerComparator} instead
*/
@Deprecated
public class WorkbenchViewerSorter extends ViewerSorter {

    /**
* Creates a workbench viewer sorter using the default collator.
*/
    public  WorkbenchViewerSorter() {
        super();
    }

    /**
* Creates a workbench viewer sorter using the given collator.
*
* @param collator the collator to use to sort strings
*/
    public  WorkbenchViewerSorter(Collator collator) {
        super(collator);
    }

    @Override
    public boolean isSorterProperty(Object element, String propertyId) {
        return propertyId.equals(IBasicPropertyConstants.P_TEXT);
    }
}
