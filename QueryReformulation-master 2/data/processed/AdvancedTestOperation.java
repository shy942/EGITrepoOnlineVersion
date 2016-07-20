/***/
package org.eclipse.ui.tests.operations;

import org.eclipse.core.commands.operations.IAdvancedUndoableOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
* @since 3.1
*/
public class AdvancedTestOperation extends TestOperation implements IAdvancedUndoableOperation {

    static final int INFO = 0;

    static final int ERROR = 1;

    static final int WARNING = 2;

    public int status = 0;

    public  AdvancedTestOperation() {
        super("A very long string that exceeds the menu label limit");
    }

    @Override
    public void aboutToNotify(OperationHistoryEvent event) {
    // do nothing
    }

    @Override
    public Object[] getAffectedObjects() {
        return null;
    }

    @Override
    public IStatus computeUndoableStatus(IProgressMonitor monitor) {
        switch(status) {
            case INFO:
                return IOperationHistory.NOTHING_TO_UNDO_STATUS;
            case ERROR:
                return IOperationHistory.OPERATION_INVALID_STATUS;
            case WARNING:
                //$NON-NLS-1$
                return new OperationStatus(IStatus.WARNING, "org.eclipse.ui.tests", 0, "Undo warning message", null);
        }
        return Status.OK_STATUS;
    }

    @Override
    public IStatus computeRedoableStatus(IProgressMonitor monitor) {
        switch(status) {
            case INFO:
                return IOperationHistory.NOTHING_TO_REDO_STATUS;
            case ERROR:
                return IOperationHistory.OPERATION_INVALID_STATUS;
            case WARNING:
                //$NON-NLS-1$
                return new OperationStatus(IStatus.WARNING, "org.eclipse.ui.tests", 0, "Redo warning message", null);
        }
        return Status.OK_STATUS;
    }
}
