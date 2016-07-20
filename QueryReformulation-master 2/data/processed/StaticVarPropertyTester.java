/***/
package org.eclipse.ui.tests.services;

import org.eclipse.core.expressions.PropertyTester;

/**
* @since 3.4
*
*/
public class StaticVarPropertyTester extends PropertyTester {

    public static boolean result = false;

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        return result;
    }
}
