/***/
package org.eclipse.core.commands.operations;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.commands.ExecutionException;

/**
* Triggered operations are a specialized implementation of a composite
* operation that keeps track of operations triggered by the execution of some
* primary operation. The composite knows which operation was the trigger for
* subsequent operations, and adds all triggered operations as children. When
* execution, undo, or redo is performed, only the triggered operation is
* executed, undone, or redone if it is still present. If the trigger is removed
* from the triggered operations, then the child operations will replace the
* triggered operations in the history.
* <p>
* This class may be instantiated by clients.
* </p>
*
* @since 3.1
*/
public final class TriggeredOperations extends AbstractOperation implements ICompositeOperation, IAdvancedUndoableOperation, IAdvancedUndoableOperation2, IContextReplacingOperation {

    private IUndoableOperation triggeringOperation;

    private IOperationHistory history;

    private List<IUndoableOperation> children = new ArrayList();

    /**
* Construct a composite triggered operations using the specified undoable
* operation as the trigger. Use the label of this trigger as the label of
* the operation.
*
* @param operation
*            the operation that will trigger other operations.
* @param history
*            the operation history containing the triggered operations.
*/
    public  TriggeredOperations(IUndoableOperation operation, IOperationHistory history) {
        super(operation.getLabel());
        triggeringOperation = operation;
        recomputeContexts();
        this.history = history;
    }

    @Override
    public void add(IUndoableOperation operation) {
        children.add(operation);
        recomputeContexts();
    }

    @Override
    public void remove(IUndoableOperation operation) {
        if (operation == triggeringOperation) {
            // the triggering operation is being removed, so we must replace
            // this composite with its individual triggers.
            triggeringOperation = null;
            // save the children before replacing the operation, since this
            // operation will be disposed as part of replacing it. We don't want
            // the children to be disposed since they are to replace this
            // operation.
            List<IUndoableOperation> childrenToRestore = new ArrayList(children);
            children = new ArrayList(0);
            recomputeContexts();
            operation.dispose();
            // now replace the triggering operation
            history.replaceOperation(this, childrenToRestore.toArray(new IUndoableOperation[childrenToRestore.size()]));
        } else {
            children.remove(operation);
            operation.dispose();
            recomputeContexts();
        }
    }

    /**
* Remove the specified context from the receiver. This method is typically
* invoked when the history is being flushed for a certain context. In the
* case of triggered operations, if the only context for the triggering
* operation is being removed, then the triggering operation must be
* replaced in the operation history with the atomic operations that it
* triggered. If the context being removed is not the only context for the
* triggering operation, the triggering operation will remain, and the
* children will each be similarly checked.
*
* @param context
*            the undo context being removed from the receiver.
*/
    @Override
    public void removeContext(IUndoContext context) {
        boolean recompute = false;
        // triggering operation
        if (triggeringOperation != null && triggeringOperation.hasContext(context)) {
            if (triggeringOperation.getContexts().length == 1) {
                remove(triggeringOperation);
                return;
            }
            triggeringOperation.removeContext(context);
            recompute = true;
        }
        // the triggering operation remains, check all the children
        ArrayList<IUndoableOperation> toBeRemoved = new ArrayList();
        for (int i = 0; i < children.size(); i++) {
            IUndoableOperation child = children.get(i);
            if (child.hasContext(context)) {
                if (child.getContexts().length == 1) {
                    toBeRemoved.add(child);
                } else {
                    child.removeContext(context);
                }
                recompute = true;
            }
        }
        for (int i = 0; i < toBeRemoved.size(); i++) {
            remove(toBeRemoved.get(i));
        }
        if (recompute) {
            recomputeContexts();
        }
    }

