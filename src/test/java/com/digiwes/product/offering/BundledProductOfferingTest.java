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
public class BundledProductOfferingTest {

    private ProductOffering offering = null;
    ProductSpecification prodSpec = null;
    TimePeriod validFor = null;
    TimePeriod validForSpec = null;
    int lowerLimit = -1;
    int upperLimit = -1;
    BundledProductOffering bundledProdOffering = null;

    @Before
    public void setUp() throws Exception {
        Date startDateTime = DateUtils.str2Date("2015-06-01 00:00:00", DateUtils.datetimeFormat);
        Date endDateTime = DateUtils.str2Date("2015-10-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
    }

    @Test
    public void testComposedOf() throws Exception {

    }

    @Test
    public void testComposeOf() throws Exception {
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering("offering_id", "offering_name", "offering_description", validFor, prodSpec);

        bundledProdOffering = new BundledProductOffering("bundle_id","bundle_name","bundle_description", validFor);
        bundledProdOffering.composeOf(offering, lowerLimit, upperLimit);
    }
}