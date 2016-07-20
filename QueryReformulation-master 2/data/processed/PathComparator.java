/***/
package org.eclipse.ui.internal.navigator.resources.nested;

import java.util.Comparator;
import org.eclipse.core.runtime.IPath;

/**
* @since 3.3
*
*/
public class PathComparator implements Comparator<IPath> {

    @Override
    public int compare(IPath arg0, IPath arg1) {
        return arg0.toString().compareTo(arg1.toString());
    }
}
