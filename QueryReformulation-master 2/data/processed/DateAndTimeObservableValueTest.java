/***/
package org.eclipse.core.tests.databinding.observable.value;

import java.util.Calendar;
import java.util.Date;
import org.eclipse.core.databinding.observable.value.DateAndTimeObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

/**
* @since 1.0
*
*/
public class DateAndTimeObservableValueTest extends AbstractDefaultRealmTestCase {

    private IObservableValue date;

    private IObservableValue time;

    private IObservableValue dateAndTime;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        date = WritableValue.withValueType(Date.class);
        time = WritableValue.withValueType(Date.class);
        dateAndTime = new DateAndTimeObservableValue(date, time);
    }

    private static Date date(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        // Calendar.JANUARY == 0
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    private static Date time(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    private static Date timestamp(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, day, hour, minute, second);
        return calendar.getTime();
    }

    public void testGetValue_NullDateNullResult() {
        date.setValue(null);
        time.setValue(time(12, 27, 17));
        assertNull(dateAndTime.getValue());
    }

    public void testGetValue_NullTimeClearsTime() {
        date.setValue(date(2009, 3, 3));
        time.setValue(null);
        assertEquals(timestamp(2009, 3, 3, 0, 0, 0), dateAndTime.getValue());
    }

    public void testGetValue() {
        date.setValue(timestamp(2009, 3, 3, 12, 30, 33));
        // apocalypse - 1s
        time.setValue(timestamp(1999, 12, 31, 23, 59, 59));
        assertEquals(timestamp(2009, 3, 3, 23, 59, 59), dateAndTime.getValue());
    }

    public void testSetValue() {
        date.setValue(date(2009, 3, 3));
        time.setValue(time(12, 32, 55));
        dateAndTime.setValue(timestamp(2010, 1, 1, 2, 3, 5));
        assertEquals(date(2010, 1, 1), date.getValue());
        assertEquals(time(2, 3, 5), time.getValue());
    }

    public void testSetValue_NullNullsDateClearsTime() {
        date.setValue(date(2009, 3, 3));
        time.setValue(time(12, 25, 34));
        dateAndTime.setValue(null);
        assertEquals(null, date.getValue());
        assertEquals(time(0, 0, 0), time.getValue());
    }

    public void testSetValue_PreserveTimeOfDateAndDateOfTime() {
        date.setValue(timestamp(2009, 3, 3, 12, 32, 55));
        time.setValue(timestamp(2009, 3, 3, 12, 32, 55));
        dateAndTime.setValue(timestamp(2010, 1, 1, 2, 3, 5));
        assertEquals(timestamp(2010, 1, 1, 12, 32, 55), date.getValue());
        assertEquals(timestamp(2009, 3, 3, 2, 3, 5), time.getValue());
    }
}
