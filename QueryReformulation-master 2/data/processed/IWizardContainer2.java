/***/
package org.eclipse.jface.wizard;

/**
* <p><code>IWizardContainer2</code> is a supplement to
* <code>IWizardContainer</code> that adds a method for updating the size of
* the wizard shell based on the contents of the current page.</p>
*
* <p>The class <code>WizardDialog</code> provides a fully functional
* implementation of this interface which will meet the needs of
* most clients. However, clients are also free to implement this
* interface if <code>WizardDialog</code> does not suit their needs.
* </p>
*
* @see org.eclipse.jface.wizard.IWizardContainer
* @since 3.0
*/
public interface IWizardContainer2 extends IWizardContainer {

    /**
* Updates the window size to reflect the state of the current wizard.
* <p>
* This method is called by the container itself
* when its wizard changes and may be called
* by the wizard at other times to force a window
* size change.
* </p>
*/
    public void updateSize();
}