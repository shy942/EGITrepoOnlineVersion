/***/
package org.eclipse.e4.ui.workbench.modeling;

import org.eclipse.e4.ui.model.application.ui.basic.MWindow;

/**
* A handler that can be inserted into the context of the application or a particular window to
* determine whether the window should be closed or not.
*
* @noreference This interface is not intended to be referenced by clients.
* @since 1.0
*/
public interface IWindowCloseHandler {

    public boolean close(MWindow window);
}
