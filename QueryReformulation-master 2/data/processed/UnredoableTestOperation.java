/***/
package org.eclipse.ui.tests.operations;

/**
* @since 3.1
*/
public class UnredoableTestOperation extends TestOperation {

     UnredoableTestOperation(String name) {
        super(name);
    }

    boolean disposed = false;

    @Override
    public boolean canRedo() {
        return false;
    }

    @Override
    public void dispose() {
        disposed = true;
    }
}
