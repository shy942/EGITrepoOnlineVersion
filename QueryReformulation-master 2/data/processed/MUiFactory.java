/***/
package org.eclipse.e4.ui.model.application.ui;

/**
* <!-- begin-user-doc -->
* The <b>Factory</b> for the model.
* It provides a create method for each non-abstract class of the model.
* @since 1.0
* <!-- end-user-doc -->
* @generated
*/
public interface MUiFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    MUiFactory INSTANCE = org.eclipse.e4.ui.model.application.ui.impl.UiFactoryImpl.eINSTANCE;

    /**
* Returns a new object of class '<em>Core Expression</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Core Expression</em>'.
* @generated
*/
    MCoreExpression createCoreExpression();
}
//MUiFactory
