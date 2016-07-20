/***/
package org.eclipse.e4.ui.css.core.dom;

import org.w3c.dom.NodeList;

/**
* Elements that implement this interface contract that only a subset of their
* children should be styled. The node will ensure that other children are
* styled appropriately when required.
*/
public interface ChildVisibilityAwareElement {

    NodeList getVisibleChildNodes();
}
