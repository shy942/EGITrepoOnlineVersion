/***/
package org.eclipse.ui;

/**
* A filter for selecting Saveables.
* @see IWorkbench#saveAll(org.eclipse.jface.window.IShellProvider, org.eclipse.jface.operation.IRunnableContext, ISaveableFilter, boolean)
* @since 3.3
*/
public interface ISaveableFilter {

    /**
* Indicate whether the given saveable matches this filter.
* @param saveable the saveable being tested
* @param containingParts the parts that contain the saveable. This list may
*    contain zero or more parts.
* @return whether the given saveable matches this filter
*/
    public boolean select(Saveable saveable, IWorkbenchPart[] containingParts);
}
