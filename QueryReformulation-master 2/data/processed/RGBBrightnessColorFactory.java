/***/
package org.eclipse.ui.internal.themes;

import java.util.Hashtable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.themes.ColorUtil;
import org.eclipse.ui.themes.IColorFactory;

/**
* @since 3.105
*
*/
public class RGBBrightnessColorFactory implements IColorFactory, IExecutableExtension {

    String color, scaleFactor;

    @Override
    public RGB createColor() {
        RGB rgb = ColorUtil.getColorValue(color);
        float scale = Float.parseFloat(scaleFactor);
        float[] hsb = rgb.getHSB();
        float b = hsb[2] * scale;
        if (b < 0)
            b = 0;
        if (b > 1)
            b = 1;
        return new RGB(hsb[0], hsb[1], b);
    }

    /**
* This executable extension requires parameters to be explicitly declared
* via the second method described in the <code>IExecutableExtension</code>
* documentation. This class expects that there will be two parameters,
* <code>color</code> that describe the color to be blended (this values may
* either be RGB triples or SWT constants) and <code>scaleFactor</code>
* which is the brightness scale factor with 1.0 having the same brightness
* as the original color.
*
* @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
*      java.lang.String, java.lang.Object)
*/
    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
        if (data instanceof Hashtable) {
            Hashtable table = (Hashtable) data;
            //$NON-NLS-1$
            color = (String) table.get("color");
            //$NON-NLS-1$
            scaleFactor = (String) table.get("scaleFactor");
        }
    }
}
