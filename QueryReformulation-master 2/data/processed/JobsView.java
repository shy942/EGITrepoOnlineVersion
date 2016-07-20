/***/
package org.eclipse.ui.examples.jobs.views;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.examples.jobs.TestJob;
import org.eclipse.ui.examples.jobs.TestJobRule;
import org.eclipse.ui.examples.jobs.UITestJob;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.IProgressConstants;
import org.eclipse.ui.progress.IProgressService;

/**
* A view that allows a user to create jobs of various types, and interact with
* and test other job-related APIs.
*/
public class JobsView extends ViewPart {

    private Combo durationField;

    private Button lockField, failureField, threadField, systemField, userField, groupField, rescheduleField, keepField, keepOneField, unknownField, gotoActionField;

    private Text quantityField, delayField, rescheduleDelay;

    private Button schedulingRuleField;

    private Button noPromptField;

    protected void busyCursorWhile() {
        try {
            final long duration = getDuration();
            final boolean shouldLock = lockField.getSelection();
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) {
                    if (shouldLock)
                        doRunInWorkspace(duration, monitor);
                    else
                        doRun(duration, monitor);
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }

    protected void createJobs() {
        int jobCount = Integer.parseInt(quantityField.getText());
        boolean ui = threadField.getSelection();
        long duration = getDuration();
        boolean lock = lockField.getSelection();
        boolean failure = failureField.getSelection();
        boolean noPrompt = noPromptField.getSelection();
        boolean system = systemField.getSelection();
        boolean useGroup = groupField.getSelection();
        boolean unknown = unknownField.getSelection();
        boolean user = userField.getSelection();
        boolean reschedule = rescheduleField.getSelection();
        final long rescheduleWait = Long.parseLong(rescheduleDelay.getText());
        boolean keep = keepField.getSelection();
        boolean keepOne = keepOneField.getSelection();
        boolean gotoAction = gotoActionField.getSelection();
        boolean schedulingRule = schedulingRuleField.getSelection();
        int groupIncrement = IProgressMonitor.UNKNOWN;
        IProgressMonitor group = new NullProgressMonitor();
        int total = IProgressMonitor.UNKNOWN;
        if (jobCount > 1) {
            total = 100;
            groupIncrement = 100 / jobCount;
        }
        if (useGroup) {
            group = Platform.getJobManager().createProgressGroup();
            //$NON-NLS-1$
            group.beginTask("Group", total);
        }
        long delay = Integer.parseInt(delayField.getText());
        for (int i = 0; i < jobCount; i++) {
            Job result;
            if (ui)
                result = new UITestJob(duration, lock, failure, unknown);
            else
                result = new TestJob(duration, lock, failure, unknown, reschedule, rescheduleWait);
            result.setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.valueOf(keep));
            result.setProperty(IProgressConstants.KEEPONE_PROPERTY, Boolean.valueOf(keepOne));
            result.setProperty(IProgressConstants.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY, Boolean.valueOf(noPrompt));
            if (gotoAction)
                result.setProperty(IProgressConstants.ACTION_PROPERTY, new //$NON-NLS-1$
                Action(//$NON-NLS-1$
                "Pop up a dialog") {

                    @Override
                    public void run() {
                        MessageDialog.openInformation(getSite().getShell(), "Goto Action", //$NON-NLS-1$ //$NON-NLS-2$
                        "The job can have an action associated with it");
                    }
                });
            result.setProgressGroup(group, groupIncrement);
            result.setSystem(system);
            result.setUser(user);
            if (schedulingRule)
                result.setRule(new TestJobRule(i));
            result.schedule(delay);
        }
    }

    /**
* @see ViewPart#createPartControl(Composite)
*/
    @Override
    public void createPartControl(Composite parent) {
        Composite body = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;
        body.setLayout(layout);
        createEntryFieldGroup(body);
        createPushButtonGroup(body);
        createCheckboxGroup(body);
    }

