/***/
package org.eclipse.ui.internal.expressions;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.ActionExpression;

/**
* <p>
* This wrappers the old {@link ActionExpression} class so that it can
* communicate via the {@link Expression} contract.
* </p>
* <p>
* This class is not intended for use outside of the
* <code>org.eclipse.ui.workbench</code> plug-in.
* </p>
*
* @since 3.2
*/
public final class LegacyActionExpressionWrapper extends WorkbenchWindowExpression {

    /**
* The seed for the hash code for all schemes.
*/
    private static final int HASH_INITIAL = LegacyActionExpressionWrapper.class.getName().hashCode();

    /**
* The legacy action expression being wrapped; never <code>null</code>.
*/
    private final ActionExpression expression;

    /**
* Constructs a new instance of {@link LegacyActionExpressionWrapper}.
*
* @param expression
*            The expression to wrap; must not be <code>null</code>.
* @param window
*            The workbench window which must be active for this expression
*            to evaluate to <code>true</code>; may be <code>null</code>
*            if the window should be disregarded.
*/
    public  LegacyActionExpressionWrapper(final ActionExpression expression, final IWorkbenchWindow window) {
        super(window);
        if (expression == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "The action expression cannot be null");
        }
        this.expression = expression;
    }

    @Override
    public final void collectExpressionInfo(final ExpressionInfo info) {
        super.collectExpressionInfo(info);
        info.markDefaultVariableAccessed();
    }

    @Override
    protected final int computeHashCode() {
        int hashCode = HASH_INITIAL * HASH_FACTOR + hashCode(getWindow());
        hashCode = hashCode * HASH_FACTOR + hashCode(expression);
        return hashCode;
    }

    @Override
    public final boolean equals(final Object object) {
        if (object instanceof LegacyActionExpressionWrapper) {
            final LegacyActionExpressionWrapper that = (LegacyActionExpressionWrapper) object;
            return equals(this.expression, that.expression) && equals(this.getWindow(), that.getWindow());
        }
        return false;
    }

    @Override
    public final EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
        final EvaluationResult result = super.evaluate(context);
        if (result == EvaluationResult.FALSE) {
            return result;
        }
        final Object defaultVariable = context.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
        if (defaultVariable instanceof IStructuredSelection) {
            final IStructuredSelection selection = (IStructuredSelection) defaultVariable;
            if (expression.isEnabledFor(selection)) {
                return EvaluationResult.TRUE;
            }
        } else if (expression.isEnabledFor(defaultVariable)) {
            return EvaluationResult.TRUE;
        }
        return EvaluationResult.FALSE;
    }

    @Override
    public final String toString() {
        final StringBuffer buffer = new StringBuffer();
        //$NON-NLS-1$
        buffer.append("LegacyActionExpressionWrapper(");
        buffer.append(expression);
        buffer.append(',');
        buffer.append(getWindow());
        buffer.append(')');
        return buffer.toString();
    }
}
