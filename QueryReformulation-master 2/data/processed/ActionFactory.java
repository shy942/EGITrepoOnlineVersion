/***/
package org.eclipse.ui.actions;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.CloseAllSavedAction;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.NavigationHistoryAction;
import org.eclipse.ui.internal.OpenPreferencesAction;
import org.eclipse.ui.internal.ToggleEditorsVisibilityAction;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.actions.CommandAction;
import org.eclipse.ui.internal.actions.DynamicHelpAction;
import org.eclipse.ui.internal.actions.HelpContentsAction;
import org.eclipse.ui.internal.actions.HelpSearchAction;
import org.eclipse.ui.internal.intro.IntroDescriptor;
import org.eclipse.ui.internal.intro.IntroMessages;

/**
* Access to standard actions provided by the workbench.
* <p>
* Most of the functionality of this class is provided by static methods and
* fields. Example usage:
*
* <pre>
* MenuManager menu = ...;
* ActionFactory.IWorkbenchAction closeEditorAction
*    = ActionFactory.CLOSE.create(window);
* menu.add(closeEditorAction);
* </pre>
* </p>
* <p>
* Clients may declare other classes that provide additional application-specific
* action factories.
* </p>
*
* @since 3.0
*/
public abstract class ActionFactory {

    /**
* Interface for a workbench action.
*/
    public interface IWorkbenchAction extends IAction {

        /**
* Disposes of this action. Once disposed, this action cannot be used.
* This operation has no effect if the action has already been
* disposed.
*/
        public void dispose();
    }

    private static class WorkbenchCommandAction extends CommandAction implements IWorkbenchAction {

        /**
* @param commandIdIn
* @param window
*/
        public  WorkbenchCommandAction(String commandIdIn, IWorkbenchWindow window) {
            super(window, commandIdIn);
        }
    }

    /**
* Workbench action (id: "about", commandId: "org.eclipse.ui.help.aboutAction"): Displays the
* About dialog. This action maintains its enablement state.
*/
    public static final ActionFactory ABOUT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "about", IWorkbenchCommandConstants.HELP_ABOUT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            IProduct product = Platform.getProduct();
            String productName = null;
            if (product != null) {
                productName = product.getName();
            }
            if (productName == null) {
                //$NON-NLS-1$
                productName = "";
            }
            action.setText(NLS.bind(WorkbenchMessages.AboutAction_text, productName));
            action.setToolTipText(NLS.bind(WorkbenchMessages.AboutAction_toolTip, productName));
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.ABOUT_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "activateEditor", commandId: "org.eclipse.ui.window.activateEditor"):
* Activate the most recently used editor. This action maintains its enablement state.
*/
    public static final ActionFactory ACTIVATE_EDITOR = new ActionFactory(//$NON-NLS-1$
    "activateEditor", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_ACTIVATE_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ActivateEditorAction_text);
            action.setToolTipText(WorkbenchMessages.ActivateEditorAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "back", commandId: "org.eclipse.ui.navigate.back"): Back. This action
* is a {@link RetargetAction} with id "back". This action maintains its enablement state.
*/
    public static final ActionFactory BACK = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "back", IWorkbenchCommandConstants.NAVIGATE_BACK) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_back);
            action.setToolTipText(WorkbenchMessages.Workbench_backToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "backardHistory", commandId: "org.eclipse.ui.navigate.backwardHistory"):
* Backward in the navigation history. This action maintains its enablement state.
*/
    public static final ActionFactory BACKWARD_HISTORY = new ActionFactory(//$NON-NLS-1$
    "backardHistory", //$NON-NLS-1$
    IWorkbenchCommandConstants.NAVIGATE_BACKWARD_HISTORY) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new NavigationHistoryAction(window, false);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "close", commandId: "org.eclipse.ui.file.close"): Close the active
* editor. This action maintains its enablement state.
*/
    public static final ActionFactory CLOSE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "close", IWorkbenchCommandConstants.FILE_CLOSE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CloseEditorAction_text);
            action.setToolTipText(WorkbenchMessages.CloseEditorAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "closeAll", commandId: "org.eclipse.ui.file.closeAll"): Close all open
* editors. This action maintains its enablement state.
*/
    public static final ActionFactory CLOSE_ALL = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "closeAll", IWorkbenchCommandConstants.FILE_CLOSE_ALL) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CloseAllAction_text);
            action.setToolTipText(WorkbenchMessages.CloseAllAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "closeOthers", commandId: "org.eclipse.ui.file.closeOthers"): Close all
