/***/
package org.eclipse.ui.internal.wizards.datatransfer.expressions;

import java.io.File;
import org.eclipse.core.expressions.ElementHandler;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionConverter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IConfigurationElement;

/**
* Adds support for a set of additional expressions to use in plugin.xml to
* decide enablement depending of file/resource structure. Expressions provided
* by this {@link ElementHandler} only applies on {@link IContainer} and
* {@link File}.
*
* @since 3.12
*/
public class FileExpressionHandler extends ElementHandler {

    @Override
    public Expression create(ExpressionConverter converter, IConfigurationElement element) {
        String name = element.getName();
        if (HasFileExpression.TAG.equals(name)) {
            return new HasFileExpression(element);
        } else if (HasFileRecursivelyExpression.TAG.equals(name)) {
            return new HasFileRecursivelyExpression(element);
        } else if (HasFileWithSuffixRecursivelyExpression.TAG.equals(name)) {
            return new HasFileWithSuffixRecursivelyExpression(element);
        } else if (HasFolderExpression.TAG.equals(name)) {
            return new HasFolderExpression(element);
        }
        return null;
    }
}
