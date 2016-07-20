/***/
package org.eclipse.ui.views.properties.tabbed;

/**
* Default implementation of a type mapper.
*
* @author Anthony Hunter
*/
public class AbstractTypeMapper implements ITypeMapper {

    public Class mapType(Object object) {
        return object.getClass();
    }
}
