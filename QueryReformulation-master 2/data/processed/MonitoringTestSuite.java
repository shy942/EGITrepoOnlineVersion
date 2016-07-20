/***/
package org.eclipse.ui.internal.monitoring;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* Test suite for {@code org.eclipse.ui.monitoring} plug-in.
* The tests in {@link EventLoopMonitorThreadManualTests} are not included in this
* suite due to their flakiness.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ EventLoopMonitorThreadTests.class, FilterHandlerTests.class, DefaultLoggerTests.class })
public class MonitoringTestSuite {
}