* editors except the one that is active. This action maintains its enablement state.
*
* @since 3.2
*/
    public static final ActionFactory CLOSE_OTHERS = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "closeOthers", IWorkbenchCommandConstants.FILE_CLOSE_OTHERS) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CloseOthersAction_text);
            action.setToolTipText(WorkbenchMessages.CloseOthersAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "closeAllPerspectives", commandId: "org.eclipse.ui.window.closeAllPerspectives"):
* Closes all perspectives. This action maintains its enablement state.
*/
    public static final ActionFactory CLOSE_ALL_PERSPECTIVES = new ActionFactory(//$NON-NLS-1$
    "closeAllPerspectives", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_CLOSE_ALL_PERSPECTIVES) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CloseAllPerspectivesAction_text);
            action.setToolTipText(WorkbenchMessages.CloseAllPerspectivesAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CLOSE_ALL_PAGES_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "closeAllSaved"): Close all open editors except those with unsaved
* changes. This action maintains its enablement state.
*/
    public static final ActionFactory CLOSE_ALL_SAVED = new ActionFactory(//$NON-NLS-1$
    "closeAllSaved") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new CloseAllSavedAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "closePerspective", commandId: "org.eclipse.ui.window.closePerspective"):
* Closes the current perspective. This action maintains its enablement state.
*/
    public static final ActionFactory CLOSE_PERSPECTIVE = new ActionFactory(//$NON-NLS-1$
    "closePerspective", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_CLOSE_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ClosePerspectiveAction_text);
            action.setToolTipText(WorkbenchMessages.ClosePerspectiveAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CLOSE_PAGE_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "intro", commandId: "org.eclipse.ui.help.quickStartAction"): Activate
* the introduction extension. This action should not be instantiated if no intro is provided.
* Use code like:
*
* <pre>
* if (window.getWorkbench().getIntroManager().hasIntro()) {
* 	introAction= ActionFactory.INTRO.create(window);
* 	register(introAction);
* }
* </pre>
*/
    public static final ActionFactory INTRO = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "intro", IWorkbenchCommandConstants.HELP_WELCOME) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(IntroMessages.Intro_action_text);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.INTRO_ACTION);
            IntroDescriptor introDescriptor = ((Workbench) window.getWorkbench()).getIntroDescriptor();
            if (introDescriptor != null) {
                action.setImageDescriptor(introDescriptor.getImageDescriptor());
                String labelOverride = introDescriptor.getLabelOverride();
                if (labelOverride != null) {
                    action.setText(labelOverride);
                }
            }
            return action;
        }
    };

    /**
* Workbench action (id: "copy", commandId: "org.eclipse.ui.edit.copy"): Copy. This action is a
* {@link RetargetAction} with id "copy". This action maintains its enablement state.
*/
    public static final ActionFactory COPY = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "copy", IWorkbenchCommandConstants.EDIT_COPY) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_copy);
            action.setToolTipText(WorkbenchMessages.Workbench_copyToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "cut", commandId: "org.eclipse.ui.edit.cut"): Cut. This action is a
* {@link RetargetAction} with id "cut". This action maintains its enablement state.
*/
    public static final ActionFactory CUT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "cut", IWorkbenchCommandConstants.EDIT_CUT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_cut);
            action.setToolTipText(WorkbenchMessages.Workbench_cutToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "delete", commandId: "org.eclipse.ui.edit.delete"): Delete. This action
* is a {@link RetargetAction} with id "delete". This action maintains its enablement state.
*/
    public static final ActionFactory DELETE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "delete", IWorkbenchCommandConstants.EDIT_DELETE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_delete);
            action.setToolTipText(WorkbenchMessages.Workbench_deleteToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            action.enableAccelerator(false);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.DELETE_RETARGET_ACTION);
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "editActionSets", commandId: "org.eclipse.ui.window.customizePerspective"):
* Edit the action sets. This action maintains its enablement state.
*/
    public static final ActionFactory EDIT_ACTION_SETS = new ActionFactory(//$NON-NLS-1$
    "editActionSets", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_CUSTOMIZE_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.EditActionSetsAction_text);
            action.setToolTipText(WorkbenchMessages.EditActionSetsAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.EDIT_ACTION_SETS_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "export", commandId: "org.eclipse.ui.file.export"): Opens the export
