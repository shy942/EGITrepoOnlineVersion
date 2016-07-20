/***/
package org.eclipse.ui.internal.navigator;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.handlers.CollapseAllHandler;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.navigator.actions.CollapseAllAction;
import org.eclipse.ui.internal.navigator.actions.LinkEditorAction;
import org.eclipse.ui.internal.navigator.filters.FilterActionGroup;
import org.eclipse.ui.internal.navigator.framelist.BackAction;
import org.eclipse.ui.internal.navigator.framelist.ForwardAction;
import org.eclipse.ui.internal.navigator.framelist.FrameList;
import org.eclipse.ui.internal.navigator.framelist.UpAction;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorViewerDescriptor;
import org.eclipse.ui.navigator.LinkHelperService;

/**
* @since 3.2
*/
public class CommonNavigatorActionGroup extends ActionGroup {

    //$NON-NLS-1$
    private static final String FRAME_ACTION_SEPARATOR_ID = "FRAME_ACTION_SEPARATOR_ID";

    //$NON-NLS-1$
    private static final String FRAME_ACTION_GROUP_ID = "FRAME_ACTION_GROUP_ID";

    private BackAction backAction;

    private ForwardAction forwardAction;

    private UpAction upAction;

    private LinkEditorAction toggleLinkingAction;

    private CollapseAllAction collapseAllAction;

    private FilterActionGroup filterGroup;

    private final CommonViewer commonViewer;

    private CommonNavigator commonNavigator;

    private final LinkHelperService linkHelperService;

    private CollapseAllHandler collapseAllHandler;

    private boolean frameActionsShown;

    /**
* Create a action group the common navigator actions.
*
* @param aNavigator
*            The IViewPart for this action group
* @param aViewer
*            The Viewer for this action group
* @param linkHelperService the link service helper
*/
    public  CommonNavigatorActionGroup(CommonNavigator aNavigator, CommonViewer aViewer, LinkHelperService linkHelperService) {
        super();
        commonNavigator = aNavigator;
        commonViewer = aViewer;
        this.linkHelperService = linkHelperService;
        makeActions();
    }

    /**
* Returns the image descriptor with the given relative path.
*/
    protected final ImageDescriptor getImageDescriptor(String relativePath) {
        //$NON-NLS-1$
        return NavigatorPlugin.getImageDescriptor("icons/full/" + relativePath);
    }

    /**
*
*/
    private void makeActions() {
        FrameList frameList = commonViewer.getFrameList();
        backAction = new BackAction(frameList);
        forwardAction = new ForwardAction(frameList);
        upAction = new UpAction(frameList);
        frameList.addPropertyChangeListener(new IPropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getProperty().equals(FrameList.P_RESET)) {
                    upAction.setEnabled(false);
                    backAction.setEnabled(false);
                    forwardAction.setEnabled(false);
                }
                commonNavigator.updateTitle();
                IActionBars actionBars = commonNavigator.getViewSite().getActionBars();
                updateToolBar(actionBars.getToolBarManager());
            }
        });
        IHandlerService service = (IHandlerService) commonNavigator.getSite().getService(IHandlerService.class);
        INavigatorViewerDescriptor viewerDescriptor = commonViewer.getNavigatorContentService().getViewerDescriptor();
        boolean hideLinkWithEditorAction = viewerDescriptor.getBooleanConfigProperty(INavigatorViewerDescriptor.PROP_HIDE_LINK_WITH_EDITOR_ACTION);
        if (!hideLinkWithEditorAction) {
            toggleLinkingAction = new LinkEditorAction(commonNavigator, commonViewer, linkHelperService);
            //$NON-NLS-1$
            ImageDescriptor syncIcon = getImageDescriptor("elcl16/synced.gif");
            toggleLinkingAction.setImageDescriptor(syncIcon);
            toggleLinkingAction.setHoverImageDescriptor(syncIcon);
            service.activateHandler(toggleLinkingAction.getActionDefinitionId(), new ActionHandler(toggleLinkingAction));
        }
        boolean hideCollapseAllAction = viewerDescriptor.getBooleanConfigProperty(INavigatorViewerDescriptor.PROP_HIDE_COLLAPSE_ALL_ACTION);
        if (!hideCollapseAllAction) {
            collapseAllAction = new CollapseAllAction(commonViewer);
            //$NON-NLS-1$
            ImageDescriptor collapseAllIcon = getImageDescriptor("elcl16/collapseall.gif");
            collapseAllAction.setImageDescriptor(collapseAllIcon);
            collapseAllAction.setHoverImageDescriptor(collapseAllIcon);
            collapseAllHandler = new CollapseAllHandler(commonViewer);
            service.activateHandler(CollapseAllHandler.COMMAND_ID, collapseAllHandler);
        }
        filterGroup = new FilterActionGroup(commonViewer);
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        actionBars.setGlobalActionHandler(ActionFactory.BACK.getId(), backAction);
        actionBars.setGlobalActionHandler(ActionFactory.FORWARD.getId(), forwardAction);
        actionBars.setGlobalActionHandler(IWorkbenchActionConstants.UP, upAction);
        filterGroup.fillActionBars(actionBars);
        fillToolBar(actionBars.getToolBarManager());
        fillViewMenu(actionBars.getMenuManager());
        actionBars.updateActionBars();
    }

    protected void fillToolBar(IToolBarManager toolBar) {
        if (backAction.isEnabled() || upAction.isEnabled() || forwardAction.isEnabled()) {
            toolBar.add(backAction);
            toolBar.add(forwardAction);
            toolBar.add(upAction);
            toolBar.add(new Separator(FRAME_ACTION_SEPARATOR_ID));
            frameActionsShown = true;
        }
        toolBar.add(new GroupMarker(FRAME_ACTION_GROUP_ID));
        if (collapseAllAction != null) {
            toolBar.add(collapseAllAction);
        }
        if (toggleLinkingAction != null) {
            toolBar.add(toggleLinkingAction);
        }
    }

    protected void fillViewMenu(IMenuManager menu) {
        menu.add(new Separator());
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        //$NON-NLS-1$
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS + "-end"));
        if (toggleLinkingAction != null) {
            menu.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS + "-end", //$NON-NLS-1$
            toggleLinkingAction);
        }
    }

    private void updateToolBar(IToolBarManager toolBar) {
        boolean hasBeenFrameActionsShown = frameActionsShown;
        frameActionsShown = backAction.isEnabled() || upAction.isEnabled() || forwardAction.isEnabled();
        if (frameActionsShown != hasBeenFrameActionsShown) {
            if (hasBeenFrameActionsShown) {
                toolBar.remove(backAction.getId());
                toolBar.remove(forwardAction.getId());
                toolBar.remove(upAction.getId());
                toolBar.remove(FRAME_ACTION_SEPARATOR_ID);
            } else {
                toolBar.prependToGroup(FRAME_ACTION_GROUP_ID, new Separator(FRAME_ACTION_SEPARATOR_ID));
                toolBar.prependToGroup(FRAME_ACTION_GROUP_ID, upAction);
                toolBar.prependToGroup(FRAME_ACTION_GROUP_ID, forwardAction);
                toolBar.prependToGroup(FRAME_ACTION_GROUP_ID, backAction);
            }
            toolBar.update(true);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        backAction.dispose();
        forwardAction.dispose();
        upAction.dispose();
        if (toggleLinkingAction != null) {
            toggleLinkingAction.dispose();
        }
        if (collapseAllHandler != null) {
            collapseAllHandler.dispose();
        }
    }
}
