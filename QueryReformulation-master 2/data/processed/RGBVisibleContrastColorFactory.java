/***/
package org.eclipse.ui.internal.themes;

import java.util.Hashtable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.themes.ColorUtil;
import org.eclipse.ui.themes.IColorFactory;

// https://bugs.eclipse.org/477487
public class RGBVisibleContrastColorFactory implements IColorFactory, IExecutableExtension {

    private String fg, bg, altBg;

    @Override
    public RGB createColor() {
        RGB cfg, cbg, cbgAlt;
        if (fg != null) {
            cfg = ColorUtil.getColorValue(fg);
        } else {
            cfg = new RGB(0, 0, 0);
        }
        if (bg != null) {
            cbg = ColorUtil.getColorValue(bg);
        } else {
            cbg = new RGB(255, 255, 255);
        }
        if (altBg != null) {
            cbgAlt = ColorUtil.getColorValue(altBg);
        } else {
            cbgAlt = new RGB(255, 255, 255);
        }
        float bfg = cfg.getHSB()[2];
        float bbg = cbg.getHSB()[2];
        float bbgAlt = cbgAlt.getHSB()[2];
        if (Math.abs(bbg - bfg) < 0.5f && Math.abs(bbgAlt - bfg) > Math.abs(bbg - bfg)) {
            return cbgAlt;
        }
        return cbg;
    }

    /**
* This executable extension requires parameters to be explicitly declared
* via the second method described in the <code>IExecutableExtension</code>
* documentation. This class expects that there will be three parameters,
* <code>foreground</code>, <code>background</code> and
* <code>alternativeBackground</code>. These values may either be RGB
* triples or SWT constants.
*/
    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
        if (data instanceof Hashtable) {
            @SuppressWarnings("unchecked") Hashtable<String, String> table = (Hashtable<String, String>) data;
            //$NON-NLS-1$
            fg = table.get("foreground");
            //$NON-NLS-1$
            bg = table.get("background");
            //$NON-NLS-1$
            altBg = table.get("alternativeBackground");
        }
    }
}
