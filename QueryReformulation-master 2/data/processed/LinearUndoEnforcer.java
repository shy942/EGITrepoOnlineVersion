/***/
package org.eclipse.core.commands.operations;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
* <p>
* An operation approver that enforces a strict linear undo. It does not allow
* the undo or redo of any operation that is not the latest available operation
* in all of its undo contexts.  This class may be instantiated by clients.
* </p>
*
* @since 3.1
*/
public final class LinearUndoEnforcer extends LinearUndoViolationDetector {

    /**
* Create an instance of LinearUndoEnforcer.
*/
    public  LinearUndoEnforcer() {
        super();
    }

    /*
* Return whether a linear redo violation is allowable.  A linear redo violation
* is defined as a request to redo a particular operation even if it is not the most
* recently added operation to the redo history.
*/
    @Override
    protected IStatus allowLinearRedoViolation(IUndoableOperation operation, IUndoContext context, IOperationHistory history, IAdaptable uiInfo) {
        return Status.CANCEL_STATUS;
    }

    /*
* Return whether a linear undo violation is allowable.  A linear undo violation
* is defined as a request to undo a particular operation even if it is not the most
* recently added operation to the undo history.
*/
    @Override
    protected IStatus allowLinearUndoViolation(IUndoableOperation operation, IUndoContext context, IOperationHistory history, IAdaptable uiInfo) {
        return Status.CANCEL_STATUS;
    }
}
