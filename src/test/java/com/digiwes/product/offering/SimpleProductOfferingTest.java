package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.spec.AtomicProductSpecification;
import com.digiwes.product.spec.ProductSpecification;
import com.digiwes.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by dongwh on 2015-7-16.
 */
public class SimpleProductOfferingTest {

    private ProductOffering offering = null;
    private ProductSpecification prodSpec = null;
    private TimePeriod validFor = null;
    private TimePeriod validForSpec = null;

    @Before
    public void setUp() throws Exception {

        Date startDateTimeSpec = DateUtils.str2Date("2015-06-01 00:00:00",DateUtils.datetimeFormat);
        Date endDateTimeSpec = DateUtils.str2Date("2015-10-01 00:00:00",DateUtils.datetimeFormat);
        validForSpec = new TimePeriod(startDateTimeSpec, endDateTimeSpec);
    }

    @Test
    public void testSimpleProductOffering(){
        String id = "0001";
        String name = "11 pound MacBook Air 6,288";
        String description = "1.6GHz Intel Core i5 process£¬Turbo Boost 2.7GHz";

        //pre  Exception
        Date startDateTime = DateUtils.str2Date("2015-01-01 00:00:00",DateUtils.datetimeFormat);
        Date endDateTime = DateUtils.str2Date("2015-05-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);
        fail("validFor is not valid, no check.");

        //in   Normal
        startDateTime = DateUtils.str2Date("2015-07-01 00:00:00",DateUtils.datetimeFormat);
        endDateTime = DateUtils.str2Date("2015-09-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);

        //after   Exception
        startDateTime = DateUtils.str2Date("2015-11-01 00:00:00",DateUtils.datetimeFormat);
        endDateTime = DateUtils.str2Date("2015-12-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);
        fail("validFor is not valid, no check.");
    }
}