/***/
package org.eclipse.e4.ui.model.application;

/**
* <!-- begin-user-doc -->
* The <b>Factory</b> for the model.
* It provides a create method for each non-abstract class of the model.
* @since 1.0
* <!-- end-user-doc -->
* @generated
*/
public interface MApplicationFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    MApplicationFactory INSTANCE = org.eclipse.e4.ui.model.application.impl.ApplicationFactoryImpl.eINSTANCE;

    /**
* Returns a new object of class '<em>Application</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Application</em>'.
* @generated
*/
    MApplication createApplication();

    /**
* Returns a new object of class '<em>Addon</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Addon</em>'.
* @generated
*/
    MAddon createAddon();
}
//MApplicationFactory
