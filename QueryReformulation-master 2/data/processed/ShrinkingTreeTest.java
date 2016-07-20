/***/
package org.eclipse.jface.tests.performance;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.test.performance.Dimension;
import org.eclipse.ui.tests.performance.TestRunnable;

/**
* ShrinkingTreeTest is a test to see how long it takes to refresh a tree that goes
* from a large item count to a smaller one.
* @since 3.3
*
*/
public class ShrinkingTreeTest extends TreeTest {

    /**
* Create a new instance of the receiver.
*
* @param testName
*/
    public  ShrinkingTreeTest(String testName) {
        super(testName);
    }

    public  ShrinkingTreeTest(String testName, int tagging) {
        super(testName, tagging);
    }

    public void testTreeViewerRefresh() throws CoreException {
        tagIfNecessary("JFace - Refresh from 1000 items to 100 items", Dimension.ELAPSED_PROCESS);
        openBrowser();
        //		int smallCount = 1;
        //		for (int i = 0; i < 3; i++) {
        //
        //			int largeCount = smallCount * 10;
        //			for (int j = 0; j < 2; j++) {
        //				System.out.println("Small " + String.valueOf(smallCount)
        //						+ "Large " + String.valueOf(largeCount));
        testRefresh(100, 1000);
    //				largeCount *= 10;
    //			}
    //			smallCount *= 10;
    //		}
    }

    /**
* Run the test for one of the fast insertions.
*
* @param count
* @throws CoreException
*/
    private void testRefresh(final int smallSize, final int largeSize) throws CoreException {
        exercise(new TestRunnable() {

            @Override
            public void run() {
                TestTreeElement input = new TestTreeElement(0, null);
                viewer.setInput(input);
                input.createChildren(largeSize);
                processEvents();
                viewer.refresh();
                viewer.expandAll();
                input.createChildren(smallSize);
                startMeasuring();
                viewer.refresh();
                stopMeasuring();
            }
        }, MIN_ITERATIONS, ITERATIONS, JFacePerformanceSuite.MAX_TIME);
        commitMeasurements();
        assertPerformance();
    }
}
