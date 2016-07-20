/***/
package org.eclipse.e4.ui.workbench.modeling;

/**
* @noreference This class is not intended to be referenced by clients.
* @since 1.0
*/
public class ModelHandlerBase {

    protected  ModelHandlerBase() {
    }

    public Object getProperty(Object element, String id) {
        return null;
    }

    public void setProperty(Object element, String id, Object value) {
    }

    public Object[] getChildren(Object element, String id) {
        return new Object[0];
    }

    public String[] getPropIds(Object element) {
        return new String[0];
    }
}
