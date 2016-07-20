/***/
package org.eclipse.e4.ui.css.core.dom.properties.converters;

import org.w3c.dom.css.RGBColor;

/**
* {@link ICSSValueConverterConfig} to manage format String of the
* {@link RGBColor}.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface ICSSValueConverterColorConfig extends ICSSValueConverterConfig {

    /*
* Format CSSValue string color into Hexadecimal.
*/
    public static final int COLOR_HEXA_FORMAT = 0;

    /*
* Format CSSValue string color into Color Name.
*/
    public static final int COLOR_NAME_FORMAT = 1;

    /*
* Format CSSValue string color into RGB.
*/
    public static final int COLOR_RGB_FORMAT = 2;

    /**
* Return format (Hexadecimal color, Color name, RGB color).
*
* @return
*/
    public int getFormat();
}
