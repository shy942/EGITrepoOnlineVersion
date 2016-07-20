/***/
package org.eclipse.jface.viewers;

/**
* <p>
* A content provider that expects every element to be a <code>TreeNode</code>.
* Most methods delegate to <code>TreeNode</code>. <code>dispose()</code>
* and <code>inputChanged(Viewer, Object, Object)</code> do nothing by
* default.
* </p>
* <p>
* This class and all of its methods may be overridden or extended.
* </p>
*
* @since 3.2
* @see org.eclipse.jface.viewers.TreeNode
*/
public class TreeNodeContentProvider implements ITreeContentProvider {

    @Override
    public void dispose() {
    // Do nothing
    }

    @Override
    public Object[] getChildren(final Object parentElement) {
        final TreeNode node = (TreeNode) parentElement;
        return node.getChildren();
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof TreeNode[]) {
            return (TreeNode[]) inputElement;
        }
        return new Object[0];
    }

    @Override
    public Object getParent(final Object element) {
        final TreeNode node = (TreeNode) element;
        return node.getParent();
    }

    @Override
    public boolean hasChildren(final Object element) {
        final TreeNode node = (TreeNode) element;
        return node.hasChildren();
    }

    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    // Do nothing
    }
}
