/***/
package org.eclipse.ui.internal.contexts;

import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.ISources;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.internal.services.EvaluationResultCache;

/**
* <p>
* A token representing the activation of a context. This token can later be
* used to cancel that activation. Without this token, then the context will
* only become inactive if the component in which the context was activated is
* destroyed.
* </p>
* <p>
* This caches the context id, so that they can later be identified.
* </p>
* <p>
* Note: this class has a natural ordering that is inconsistent with equals.
* </p>
*
* @since 3.1
*/
final class ContextActivation extends EvaluationResultCache implements IContextActivation {

    /**
* The identifier for the context which should be active. This value is
* never <code>null</code>.
*/
    private final String contextId;

    /**
* The context service from which this context activation was requested.
* This value is never <code>null</code>.
*/
    private final IContextService contextService;

    /**
* Constructs a new instance of <code>ContextActivation</code>.
*
* @param contextId
*            The identifier for the context which should be activated. This
*            value must not be <code>null</code>.
* @param expression
*            The expression that must evaluate to <code>true</code>
*            before this handler is active. This value may be
*            <code>null</code> if it is always active.
* @param contextService
*            The context service from which the handler activation was
*            requested; must not be <code>null</code>.
* @see ISources
*/
    public  ContextActivation(final String contextId, final Expression expression, final IContextService contextService) {
        super(expression);
        if (contextId == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "The context identifier for a context activation cannot be null");
        }
        if (contextService == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "The context service for an activation cannot be null");
        }
        this.contextId = contextId;
        this.contextService = contextService;
    }

    @Override
    public final void clearActive() {
        clearResult();
    }

    @Override
    public final String getContextId() {
        return contextId;
    }

    @Override
    public final IContextService getContextService() {
        return contextService;
    }

    @Override
    public final boolean isActive(final IEvaluationContext context) {
        return evaluate(context);
    }

    @Override
    public final String toString() {
        final StringBuffer buffer = new StringBuffer();
        //$NON-NLS-1$
        buffer.append("ContextActivation(contextId=");
        buffer.append(contextId);
        //$NON-NLS-1$
        buffer.append(",sourcePriority=");
        buffer.append(getSourcePriority());
        buffer.append(')');
        return buffer.toString();
    }
}
