/***/
package org.eclipse.e4.ui.internal.workbench.renderers.swt;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.ui.model.application.ui.menu.MItem;

/**
* A bridging interface with the 3.x ICommandService for registering element
* item update callbacks.
* <p>
* See bug 366568.
* </p>
*/
public interface IUpdateService {

    public Runnable registerElementForUpdate(ParameterizedCommand parameterizedCommand, MItem item);
}
