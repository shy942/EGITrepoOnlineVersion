/***/
package org.eclipse.ui.views.properties.tabbed;

/**
* Represents a section descriptor provider for tabbed property sections.
*
* @author Anthony Hunter
*/
public interface ISectionDescriptorProvider {

    /**
* Returns all section descriptors for the contributor.
* @return all section descriptors.
*/
    public ISectionDescriptor[] getSectionDescriptors();
}
