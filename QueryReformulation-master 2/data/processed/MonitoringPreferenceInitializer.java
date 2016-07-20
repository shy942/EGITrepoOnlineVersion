/***/
package org.eclipse.ui.internal.monitoring.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.internal.monitoring.MonitoringPlugin;
import org.eclipse.ui.monitoring.PreferenceConstants;

/**
* Initializes the default values of the monitoring plug-in preferences.
*/
public class MonitoringPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = MonitoringPlugin.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.MONITORING_ENABLED, false);
        // 0.5 sec
        store.setDefault(PreferenceConstants.LONG_EVENT_WARNING_THRESHOLD_MILLIS, 500);
        // 2 sec
        store.setDefault(PreferenceConstants.LONG_EVENT_ERROR_THRESHOLD_MILLIS, 2000);
        store.setDefault(PreferenceConstants.MAX_STACK_SAMPLES, 3);
        store.setDefault(PreferenceConstants.DEADLOCK_REPORTING_THRESHOLD_MILLIS, // 5 min
        5 * 60 * 1000);
        store.setDefault(PreferenceConstants.LOG_TO_ERROR_LOG, true);
        //$NON-NLS-1$
        store.setDefault(PreferenceConstants.UI_THREAD_FILTER, "");
        store.setDefault(PreferenceConstants.NONINTERESTING_THREAD_FILTER, //$NON-NLS-1$
        "java.*" + //$NON-NLS-1$
        ",sun.*" + //$NON-NLS-1$
        ",org.eclipse.core.internal.jobs.WorkerPool.sleep" + //$NON-NLS-1$
        ",org.eclipse.core.internal.jobs.WorkerPool.startJob" + //$NON-NLS-1$
        ",org.eclipse.core.internal.jobs.Worker.run" + //$NON-NLS-1$
        ",org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.getNextEvent" + //$NON-NLS-1$
        ",org.eclipse.osgi.framework.eventmgr.EventManager$EventThread.run" + //$NON-NLS-1$
        ",org.eclipse.equinox.internal.util.impl.tpt.timer.TimerImpl.run" + //$NON-NLS-1$
        ",org.eclipse.equinox.internal.util.impl.tpt.threadpool.Executor.run");
    }
}
