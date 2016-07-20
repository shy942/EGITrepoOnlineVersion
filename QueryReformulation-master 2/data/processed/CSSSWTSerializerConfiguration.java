/***/
package org.eclipse.e4.ui.css.swt.serializers;

import org.eclipse.e4.ui.css.core.serializers.CSSSerializerConfiguration;

/**
* {@link CSSSerializerConfiguration} configuration used to get style of SWT control.
*/
public class CSSSWTSerializerConfiguration extends CSSSerializerConfiguration {

    public static final CSSSerializerConfiguration INSTANCE = new CSSSWTSerializerConfiguration();

    public  CSSSWTSerializerConfiguration() {
        super.addAttributeFilter("style");
    }
}
