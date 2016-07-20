/***/
package org.eclipse.ui.part;

import org.eclipse.ui.IWorkbenchPart;

/**
* This interface is used to identify workbench views which
* allow other parts (typically the active part) to supply
* their contents.
* The interface allows access to the part which contributed the current
* contents.
*/
public interface IContributedContentsView {

    /**
* Returns the workbench part which contributed the
* current contents of this view.
*
* @return the part which contributed the current contents
*/
    public IWorkbenchPart getContributingPart();
}