* wizard. This action maintains its enablement state.
*/
    public static final ActionFactory EXPORT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "export", IWorkbenchCommandConstants.FILE_EXPORT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ExportResourcesAction_fileMenuText);
            action.setToolTipText(WorkbenchMessages.ExportResourcesAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.EXPORT_ACTION);
            action.setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EXPORT_WIZ));
            return action;
        }
    };

    /**
* Workbench action (id: "find", commandId: "org.eclipse.ui.edit.findReplace"): Find. This
* action is a {@link RetargetAction} with id "find". This action maintains its enablement
* state.
*/
    public static final ActionFactory FIND = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "find", IWorkbenchCommandConstants.EDIT_FIND_AND_REPLACE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_findReplace);
            action.setToolTipText(WorkbenchMessages.Workbench_findReplaceToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            //		action.setDisabledImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_SEARCH_SRC_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "forward", commandId: "org.eclipse.ui.navigate.forward"): Forward. This
* action is a {@link RetargetAction} with id "forward". This action maintains its enablement
* state.
*/
    public static final ActionFactory FORWARD = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "forward", IWorkbenchCommandConstants.NAVIGATE_FORWARD) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_forward);
            action.setToolTipText(WorkbenchMessages.Workbench_forwardToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "forwardHistory", commandId: "org.eclipse.ui.navigate.forwardHistory"):
* Forward in the navigation history. This action maintains its enablement state.
*/
    public static final ActionFactory FORWARD_HISTORY = new ActionFactory(//$NON-NLS-1$
    "forwardHistory", //$NON-NLS-1$
    IWorkbenchCommandConstants.NAVIGATE_FORWARD_HISTORY) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new NavigationHistoryAction(window, true);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "goInto", commandId: "org.eclipse.ui.navigate.goInto"): Go Into. This
* action is a {@link RetargetAction} with id "goInto". This action maintains its enablement
* state.
*/
    public static final ActionFactory GO_INTO = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "goInto", IWorkbenchCommandConstants.NAVIGATE_GO_INTO) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_goInto);
            action.setToolTipText(WorkbenchMessages.Workbench_goIntoToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "import", commandId: "org.eclipse.ui.file.import"): Opens the import
* wizard. This action maintains its enablement state.
*/
    public static final ActionFactory IMPORT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "import", IWorkbenchCommandConstants.FILE_IMPORT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ImportResourcesAction_text);
            action.setToolTipText(WorkbenchMessages.ImportResourcesAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.IMPORT_ACTION);
            action.setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_IMPORT_WIZ));
            return action;
        }
    };

    /**
* Workbench action (id: "lockToolBar"): Lock/unlock the workbench window
* tool bar. This action maintains its enablement state.
*/
    public static final ActionFactory LOCK_TOOL_BAR = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "lockToolBar", IWorkbenchCommandConstants.WINDOW_LOCK_TOOLBAR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setToolTipText(WorkbenchMessages.LockToolBarAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.LOCK_TOOLBAR_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "maximize", commandId: "org.eclipse.ui.window.maximizePart"):
* Maximize/restore the active part. This action maintains its enablement state.
*/
    public static final ActionFactory MAXIMIZE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "maximize", IWorkbenchCommandConstants.WINDOW_MAXIMIZE_ACTIVE_VIEW_OR_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setToolTipText(WorkbenchMessages.MaximizePartAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.MAXIMIZE_PART_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "minimize", commandId: "org.eclipse.ui.window.minimizePart"): Minimizes
* the active part. This action maintains its enablement state.
*
* @since 3.1
*/
    public static final ActionFactory MINIMIZE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "minimize", IWorkbenchCommandConstants.WINDOW_MINIMIZE_ACTIVE_VIEW_OR_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setToolTipText(WorkbenchMessages.MinimizePartAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.MINIMIZE_PART_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "move", commandId: "org.eclipse.ui.edit.move"): Move. This action is a
* {@link RetargetAction} with id "move". This action maintains its enablement state.
*/
    public static final ActionFactory MOVE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "move", IWorkbenchCommandConstants.FILE_MOVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_move);
            action.setToolTipText(WorkbenchMessages.Workbench_moveToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "new", commandId: "org.eclipse.ui.newWizard"): Opens the new wizard
* dialog. This action maintains its enablement state.
*/
    public static final ActionFactory NEW = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "new", IWorkbenchCommandConstants.FILE_NEW) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            ISharedImages images = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
            action.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
            action.setText(WorkbenchMessages.NewWizardAction_text);
            action.setToolTipText(WorkbenchMessages.NewWizardAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.NEW_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "newWizardDropDown"): Drop-down action which shows shows the new wizard
