/***/
package org.eclipse.e4.ui.tests.application;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.application.impl.ApplicationPackageImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

public class ModelElementTest {

    @Test
    public void testForMApplicationInterface() {
        List<EClass> failedClasses = new ArrayList<EClass>();
        checkPackageForMApplicationInterface(failedClasses, ApplicationPackageImpl.eINSTANCE);
        if (failedClasses.size() > 0) {
            StringBuilder b = new StringBuilder("The following concrete classes don't implement 'MApplicationElement':\n");
            for (EClass c : failedClasses) {
                b.append("* " + c.getName() + "\n");
            }
            System.err.println(b.toString());
            fail(b.toString());
        }
    }

    private void checkPackageForMApplicationInterface(List<EClass> failedClasses, EPackage ePackage) {
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            if (classifier instanceof EClass) {
                EClass c = (EClass) classifier;
                if (!c.isInterface() && c != ApplicationPackageImpl.Literals.STRING_TO_STRING_MAP && c != ApplicationPackageImpl.Literals.STRING_TO_OBJECT_MAP) {
                    if (!MApplicationElement.class.isAssignableFrom(c.getInstanceClass())) {
                        failedClasses.add(c);
                    }
                }
            }
        }
        for (EPackage subPackage : ePackage.getESubpackages()) {
            checkPackageForMApplicationInterface(failedClasses, subPackage);
        }
    }

    @Test
    public void testForOptimalBaseClass() {
        List<EClass> failedClasses = new ArrayList<EClass>();
        checkPackageForOptimalBaseClass(failedClasses, ApplicationPackageImpl.eINSTANCE);
        if (failedClasses.size() > 0) {
            StringBuilder b = new StringBuilder("The following concrete classes have a mixin as base 'MApplicationElement':\n");
            for (EClass c : failedClasses) {
                b.append("* " + c.getName() + "\n");
            }
            System.err.println(b.toString());
            fail(b.toString());
        }
    }

    private void checkPackageForOptimalBaseClass(List<EClass> failedClasses, EPackage ePackage) {
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            if (classifier instanceof EClass) {
                EClass c = (EClass) classifier;
                if (c != ApplicationPackageImpl.Literals.STRING_TO_STRING_MAP && c != ApplicationPackageImpl.Literals.STRING_TO_OBJECT_MAP && c != ApplicationPackageImpl.Literals.APPLICATION_ELEMENT && !c.isInterface()) {
                    if (c.getESuperTypes().size() == 0 || c.getESuperTypes().get(0).isInterface()) {
                        failedClasses.add(c);
                    }
                }
            }
        }
        for (EPackage subPackage : ePackage.getESubpackages()) {
            checkPackageForOptimalBaseClass(failedClasses, subPackage);
        }
    }
}