    @Override
    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (triggeringOperation != null) {
            history.openOperation(this, IOperationHistory.EXECUTE);
            try {
                IStatus status = triggeringOperation.execute(monitor, info);
                history.closeOperation(status.isOK(), false, IOperationHistory.EXECUTE);
                return status;
            } catch (ExecutionException e) {
                history.closeOperation(false, false, IOperationHistory.EXECUTE);
                throw e;
            } catch (RuntimeException e) {
                history.closeOperation(false, false, IOperationHistory.EXECUTE);
                throw e;
            }
        }
        return IOperationHistory.OPERATION_INVALID_STATUS;
    }

    @Override
    public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (triggeringOperation != null) {
            history.openOperation(this, IOperationHistory.REDO);
            List<IUndoableOperation> childrenToRestore = new ArrayList(children);
            try {
                removeAllChildren();
                IStatus status = triggeringOperation.redo(monitor, info);
                if (!status.isOK()) {
                    children = childrenToRestore;
                }
                history.closeOperation(status.isOK(), false, IOperationHistory.REDO);
                return status;
            } catch (ExecutionException e) {
                children = childrenToRestore;
                history.closeOperation(false, false, IOperationHistory.REDO);
                throw e;
            } catch (RuntimeException e) {
                children = childrenToRestore;
                history.closeOperation(false, false, IOperationHistory.REDO);
                throw e;
            }
        }
        return IOperationHistory.OPERATION_INVALID_STATUS;
    }

    @Override
    public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (triggeringOperation != null) {
            history.openOperation(this, IOperationHistory.UNDO);
            List<IUndoableOperation> childrenToRestore = new ArrayList(children);
            try {
                removeAllChildren();
                IStatus status = triggeringOperation.undo(monitor, info);
                if (!status.isOK()) {
                    children = childrenToRestore;
                }
                history.closeOperation(status.isOK(), false, IOperationHistory.UNDO);
                return status;
            } catch (ExecutionException e) {
                children = childrenToRestore;
                history.closeOperation(false, false, IOperationHistory.UNDO);
                throw e;
            } catch (RuntimeException e) {
                children = childrenToRestore;
                history.closeOperation(false, false, IOperationHistory.UNDO);
                throw e;
            }
        }
        return IOperationHistory.OPERATION_INVALID_STATUS;
    }

    @Override
    public boolean canUndo() {
        if (triggeringOperation != null) {
            return triggeringOperation.canUndo();
        }
        return false;
    }

    @Override
    public boolean canExecute() {
        if (triggeringOperation != null) {
            return triggeringOperation.canExecute();
        }
        return false;
    }

    @Override
    public boolean canRedo() {
        if (triggeringOperation != null) {
            return triggeringOperation.canRedo();
        }
        return false;
    }

    /*
* Dispose all operations in the receiver.
*/
    @Override
    public void dispose() {
        for (int i = 0; i < children.size(); i++) {
            (children.get(i)).dispose();
        }
        if (triggeringOperation != null) {
            triggeringOperation.dispose();
        }
    }

    /*
* Recompute contexts in light of some change in the children
*/
    private void recomputeContexts() {
        ArrayList<IUndoContext> allContexts = new ArrayList();
        if (triggeringOperation != null) {
            IUndoContext[] contexts = triggeringOperation.getContexts();
            for (int i = 0; i < contexts.length; i++) {
                allContexts.add(contexts[i]);
            }
        }
        for (int i = 0; i < children.size(); i++) {
            IUndoContext[] contexts = children.get(i).getContexts();
            for (int j = 0; j < contexts.length; j++) {
                if (!allContexts.contains(contexts[j])) {
                    allContexts.add(contexts[j]);
                }
            }
        }
        contexts = allContexts;
    }

    /*
* Remove all non-triggering children
*/
    private void removeAllChildren() {
        IUndoableOperation[] nonTriggers = children.toArray(new IUndoableOperation[children.size()]);
        for (int i = 0; i < nonTriggers.length; i++) {
            children.remove(nonTriggers[i]);
            nonTriggers[i].dispose();
        }
    }

    /**
* Return the operation that triggered the other operations in this
* composite.
*
* @return the IUndoableOperation that triggered the other children.
*/
    public IUndoableOperation getTriggeringOperation() {
        return triggeringOperation;
    }

    @Override
    public Object[] getAffectedObjects() {
        if (triggeringOperation instanceof IAdvancedUndoableOperation) {
            return ((IAdvancedUndoableOperation) triggeringOperation).getAffectedObjects();
        }
        return null;
    }

    @Override
    public void aboutToNotify(OperationHistoryEvent event) {
        if (triggeringOperation instanceof IAdvancedUndoableOperation) {
            ((IAdvancedUndoableOperation) triggeringOperation).aboutToNotify(event);
        }
    }

    @Override
    public IStatus computeUndoableStatus(IProgressMonitor monitor) throws ExecutionException {
        if (triggeringOperation instanceof IAdvancedUndoableOperation) {
            try {
                return ((IAdvancedUndoableOperation) triggeringOperation).computeUndoableStatus(monitor);
            } catch (OperationCanceledException e) {
                return Status.CANCEL_STATUS;
            }
        }
        return Status.OK_STATUS;
    }

    @Override
    public IStatus computeRedoableStatus(IProgressMonitor monitor) throws ExecutionException {
        if (triggeringOperation instanceof IAdvancedUndoableOperation) {
            try {
                return ((IAdvancedUndoableOperation) triggeringOperation).computeRedoableStatus(monitor);
            } catch (OperationCanceledException e) {
                return Status.CANCEL_STATUS;
            }
        }
        return Status.OK_STATUS;
    }

    /**
* Replace the undo context of the receiver with the provided replacement
* undo context. In the case of triggered operations, all contained
* operations are checked and any occurrence of the original context is
* replaced with the new undo context.
* <p>
* This message has no effect if the original undo context is not present in
* the receiver.
*
* @param original
*            the undo context which is to be replaced
* @param replacement
*            the undo context which is replacing the original
* @since 3.2
*/
    @Override
    public void replaceContext(IUndoContext original, IUndoContext replacement) {
        // first check the triggering operation
        if (triggeringOperation != null && triggeringOperation.hasContext(original)) {
            if (triggeringOperation instanceof IContextReplacingOperation) {
                ((IContextReplacingOperation) triggeringOperation).replaceContext(original, replacement);
            } else {
                triggeringOperation.removeContext(original);
                triggeringOperation.addContext(replacement);
            }
        }
        // Now check all the children
        for (int i = 0; i < children.size(); i++) {
            IUndoableOperation child = children.get(i);
            if (child.hasContext(original)) {
                if (child instanceof IContextReplacingOperation) {
                    ((IContextReplacingOperation) child).replaceContext(original, replacement);
                } else {
                    child.removeContext(original);
                    child.addContext(replacement);
                }
            }
        }
        recomputeContexts();
    }

    /**
* Add the specified context to the operation. Overridden in
* TriggeredOperations to add the specified undo context to the triggering
* operation.
*
* @param context
*            the context to be added
*
* @since 3.2
*/
    @Override
    public void addContext(IUndoContext context) {
        if (triggeringOperation != null) {
            triggeringOperation.addContext(context);
            recomputeContexts();
        }
    }

    /**
* @since 3.6
*/
    @Override
    public IStatus computeExecutionStatus(IProgressMonitor monitor) throws ExecutionException {
        if (triggeringOperation instanceof IAdvancedUndoableOperation2) {
            try {
                return ((IAdvancedUndoableOperation2) triggeringOperation).computeExecutionStatus(monitor);
            } catch (OperationCanceledException e) {
                return Status.CANCEL_STATUS;
            }
        }
        return Status.OK_STATUS;
    }

    /**
* @since 3.6
*/
    @Override
    public void setQuietCompute(boolean quiet) {
        if (triggeringOperation instanceof IAdvancedUndoableOperation2) {
            ((IAdvancedUndoableOperation2) triggeringOperation).setQuietCompute(quiet);
        }
    }

    /**
* @since 3.6
*/
    @Override
    public boolean runInBackground() {
        if (triggeringOperation instanceof IAdvancedUndoableOperation2) {
            return ((IAdvancedUndoableOperation2) triggeringOperation).runInBackground();
        }
        return false;
    }
}