package com.digiwes.basetype;

import com.digiwes.common.utils.TimeUtils;
import org.junit.Test;

import java.sql.Time;
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
        TimePeriod timePeriod = createTimePeriod("20150717","20150816");
       //Case: parameter is null
        try {
            timePeriod.isInTimePeriod((Date) null);
            fail("parameter can not be null");
        } catch (Exception e) {

        }
                
        assertFalse("lower test failed",timePeriod.isInTimePeriod(TimeUtils.parseDate("20150716", "yyyyMMdd")) );
        assertFalse("higher test failed",timePeriod.isInTimePeriod(TimeUtils.parseDate("20150920", "yyyyMMdd")) );

        assertTrue("bottom border test failed", timePeriod.isInTimePeriod(TimeUtils.parseDate("20150717", "yyyyMMdd")));
        assertTrue("top border test failed", timePeriod.isInTimePeriod(TimeUtils.parseDate("20150816", "yyyyMMdd")));
        assertTrue("middle value test failed", timePeriod.isInTimePeriod(TimeUtils.parseDate("20150801", "yyyyMMdd")));
    }

    private TimePeriod createTimePeriod(String startDate, String endDate) throws Exception {
        return new TimePeriod(TimeUtils.parseDate(startDate, "yyyyMMdd"), TimeUtils.parseDate(endDate, "yyyyMMdd"));
    }

    @Test
    public void testIsOverlap() throws Exception {
        TimePeriod timePeriod = createTimePeriod("20150717","20150816");
        //case1:parameter is null
        try {
            timePeriod.isOverlap(null);
            fail("parameter is null");
        } catch (Exception e){

        }
        //Case2: timePeriod =timePeriod1
        TimePeriod timePeriod1 =createTimePeriod("20150717","20150816");
        assertEquals("timePeriod equals timePeriod1",true,timePeriod.isOverlap(timePeriod1));
        // case3:
        timePeriod1 =createTimePeriod("20150715","20150716");
        assertEquals("timePeriod1.endDateTime<timePeriod.startDateTime",false,timePeriod.isOverlap(timePeriod1));

        //case4:
        timePeriod1 =createTimePeriod("20150715","20150718");
        assertEquals("endDateTime1>startDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case5:
        timePeriod1 =createTimePeriod("20150715","20150816");
        assertEquals("startDateTime1<startDate &&endDateTime ==endDateTime1  ",true,timePeriod.isOverlap(timePeriod1));
        //case6:
        timePeriod1 =createTimePeriod("20150715","20150817");
        assertEquals("startDateTime1<startDate &&endDateTime1>endDateTime  ",true,timePeriod.isOverlap(timePeriod1));
        //case7:
        timePeriod1 =createTimePeriod("20150717","20150717");
        assertEquals("startDateTime1=startDate && endDateTime1=startDate",true,timePeriod.isOverlap(timePeriod1));
        //case8:
        timePeriod1 =createTimePeriod("20150715","20150717");
        assertEquals("startDateTime1<startDate && endDateTime1=startDate",true,timePeriod.isOverlap(timePeriod1));
        //case9:
        timePeriod1 =createTimePeriod("20150717","20150719");
        assertEquals("startDateTime1 ==startDate && endDateTime1>startDate&&endDateTime1<endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case10:
        timePeriod1 =createTimePeriod("20150717","20150817");
        assertEquals("startDateTime1 ==startDate && endDateTime1>endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case11:
        timePeriod1 =createTimePeriod("20150816","20150816");
        assertEquals("startDateTime1 ==endDateTime && endDateTime1=endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case12:
        timePeriod1 =createTimePeriod("20150816","20150817");
        assertEquals("startDateTime1 == endDateTime && endDateTime1>endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case13:
        timePeriod1 =createTimePeriod("20150817","20150818");
        assertEquals("startDateTime1 == endDateTime && endDateTime1>endDateTime",false,timePeriod.isOverlap(timePeriod1));
        //case14:
        timePeriod1 =createTimePeriod("20150718","20150810");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1<endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case15:
        timePeriod1 =createTimePeriod("20150718","20150816");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1=endDateTime",true,timePeriod.isOverlap(timePeriod1));
        //case15:
        timePeriod1 =createTimePeriod("20150718","20150818");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1>endDateTime",true,timePeriod.isOverlap(timePeriod1));
    }

    @Test
    public void testIsInTimePeriod1() throws Exception {
        TimePeriod timePeriod = createTimePeriod("20150717","20150816");
        //case1:parameter is null
        try {
            timePeriod.isOverlap(null);
            fail("parameter is null");
        } catch (Exception e){

        }
        //Case2: timePeriod =timePeriod2
        TimePeriod timePeriod1 =createTimePeriod("20150717","20150816");
        assertEquals("timePeriod equals timePeriod1",true,timePeriod.isOverlap(timePeriod1));
        // case3: timePeriod.startDateTime> timePeriod1.startDateTime  && timePeriod.endDateTime<timePeriod1.endDateTime
        timePeriod1 =createTimePeriod("20150715","20150716");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));

        //case3:
        timePeriod1 =createTimePeriod("20150715","20150718");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case4:
        timePeriod1 =createTimePeriod("20150715","20150816");
        assertEquals(" ",true,timePeriod.isInTimePeriod(timePeriod1));
        //case5:
        timePeriod1 =createTimePeriod("20150715","20150817");
        assertEquals(" ",true,timePeriod.isInTimePeriod(timePeriod1));
        //case6:
        timePeriod1 =createTimePeriod("20150717","20150717");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case7:
        timePeriod1 =createTimePeriod("20150715","20150717");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case8:
        timePeriod1 =createTimePeriod("20150717","20150719");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case9:
        timePeriod1 =createTimePeriod("20150717","20150816");
        assertEquals(" ",true,timePeriod.isInTimePeriod(timePeriod1));
        //case10:
        timePeriod1 =createTimePeriod("20150717","20150817");
        assertEquals(" ",true,timePeriod.isInTimePeriod(timePeriod1));
        //case11:
        timePeriod1 =createTimePeriod("20150816","20150816");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case12:
        timePeriod1 =createTimePeriod("20150816","20150817");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case13:
        timePeriod1 =createTimePeriod("20150817","20150818");
        assertEquals(" ",false,timePeriod.isInTimePeriod(timePeriod1));
        //case14:
        timePeriod1 =createTimePeriod("20150718","20150810");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1<endDateTime",false,timePeriod.isInTimePeriod(timePeriod1));
        //case15:
        timePeriod1 =createTimePeriod("20150718","20150816");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1=endDateTime",false,timePeriod.isInTimePeriod(timePeriod1));
        //case15:
        timePeriod1 =createTimePeriod("20150718","20150818");
        assertEquals("startDateTime1 >startDateTime  && endDateTime1>endDateTime",false,timePeriod.isInTimePeriod(timePeriod1));

    }
}