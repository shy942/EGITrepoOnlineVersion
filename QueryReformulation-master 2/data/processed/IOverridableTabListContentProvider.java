/***/
package org.eclipse.ui.views.properties.tabbed;

import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
* A content provider for the tabbed property sheet page's list of tabs. Used by
* a section that overrides the tabs that are provided by the tabbed property
* registry with a new list of tabs.
* <p>
* The overridable tab list is a content provider that provides both the
* sections and the tab labels.
* @author Anthony Hunter
* @since 3.4
*/
public interface IOverridableTabListContentProvider extends IStructuredContentProvider {

    /**
* Override the tabs displayed in the tab list with a new list of tabs.
*/
    public void overrideTabs();
}
