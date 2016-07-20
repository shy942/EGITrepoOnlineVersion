/***/
package org.eclipse.e4.ui.progress;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.progress.internal.FinishedJobs;

/**
* Removes finished jobs from the progress view.
*
* @noreference
*/
public class ClearAllHandler {

    @Execute
    public static void clearAll(FinishedJobs finishedJobs) {
        finishedJobs.clearAll();
    }
}
