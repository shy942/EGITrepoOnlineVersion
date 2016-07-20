/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Shell;

/**
* @since 3.7
*
*/
public class DummyTitlePathUpdater extends TitlePathUpdater {

    @Override
    public void updateTitlePath(Shell window, String path) {
    // do nothing
    }
}
