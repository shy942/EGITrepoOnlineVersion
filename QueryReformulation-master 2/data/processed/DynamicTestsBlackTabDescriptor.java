/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.tests.views.properties.tabbed.Activator;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsBlackSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsBlackTabDescriptor extends AbstractTabDescriptor {

    private Image image;

    public  DynamicTestsBlackTabDescriptor() {
        super();
        getSectionDescriptors().add(new DynamicTestsBlackSectionDescriptor(new DynamicTestsTypeMapper()));
    }

    @Override
    public String getAfterTab() {
        //$NON-NLS-1$
        return "ColorTab";
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "default";
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "BlackTab";
    }

    @Override
    public Image getImage() {
        if (image == null) {
            image = Activator.getImageDescriptor(//$NON-NLS-1$
            "icons/black_triangle.gif").createImage();
        }
        return image;
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Black";
    }

    @Override
    public boolean isIndented() {
        return true;
    }
}
