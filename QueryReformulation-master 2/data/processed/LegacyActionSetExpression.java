/***/
package org.eclipse.ui.internal.expressions;

import java.util.Collection;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;

/**
* <p>
* An expression that evaluates whether a particular action set is active.
* </p>
* <p>
* This class is not intended for use outside of the
* <code>org.eclipse.ui.workbench</code> plug-in.
* </p>
*
* @since 3.2
*/
public final class LegacyActionSetExpression extends WorkbenchWindowExpression {

    /**
* The seed for the hash code for all schemes.
*/
    private static final int HASH_INITIAL = LegacyActionSetExpression.class.getName().hashCode();

    /**
* The identifier of the action set that must be active for this expression
* to evaluate to <code>true</code>. This value is never
* <code>null</code>.
*/
    private final String actionSetId;

    /**
* Constructs a new instance of {@link LegacyActionSetExpression}.
*
* @param actionSetId
*            The identifier of the action set that must be active for this
*            expression to evaluate to <code>true</code>; must not be
*            <code>null</code>.
* @param window
*            The workbench window in which this handler should be active.
*            This avoids conflicts between handlers from different windows.
*            This should not be <code>null</code>.
*/
    public  LegacyActionSetExpression(final String actionSetId, final IWorkbenchWindow window) {
        super(window);
        if (actionSetId == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "The action set identifier cannot be null");
        }
        this.actionSetId = actionSetId;
    }

    @Override
    public final void collectExpressionInfo(final ExpressionInfo info) {
        super.collectExpressionInfo(info);
        info.addVariableNameAccess(ISources.ACTIVE_CONTEXT_NAME);
    }

    protected final int computeHhashCode() {
        int hashCode = HASH_INITIAL * HASH_FACTOR + hashCode(getWindow());
        hashCode = hashCode * HASH_FACTOR + hashCode(actionSetId);
        return hashCode;
    }

    @Override
    public final boolean equals(final Object object) {
        if (object instanceof LegacyActionSetExpression) {
            final LegacyActionSetExpression that = (LegacyActionSetExpression) object;
            return equals(this.actionSetId, that.actionSetId) && equals(this.getWindow(), that.getWindow());
        }
        return false;
    }

    @Override
    public final EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
        final EvaluationResult result = super.evaluate(context);
        if (result == EvaluationResult.FALSE) {
            return result;
        }
        Object obj = context.getVariable(ISources.ACTIVE_CONTEXT_NAME);
        if (obj instanceof Collection<?>) {
            return EvaluationResult.valueOf(((Collection) obj).contains(actionSetId));
        }
        return EvaluationResult.FALSE;
    }

    @Override
    public final String toString() {
        final StringBuffer buffer = new StringBuffer();
        //$NON-NLS-1$
        buffer.append("ActionSetExpression(");
        buffer.append(actionSetId);
        buffer.append(',');
        buffer.append(getWindow());
        buffer.append(')');
        return buffer.toString();
    }
}
