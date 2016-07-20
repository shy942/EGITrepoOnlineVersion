/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.tweaklets.Tweaklets.TweakKey;

/**
*
* Tweaklet to update the Shell when the active editor is changed
*
* @since 3.7
*
*/
public abstract class TitlePathUpdater {

    public static TweakKey KEY = new Tweaklets.TweakKey(TitlePathUpdater.class);

    static {
        Tweaklets.setDefault(KEY, new DummyTitlePathUpdater());
    }

    public abstract void updateTitlePath(Shell window, String path);
}
