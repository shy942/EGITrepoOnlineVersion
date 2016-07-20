/***/
package org.eclipse.jface.viewers;

/**
* An extension to {@link ILabelProvider} that is given the
* path of the element being decorated, when it is available.
* @since 3.2
*/
public interface ITreePathLabelProvider extends IBaseLabelProvider {

    /**
* Updates the label for the given element.
*
* @param label the label to update
* @param elementPath the path of the element being decorated
*/
    public void updateLabel(ViewerLabel label, TreePath elementPath);
}
