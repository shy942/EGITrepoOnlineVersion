/***/
package org.eclipse.jface.tests.performance;

/**
* The TestElement is the element used for testing
* viewers.
*
*/
public class TestElement {

    String name;

    /**
* Create a new instance of the receiver.
*
*/
    public  TestElement() {
        super();
    }

    /**
* Create a new instance of the receiver.
*
* @param index
*/
    public  TestElement(int index) {
        name = TestTreeElement.generateFirstEntry() + String.valueOf(index);
    }

    public String getText() {
        return name;
    }
}
