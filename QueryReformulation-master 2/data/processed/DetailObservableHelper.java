/***/
package org.eclipse.core.internal.databinding.observable.masterdetail;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.internal.databinding.observable.Util;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/* package */
class DetailObservableHelper {

    /* package */
    static void warnIfDifferentRealms(Realm detailRealm, Realm innerObservableRealm) {
        if (!Util.equals(detailRealm, innerObservableRealm)) {
            Throwable throwable = new Throwable();
            throwable.fillInStackTrace();
            String message = //$NON-NLS-1$
            "Inner observable realm (" + innerObservableRealm + //$NON-NLS-1$
            ") not equal to detail realm (" + detailRealm + //$NON-NLS-1$
            ")";
            Policy.getLog().log(new Status(IStatus.WARNING, Policy.JFACE_DATABINDING, message, throwable));
        }
    }
}
