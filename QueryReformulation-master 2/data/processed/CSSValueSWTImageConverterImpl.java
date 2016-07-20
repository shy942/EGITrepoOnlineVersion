/***/
package org.eclipse.e4.ui.css.swt.properties.converters;

import org.eclipse.e4.ui.css.core.dom.properties.converters.AbstractCSSValueConverter;
import org.eclipse.e4.ui.css.core.dom.properties.converters.ICSSValueConverter;
import org.eclipse.e4.ui.css.core.dom.properties.converters.ICSSValueConverterConfig;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.helpers.CSSSWTImageHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.css.CSSValue;

public class CSSValueSWTImageConverterImpl extends AbstractCSSValueConverter {

    public static final ICSSValueConverter INSTANCE = new CSSValueSWTImageConverterImpl();

    public  CSSValueSWTImageConverterImpl() {
        super(Image.class);
    }

    @Override
    public Object convert(CSSValue value, CSSEngine engine, Object context) throws Exception {
        Display display = (Display) context;
        return CSSSWTImageHelper.getImage(value, engine.getResourcesLocatorManager(), display);
    }

    @Override
    public String convert(Object value, CSSEngine engine, Object context, ICSSValueConverterConfig config) throws Exception {
        // TODO : manage SWT image to CSS value conversion.
        return null;
    }
}
