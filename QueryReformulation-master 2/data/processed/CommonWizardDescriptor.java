/***/
package org.eclipse.ui.internal.navigator.wizards;

import java.util.Iterator;
import org.eclipse.core.expressions.ElementHandler;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.internal.navigator.extensions.INavigatorContentExtPtConstants;

/**
* <p>
* <strong>EXPERIMENTAL</strong>. This class or interface has been added as
* part of a work in progress. There is a guarantee neither that this API will
* work nor that it will remain the same. Please do not use this API without
* consulting with the Platform/UI team.
* </p>
*
* @since 3.2
*/
public class CommonWizardDescriptor implements INavigatorContentExtPtConstants, IPluginContribution {

    /** The default menu group id for commonWizards without a menuGroupId attribute. */
    //$NON-NLS-1$
    public static final String DEFAULT_MENU_GROUP_ID = "all-uncategorized";

    private String id;

    private String wizardId;

    private String menuGroupId;

    private String type;

    private Expression enablement;

    private IConfigurationElement configElement;

    /**
* @param aConfigElement The configuration element from the extension point.
* @throws WorkbenchException
*             if the configuration element could not be parsed. Reasons
*             include:
*             <ul>
*             <li>A required attribute is missing.</li>
*             <li>More elements are define than is allowed.</li>
*             </ul>
*/
    public  CommonWizardDescriptor(IConfigurationElement aConfigElement) throws WorkbenchException {
        super();
        configElement = aConfigElement;
        init();
    }

    /**
* @param aConfigElement The configuration element from the extension point.
* @param anId the identifier for visibility purposes.
*
* @throws WorkbenchException
*             if the configuration element could not be parsed. Reasons
*             include:
*             <ul>
*             <li>A required attribute is missing.</li>
*             <li>More elements are define than is allowed.</li>
*             </ul>
*/
    public  CommonWizardDescriptor(IConfigurationElement aConfigElement, String anId) throws WorkbenchException {
        super();
        configElement = aConfigElement;
        id = anId;
        init();
    }

    /**
* Determine if this content extension is enabled for the given selection.
* The content extension is enabled for the selection if and only if it is
* enabled for each element in the selection.
*
* @param aStructuredSelection
*            The selection from the viewer
* @return True if and only if the extension is enabled for each element in
*         the selection.
*/
    public boolean isEnabledFor(IStructuredSelection aStructuredSelection) {
        if (enablement == null) {
            return false;
        }
        IEvaluationContext context = null;
        IEvaluationContext parentContext = NavigatorPlugin.getApplicationContext();
        Iterator elements = aStructuredSelection.iterator();
        while (elements.hasNext()) {
            context = new EvaluationContext(parentContext, elements.next());
            context.setAllowPluginActivation(true);
            if (NavigatorPlugin.safeEvaluate(enablement, context) == EvaluationResult.FALSE) {
                return false;
            }
        }
        return true;
    }

    /**
* Determine if this content extension is enabled for the given element.
*
* @param anElement
*            The element that should be used for the evaluation.
* @return True if and only if the extension is enabled for the element.
*/
    public boolean isEnabledFor(Object anElement) {
        if (enablement == null) {
            return false;
        }
        IEvaluationContext context = NavigatorPlugin.getEvalContext(anElement);
        return (NavigatorPlugin.safeEvaluate(enablement, context) == EvaluationResult.TRUE);
    }

    void init() throws WorkbenchException {
        wizardId = configElement.getAttribute(ATT_WIZARD_ID);
        type = configElement.getAttribute(ATT_TYPE);
        menuGroupId = configElement.getAttribute(ATT_MENU_GROUP_ID);
        if (menuGroupId == null) {
            menuGroupId = DEFAULT_MENU_GROUP_ID;
        }
        /*
* The id defaults to the id of the enclosing navigatorContent extension, if any
* If not enclosed, this can be null initially, so it will default to the
* value of the associatedExtensionId
*
* Code elsewhere anticipates that this attribute may be null, so there is
* no need to set it to a default non-null value. Indeed, this will cause
* incorrect behavior.
* */
        if (id == null) {
            id = configElement.getAttribute(ATT_ASSOCIATED_EXTENSION_ID);
        }
        if (wizardId == null || wizardId.length() == 0) {
            throw new WorkbenchException(//$NON-NLS-1$
            "Missing attribute: " + ATT_WIZARD_ID + //$NON-NLS-1$
            " in common wizard extension: " + configElement.getDeclaringExtension().getNamespaceIdentifier());
        }
        if (type == null || type.length() == 0) {
            throw new WorkbenchException(//$NON-NLS-1$
            "Missing attribute: " + ATT_TYPE + //$NON-NLS-1$
            " in common wizard extension: " + configElement.getDeclaringExtension().getNamespaceIdentifier());
        }
        IConfigurationElement[] children = configElement.getChildren(TAG_ENABLEMENT);
        if (children.length == 1) {
            try {
                enablement = ElementHandler.getDefault().create(ExpressionConverter.getDefault(), children[0]);
            } catch (CoreException e) {
                NavigatorPlugin.log(IStatus.ERROR, 0, e.getMessage(), e);
            }
        } else if (children.length > 1) {
            throw new WorkbenchException(//$NON-NLS-1$
            "More than one element: " + TAG_ENABLEMENT + //$NON-NLS-1$
            " in common wizard extension: " + configElement.getDeclaringExtension().getUniqueIdentifier());
        }
    }

    /**
*
* @return Returns the common wizard wizardId
*/
    public String getWizardId() {
        return wizardId;
    }

    /**
* @return Returns the type.
*/
    public String getType() {
        return type;
    }

    /**
* @return the declaring namespace.
*/
    public String getNamespace() {
        return configElement.getDeclaringExtension().getNamespaceIdentifier();
    }

    /**
*
* @return The identifier of the wizard descriptor for visiblity purposes (or null) if none.
*/
    public String getId() {
        return id;
    }

    /**
*
* @return A developer-defined logical group that this wizard menu option and
* 	others like it should be rendered in a localized manner.
*/
    public String getMenuGroupId() {
        return menuGroupId;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return "CommonWizardDescriptor[" + getId() + ", wizardId=" + getWizardId() + "]";
    }

    @Override
    public String getLocalId() {
        return getWizardId();
    }

    @Override
    public String getPluginId() {
        return (configElement != null) ? configElement.getNamespaceIdentifier() : null;
    }
}
