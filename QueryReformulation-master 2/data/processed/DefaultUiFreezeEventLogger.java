/***/
package org.eclipse.ui.internal.monitoring;

import java.lang.management.LockInfo;
import java.lang.management.ThreadInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.monitoring.IUiFreezeEventLogger;
import org.eclipse.ui.monitoring.PreferenceConstants;
import org.eclipse.ui.monitoring.StackSample;
import org.eclipse.ui.monitoring.UiFreezeEvent;

/**
* Writes {@link UiFreezeEvent}s to the Eclipse error log.
*/
public class DefaultUiFreezeEventLogger implements IUiFreezeEventLogger {

    //$NON-NLS-1$
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    private final long longEventErrorThresholdMillis;

    private static class SeverityMultiStatus extends MultiStatus {

        public  SeverityMultiStatus(int severity, String pluginId, String message, Throwable exception) {
            super(pluginId, OK, message, exception);
            setSeverity(severity);
        }
    }

    public  DefaultUiFreezeEventLogger(long longEventErrorThresholdMillis) {
        this.longEventErrorThresholdMillis = longEventErrorThresholdMillis;
    }

    /**
* Converts the given {@link UiFreezeEvent} into a {@link MultiStatus} and writes it to the log.
*
* @param event the event that caused the UI thread to freeze
*/
    @Override
    public void log(UiFreezeEvent event) {
        long lastTimestamp = event.getStartTimestamp();
        String startTime = dateFormat.format(new Date(lastTimestamp));
        String template = event.isStillRunning() ? Messages.DefaultUiFreezeEventLogger_ui_freeze_ongoing_header_2 : Messages.DefaultUiFreezeEventLogger_ui_freeze_finished_header_2;
        long duration = event.getTotalDuration();
        //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String format = duration >= 100000 ? "%.0f" : duration >= 10 ? "%.2g" : "%.1g";
        String header = NLS.bind(template, String.format(format, duration / 1000.0), startTime);
        StackSample[] stackTraceSamples = event.getStackTraceSamples();
        if (stackTraceSamples.length == 0 && (event.isStarvedAwake() || event.isStarvedAsleep())) {
            String note = (event.isStarvedAwake() || event.isStarvedAsleep()) ? Messages.DefaultUiFreezeEventLogger_starved_awake_and_asleep : event.isStarvedAwake() ? Messages.DefaultUiFreezeEventLogger_starved_awake : Messages.DefaultUiFreezeEventLogger_starved_asleep;
            header += note;
        }
        int severity = duration >= longEventErrorThresholdMillis ? IStatus.ERROR : IStatus.WARNING;
        MultiStatus loggedEvent = new SeverityMultiStatus(severity, PreferenceConstants.PLUGIN_ID, header, null);
        for (StackSample sample : stackTraceSamples) {
            double deltaInSeconds = (sample.getTimestamp() - lastTimestamp) / 1000.0;
            ThreadInfo[] threads = sample.getStackTraces();
            // The first thread is guaranteed to be the display thread.
            Exception stackTrace = new Exception(Messages.DefaultUiFreezeEventLogger_stack_trace_header);
            stackTrace.setStackTrace(threads[0].getStackTrace());
            String traceText = NLS.bind(Messages.DefaultUiFreezeEventLogger_sample_header_2, dateFormat.format(sample.getTimestamp()), //$NON-NLS-1$
            String.format("%.3f", deltaInSeconds));
            MultiStatus traceStatus = new SeverityMultiStatus(IStatus.INFO, PreferenceConstants.PLUGIN_ID, //$NON-NLS-1$
            String.format("%s\n%s", traceText, createThreadMessage(threads[0])), stackTrace);
            loggedEvent.add(traceStatus);
            for (int j = 1; j < threads.length; j++) {
                traceStatus.add(createThreadStatus(threads[j]));
            }
            lastTimestamp = sample.getTimestamp();
        }
        MonitoringPlugin.getDefault().getLog().log(loggedEvent);
    }

    private static IStatus createThreadStatus(ThreadInfo thread) {
        Exception stackTrace = new Exception(Messages.DefaultUiFreezeEventLogger_stack_trace_header);
        stackTrace.setStackTrace(thread.getStackTrace());
        StringBuilder threadText = createThreadMessage(thread);
        String lockName = thread.getLockName();
        if (lockName != null && !lockName.isEmpty()) {
            LockInfo lock = thread.getLockInfo();
            String lockOwnerName = thread.getLockOwnerName();
            if (lockOwnerName == null) {
                threadText.append(NLS.bind(Messages.DefaultUiFreezeEventLogger_waiting_for_1, getClassAndHashCode(lock)));
            } else {
                threadText.append(NLS.bind(Messages.DefaultUiFreezeEventLogger_waiting_for_with_lock_owner_3, new Object[] { getClassAndHashCode(lock), lockOwnerName, thread.getLockOwnerId() }));
            }
        }
        for (LockInfo lockInfo : thread.getLockedSynchronizers()) {
            threadText.append(NLS.bind(Messages.DefaultUiFreezeEventLogger_holding_1, getClassAndHashCode(lockInfo)));
        }
        return new Status(IStatus.INFO, PreferenceConstants.PLUGIN_ID, threadText.toString(), stackTrace);
    }

    private static StringBuilder createThreadMessage(ThreadInfo thread) {
        String threadDetails = NLS.bind(Messages.DefaultUiFreezeEventLogger_thread_details, thread.getThreadId(), thread.getThreadState());
        StringBuilder threadText = new StringBuilder(NLS.bind(Messages.DefaultUiFreezeEventLogger_thread_header_2, thread.getThreadName(), threadDetails));
        return threadText;
    }

    private static String getClassAndHashCode(LockInfo info) {
        //$NON-NLS-1$
        return String.format("%s@%08x", info.getClassName(), info.getIdentityHashCode());
    }
}
