/***/
package org.eclipse.ui.tests.decorators;

/**
* The TableElement is the content for tables in the decorator
* testing.
*/
public class TableElement extends TestElement {

    int index;

    public  TableElement(int newIndex) {
        super();
        index = newIndex;
        name = "Table Item " + String.valueOf(index);
    }
}
