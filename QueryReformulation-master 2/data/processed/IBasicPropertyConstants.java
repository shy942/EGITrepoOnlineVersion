/***/
package org.eclipse.jface.viewers;

/**
* Predefined property names used for elements displayed in viewers.
*
* @see StructuredViewer#update(Object, String[])
* @see StructuredViewer#update(Object[], String[])
* @see IBaseLabelProvider#isLabelProperty
* @see ViewerComparator#isSorterProperty
* @see ViewerFilter#isFilterProperty
*/
public interface IBasicPropertyConstants {

    /**
* Property name constant (value <code>"org.eclipse.jface.text"</code>)
* for an element's label text.
*
* @see org.eclipse.jface.viewers.ILabelProvider#getText
*/
    //$NON-NLS-1$
    public static final String P_TEXT = "org.eclipse.jface.text";

    /**
* Property name constant (value <code>"org.eclipse.jface.image"</code>)
* for an element's label image.
*
* @see org.eclipse.jface.viewers.ILabelProvider#getImage
*/
    //$NON-NLS-1$
    public static final String P_IMAGE = "org.eclipse.jface.image";

    /**
* Property name constant (value <code>"org.eclipse.jface.children"</code>)
* for an element's children.
*/
    //$NON-NLS-1$
    public static final String P_CHILDREN = "org.eclipse.jface.children";

    /**
* Property name constant (value <code>"org.eclipse.jface.parent"</code>)
* for an element's parent object.
*/
    //$NON-NLS-1$
    public static final String P_PARENT = "org.eclipse.jface.parent";
}
