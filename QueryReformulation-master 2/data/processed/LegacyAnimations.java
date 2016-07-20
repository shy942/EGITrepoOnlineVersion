/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.LegacyAnimationFeedback;
import org.eclipse.ui.internal.RectangleAnimationFeedbackBase;

/**
* Return the default (legacy) animation.
*
* @since 3.3
*
*/
public class LegacyAnimations extends Animations {

    /** Default c'tor */
    public  LegacyAnimations() {
    }

    @Override
    public RectangleAnimationFeedbackBase createFeedback(Shell shell) {
        return new LegacyAnimationFeedback(shell, null, null);
    }
}
