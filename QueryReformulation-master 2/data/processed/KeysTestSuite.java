/***/
package org.eclipse.ui.tests.keys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* Tests for all areas of the key support for the platform.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ BindingInteractionsTest.class, BindingManagerTest.class, BindingPersistenceTest.class, //Bug36420Test.class,
Bug36537Test.class, //		Bug40023Test.class,
Bug42024Test.class, Bug42035Test.class, //		Bug42627Test.class,
Bug43168Test.class, Bug43321Test.class, Bug43538Test.class, Bug43597Test.class, Bug43610Test.class, Bug43800Test.class, KeysCsvTest.class, //Bug53489Test.class,
Bug189167Test.class, KeysPreferenceModelTest.class })
public class KeysTestSuite {
}
