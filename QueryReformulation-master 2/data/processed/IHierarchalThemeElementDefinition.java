/***/
package org.eclipse.ui.internal.themes;

/**
* A theme element whose value may default to that of another theme element.
*
* @since 3.0
*/
public interface IHierarchalThemeElementDefinition extends IThemeElementDefinition {

    /**
* Return the id of the element this element defaults to.
*
* @return the id of the element this element defaults to, or
* <code>null</code> if it does not default to another element.
*/
    String getDefaultsTo();
}
