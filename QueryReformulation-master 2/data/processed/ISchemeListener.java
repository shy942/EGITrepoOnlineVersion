/***/
package org.eclipse.jface.bindings;

/**
* <p>
* An instance of <code>ISchemeListener</code> can be used by clients to
* receive notification of changes to one or more instances of
* <code>IScheme</code>.
* </p>
* <p>
* This interface may be implemented by clients.
* </p>
*
* @since 3.1
* @see Scheme#addSchemeListener(ISchemeListener)
* @see Scheme#removeSchemeListener(ISchemeListener)
* @see SchemeEvent
*/
public interface ISchemeListener {

    /**
* Notifies that one or more attributes of an instance of
* <code>IScheme</code> have changed. Specific details are described in
* the <code>SchemeEvent</code>.
*
* @param schemeEvent
*            the scheme event. Guaranteed not to be <code>null</code>.
*/
    void schemeChanged(SchemeEvent schemeEvent);
}
