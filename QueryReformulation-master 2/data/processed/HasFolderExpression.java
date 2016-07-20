/***/
package org.eclipse.ui.internal.wizards.datatransfer.expressions;

import java.io.File;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;

/**
* Expression to check whether a given container contains a folder under the
* provided path.
*
* @since 3.12
*/
public class HasFolderExpression extends Expression {

    /**
* The name of the XML tag to use this rule in a plugin.xml
*/
    //$NON-NLS-1$
    public static final String TAG = "hasFolder";

    String path;

    /**
* Build expression with a path.
*
* @param path
*            Path of a folder to look for under given container.
*/
    public  HasFolderExpression(String path) {
        this.path = path;
    }

    /**
* Build expression retrieving the suffix as the 'path' attribute on the
* provided {@link IConfigurationElement}.
*
* @param element
*/
    public  HasFolderExpression(IConfigurationElement element) {
        //$NON-NLS-1$
        this(element.getAttribute("path"));
    }

    @Override
    public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {
        Object root = context.getDefaultVariable();
        if (root instanceof File) {
            return EvaluationResult.valueOf(new File((File) root, this.path).isDirectory());
        } else {
            IContainer container = Adapters.adapt(root, IContainer.class);
            return EvaluationResult.valueOf(container.getFolder(new Path(this.path)).exists());
        }
    }
}
