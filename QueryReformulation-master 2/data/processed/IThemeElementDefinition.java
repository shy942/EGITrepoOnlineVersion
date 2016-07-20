/***/
package org.eclipse.ui.internal.themes;

/**
* A basic element (color, font) of a theme must implement this
* interface.
*
* @since 3.0
*/
public interface IThemeElementDefinition {

    /**
* Returns the name for this element.
*
* @return the name for this element
*/
    public String getName();

    /**
* Returns the id for this element.
*
* @return the id for this element.  This will never be <code>null</code>.
*/
    public String getId();

    /**
* Returns the description for this element.
*
* @return the description for this element.  This may be <code>null</code>.
*/
    public String getDescription();
}
