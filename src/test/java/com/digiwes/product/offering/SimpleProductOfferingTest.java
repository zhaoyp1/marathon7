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
    ProductSpecification prodSpec = null;
    TimePeriod validFor = null;
    TimePeriod validForSpec = null;

    @Before
    public void setUp() throws Exception {
        Date startDateTime = DateUtils.str2Date("2015-06-01 00:00:00",DateUtils.datetimeFormat);
        Date endDateTime = DateUtils.str2Date("2015-10-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
    }

    @Test
    public void testSimpleProductOffering(){
        String id = "0001";
        String name = "11 pound MacBook Air 6,288";
        String description = "1.6GHz Intel Core i5 process£¬Turbo Boost 2.7GHz";

        //pre
        Date startDateTimeSpec = DateUtils.str2Date("2015-01-01 00:00:00",DateUtils.datetimeFormat);
        Date endDateTimeSpec = DateUtils.str2Date("2015-05-01 00:00:00",DateUtils.datetimeFormat);
        validForSpec = new TimePeriod(startDateTimeSpec, endDateTimeSpec);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);

        //in
        startDateTimeSpec = DateUtils.str2Date("2015-07-01 00:00:00",DateUtils.datetimeFormat);
        endDateTimeSpec = DateUtils.str2Date("2015-09-01 00:00:00",DateUtils.datetimeFormat);
        validForSpec = new TimePeriod(startDateTimeSpec, endDateTimeSpec);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);

        //after
        startDateTimeSpec = DateUtils.str2Date("2015-11-01 00:00:00",DateUtils.datetimeFormat);
        endDateTimeSpec = DateUtils.str2Date("2015-12-01 00:00:00",DateUtils.datetimeFormat);
        validForSpec = new TimePeriod(startDateTimeSpec, endDateTimeSpec);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering(id,  name,  description,  validFor,  prodSpec);
    }
}