* drop down, or opens the new wizard dialog when pressed. For use in the toolbar. This action
* maintains its enablement state.
*
* @since 3.1
*/
    public static final ActionFactory NEW_WIZARD_DROP_DOWN = new ActionFactory(//$NON-NLS-1$
    "newWizardDropDown") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new NewWizardDropDownAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "next", commandId: "org.eclipse.ui.navigate.next"): Next. This action
* is a {@link RetargetAction} with id "next". This action maintains its enablement state.
*/
    public static final ActionFactory NEXT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "next", IWorkbenchCommandConstants.NAVIGATE_NEXT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_next);
            action.setToolTipText(WorkbenchMessages.Workbench_nextToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "nextEditor", commandId: "org.eclipse.ui.window.nextEditor"): Next
* editor. This action maintains its enablement state.
* <p>
* <code>NEXT_EDITOR</code> and <code>PREVIOUS_EDITOR</code> form a cycle action pair. For a
* given window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory NEXT_EDITOR = new ActionFactory(//$NON-NLS-1$
    "nextEditor", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_NEXT_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CycleEditorAction_next_text);
            action.setToolTipText(WorkbenchMessages.CycleEditorAction_next_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_EDITOR_FORWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "nextPart", commandId: "org.eclipse.ui.window.nextView"): Next part.
* This action maintains its enablement state.
* <p>
* <code>NEXT_PART</code> and <code>PREVIOUS_PART</code> form a cycle action pair. For a given
* window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory NEXT_PART = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "nextPart", IWorkbenchCommandConstants.WINDOW_NEXT_VIEW) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CyclePartAction_next_text);
            action.setToolTipText(WorkbenchMessages.CyclePartAction_next_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_PART_FORWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "nextPerspective", commandId: "org.eclipse.ui.window.nextPerspective"):
* Next perspective. This action maintains its enablement state.
* <p>
* <code>NEXT_PERSPECTIVE</code> and <code>PREVIOUS_PERSPECTIVE</code> form a cycle action pair.
* For a given window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory NEXT_PERSPECTIVE = new ActionFactory(//$NON-NLS-1$
    "nextPerspective", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_NEXT_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CyclePerspectiveAction_next_text);
            action.setToolTipText(WorkbenchMessages.CyclePerspectiveAction_next_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_PERSPECTIVE_FORWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "openNewWindow", commandId: "org.eclipse.ui.window.newWindow"): Open a
* new workbench window. This action maintains its enablement state.
*/
    public static final ActionFactory OPEN_NEW_WINDOW = new ActionFactory(//$NON-NLS-1$
    "openNewWindow", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_NEW_WINDOW) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.OpenInNewWindowAction_text);
            action.setToolTipText(WorkbenchMessages.OpenInNewWindowAction_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.OPEN_NEW_WINDOW_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "paste", commandId: "org.eclipse.ui.edit.paste"): Paste. This action is
* a {@link RetargetAction} with id "paste". This action maintains its enablement state.
*/
    public static final ActionFactory PASTE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "paste", IWorkbenchCommandConstants.EDIT_PASTE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_paste);
            action.setToolTipText(WorkbenchMessages.Workbench_pasteToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "preferences", commandId: "org.eclipse.ui.window.preferences"):
* Displays the Preferences dialog. This action maintains its enablement state.
*/
    public static final ActionFactory PREFERENCES = new ActionFactory(//$NON-NLS-1$
    "preferences", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_PREFERENCES) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new OpenPreferencesAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "previous", commandId: "org.eclipse.ui.navigate.previous"): Previous.
* This action is a {@link RetargetAction} with id "previous". This action maintains its
* enablement state.
*/
    public static final ActionFactory PREVIOUS = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "previous", IWorkbenchCommandConstants.NAVIGATE_PREVIOUS) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_previous);
            action.setToolTipText(WorkbenchMessages.Workbench_previousToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "previousEditor", commandId: "org.eclipse.ui.window.previousEditor"):
