/***/
package org.eclipse.e4.ui.progress;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.progress.internal.ProgressManagerUtil;

/**
* Opens the progress view.
*
* @noreference
*/
public class OpenProgressViewHandler {

    @Execute
    public void openProgressView() {
        ProgressManagerUtil.openProgressView();
    }
}
