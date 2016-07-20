/***/
package org.eclipse.ui.tests.rcp.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.test.performance.PerformanceMeter;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.tests.harness.util.RCPTestWorkbenchAdvisor;

/**
* This implementation of the workbench advisor tracks performance for the intervals between
* lifecycle events.
*
* @since 3.1
*/
public class RestoreWorkbenchIntervalMonitor extends RCPTestWorkbenchAdvisor {

    private PerformanceMeter startupMeter;

    private PerformanceMeter shutdownMeter;

    private boolean createRestorableWorkbench = false;

    private IWorkbenchConfigurer workbenchConfigurer;

    /**
* The default behavior is to create a workbench that can be restored later.  This
* constructor starts that behavior by setting a flag that will be checked in the
* appropriate methods.
*/
    public  RestoreWorkbenchIntervalMonitor(PerformanceMeter startupMeter, PerformanceMeter shutdownMeter, boolean createRestorableWorkbench) {
        super(2);
        this.startupMeter = startupMeter;
        this.shutdownMeter = shutdownMeter;
        this.createRestorableWorkbench = createRestorableWorkbench;
    }

    @Override
    public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        workbenchConfigurer = configurer;
        workbenchConfigurer.setSaveAndRestore(true);
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
        boolean ret = super.preShutdown();
        shutdownMeter.start();
        return ret;
    }

    @Override
    public void eventLoopIdle(Display d) {
        if (createRestorableWorkbench) {
            workbenchConfigurer.getWorkbench().restart();
        } else {
            super.eventLoopIdle(d);
        }
    }
}
