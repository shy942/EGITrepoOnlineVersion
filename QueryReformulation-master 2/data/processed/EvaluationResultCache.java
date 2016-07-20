/***/
package org.eclipse.ui.internal.services;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.ISources;

/**
* <p>
* A token representing the activation or contribution of some expression-based
* element. This caches the evaluation result so that it is only re-computed as
* necessary.
* </p>
*
* @since 3.2
*/
public abstract class EvaluationResultCache implements IEvaluationResultCache {

    /**
* The previous computed evaluation result. If no evaluation result is
* available, then this value is <code>null</code>.
*/
    private EvaluationResult evaluationResult = null;

    /**
* The expression to evaluate. This value may be <code>null</code>, in
* which case the evaluation result is always <code>true</code>.
*/
    private final Expression expression;

    /**
* The priority that has been given to this expression.
*/
    private final int sourcePriority;

    /**
* Constructs a new instance of <code>EvaluationResultCache</code>.
*
* @param expression
*            The expression that must evaluate to <code>true</code>
*            before this handler is active. This value may be
*            <code>null</code> if it is always active.
* @see ISources
*/
    protected  EvaluationResultCache(final Expression expression) {
        this.expression = expression;
        this.sourcePriority = SourcePriorityNameMapping.computeSourcePriority(expression);
    }

    @Override
    public final void clearResult() {
        evaluationResult = null;
    }

    @Override
    public final boolean evaluate(final IEvaluationContext context) {
        if (expression == null) {
            return true;
        }
        if (evaluationResult == null) {
            try {
                evaluationResult = expression.evaluate(context);
            } catch (final CoreException e) {
                evaluationResult = EvaluationResult.FALSE;
                return false;
            }
        }
        // return true if  the result is FALSE or NOT_LOADED
        return evaluationResult != EvaluationResult.FALSE;
    }

    @Override
    public final Expression getExpression() {
        return expression;
    }

    @Override
    public final int getSourcePriority() {
        return sourcePriority;
    }

    @Override
    public final void setResult(final boolean result) {
        if (result) {
            evaluationResult = EvaluationResult.TRUE;
        } else {
            evaluationResult = EvaluationResult.FALSE;
        }
    }
}
