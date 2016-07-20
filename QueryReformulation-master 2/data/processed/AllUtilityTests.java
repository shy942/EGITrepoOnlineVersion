/***/
package org.eclipse.ui.tests.forms.util;

import junit.framework.Test;
import junit.framework.TestSuite;

/*
* Tests forms performance (automated).
*/
public class AllUtilityTests extends TestSuite {

    /*
* Returns the entire test suite.
*/
    public static Test suite() {
        return new AllUtilityTests();
    }

    /*
* Constructs a new performance test suite.
*/
    public  AllUtilityTests() {
        addTestSuite(FormImagesTests.class);
        addTestSuite(FormFontsTests.class);
        addTestSuite(FormColorsTests.class);
        addTestSuite(FormToolkitTest.class);
        addTestSuite(ImageHyperlinkTest.class);
    }
}
