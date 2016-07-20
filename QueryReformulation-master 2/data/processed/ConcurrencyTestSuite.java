/***/
package org.eclipse.ui.tests.concurrency;

import org.junit.runner.RunWith;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
* The suite of tests related to concurrency and deadlock.
*
* @since 3.1
*/
@RunWith(org.junit.runners.AllTests.class)
public final class ConcurrencyTestSuite extends TestSuite {

    /**
* Returns the suite. This is required to use the JUnit Launcher.
*/
    public static final Test suite() {
        return new ConcurrencyTestSuite();
    }

    /**
* Constructs a new instance of <code>ConcurrencyTestSuite</code> with all of
* the relevant test cases.
*/
    public  ConcurrencyTestSuite() {
        addTestSuite(ModalContextCrashTest.class);
        addTestSuite(NestedSyncExecDeadlockTest.class);
        addTestSuite(SyncExecWhileUIThreadWaitsForRuleTest.class);
        addTestSuite(SyncExecWhileUIThreadWaitsForLock.class);
        addTestSuite(TestBug105491.class);
        addTestSuite(TestBug108162.class);
        addTestSuite(TestBug138695.class);
        addTestSuite(TestBug98621.class);
        addTestSuite(TransferRuleTest.class);
        addTestSuite(Bug_262032.class);
        addTestSuite(TestBug269121.class);
    }
}
