/***/
package org.eclipse.ui.tests.views.properties.tabbed.text;

import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the text test view.
*
* @author Anthony Hunter
*/
public class TextTestsTabDescriptor extends AbstractTabDescriptor {

    private String word;

    public  TextTestsTabDescriptor(String aWord) {
        super();
        this.word = aWord;
        getSectionDescriptors().add(new TextTestsSectionDescriptor(aWord, getId()));
        /* TextTestsSectionDescriptor2 added to the tests and it is filtered */
        getSectionDescriptors().add(new TextTestsSectionDescriptor2(aWord, getId()));
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "default";
    }

    @Override
    public String getId() {
        return word + "@" + Integer.toHexString(word.hashCode());
    }

    @Override
    public String getLabel() {
        return word;
    }
}
