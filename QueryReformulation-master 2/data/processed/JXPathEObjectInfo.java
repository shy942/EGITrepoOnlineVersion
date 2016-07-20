/***/
package org.eclipse.e4.emf.internal.xpath.helper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class JXPathEObjectInfo {

    private final EClass eClass;

    public  JXPathEObjectInfo(EClass eClass) {
        this.eClass = eClass;
    }

    public EStructuralFeature[] getPropertyDescriptors() {
        return eClass.getEAllStructuralFeatures().toArray(new EStructuralFeature[0]);
    }

    public EStructuralFeature getPropertyDescriptor(String propertyName) {
        return eClass.getEStructuralFeature(propertyName);
    }

    public boolean isAtomic() {
        return false;
    }
}
