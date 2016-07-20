/***/
package org.eclipse.ui.internal.expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;

/**
* Copied from org.eclipse.core.internal.expressions.
*/
public abstract class CompositeExpression extends Expression {

    private static final Expression[] EMPTY_ARRAY = new Expression[0];

    protected List fExpressions;

    public void add(Expression expression) {
        if (fExpressions == null) {
            fExpressions = new ArrayList(2);
        }
        fExpressions.add(expression);
    }

    public Expression[] getChildren() {
        if (fExpressions == null) {
            return EMPTY_ARRAY;
        }
        return (Expression[]) fExpressions.toArray(new Expression[fExpressions.size()]);
    }

    protected EvaluationResult evaluateAnd(IEvaluationContext scope) throws CoreException {
        if (fExpressions == null) {
            return EvaluationResult.TRUE;
        }
        EvaluationResult result = EvaluationResult.TRUE;
        for (Iterator iter = fExpressions.iterator(); iter.hasNext(); ) {
            Expression expression = (Expression) iter.next();
            result = result.and(expression.evaluate(scope));
            // that we find a false which will result in a better result.
            if (result == EvaluationResult.FALSE) {
                return result;
            }
        }
        return result;
    }

    protected EvaluationResult evaluateOr(IEvaluationContext scope) throws CoreException {
        if (fExpressions == null) {
            return EvaluationResult.TRUE;
        }
        EvaluationResult result = EvaluationResult.FALSE;
        for (Iterator iter = fExpressions.iterator(); iter.hasNext(); ) {
            Expression expression = (Expression) iter.next();
            result = result.or(expression.evaluate(scope));
            if (result == EvaluationResult.TRUE) {
                return result;
            }
        }
        return result;
    }

    @Override
    public void collectExpressionInfo(ExpressionInfo info) {
        if (fExpressions == null) {
            return;
        }
        for (Iterator iter = fExpressions.iterator(); iter.hasNext(); ) {
            Expression expression = (Expression) iter.next();
            expression.collectExpressionInfo(info);
        }
    }
}