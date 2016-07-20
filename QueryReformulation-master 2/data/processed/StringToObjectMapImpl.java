/***/
package org.eclipse.e4.ui.model.application.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
* <!-- begin-user-doc -->
* An implementation of the model object '<em><b>String To Object Map</b></em>'.
* <!-- end-user-doc -->
* <p>
* The following features are implemented:
* </p>
* <ul>
*   <li>{@link org.eclipse.e4.ui.model.application.impl.StringToObjectMapImpl#getTypedKey <em>Key</em>}</li>
*   <li>{@link org.eclipse.e4.ui.model.application.impl.StringToObjectMapImpl#getTypedValue <em>Value</em>}</li>
* </ul>
*
* @generated
*/
public class StringToObjectMapImpl extends org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container implements BasicEMap.Entry<String, Object> {

    /**
* The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @see #getTypedKey()
* @generated
* @ordered
*/
    protected static final String KEY_EDEFAULT = null;

    /**
* The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @see #getTypedKey()
* @generated
* @ordered
*/
    protected String key = KEY_EDEFAULT;

    /**
* The default value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @see #getTypedValue()
* @generated
* @ordered
*/
    protected static final Object VALUE_EDEFAULT = null;

    /**
* The cached value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @see #getTypedValue()
* @generated
* @ordered
*/
    protected Object value = VALUE_EDEFAULT;

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    protected  StringToObjectMapImpl() {
        super();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    protected EClass eStaticClass() {
        return ApplicationPackageImpl.Literals.STRING_TO_OBJECT_MAP;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public String getTypedKey() {
        return key;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public void setTypedKey(String newKey) {
        String oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackageImpl.STRING_TO_OBJECT_MAP__KEY, oldKey, key));
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public Object getTypedValue() {
        return value;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public void setTypedValue(Object newValue) {
        Object oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackageImpl.STRING_TO_OBJECT_MAP__VALUE, oldValue, value));
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__KEY:
                return getTypedKey();
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__VALUE:
                return getTypedValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__KEY:
                setTypedKey((String) newValue);
                return;
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__VALUE:
                setTypedValue(newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public void eUnset(int featureID) {
        switch(featureID) {
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__KEY:
                setTypedKey(KEY_EDEFAULT);
                return;
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__VALUE:
                setTypedValue(VALUE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public boolean eIsSet(int featureID) {
        switch(featureID) {
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__KEY:
                return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
            case ApplicationPackageImpl.STRING_TO_OBJECT_MAP__VALUE:
                return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
        }
        return super.eIsSet(featureID);
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();
        StringBuffer result = new StringBuffer(super.toString());
        //$NON-NLS-1$
        result.append(" (key: ");
        result.append(key);
        //$NON-NLS-1$
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    protected int hash = -1;

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public int getHash() {
        if (hash == -1) {
            Object theKey = getKey();
            hash = (theKey == null ? 0 : theKey.hashCode());
        }
        return hash;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public void setHash(int hash) {
        this.hash = hash;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public String getKey() {
        return getTypedKey();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public void setKey(String key) {
        setTypedKey(key);
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public Object getValue() {
        return getTypedValue();
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    public Object setValue(Object value) {
        Object oldValue = getValue();
        setTypedValue(value);
        return oldValue;
    }

    /**
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @generated
*/
    @SuppressWarnings("unchecked")
    public EMap<String, Object> getEMap() {
        EObject container = eContainer();
        return container == null ? null : (EMap<String, Object>) container.eGet(eContainmentFeature());
    }
}
//StringToObjectMapImpl