* Previous editor. This action maintains its enablement state.
* <p>
* <code>NEXT_EDITOR</code> and <code>PREVIOUS_EDITOR</code> form a cycle action pair. For a
* given window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory PREVIOUS_EDITOR = new ActionFactory(//$NON-NLS-1$
    "previousEditor", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_PREVIOUS_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CycleEditorAction_prev_text);
            action.setToolTipText(WorkbenchMessages.CycleEditorAction_prev_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_EDITOR_BACKWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "previousPart", commandId: "org.eclipse.ui.window.previousView"):
* Previous part. This action maintains its enablement state.
* <p>
* <code>NEXT_PART</code> and <code>PREVIOUS_PART</code> form a cycle action pair. For a given
* window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory PREVIOUS_PART = new ActionFactory(//$NON-NLS-1$
    "previousPart", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_PREVIOUS_VIEW) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CyclePartAction_prev_text);
            action.setToolTipText(WorkbenchMessages.CyclePartAction_prev_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_PART_BACKWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "previousPerspective", commandId: "org.eclipse.ui.window.previousPerspective"):
* Previous perspective. This action maintains its enablement state.
* <p>
* <code>NEXT_PERSPECTIVE</code> and <code>PREVIOUS_PERSPECTIVE</code> form a cycle action pair.
* For a given window, use {@link ActionFactory#linkCycleActionPair
* ActionFactory.linkCycleActionPair</code>} to connect the two.
* </p>
*/
    public static final ActionFactory PREVIOUS_PERSPECTIVE = new ActionFactory(//$NON-NLS-1$
    "previousPerspective", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_PREVIOUS_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.CyclePerspectiveAction_prev_text);
            action.setToolTipText(WorkbenchMessages.CyclePerspectiveAction_prev_toolTip);
            // @issue missing action ids
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.CYCLE_PERSPECTIVE_BACKWARD_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "print", commandId: "org.eclipse.ui.file.print"): Print. This action is
* a {@link RetargetAction} with id "print". This action maintains its enablement state.
*/
    public static final ActionFactory PRINT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "print", IWorkbenchCommandConstants.FILE_PRINT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_print);
            action.setToolTipText(WorkbenchMessages.Workbench_printToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            action.setImageDescriptor(WorkbenchImages.getImageDescriptor(ISharedImages.IMG_ETOOL_PRINT_EDIT));
            action.setDisabledImageDescriptor(WorkbenchImages.getImageDescriptor(ISharedImages.IMG_ETOOL_PRINT_EDIT_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "properties", commandId: "org.eclipse.ui.file.properties"): Properties.
* This action is a {@link RetargetAction} with id "properties". This action maintains its
* enablement state.
*/
    public static final ActionFactory PROPERTIES = new ActionFactory(//$NON-NLS-1$
    "properties", //$NON-NLS-1$
    IWorkbenchCommandConstants.FILE_PROPERTIES) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_properties);
            action.setToolTipText(WorkbenchMessages.Workbench_propertiesToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "quit", commandId: "org.eclipse.ui.file.exit"): Quit (close the
* workbench). This action maintains its enablement state.
*/
    public static final ActionFactory QUIT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "quit", IWorkbenchCommandConstants.FILE_EXIT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.Exit_text);
            action.setToolTipText(WorkbenchMessages.Exit_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.QUIT_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "redo", commandId: "org.eclipse.ui.edit.redo"): Redo. This action is a
* {@link RetargetAction} with id "redo". This action maintains its enablement state.
*/
    public static final ActionFactory REDO = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "redo", IWorkbenchCommandConstants.EDIT_REDO) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            LabelRetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_redo);
            action.setToolTipText(WorkbenchMessages.Workbench_redoToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "refresh", commandId: "org.eclipse.ui.file.refresh"): Refresh. This
* action is a {@link RetargetAction} with id "refresh". This action maintains its enablement
* state.
*/
    public static final ActionFactory REFRESH = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "refresh", IWorkbenchCommandConstants.FILE_REFRESH) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_refresh);
            action.setToolTipText(WorkbenchMessages.Workbench_refreshToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "rename", commandId: "org.eclipse.ui.edit.rename"): Rename. This action
* is a {@link RetargetAction} with id "rename". This action maintains its enablement state.
*/
    public static final ActionFactory RENAME = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "rename", IWorkbenchCommandConstants.FILE_RENAME) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_rename);
            action.setToolTipText(WorkbenchMessages.Workbench_renameToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "resetPerspective", commandId: "org.eclipse.ui.window.resetPerspective"):
