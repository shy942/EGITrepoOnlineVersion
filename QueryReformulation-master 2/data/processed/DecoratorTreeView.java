/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
* The DecoratorTreeView is a TreeView that tests the
* font and color decorations.
*/
public class DecoratorTreeView extends DecoratorTestPart {

    TreeViewer viewer;

    /**
*
*/
    public  DecoratorTreeView() {
        super();
    // XXX Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent) {

            @Override
            protected void labelProviderChanged() {
                super.labelProviderChanged();
            }
        };
        viewer.setLabelProvider(getLabelProvider());
        viewer.setContentProvider(new TestTreeContentProvider());
        viewer.setInput(this);
        GridData data = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
        viewer.getControl().setLayoutData(data);
    }

    @Override
    public void setFocus() {
    // XXX Auto-generated method stub
    }
}