    /**
* Create all push button parts for the jobs view.
*
* @param parent
*/
    private void createPushButtonGroup(Composite parent) {
        Composite group = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        group.setLayout(layout);
        group.setLayoutData(new GridData(GridData.FILL_BOTH));
        // create jobs
        Button create = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        create.setText("Create jobs");
        create.setToolTipText(//$NON-NLS-1$
        "Creates and schedules jobs according to above parameters");
        create.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        create.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                createJobs();
            }
        });
        // touch workspace
        Button touch = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        touch.setText("Touch workspace");
        //$NON-NLS-1$
        touch.setToolTipText("Modifies the workspace in the UI thread");
        touch.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        touch.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                touchWorkspace();
            }
        });
        // busy cursor while
        Button busyWhile = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        busyWhile.setText("busyCursorWhile");
        //$NON-NLS-1$
        busyWhile.setToolTipText("Uses IProgressService.busyCursorWhile");
        busyWhile.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        busyWhile.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                busyCursorWhile();
            }
        });
        // progress monitor dialog with fork=false
        Button noFork = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        noFork.setText("runInUI");
        //$NON-NLS-1$
        noFork.setToolTipText("Uses IProgressService.runInUI");
        noFork.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        noFork.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                progressNoFork();
            }
        });
        // progress monitor dialog with fork=false
        Button exception = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        exception.setText("Runtime Exception");
        //$NON-NLS-1$
        exception.setToolTipText("NullPointerException when running");
        exception.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        exception.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                jobWithRuntimeException();
            }
        });
        // join the running test jobs
        Button join = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        join.setText("Join Test Jobs");
        //$NON-NLS-1$
        join.setToolTipText("IJobManager.join() on test jobs");
        join.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        join.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                joinTestJobs();
            }
        });
        // join the running test jobs
        Button window = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        window.setText("Runnable in Window");
        window.setToolTipText(//$NON-NLS-1$
        "Using a runnable context in the workbench window");
        window.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        window.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                runnableInWindow();
            }
        });
        // join the running test jobs
        Button sleep = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        sleep.setText("Sleep");
        //$NON-NLS-1$
        sleep.setToolTipText("Calls sleep() on all TestJobs");
        sleep.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        sleep.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                doSleep();
            }
        });
        // join the running test jobs
        Button wake = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        wake.setText("WakeUp");
        //$NON-NLS-1$
        wake.setToolTipText("Calls wakeUp() on all TestJobs");
        wake.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        wake.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                doWakeUp();
            }
        });
        // show in dialog
        Button showInDialog = new Button(group, SWT.PUSH);
        //$NON-NLS-1$
        showInDialog.setText("showInDialog");
        //$NON-NLS-1$
        showInDialog.setToolTipText("Uses IProgressService.showInDialog");
        showInDialog.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        showInDialog.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                showInDialog();
            }
        });
    }

    /**
* Test the showInDialog API
*
*/
    protected void showInDialog() {
        Job showJob = new //$NON-NLS-1$
        Job(//$NON-NLS-1$
        "Show In Dialog") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                //$NON-NLS-1$
                monitor.beginTask("Run in dialog", 100);
                for (int i = 0; i < 100; i++) {
                    if (monitor.isCanceled())
                        return Status.CANCEL_STATUS;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return Status.CANCEL_STATUS;
                    }
                    monitor.worked(1);
                }
                return Status.OK_STATUS;
            }
        };
        showJob.schedule();
        PlatformUI.getWorkbench().getProgressService().showInDialog(getSite().getShell(), showJob);
    }

    /**
* Wakes up all sleeping test jobs.
*/
    protected void doWakeUp() {
        Platform.getJobManager().wakeUp(TestJob.FAMILY_TEST_JOB);
    }

    /**
* Puts to sleep all waiting test jobs.
*/
    protected void doSleep() {
        Platform.getJobManager().sleep(TestJob.FAMILY_TEST_JOB);
    }

    /**
* @param body
*/
    private void createEntryFieldGroup(Composite body) {
        // duration
        Label label = new Label(body, SWT.NONE);
        //$NON-NLS-1$
        label.setText("Duration:");
        durationField = new Combo(body, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        durationField.setLayoutData(data);
        //$NON-NLS-1$
        durationField.add("0");
        //$NON-NLS-1$
        durationField.add("1 millisecond");
        //$NON-NLS-1$
        durationField.add("1 second");
        //$NON-NLS-1$
        durationField.add("10 seconds");
        //$NON-NLS-1$
        durationField.add("1 minute");
        //$NON-NLS-1$
        durationField.add("10 minutes");
        durationField.select(4);
        // delay
        label = new Label(body, SWT.NONE);
        //$NON-NLS-1$
        label.setText("Start delay (ms):");
        delayField = new Text(body, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        delayField.setLayoutData(data);
        //$NON-NLS-1$
        delayField.setText("0");
        // quantity
        label = new Label(body, SWT.NONE);
        //$NON-NLS-1$
        label.setText("Quantity:");
        quantityField = new Text(body, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        quantityField.setLayoutData(data);
        //$NON-NLS-1$
        quantityField.setText("1");
        // reschedule delay
        label = new Label(body, SWT.NONE);
        //$NON-NLS-1$
        label.setText("Reschedule Delay (ms):");
        rescheduleDelay = new Text(body, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        rescheduleDelay.setLayoutData(data);
        //$NON-NLS-1$
        rescheduleDelay.setText("1000");
    }

    /**
* Creates all of the checkbox buttons.
*/
    private void createCheckboxGroup(Composite parent) {
        Composite group = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        group.setLayout(layout);
        group.setLayoutData(new GridData(GridData.FILL_BOTH));
        // lock
        lockField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        lockField.setText("Lock the workspace");
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        lockField.setLayoutData(data);
        // system
        systemField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        systemField.setText("System job");
        data = new GridData(GridData.FILL_HORIZONTAL);
        systemField.setLayoutData(data);
        // thread
        threadField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        threadField.setText("Run in UI thread");
        data = new GridData(GridData.FILL_HORIZONTAL);
        threadField.setLayoutData(data);
        // groups
        groupField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        groupField.setText("Run in Group");
        data = new GridData(GridData.FILL_HORIZONTAL);
        groupField.setLayoutData(data);
        // reschedule
        rescheduleField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        rescheduleField.setText("Reschedule");
        data = new GridData(GridData.FILL_HORIZONTAL);
        rescheduleField.setLayoutData(data);
        // keep
        keepField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        keepField.setText("Keep");
        data = new GridData(GridData.FILL_HORIZONTAL);
        keepField.setLayoutData(data);
        // keep one
        keepOneField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        keepOneField.setText("KeepOne");
        data = new GridData(GridData.FILL_HORIZONTAL);
        keepOneField.setLayoutData(data);
        // IProgressMonitor.UNKNOWN
        unknownField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        unknownField.setText("Indeterminate Progress");
        data = new GridData(GridData.FILL_HORIZONTAL);
        unknownField.setLayoutData(data);
        // whether the job is a user job
        userField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        userField.setText("User job");
        data = new GridData(GridData.FILL_HORIZONTAL);
        userField.setLayoutData(data);
        // whether the job has a goto action
        gotoActionField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        gotoActionField.setText("Goto action");
        data = new GridData(GridData.FILL_HORIZONTAL);
        gotoActionField.setLayoutData(data);
        // whether the job should use a scheduling rule
        schedulingRuleField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        schedulingRuleField.setText("Schedule sequentially");
        schedulingRuleField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // failure
        failureField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        failureField.setText("Fail");
        failureField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // failure
        noPromptField = new Button(group, SWT.CHECK);
        //$NON-NLS-1$
        noPromptField.setText("No Prompt");
        noPromptField.setToolTipText(//$NON-NLS-1$
        "Set the IProgressConstants.NO_IMMEDIATE_ERROR_PROMPT_PROPERTY to true");
        noPromptField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    protected void doRun(long duration, IProgressMonitor monitor) {
        final long sleep = 10;
        int ticks = (int) (duration / sleep);
        monitor.beginTask("Spinning inside IProgressService.busyCursorWhile", //$NON-NLS-1$
        ticks);
        //$NON-NLS-1$
        monitor.setTaskName("Spinning inside IProgressService.busyCursorWhile");
        for (int i = 0; i < ticks; i++) {
            //$NON-NLS-1$
            monitor.subTask("Processing tick #" + i);
            if (monitor.isCanceled())
                return;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            }
            monitor.worked(1);
        }
    }

    protected void doRunInWorkspace(final long duration, IProgressMonitor monitor) {
        try {
            ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

                @Override
                public void run(IProgressMonitor monitor) throws CoreException {
                    doRun(duration, monitor);
                }
            }, monitor);
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    protected long getDuration() {
        switch(durationField.getSelectionIndex()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 1000;
            case 3:
                return 10000;
            case 4:
                return 60000;
            case 5:
            default:
                return 600000;
        }
    }

    protected void jobWithRuntimeException() {
        Job runtimeExceptionJob = new //$NON-NLS-1$
        Job(//$NON-NLS-1$
        "Job with Runtime exception") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                throw new NullPointerException();
            }
        };
        runtimeExceptionJob.schedule();
    }

    /**
* Example usage of the IJobManager.join method.
*/
    protected void joinTestJobs() {
        try {
            // note that when a null progress monitor is used when in the UI
            // thread, the workbench will create a default progress monitor
            // that reports progress in a modal dialog with details area
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InterruptedException {
                    Job.getJobManager().join(TestJob.FAMILY_TEST_JOB, monitor);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void progressNoFork() {
        try {
            final long duration = getDuration();
            final boolean shouldLock = lockField.getSelection();
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            progressService.runInUI(progressService, new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InterruptedException {
                    if (shouldLock)
                        doRunInWorkspace(duration, monitor);
                    else
                        doRun(duration, monitor);
                }
            }, ResourcesPlugin.getWorkspace().getRoot());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
* @see ViewPart#setFocus()
*/
    @Override
    public void setFocus() {
        if (durationField != null && !durationField.isDisposed())
            durationField.setFocus();
    }

    protected void touchWorkspace() {
        // create an asyncExec to touch the workspace the specific number of
        // times
        int jobCount = Integer.parseInt(quantityField.getText());
        for (int i = 0; i < jobCount; i++) {
            getSite().getShell().getDisplay().asyncExec(new Runnable() {

                @Override
                public void run() {
                    try {
                        ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

                            @Override
                            public void run(IProgressMonitor monitor) {
                            // no-op
                            }
                        }, null);
                    } catch (OperationCanceledException e) {
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
* Run a workspace runnable in the application window.
*
*/
    public void runnableInWindow() {
        final long time = getDuration();
        final long sleep = 10;
        IRunnableWithProgress runnableTest = new WorkspaceModifyOperation() {

            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                int ticks = (int) (time / sleep);
                monitor.beginTask("Spinning inside ApplicationWindow.run()", //$NON-NLS-1$
                ticks);
                //$NON-NLS-1$
                monitor.setTaskName("Spinning inside ApplicationWindow.run()");
                for (int i = 0; i < ticks; i++) {
                    //$NON-NLS-1$
                    monitor.subTask("Processing tick #" + i);
                    if (monitor.isCanceled())
                        return;
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                    }
                    monitor.worked(1);
                }
            }
        };
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, true, runnableTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
