/***/
package org.eclipse.e4.ui.services.help;

/**
* Provides services related to the help system.
*/
public interface EHelpService {

    //$NON-NLS-1$
    public static final String HELP_CONTEXT_ID = "HelpContextId";

    /**
* Calls the help support system to display the given help context ID.
*
* @param contextId
*            the ID of the context to display
*/
    void displayHelp(String contextId);

    /**
* Sets the given id for the help system on the given object.
*
* @param element
*            the element on which to register the help id
* @param helpContextId
*            the id to use when help system is invoked
*/
    void setHelp(Object element, String helpContextId);
}
