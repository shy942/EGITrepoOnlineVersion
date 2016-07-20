/***/
package org.eclipse.ui.tests.navigator.jst;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.graphics.Image;

/**
*
* Provides a simple interface that is used to manipulate the rendering of Java elements to a format
* that is friendlier for Web Projects.
*
*/
public interface ICompressedNode {

    /**
*
* @return The image to display for this node.
*/
    public Image getImage();

    /**
*
* @return The text label to display for this node.
*/
    public String getLabel();

    /**
*
* @param delegateContentProvider
*            The content provider that should used for any children that I cannot provide
*            children for.
* @return The children either from me or the delegateContentProvider.
*/
    public Object[] getChildren(ITreeContentProvider delegateContentProvider);
}
