/***/
package org.eclipse.ui.tests.operations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* Tests for the platform operations support.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ OperationsAPITest.class, WorkbenchOperationHistoryTests.class, MultiThreadedOperationsTests.class, WorkbenchOperationStressTests.class, WorkspaceOperationsTests.class })
public class OperationsTestSuite {
}
