/***/
package org.eclipse.ui.tests.multieditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.part.AbstractMultiEditor;

public class ConcreteAbstractMultiEditor extends AbstractMultiEditor {

    private Composite left;

    private Composite right;

    private IEditorReference leftReference;

    @Override
    protected void innerEditorsCreated() {
    // no-op
    }

    @Override
    public Composite getInnerEditorContainer(IEditorReference innerEditorReference) {
        if (leftReference == null) {
            leftReference = innerEditorReference;
            return left;
        }
        return right;
    }

    @Override
    public void createPartControl(Composite parent) {
        SashForm form = new SashForm(parent, SWT.HORIZONTAL);
        left = new Composite(form, SWT.NONE);
        left.setLayout(new FillLayout());
        right = new Composite(form, SWT.NONE);
        right.setLayout(new FillLayout());
        form.setWeights(new int[] { 50, 50 });
    }
}
