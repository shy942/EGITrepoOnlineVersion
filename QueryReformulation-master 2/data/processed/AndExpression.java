/***/
package org.eclipse.ui.internal.expressions;

import java.util.Iterator;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;

/**
* Copied from org.eclipse.core.internal.expressions.
*/
public final class AndExpression extends CompositeExpression {

    /**
* The seed for the hash code for all schemes.
*/
    private static final int HASH_INITIAL = AndExpression.class.getName().hashCode();

    @Override
    protected final int computeHashCode() {
        return HASH_INITIAL * HASH_FACTOR + hashCode(fExpressions);
    }

    @Override
    public final boolean equals(final Object object) {
        if (object instanceof AndExpression) {
            final AndExpression that = (AndExpression) object;
            return equals(this.fExpressions, that.fExpressions);
        }
        return false;
    }

    @Override
    public final EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
        return evaluateAnd(context);
    }

    @Override
    public final String toString() {
        final StringBuffer buffer = new StringBuffer();
        //$NON-NLS-1$
        buffer.append("AndExpression(");
        if (fExpressions != null) {
            final Iterator itr = fExpressions.iterator();
            while (itr.hasNext()) {
                final Expression expression = (Expression) itr.next();
                buffer.append(expression.toString());
                if (itr.hasNext()) {
                    buffer.append(',');
                }
            }
        }
        buffer.append(')');
        return buffer.toString();
    }
}
