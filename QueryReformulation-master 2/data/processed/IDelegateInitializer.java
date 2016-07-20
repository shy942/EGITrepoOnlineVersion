/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.e4.ui.model.application.MApplicationElement;

/**
*
*/
public interface IDelegateInitializer {

    public void initialize(MApplicationElement model, Object delegate);
}
