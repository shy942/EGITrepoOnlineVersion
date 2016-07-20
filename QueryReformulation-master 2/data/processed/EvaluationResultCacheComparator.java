/***/
package org.eclipse.ui.internal.services;

import java.util.Comparator;
import org.eclipse.ui.internal.util.Util;

/**
* <p>
* Compares two evaluation result caches ({@link IEvaluationResultCache}). The
* cache with the lowest source priority is considered "greater". In the event
* of a tie, other characteristics are checked.
* </p>
* <p>
* This class is only intended for use within the
* <code>org.eclipse.ui.workbench</code> plug-in.
* </p>
*
* @since 3.2
*
*/
public final class EvaluationResultCacheComparator implements Comparator {

    @Override
    public final int compare(final Object object1, final Object object2) {
        if (Util.equals(object2, object1)) {
            return 0;
        }
        final IEvaluationResultCache cache1 = (IEvaluationResultCache) object1;
        final IEvaluationResultCache cache2 = (IEvaluationResultCache) object2;
        int comparison;
        // that's not compatible with equals.
        if (object1 instanceof Comparable && object2 instanceof Comparable) {
            comparison = Util.compare((Comparable) object2, (Comparable) object1);
            if (comparison != 0) {
                return comparison;
            }
        }
        return Util.compareIdentity(cache2, cache1);
    }
}
