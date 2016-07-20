/***/
package org.eclipse.ui.tests.rcp.util;

import org.eclipse.test.performance.PerformanceMeter;
import org.eclipse.ui.tests.harness.util.RCPTestWorkbenchAdvisor;

/**
* This implementation of the workbench advisor tracks performance for the intervals between
* lifecycle events.
*
* @since 3.1
*/
public class OpenWorkbenchIntervalMonitor extends RCPTestWorkbenchAdvisor {

    private PerformanceMeter startupMeter;

    private PerformanceMeter shutdownMeter;

    public  OpenWorkbenchIntervalMonitor(PerformanceMeter startupMeter, PerformanceMeter shutdownMeter) {
        super(2);
        this.startupMeter = startupMeter;
        this.shutdownMeter = shutdownMeter;
    }

    @Override
    public void postStartup() {
        startupMeter.stop();
        // no reason to track performance between when startup completes and shutdown starts
        // since that is just testing overhead
        super.postStartup();
    }

    @Override
    public boolean preShutdown() {
        shutdownMeter.start();
        return super.preShutdown();
    }
}
