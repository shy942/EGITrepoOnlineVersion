/***/
package org.eclipse.ui.tests.activities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* The ActivitiesTestSuite class runs the activities' test suites.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ ImagesTest.class, UtilTest.class, StaticTest.class, DynamicTest.class, PersistanceTest.class, ActivityPreferenceTest.class, MenusTest.class, PatternUtilTest.class })
public class ActivitiesTestSuite {
}
