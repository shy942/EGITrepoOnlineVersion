/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.RectangleAnimationFeedbackBase;
import org.eclipse.ui.internal.RectangleAnimationImageFeedback;

/**
* Return an animation feedback that uses images.
*
* @since 3.3
*
*/
public class ImageAnimationTweak extends Animations {

    /** Default c'tor */
    public  ImageAnimationTweak() {
    }

    @Override
    public RectangleAnimationFeedbackBase createFeedback(Shell shell) {
        return new RectangleAnimationImageFeedback(shell, null, null);
    }
}