* Resets the current perspective. This action maintains its enablement state.
*/
    public static final ActionFactory RESET_PERSPECTIVE = new ActionFactory(//$NON-NLS-1$
    "resetPerspective", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_RESET_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ResetPerspective_text);
            action.setToolTipText(WorkbenchMessages.ResetPerspective_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.RESET_PERSPECTIVE_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "revert", commandId: "org.eclipse.ui.file.revert"): Revert. This action
* is a {@link RetargetAction} with id "revert". This action maintains its enablement state.
*/
    public static final ActionFactory REVERT = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "revert", IWorkbenchCommandConstants.FILE_REVERT) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_revert);
            action.setToolTipText(WorkbenchMessages.Workbench_revertToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "save", commandId: "org.eclipse.ui.file.save"): Save the active editor.
* This action maintains its enablement state.
*/
    public static final ActionFactory SAVE = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "save", IWorkbenchCommandConstants.FILE_SAVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setText(WorkbenchMessages.SaveAction_text);
            action.setToolTipText(WorkbenchMessages.SaveAction_toolTip);
            action.setId(getId());
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.SAVE_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "saveAll", commandId: "org.eclipse.ui.file.saveAll"): Save all open
* editors with unsaved changes. This action maintains its enablement state.
*/
    public static final ActionFactory SAVE_ALL = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "saveAll", IWorkbenchCommandConstants.FILE_SAVE_ALL) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setText(WorkbenchMessages.SaveAll_text);
            action.setToolTipText(WorkbenchMessages.SaveAll_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.SAVE_ALL_ACTION);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "saveAs", commandId: "org.eclipse.ui.file.saveAs"): Save As for the
* active editor. This action maintains its enablement state.
*/
    public static final ActionFactory SAVE_AS = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "saveAs", IWorkbenchCommandConstants.FILE_SAVE_AS) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setText(WorkbenchMessages.SaveAs_text);
            action.setToolTipText(WorkbenchMessages.SaveAs_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.SAVE_AS_ACTION);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "savePerspective", commandId: "org.eclipse.ui.window.savePerspective"):
* Save the current perspective. This action maintains its enablement state.
*/
    public static final ActionFactory SAVE_PERSPECTIVE = new ActionFactory(//$NON-NLS-1$
    "savePerspective", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_SAVE_PERSPECTIVE_AS) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.SavePerspective_text);
            action.setToolTipText(WorkbenchMessages.SavePerspective_toolTip);
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.SAVE_PERSPECTIVE_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "selectAll", commandId: "org.eclipse.ui.edit.selectAll"): Select All.
* This action is a {@link RetargetAction} with id "selectAll". This action maintains its
* enablement state.
*/
    public static final ActionFactory SELECT_ALL = new ActionFactory(//$NON-NLS-1$
    "selectAll", //$NON-NLS-1$
    IWorkbenchCommandConstants.EDIT_SELECT_ALL) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new RetargetAction(getId(), WorkbenchMessages.Workbench_selectAll);
            action.setToolTipText(WorkbenchMessages.Workbench_selectAllToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "showEditor"): Show/hide the editor area. This action maintains its
* enablement state.
*/
    public static final ActionFactory SHOW_EDITOR = new ActionFactory(//$NON-NLS-1$
    "showEditor") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new ToggleEditorsVisibilityAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "showOpenEditors"): Show a list of open (and recently closed) editors.
* This action maintains its enablement state.
*/
    public static final ActionFactory SHOW_OPEN_EDITORS = new ActionFactory(//$NON-NLS-1$
    "showOpenEditors") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            //$NON-NLS-1$
            WorkbenchCommandAction action = new WorkbenchCommandAction("org.eclipse.ui.window.switchToEditor", window);
            action.setId(getId());
            action.setText(WorkbenchMessages.WorkbenchEditorsAction_label);
            // @issue missing action id
            window.getWorkbench().getHelpSystem().setHelp(action, IWorkbenchHelpContextIds.WORKBENCH_EDITORS_ACTION);
            return action;
        }
    };

    /**
* Workbench action (id: "showWorkbookEditors"): Shows a list of open editors in the current or
* last active workbook.
*/
    public static final ActionFactory SHOW_WORKBOOK_EDITORS = new ActionFactory(//$NON-NLS-1$
    "showWorkBookEditors") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            //$NON-NLS-1$
            WorkbenchCommandAction action = new WorkbenchCommandAction("org.eclipse.ui.window.openEditorDropDown", window);
            action.setId(getId());
            action.setText(WorkbenchMessages.WorkbookEditorsAction_label);
            return action;
        }
    };

    /**
* Workbench action (id: "showQuickAccess"): Shows a list of UI elements like editors, views,
* perspectives etc.
*
* @since 3.3
*/
    public static final ActionFactory SHOW_QUICK_ACCESS = new ActionFactory(//$NON-NLS-1$
    "showQuickAccess") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            //$NON-NLS-1$
            WorkbenchCommandAction action = new WorkbenchCommandAction("org.eclipse.ui.window.quickAccess", window);
            action.setId(getId());
            action.setText(WorkbenchMessages.QuickAccessAction_text);
            action.setToolTipText(WorkbenchMessages.QuickAccessAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "showPartPaneMenu"): Show the part pane menu. This action maintains its
