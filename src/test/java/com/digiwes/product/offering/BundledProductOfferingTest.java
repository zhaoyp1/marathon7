package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
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
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validFor);
        offering = new SimpleProductOffering("offering_id", "offering_name", "offering_description", validFor, prodSpec);
        bundledProdOffering = new BundledProductOffering("bundle_id","bundle_name","bundle_description", validFor);

        //offering is null
        ProductOffering offeringNull = null;
        BusinessCode rtnCode = bundledProdOffering.composeOf(offeringNull, lowerLimit, upperLimit);
        assertEquals("offering is null", BusinessCode.PROD_OFFERING_IS_NULL, rtnCode);
        assertEquals("offering is null", 0, bundledProdOffering.getBundledProdOfferOption().size());

        //min > max
        int lowerLimitMin = 2;
        int upperLimitMax = 1;
        rtnCode = bundledProdOffering.composeOf(offering, lowerLimitMin, upperLimitMax);
        assertEquals("add an simpleOffering to bundleProductOffering, min > max", BusinessCode.PROD_OFFERING_BUNDLED_OPTION_LOWERLIMIT_GT_UPPERLIMIT.getCode(), rtnCode.getCode());
        assertEquals("add an simpleOffering to bundleProductOffering, min > max", 0, bundledProdOffering.getBundledProdOfferOption().size());

        //normal
        rtnCode = bundledProdOffering.composeOf(offering, lowerLimit, upperLimit);
        assertEquals("add an simpleOffering to bundleProductOffering", BusinessCode.SUCCESS, rtnCode.getCode());
        assertEquals("add an simpleOffering to bundleProductOffering", 1, bundledProdOffering.getBundledProdOfferOption().size());
        assertEquals("add an simpleOffering to bundleProductOffering", offering, bundledProdOffering.getBundledProdOfferOption().get(0).getProductOffering());

        //repeat offering can add
        rtnCode = bundledProdOffering.composeOf(offering, lowerLimit, upperLimit);
        assertEquals("add an simpleOffering to bundleProductOffering", BusinessCode.PROD_OFFERING_BUNDLED_OFFERING_EXISTED.getCode(), rtnCode.getCode());
        assertEquals("add an simpleOffering to bundleProductOffering", 1, bundledProdOffering.getBundledProdOfferOption().size());
    }
}