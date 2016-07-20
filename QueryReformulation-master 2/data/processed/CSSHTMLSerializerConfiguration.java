/***/
package org.eclipse.e4.ui.css.core.serializers;

/**
* CSS HTML Serializer configuration used by {@link CSSSerializer} to filter the
* attribute type of the HTML widget like input[type='text'].
*/
public class CSSHTMLSerializerConfiguration extends CSSSerializerConfiguration {

    public static final CSSSerializerConfiguration INSTANCE = new CSSHTMLSerializerConfiguration();

    public  CSSHTMLSerializerConfiguration() {
        super.addAttributeFilter("type");
    }
}
