/***/
package org.eclipse.jface.bindings.keys;

/**
* <p>
* A factory class for <code>ILookup</code> instances. This factory can be
* used to retrieve instances of look-ups defined by this package. It also
* allows you to define your own look-up for use in the classes.
* </p>
*
* @since 3.1
*/
public final class KeyLookupFactory {

    /**
* The SWT key look-up defined by this package.
*/
    private static final SWTKeyLookup SWT_KEY_LOOKUP = new SWTKeyLookup();

    /**
* The instance that should be used by <code>KeyStroke</code> in
* converting string representations to instances.
*/
    private static IKeyLookup defaultLookup = SWT_KEY_LOOKUP;

    /**
* Provides an instance of <code>SWTKeyLookup</code>.
*
* @return The SWT look-up table for key stroke format information; never
*         <code>null</code>.
*/
    public static final IKeyLookup getSWTKeyLookup() {
        return SWT_KEY_LOOKUP;
    }

    /**
* An accessor for the current default look-up.
*
* @return The default look-up; never <code>null</code>.
*/
    public static final IKeyLookup getDefault() {
        return defaultLookup;
    }

    /**
* Sets the default look-up.
*
* @param defaultLookup
*            the default look-up. Must not be <code>null</code>.
*/
    public static final void setDefault(final IKeyLookup defaultLookup) {
        if (defaultLookup == null) {
            //$NON-NLS-1$
            throw new NullPointerException("The look-up must not be null");
        }
        KeyLookupFactory.defaultLookup = defaultLookup;
    }

    /**
* This class should not be instantiated.
*/
    private  KeyLookupFactory() {
    // Not to be constructred.
    }
}
