/***/
package org.eclipse.ui.internal;

import org.eclipse.core.expressions.Expression;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.IEvaluationReference;
import org.eclipse.ui.services.IEvaluationService;

/**
* This internal class serves as a foundation for any handler that would like
* its enabled state controlled by core expressions and the IEvaluationService.
*
* @since 3.3
*/
public abstract class AbstractEvaluationHandler extends AbstractEnabledHandler {

    //$NON-NLS-1$
    private static final String PROP_ENABLED = "enabled";

    private IEvaluationService evaluationService;

    private IPropertyChangeListener enablementListener;

    private IEvaluationReference enablementRef;

    protected IEvaluationService getEvaluationService() {
        if (evaluationService == null) {
            evaluationService = PlatformUI.getWorkbench().getService(IEvaluationService.class);
        }
        return evaluationService;
    }

    protected void registerEnablement() {
        enablementRef = getEvaluationService().addEvaluationListener(getEnabledWhenExpression(), getEnablementListener(), PROP_ENABLED);
    }

    protected abstract Expression getEnabledWhenExpression();

    /**
* @return
*/
    private IPropertyChangeListener getEnablementListener() {
        if (enablementListener == null) {
            enablementListener = new IPropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent event) {
                    if (event.getProperty() == PROP_ENABLED) {
                        if (event.getNewValue() instanceof Boolean) {
                            setEnabled(((Boolean) event.getNewValue()).booleanValue());
                        } else {
                            setEnabled(false);
                        }
                    }
                }
            };
        }
        return enablementListener;
    }

    @Override
    public void dispose() {
        if (enablementRef != null) {
            evaluationService.removeEvaluationListener(enablementRef);
            enablementRef = null;
            enablementListener = null;
            evaluationService = null;
        }
        super.dispose();
    }
}
