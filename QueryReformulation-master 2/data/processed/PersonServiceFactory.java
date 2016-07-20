/***/
package org.eclipse.ui.examples.contributions.model;

import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

/**
* Supply the person service to the IServiceLocator framework.
*
* @since 3.4
*/
public class PersonServiceFactory extends AbstractServiceFactory {

    @Override
    public Object create(Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
        if (!IPersonService.class.equals(serviceInterface)) {
            return null;
        }
        Object parentService = parentLocator.getService(IPersonService.class);
        if (parentService == null) {
            // the global level person service implementation
            return new PersonService(locator);
        }
        return new PersonServiceSlave(locator, (IPersonService) parentService);
    }
}
