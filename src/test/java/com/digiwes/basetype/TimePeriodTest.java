package com.digiwes.basetype;

import com.digiwes.common.utils.TimeUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Nisx on 2015/7/17.
 */
public class TimePeriodTest {

    @Test
    public void testCreateTimePeriod() throws Exception {
        TimePeriod timePeriod = null;
        //Case£ºParameter null test
        try {
            timePeriod = new TimePeriod(null, null);
            fail("parameter null test failed");
        } catch (Exception e) {

        }
        try {
            timePeriod = new TimePeriod(new Date(), null);
            fail("parameter null test failed");
        } catch (Exception e) {

        }
        try {
            timePeriod = new TimePeriod(null, new Date());
            fail("parameter null test failed");
        } catch (Exception e) {

        }

        //Case£ºstartDateTime > endDateTime
        try {
            Date startDate = TimeUtils.parseDate("20150717", "yyyyMMdd");
            Date endDate = TimeUtils.parseDate("20150716", "yyyyMMdd");
            timePeriod = new TimePeriod(startDate, endDate);
            fail("startDateTime > endDateTime is not allowed");
        } catch (Exception e) {

        }
        //Case£ºstartDateTime = endDateTime
        try {
            Date startDate = TimeUtils.parseDate("20150717", "yyyyMMdd");
            Date endDate = TimeUtils.parseDate("20150717", "yyyyMMdd");
            timePeriod = new TimePeriod(startDate, endDate);
        } catch (Exception e) {
            fail("startDateTime = endDateTime is allowed£¬but test error!");
        }
        //Case£ºstartDateTime < endDateTime
        try {
            Date startDate = TimeUtils.parseDate("20150717", "yyyyMMdd");
            Date endDate = TimeUtils.parseDate("20150719", "yyyyMMdd");
            timePeriod = new TimePeriod(startDate, endDate);
            assertEquals("startDateTime is incorrect",startDate, timePeriod.getStartDateTime());
            assertEquals("endDateTime is incorrect",endDate, timePeriod.getEndDateTime());
        } catch (Exception e) {
            fail("startDateTime < endDateTime is allowed£¬but test error!");
        }
    }
    @Test
    public void testIsInTimePeriod() throws Exception {

    }

    @Test
    public void testIsOverlap() throws Exception {

    }

    @Test
    public void testIsInTimePeriod1() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }
}