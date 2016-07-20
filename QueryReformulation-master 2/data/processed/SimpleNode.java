/***/
package org.eclipse.jface.examples.databinding.contentprovider.test;

import org.eclipse.core.databinding.observable.set.IObservableSet;

/**
* This object will be given randomly-generated children
*
* @since 1.0
*/
public class SimpleNode {

    private String nodeName;

    private IObservableSet<?> children;

    public  SimpleNode(String nodeName, IObservableSet<?> children) {
        super();
        this.nodeName = nodeName;
        this.children = children;
    }

    public String getNodeName() {
        return nodeName;
    }

    public IObservableSet<?> getChildren() {
        return children;
    }
}
