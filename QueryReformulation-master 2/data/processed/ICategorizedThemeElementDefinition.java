/***/
package org.eclipse.ui.internal.themes;

/**
* A theme element that may belong to a category.
*
* @since 3.0
*/
public interface ICategorizedThemeElementDefinition extends IThemeElementDefinition {

    /**
* Returns the category of this element.
*
* @return the category of this element, or <code>null</code> if it does not belong to one.
*/
    String getCategoryId();
}
