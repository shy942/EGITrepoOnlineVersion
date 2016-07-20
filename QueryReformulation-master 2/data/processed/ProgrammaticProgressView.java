/***/
package org.eclipse.e4.ui.progress;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.progress.internal.DetailedProgressViewer;
import org.eclipse.e4.ui.progress.internal.FinishedJobs;
import org.eclipse.e4.ui.progress.internal.ProgressManager;
import org.eclipse.e4.ui.progress.internal.ProgressManagerUtil;
import org.eclipse.e4.ui.progress.internal.ProgressViewUpdater;
import org.eclipse.e4.ui.progress.internal.ProgressViewerContentProvider;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
* @noreference
*/
public class ProgrammaticProgressView {

    //$NON-NLS-1$
    private static final String CLEAR_ALL_ICON_URI = "platform:/plugin/org.eclipse.e4.ui.progress/icons/full/elcl16/progress_remall.png";

    DetailedProgressViewer viewer;

    @Inject
    ESelectionService selectionService;

    @Inject
    EModelService modelService;

    MApplication application;

    MPart part;

    ISelectionChangedListener selectionListener;

    private MCommand clearAllCommand;

    private MCommand showPreferencesCommand;

    @PostConstruct
    public void createPartControl(Composite parent, MApplication application, MPart part, ProgressManager progressManager, IProgressService progressService, FinishedJobs finishedJobs, ProgressViewUpdater viewUpdater) {
        this.application = application;
        this.part = part;
        viewer = new DetailedProgressViewer(parent, SWT.MULTI | SWT.H_SCROLL, progressService, finishedJobs);
        viewer.setComparator(ProgressManagerUtil.getProgressViewerComparator());
        viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        //		helpSystem.setHelp(parent, IWorkbenchHelpContextIds.RESPONSIVE_UI);
        ProgressViewerContentProvider provider = new ProgressViewerContentProvider(viewer, finishedJobs, viewUpdater, progressManager, true, true);
        viewer.setContentProvider(provider);
        viewer.setInput(progressManager);
        selectionListener = new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (selectionService != null)
                    selectionService.setSelection(event.getSelection());
            }
        };
        viewer.addSelectionChangedListener(selectionListener);
        createCommands();
        createViewMenu();
    }

    @Focus
    public void setFocus() {
        if (viewer != null) {
            viewer.setFocus();
        }
    }

    private void createCommands() {
        clearAllCommand = modelService.createModelElement(MCommand.class);
        //$NON-NLS-1$
        clearAllCommand.setElementId("clearAllCommand");
        //$NON-NLS-1$
        clearAllCommand.setCommandName("clearAllCommand");
        application.getCommands().add(clearAllCommand);
        showPreferencesCommand = modelService.createModelElement(MCommand.class);
        //$NON-NLS-1$
        showPreferencesCommand.setElementId("showPreferencesCommand");
        //$NON-NLS-1$
        showPreferencesCommand.setCommandName("showPreferencesCommand");
        application.getCommands().add(showPreferencesCommand);
        MHandler clearAllHandler = modelService.createModelElement(MHandler.class);
        clearAllHandler.setCommand(clearAllCommand);
        //$NON-NLS-1$
        clearAllHandler.setContributionURI("bundleclass://org.eclipse.e4.ui.progress/org.eclipse.e4.ui.progress.ClearAllHandler");
        part.getHandlers().add(clearAllHandler);
        MHandler showPreferencesHandler = modelService.createModelElement(MHandler.class);
        showPreferencesHandler.setCommand(showPreferencesCommand);
        //$NON-NLS-1$
        showPreferencesHandler.setContributionURI("bundleclass://org.eclipse.e4.ui.progress/org.eclipse.e4.ui.progress.OpenPreferenceDialogHandler");
        part.getHandlers().add(showPreferencesHandler);
    }

    private void createViewMenu() {
        MHandledToolItem clearAllButton = modelService.createModelElement(MHandledToolItem.class);
        clearAllButton.setIconURI(CLEAR_ALL_ICON_URI);
        clearAllButton.setCommand(clearAllCommand);
        MToolBar toolBar = modelService.createModelElement(MToolBar.class);
        toolBar.getChildren().add(clearAllButton);
        part.setToolbar(toolBar);
        MHandledMenuItem clearAllMenuItem = modelService.createModelElement(MHandledMenuItem.class);
        //$NON-NLS-1$
        clearAllMenuItem.setLabel("Clear All");
        clearAllMenuItem.setIconURI(CLEAR_ALL_ICON_URI);
        clearAllMenuItem.setCommand(clearAllCommand);
        MHandledMenuItem preferencesMenuItem = modelService.createModelElement(MHandledMenuItem.class);
        //$NON-NLS-1$
        preferencesMenuItem.setLabel("Preferences");
        preferencesMenuItem.setCommand(showPreferencesCommand);
        MMenu menu = modelService.createModelElement(MMenu.class);
        // required //$NON-NLS-1$
        menu.getTags().add("ViewMenu");
        menu.getChildren().add(clearAllMenuItem);
        menu.getChildren().add(preferencesMenuItem);
        part.getMenus().add(menu);
    }
}
