/***/
package org.eclipse.ui.examples.readmetool;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
* Content outline page for the readme editor.
*/
public class ReadmeContentOutlinePage extends ContentOutlinePage {

    protected IFile input;

    class OutlineAction extends Action {

        private Shell shell;

        public  OutlineAction(String label) {
            super(label);
            getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {

                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    setEnabled(!event.getSelection().isEmpty());
                }
            });
        }

        public void setShell(Shell shell) {
            this.shell = shell;
        }

        @Override
        public void run() {
            MessageDialog.openInformation(shell, MessageUtil.getString(//$NON-NLS-1$
            "Readme_Outline"), //$NON-NLS-1$
            MessageUtil.getString("ReadmeOutlineActionExecuted"));
        }
    }

    /**
* Creates a new ReadmeContentOutlinePage.
*/
    public  ReadmeContentOutlinePage(IFile input) {
        super();
        this.input = input;
    }

    /**
* Creates the control and registers the popup menu for this page
* Menu id "org.eclipse.ui.examples.readmetool.outline"
*/
    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IReadmeConstants.CONTENT_OUTLINE_PAGE_CONTEXT);
        TreeViewer viewer = getTreeViewer();
        viewer.setContentProvider(new WorkbenchContentProvider());
        viewer.setLabelProvider(new WorkbenchLabelProvider());
        viewer.setInput(getContentOutline(input));
        initDragAndDrop();
        // Configure the context menu.
        //$NON-NLS-1$
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + //$NON-NLS-1$
        "-end"));
        Menu menu = menuMgr.createContextMenu(viewer.getTree());
        viewer.getTree().setMenu(menu);
        // Be sure to register it so that other plug-ins can add actions.
        getSite().registerContextMenu("org.eclipse.ui.examples.readmetool.outline", menuMgr, //$NON-NLS-1$
        viewer);
        getSite().getActionBars().setGlobalActionHandler(IReadmeConstants.RETARGET2, //$NON-NLS-1$
        new OutlineAction(MessageUtil.getString("Outline_Action2")));
        OutlineAction action = new OutlineAction(MessageUtil.getString(//$NON-NLS-1$
        "Outline_Action3"));
        //$NON-NLS-1$
        action.setToolTipText(MessageUtil.getString("Readme_Outline_Action3"));
        getSite().getActionBars().setGlobalActionHandler(IReadmeConstants.LABELRETARGET3, action);
        //$NON-NLS-1$
        action = new OutlineAction(MessageUtil.getString("Outline_Action4"));
        getSite().getActionBars().setGlobalActionHandler(IReadmeConstants.ACTION_SET_RETARGET4, action);
        //$NON-NLS-1$
        action = new OutlineAction(MessageUtil.getString("Outline_Action5"));
        //$NON-NLS-1$
        action.setToolTipText(MessageUtil.getString("Readme_Outline_Action5"));
        getSite().getActionBars().setGlobalActionHandler(IReadmeConstants.ACTION_SET_LABELRETARGET5, action);
    }

    /**
* Gets the content outline for a given input element.
* Returns the outline (a list of MarkElements), or null
* if the outline could not be generated.
*/
    private IAdaptable getContentOutline(IAdaptable input) {
        return ReadmeModelFactory.getInstance().getContentOutline(input);
    }

    /**
* Initializes drag and drop for this content outline page.
*/
    private void initDragAndDrop() {
        int ops = DND.DROP_COPY | DND.DROP_MOVE;
        Transfer[] transfers = new Transfer[] { TextTransfer.getInstance(), PluginTransfer.getInstance() };
        getTreeViewer().addDragSupport(ops, transfers, new ReadmeContentOutlineDragListener(this));
    }

    /**
* Forces the page to update its contents.
*
* @see ReadmeEditor#doSave(IProgressMonitor)
*/
    public void update() {
        getControl().setRedraw(false);
        getTreeViewer().setInput(getContentOutline(input));
        getTreeViewer().expandAll();
        getControl().setRedraw(true);
    }
}
