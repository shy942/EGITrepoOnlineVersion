/***/
package org.eclipse.e4.ui.model.fragment.impl;

import org.eclipse.e4.ui.model.fragment.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
* <!-- begin-user-doc -->
* An implementation of the model <b>Factory</b>.
* <!-- end-user-doc -->
* @generated
*/
public class FragmentFactoryImpl extends EFactoryImpl implements MFragmentFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public static final FragmentFactoryImpl eINSTANCE = init();

    /**
* Creates the default factory implementation.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public static FragmentFactoryImpl init() {
        try {
            FragmentFactoryImpl theFragmentFactory = (FragmentFactoryImpl) EPackage.Registry.INSTANCE.getEFactory(FragmentPackageImpl.eNS_URI);
            if (theFragmentFactory != null) {
                return theFragmentFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FragmentFactoryImpl();
    }

    /**
* Creates an instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public  FragmentFactoryImpl() {
        super();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public EObject create(EClass eClass) {
        switch(eClass.getClassifierID()) {
            case FragmentPackageImpl.MODEL_FRAGMENTS:
                return (EObject) createModelFragments();
            case FragmentPackageImpl.STRING_MODEL_FRAGMENT:
                return (EObject) createStringModelFragment();
            default:
                //$NON-NLS-1$ //$NON-NLS-2$
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public MModelFragments createModelFragments() {
        ModelFragmentsImpl modelFragments = new ModelFragmentsImpl();
        return modelFragments;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public MStringModelFragment createStringModelFragment() {
        StringModelFragmentImpl stringModelFragment = new StringModelFragmentImpl();
        return stringModelFragment;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public FragmentPackageImpl getFragmentPackage() {
        return (FragmentPackageImpl) getEPackage();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @deprecated
* @generated
*/
    @Deprecated
    public static FragmentPackageImpl getPackage() {
        return FragmentPackageImpl.eINSTANCE;
    }
}
//FragmentFactoryImpl