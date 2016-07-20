/***/
package org.eclipse.ui.tests.progress;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* Test suite for the Progress View and related API
*
* @since 3.6
* @author Prakash G.R. (grprakash@in.ibm.com)
*
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ ProgressContantsTest.class, ProgressViewTests.class, JobInfoTest.class, JobInfoTestOrdering.class, ProgressAnimationItemTest.class })
public class ProgressTestSuite {
}
