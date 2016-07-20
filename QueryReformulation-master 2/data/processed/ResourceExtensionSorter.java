/***/
package org.eclipse.ui.internal.navigator.resources.workbench;

// we can't convert this to a ViewerComparator.
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.views.navigator.ResourceSorter;
import com.ibm.icu.text.Collator;

/**
* TODO - This refers to the deprecated ResourceSorter, however we are stuck with this
* for the time being because the CommonSorter extension point uses a ViewerSorter.
* We should provide an option for a ViewerComparator and then we can remove this
* class.
*
* @since 3.2
*
*/
public class ResourceExtensionSorter extends ResourceSorter {

    private Collator icuCollator;

    /**
* Construct a sorter that uses the name of the resource as its sorting
* criteria.
*
*/
    public  ResourceExtensionSorter() {
        super(ResourceSorter.NAME);
        icuCollator = Collator.getInstance();
    }

    @Override
    protected int compareNames(IResource resource1, IResource resource2) {
        return icuCollator.compare(resource1.getName(), resource2.getName());
    }
}
