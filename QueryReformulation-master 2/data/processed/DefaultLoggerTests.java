/***/
package org.eclipse.ui.internal.monitoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.monitoring.PreferenceConstants;
import org.eclipse.ui.monitoring.StackSample;
import org.eclipse.ui.monitoring.UiFreezeEvent;
import org.junit.Before;
import org.junit.Test;

/**
* JUnit test for the {@link DefaultUiFreezeEventLogger}.
*/
public class DefaultLoggerTests {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    private static final String RUNTIME_ID = "org.eclipse.core.runtime";

    private static final long TIME = 120000000;

    private static final long DURATION = 500;

    private DefaultUiFreezeEventLogger logger;

    private ThreadInfo thread;

    private IStatus loggedStatus;

    @Before
    public void setUp() {
        logger = new DefaultUiFreezeEventLogger(DURATION * 10);
        createLogListener();
    }

    private void createLogListener() {
        Platform.addLogListener(new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (plugin.equals(RUNTIME_ID)) {
                    loggedStatus = status;
                }
            }
        });
    }

    private UiFreezeEvent createFreezeEvent() {
        ThreadMXBean jvmThreadManager = ManagementFactory.getThreadMXBean();
        thread = jvmThreadManager.getThreadInfo(Thread.currentThread().getId(), Integer.MAX_VALUE);
        StackSample[] samples = { new StackSample(TIME, new ThreadInfo[] { thread }) };
        UiFreezeEvent event = new UiFreezeEvent(TIME, DURATION, samples, false, false, false);
        return event;
    }

    @Test
    public void testLogEvent() {
        UiFreezeEvent event = createFreezeEvent();
        String expectedTime = dateFormat.format(new Date(TIME));
        String expectedHeader = String.format("UI freeze of %.2gs at %s", DURATION / 1000.0, expectedTime);
        String expectedEventMessage = String.format("Sample at %s (+%.3fs)", expectedTime, 0.000);
        logger.log(event);
        assertEquals(PreferenceConstants.PLUGIN_ID, loggedStatus.getPlugin());
        assertTrue("Logged status was not a MultiStatus", loggedStatus.isMultiStatus());
        assertEquals(expectedHeader, loggedStatus.getMessage());
        assertEquals("One nested IStatus did not get logged correctly.", 1, loggedStatus.getChildren().length);
        IStatus freezeEvent = loggedStatus.getChildren()[0];
        assertTrue(freezeEvent.getMessage().contains(expectedEventMessage));
        StackTraceElement[] threadStackTrace = thread.getStackTrace();
        StackTraceElement[] loggedStackTrace = freezeEvent.getException().getStackTrace();
        assertEquals(threadStackTrace.length, loggedStackTrace.length);
        for (int i = 0; i < threadStackTrace.length; i++) {
            assertEquals(threadStackTrace[i], loggedStackTrace[i]);
        }
    }
}
