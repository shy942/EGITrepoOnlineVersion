/***/
package org.eclipse.e4.ui.model.application.commands;

/**
* <!-- begin-user-doc -->
* The <b>Factory</b> for the model.
* It provides a create method for each non-abstract class of the model.
* @since 1.0
* <!-- end-user-doc -->
* @generated
*/
public interface MCommandsFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    MCommandsFactory INSTANCE = org.eclipse.e4.ui.model.application.commands.impl.CommandsFactoryImpl.eINSTANCE;

    /**
* Returns a new object of class '<em>Binding Context</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Binding Context</em>'.
* @generated
*/
    MBindingContext createBindingContext();

    /**
* Returns a new object of class '<em>Binding Table</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Binding Table</em>'.
* @generated
*/
    MBindingTable createBindingTable();

    /**
* Returns a new object of class '<em>Command</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Command</em>'.
* @generated
*/
    MCommand createCommand();

    /**
* Returns a new object of class '<em>Command Parameter</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Command Parameter</em>'.
* @generated
*/
    MCommandParameter createCommandParameter();

    /**
* Returns a new object of class '<em>Handler</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Handler</em>'.
* @generated
*/
    MHandler createHandler();

    /**
* Returns a new object of class '<em>Key Binding</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Key Binding</em>'.
* @generated
*/
    MKeyBinding createKeyBinding();

    /**
* Returns a new object of class '<em>Parameter</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Parameter</em>'.
* @generated
*/
    MParameter createParameter();

    /**
* Returns a new object of class '<em>Category</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Category</em>'.
* @generated
*/
    MCategory createCategory();
}
//MCommandsFactory
