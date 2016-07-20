/***/
package org.eclipse.ui.internal.preferences;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.activities.WorkbenchActivityHelper;

/**
* @since 3.4
* @author Jan Diederich
*/
public class WorkbenchPreferenceExpressionNode extends PreferenceNode implements IPluginContribution {

    /**
* @param id The id.
* @see PreferenceNode#PreferenceNode(String)
*/
    public  WorkbenchPreferenceExpressionNode(String id) {
        super(id);
    }

    @Override
    public IPreferenceNode findSubNode(String id) {
        return getNodeExpression(super.findSubNode(id));
    }

    @Override
    public IPreferenceNode[] getSubNodes() {
        IPreferenceNode[] prefNodes = super.getSubNodes();
        int size = prefNodes.length;
        List list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            IPreferenceNode prefNode = getNodeExpression(prefNodes[i]);
            if (prefNode != null) {
                list.add(prefNode);
            }
        }
        return (IPreferenceNode[]) list.toArray(new IPreferenceNode[list.size()]);
    }

    /**
* Returns the given <code>prefNode</code>, but only if it's no
* WorkbenchPreferenceExtensionNode which fails the Expression check.
*
* @param prefNode
*            The preference node which will be checked. Can be <code>null
*            </code>.
* @return The given <code>prefNode</code>, or <code>null</code> if it
*         fails the Expressions check.
*/
    public static IPreferenceNode getNodeExpression(IPreferenceNode prefNode) {
        if (prefNode == null)
            return null;
        if (prefNode instanceof WorkbenchPreferenceExtensionNode) {
            WorkbenchPreferenceExpressionNode node = (WorkbenchPreferenceExtensionNode) prefNode;
            if (WorkbenchActivityHelper.restrictUseOf(node)) {
                return null;
            }
        }
        return prefNode;
    }

    @Override
    public String getLocalId() {
        return getId();
    }

    @Override
    public String getPluginId() {
        //$NON-NLS-1$
        return "";
    }
}