* enablement state.
*/
    public static final ActionFactory SHOW_PART_PANE_MENU = new ActionFactory(//$NON-NLS-1$
    "showPartPaneMenu") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            //$NON-NLS-1$
            WorkbenchCommandAction action = new WorkbenchCommandAction("org.eclipse.ui.window.showSystemMenu", window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ShowPartPaneMenuAction_text);
            action.setToolTipText(WorkbenchMessages.ShowPartPaneMenuAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "showViewMenu", commandId: "org.eclipse.ui.window.showViewMenu"): Show
* the view menu. This action maintains its enablement state.
*/
    public static final ActionFactory SHOW_VIEW_MENU = new ActionFactory(//$NON-NLS-1$
    "showViewMenu", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_SHOW_VIEW_MENU) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.ShowViewMenuAction_text);
            action.setToolTipText(WorkbenchMessages.ShowViewMenuAction_toolTip);
            return action;
        }
    };

    /**
* Workbench action (id: "undo", commandId: "org.eclipse.ui.edit.undo"): Undo. This action is a
* {@link RetargetAction} with id "undo". This action maintains its enablement state.
*/
    public static final ActionFactory UNDO = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "undo", IWorkbenchCommandConstants.EDIT_UNDO) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            LabelRetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_undo);
            action.setToolTipText(WorkbenchMessages.Workbench_undoToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            ISharedImages sharedImages = window.getWorkbench().getSharedImages();
            action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
            action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO_DISABLED));
            return action;
        }
    };

    /**
* Workbench action (id: "up", commandId: "org.eclipse.ui.navigate.up"): Up. This action is a
* {@link RetargetAction} with id "up". This action maintains its enablement state.
*/
    public static final ActionFactory UP = new //$NON-NLS-1$
    ActionFactory(//$NON-NLS-1$
    "up", IWorkbenchCommandConstants.NAVIGATE_UP) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            RetargetAction action = new LabelRetargetAction(getId(), WorkbenchMessages.Workbench_up);
            action.setToolTipText(WorkbenchMessages.Workbench_upToolTip);
            window.getPartService().addPartListener(action);
            action.setActionDefinitionId(getCommandId());
            return action;
        }
    };

    /**
* Workbench action (id: "helpContents", commandId: "org.eclipse.ui.help.helpContents"): Open
* the help contents. This action is always enabled.
*/
    public static final ActionFactory HELP_CONTENTS = new ActionFactory(//$NON-NLS-1$
    "helpContents", //$NON-NLS-1$
    IWorkbenchCommandConstants.HELP_HELP_CONTENTS) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new HelpContentsAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "helpSearch", commandId: "org.eclipse.ui.help.helpSearch"): Open the
* help search. This action is always enabled.
*
* @since 3.1
*/
    public static final ActionFactory HELP_SEARCH = new ActionFactory(//$NON-NLS-1$
    "helpSearch", //$NON-NLS-1$
    IWorkbenchCommandConstants.HELP_HELP_SEARCH) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new HelpSearchAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "dynamicHelp", commandId: "org.eclipse.ui.help.dynamicHelp"): Open the
