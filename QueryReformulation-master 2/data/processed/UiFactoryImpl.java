/***/
package org.eclipse.e4.ui.model.application.ui.impl;

import org.eclipse.e4.ui.model.application.ui.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class UiFactoryImpl extends EFactoryImpl implements MUiFactory {

    /**
* The singleton instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public static final UiFactoryImpl eINSTANCE = init();

    /**
* Creates the default factory implementation.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public static UiFactoryImpl init() {
        try {
            UiFactoryImpl theUiFactory = (UiFactoryImpl) EPackage.Registry.INSTANCE.getEFactory(UiPackageImpl.eNS_URI);
            if (theUiFactory != null) {
                return theUiFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UiFactoryImpl();
    }

    /**
* Creates an instance of the factory.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public  UiFactoryImpl() {
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
            case UiPackageImpl.CORE_EXPRESSION:
                return (EObject) createCoreExpression();
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
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch(eDataType.getClassifierID()) {
            case UiPackageImpl.SIDE_VALUE:
                return createSideValueFromString(eDataType, initialValue);
            default:
                //$NON-NLS-1$ //$NON-NLS-2$
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch(eDataType.getClassifierID()) {
            case UiPackageImpl.SIDE_VALUE:
                return convertSideValueToString(eDataType, instanceValue);
            default:
                //$NON-NLS-1$ //$NON-NLS-2$
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public MCoreExpression createCoreExpression() {
        CoreExpressionImpl coreExpression = new CoreExpressionImpl();
        return coreExpression;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public SideValue createSideValueFromString(EDataType eDataType, String initialValue) {
        SideValue result = SideValue.get(initialValue);
        //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public String convertSideValueToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public UiPackageImpl getUiPackage() {
        return (UiPackageImpl) getEPackage();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @deprecated
* @generated
*/
    @Deprecated
    public static UiPackageImpl getPackage() {
        return UiPackageImpl.eINSTANCE;
    }
}
//UiFactoryImpl
