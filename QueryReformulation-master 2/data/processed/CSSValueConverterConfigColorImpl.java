/***/
package org.eclipse.e4.ui.css.core.dom.properties.converters;

/**
* CSS Value converter color config to format the CSSValue string color.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public class CSSValueConverterConfigColorImpl implements ICSSValueConverterColorConfig {

    public static final ICSSValueConverterConfig COLOR_HEXA_FORMAT_CONFIG = new CSSValueConverterConfigColorImpl(COLOR_HEXA_FORMAT);

    public static final ICSSValueConverterConfig COLOR_NAME_FORMAT_CONFIG = new CSSValueConverterConfigColorImpl(COLOR_NAME_FORMAT);

    public static final ICSSValueConverterConfig COLOR_RGB_FORMAT_CONFIG = new CSSValueConverterConfigColorImpl(COLOR_RGB_FORMAT);

    private int format;

    public  CSSValueConverterConfigColorImpl(int format) {
        this.format = format;
    }

    @Override
    public int getFormat() {
        return format;
    }
}
