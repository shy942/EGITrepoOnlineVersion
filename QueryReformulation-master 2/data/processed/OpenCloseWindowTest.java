/***/
package org.eclipse.ui.tests.performance;

import org.eclipse.test.performance.Dimension;
import org.eclipse.ui.IWorkbenchWindow;

/**
* @since 3.1
*/
public class OpenCloseWindowTest extends BasicPerformanceTest {

    private String id;

    /**
* @param tagging
* @param testName
*/
    public  OpenCloseWindowTest(String id, int tagging) {
        super("testOpenCloseWindows:" + id, tagging);
        this.id = id;
    }

    @Override
    protected void runTest() throws Throwable {
        tagIfNecessary("UI - Open/Close Window", Dimension.ELAPSED_PROCESS);
        exercise(new TestRunnable() {

            @Override
            public void run() throws Exception {
                processEvents();
                EditorTestHelper.calmDown(500, 30000, 500);
                startMeasuring();
                IWorkbenchWindow window = openTestWindow(id);
                processEvents();
                window.close();
                processEvents();
                stopMeasuring();
            }
        });
        commitMeasurements();
        assertPerformance();
    }
}
