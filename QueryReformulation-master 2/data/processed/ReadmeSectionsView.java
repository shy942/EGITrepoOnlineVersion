/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

/**
* This class demonstrates a simple view containing a single viewer.
*/
public class ReadmeSectionsView extends ViewPart implements ISelectionListener {

    ListViewer viewer;

    /**
* Creates a new ReadmeSectionsView .
*/
    public  ReadmeSectionsView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        viewer = new ListViewer(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), IReadmeConstants.SECTIONS_VIEW_CONTEXT);
        // if the objects in the viewer implement IWorkbenchAdapter,
        // these generic content and label providers can be used.
        viewer.setContentProvider(new WorkbenchContentProvider());
        viewer.setLabelProvider(new WorkbenchLabelProvider());
        // add myself as a global selection listener
        getSite().getPage().addSelectionListener(this);
        // prime the selection
        selectionChanged(null, getSite().getPage().getSelection());
    }

    /**
* The <code>ReadmeSectionView</code> implementation of this
* <code>IWorkbenchPart</code> method runs super
* and removes itself from the global selection listener.
*/
    @Override
    public void dispose() {
        super.dispose();
        getSite().getPage().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection sel) {
        //if the selection is a readme file, get its sections.
        AdaptableList input = ReadmeModelFactory.getInstance().getSections(sel);
        viewer.setInput(input);
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}
