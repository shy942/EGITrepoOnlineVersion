/***/
package org.eclipse.e4.ui.model.fragment;

/**
* <!-- begin-user-doc -->
* The <b>Factory</b> for the model.
* It provides a create method for each non-abstract class of the model.
* @since 1.0
* <!-- end-user-doc -->
* @generated
*/
public interface MFragmentFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    MFragmentFactory INSTANCE = org.eclipse.e4.ui.model.fragment.impl.FragmentFactoryImpl.eINSTANCE;

    /**
* Returns a new object of class '<em>Model Fragments</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>Model Fragments</em>'.
* @generated
*/
    MModelFragments createModelFragments();

    /**
* Returns a new object of class '<em>String Model Fragment</em>'.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @return a new object of class '<em>String Model Fragment</em>'.
* @generated
*/
    MStringModelFragment createStringModelFragment();
}
//MFragmentFactory
