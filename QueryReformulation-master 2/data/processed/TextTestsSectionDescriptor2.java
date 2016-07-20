/***/
package org.eclipse.ui.tests.views.properties.tabbed.text;

import org.eclipse.jface.viewers.IFilter;

/**
* A section descriptor for the text test view that should be filtered and not
* shown.
*
* @author Anthony Hunter
* @since 3.4
*/
public class TextTestsSectionDescriptor2 extends TextTestsSectionDescriptor {

    public  TextTestsSectionDescriptor2(String word, String tabId) {
        super(word, tabId);
    }

    /*
* @see
* org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor#getFilter
* ()
*/
    @Override
    public IFilter getFilter() {
        return new IFilter() {

            @Override
            public boolean select(Object toTest) {
                return false;
            }
        };
    }
}