* dynamic help. This action is always enabled.
*
* @since 3.1
*/
    public static final ActionFactory DYNAMIC_HELP = new ActionFactory(//$NON-NLS-1$
    "dynamicHelp", //$NON-NLS-1$
    IWorkbenchCommandConstants.HELP_DYNAMIC_HELP) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new DynamicHelpAction(window);
            action.setId(getId());
            return action;
        }
    };

    /**
* Workbench action (id: "openPerspectiveDialog", commandId: "org.eclipse.ui.perspectives.showPerspective"):
* Open the Open Perspective dialog. This action is always enabled.
*
* @since 3.1
*/
    public static final ActionFactory OPEN_PERSPECTIVE_DIALOG = new ActionFactory(//$NON-NLS-1$
    "openPerspectiveDialog", //$NON-NLS-1$
    IWorkbenchCommandConstants.PERSPECTIVES_SHOW_PERSPECTIVE) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.OpenPerspectiveDialogAction_text);
            action.setToolTipText(WorkbenchMessages.OpenPerspectiveDialogAction_tooltip);
            action.setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_NEW_PAGE));
            return action;
        }
    };

    /**
* Workbench action (id: "newEditor", commandId: "org.eclipse.ui.window.newEditor"): Open a new
* editor on the active editor's input. This action maintains its enablement state.
*
* @since 3.1
*/
    public static final ActionFactory NEW_EDITOR = new ActionFactory(//$NON-NLS-1$
    "newEditor", //$NON-NLS-1$
    IWorkbenchCommandConstants.WINDOW_NEW_EDITOR) {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
            action.setId(getId());
            action.setText(WorkbenchMessages.NewEditorAction_text);
            action.setToolTipText(WorkbenchMessages.NewEditorAction_tooltip);
            return action;
        }
    };

    /**
* Workbench action (id: "toggleCoolbar"): Toggle the visibility of the coolbar and perspective
* switcher. This will only enable visibility of the coolbar and perspective bar if the window
* advisor creating the window allowed for their visibility initially.
*
* @since 3.3
*/
    public static final ActionFactory TOGGLE_COOLBAR = new ActionFactory(//$NON-NLS-1$
    "toggleCoolbar") {

        @Override
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            WorkbenchCommandAction action = new WorkbenchCommandAction("org.eclipse.ui.ToggleCoolbarAction", //$NON-NLS-1$
            window);
            action.setId(getId());
            // set the default text - this will be updated by the handler
            action.setText(WorkbenchMessages.ToggleCoolbarVisibilityAction_hide_text);
            action.setToolTipText(WorkbenchMessages.ToggleCoolbarVisibilityAction_toolTip);
            return action;
        }
    };

    /**
* Establishes bi-direction connections between the forward and backward
* actions of a cycle pair.
* <p>
* Example usage:
*
* <pre>
* ActionFactory.IWorkbenchAction nextEditorAction = ActionFactory.NEXT_EDITOR
* 		.create(window);
* ActionFactory.IWorkbenchAction previousEditorAction = ActionFactory.PREVIOUS_EDITOR
* 		.create(window);
* ActionFactory.linkCycleActionPair(nextEditorAction, previousEditorAction);
* </pre>
*
* </p>
*
* @param next
*            the action that moves forward
* @param previous
*            the action that moves backward
*/
    public static void linkCycleActionPair(IWorkbenchAction next, IWorkbenchAction previous) {
    }

    /**
* Id of actions created by this action factory.
*/
    private final String actionId;

    /**
* Optional ID for this action.
*/
    private final String commandId;

    /**
* Creates a new workbench action factory with the given id.
*
* @param actionId
*            the id of actions created by this action factory
*/
    protected  ActionFactory(String actionId) {
        this(actionId, null);
    }

    /**
* Create a new workbench action factory with the given IDs.
*
* @param actionId
*            the id of actions created by this action factory
* @param commandId
*            the matching command id
* @since 3.5
*/
    protected  ActionFactory(String actionId, String commandId) {
        this.actionId = actionId;
        this.commandId = commandId;
    }

    /**
* Creates a new standard action for the given workbench window. The action
* has an id as specified by the particular factory.
* <p>
* Actions automatically register listeners against the workbench window so
* that they can keep their enablement state up to date. Ordinarily, the
* window's references to these listeners will be dropped automatically
* when the window closes. However, if the client needs to get rid of an
* action while the window is still open, the client must call
* {@link IWorkbenchAction#dispose dispose}to give the action an
* opportunity to deregister its listeners and to perform any other
* cleanup.
* </p>
*
* @param window
*            the workbench window
* @return the workbench action
*/
    public abstract IWorkbenchAction create(IWorkbenchWindow window);

    /**
* Returns the id of this action factory.
*
* @return the id of actions created by this action factory
*/
    public String getId() {
        return actionId;
    }

    /**
* Return the command id of this action factory.
*
* @return the command id of the action created by this action factory. May
*         be <code>null</code>.
* @since 3.5
*/
    public String getCommandId() {
        return commandId;
    }
}
