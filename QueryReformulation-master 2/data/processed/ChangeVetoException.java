/***/
package org.eclipse.core.databinding.observable.value;

/**
* @since 1.0
*
*/
public class ChangeVetoException extends RuntimeException {

    /**
* @param string
*/
    public  ChangeVetoException(String string) {
        super(string);
    }

    private static final long serialVersionUID = 1L;
}
