/***/
package org.eclipse.e4.ui.model.application.ui.advanced;

/**
* <!-- begin-user-doc -->
* The <b>Factory</b> for the model.
* It provides a create method for each non-abstract class of the model.
* @since 1.0
* <!-- end-user-doc -->
* @generated
*/
public interface MAdvancedFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    MAdvancedFactory INSTANCE = org.eclipse.e4.ui.model.application.ui.advanced.impl.AdvancedFactoryImpl.eINSTANCE;

    /**
* Returns a new object of class '<em>Placeholder</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Placeholder</em>'.
* @generated
*/
    MPlaceholder createPlaceholder();

    /**
* Returns a new object of class '<em>Perspective</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Perspective</em>'.
* @generated
*/
    MPerspective createPerspective();

    /**
* Returns a new object of class '<em>Perspective Stack</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Perspective Stack</em>'.
* @generated
*/
    MPerspectiveStack createPerspectiveStack();

    /**
* Returns a new object of class '<em>Area</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Area</em>'.
* @generated
*/
    MArea createArea();
}
//MAdvancedFactory
