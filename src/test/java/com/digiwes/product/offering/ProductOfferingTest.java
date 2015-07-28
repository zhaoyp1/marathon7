package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdOfferingEnum;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.product.spec.AtomicProductSpecification;
import com.digiwes.product.spec.ProductSpecification;
import com.digiwes.utils.DateUtils;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dongwh on 2015-7-16.
 */
public class ProductOfferingTest {

    private ProductOffering offering = null;
    private ProductSpecification prodSpec = null;
    private ProductSpecification prodSpecTarget = null;
    private TimePeriod validFor = null;
    private TimePeriod validForSpec = null;


    @Before
    public void setUp() throws Exception {

        Date startDateTimeSpec = DateUtils.str2Date("2015-06-01 00:00:00", DateUtils.datetimeFormat);
        Date endDateTimeSpec = DateUtils.str2Date("2015-10-01 00:00:00",DateUtils.datetimeFormat);
        validForSpec = new TimePeriod(startDateTimeSpec, endDateTimeSpec);

        Date startDateTime = DateUtils.str2Date("2015-07-01 00:00:00",DateUtils.datetimeFormat);
        Date endDateTime = DateUtils.str2Date("2015-09-01 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
        prodSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        offering = new SimpleProductOffering("0001",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpec);
    }

    @org.junit.Test
    public void testAssociate() throws Exception {
        prodSpecTarget = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);

        //offeringTarget is null
        ProductOffering offeringTargetNull = null;
        BusinessCode rtnCode = offering.associate(offeringTargetNull, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        assertEquals("target offering is null", 0, offering.getProdOfferingRelationship().size());
        assertEquals("target offering is null", BusinessCode.PROD_OFFERING_IS_NULL.getCode(), rtnCode.getCode());

        //type of relationship is null
        ProductOffering offeringTarget = new SimpleProductOffering("0002",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        rtnCode = offering.associate(offeringTarget, null, validFor);
        assertEquals("type of relationship is null", 0, offering.getProdOfferingRelationship().size());
        assertEquals("type of relationship is null", BusinessCode.PROD_OFFERING_RELATIONSHIP_TYPE_IS_NULL_OR_EMPTY.getCode(), rtnCode.getCode());

        //type of relationship is empty
        rtnCode = offering.associate(offeringTarget, "", validFor);
        assertEquals("type of relationship is null", 0, offering.getProdOfferingRelationship().size());
        assertEquals("type of relationship is null", BusinessCode.PROD_OFFERING_RELATIONSHIP_TYPE_IS_NULL_OR_EMPTY.getCode(), rtnCode.getCode());

        //validFor is null
        rtnCode = offering.associate(offeringTarget, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), null);
        assertEquals("validFor is null", 0, offering.getProdOfferingRelationship().size());
        assertEquals("validFor is null", BusinessCode.PROD_OFFERING_VALIDFOR_IS_NULL.getCode(), rtnCode.getCode());

        //can't create relationship to itself
        ProductOffering offeringTargetItself = new SimpleProductOffering("0001",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpec);
        rtnCode = offering.associate(offeringTargetItself, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        assertEquals("can't create relationship to itself", 0, offering.getProdOfferingRelationship().size());
        assertEquals("can't create relationship to itself", BusinessCode.PROD_OFFERING_ASSOCIATE_WITH_ITSELF.getCode(), rtnCode.getCode());

        //normal
        offering.associate(offeringTarget, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        assertEquals("is success to add an relationship", 1, offering.getProdOfferingRelationship().size());
        ProductOfferingRelationship offeringRelationship = new ProductOfferingRelationship(offering, offeringTarget, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        List<ProductOfferingRelationship> prodOfferingRelationship = new ArrayList<ProductOfferingRelationship>();
        prodOfferingRelationship.add(offeringRelationship);
        assertEquals("is success to add an relationship", offeringRelationship, offering.getProdOfferingRelationship().get(0));
        assertEquals("is success to add an relationship", prodOfferingRelationship, offering.getProdOfferingRelationship());
    }

    @org.junit.Test
    public void testRetrieveRelatedOffering() throws Exception {
        prodSpecTarget = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        ProductOffering offeringTarget = new SimpleProductOffering("0002",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        ProductOffering offeringTargetTwo = new SimpleProductOffering("0003",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        ProductOffering offeringTargetThree = new SimpleProductOffering("0004",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        offering.associate(offeringTarget, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        offering.associate(offeringTargetTwo, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        offering.associate(offeringTargetThree, ProdOfferingEnum.OfferingRelationshipType.DEPENDENCY.getValue(), validFor);

        //relationType is null
        try {
            offering.retrieveRelatedOffering(null);
            fail("relationType is null, no check.");
        }catch (Exception e){

        }

        //retrieve
        List<ProductOffering> productOffering =  offering.retrieveRelatedOffering(ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue());
        assertEquals("retrieve productOfferingRelationship", 2, productOffering.size());
    }

    @org.junit.Test
    public void testRetrieveRelatedOffering1() throws Exception {
        prodSpecTarget = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validForSpec);
        ProductOffering offeringTarget = new SimpleProductOffering("0002",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        ProductOffering offeringTargetTwo = new SimpleProductOffering("0003",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        ProductOffering offeringTargetThree = new SimpleProductOffering("0004",  "11 pound MacBook Air 6,288",  "1.6GHz Intel Core i5 process��Turbo Boost 2.7GHz",  validFor,  prodSpecTarget);
        offering.associate(offeringTarget, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        offering.associate(offeringTargetTwo, ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), validFor);
        offering.associate(offeringTargetThree, ProdOfferingEnum.OfferingRelationshipType.DEPENDENCY.getValue(), validFor);
        Date time = DateUtils.str2Date("2015-08-01 00:00:00", DateUtils.datetimeFormat);

        //relationType is null
        try {
            offering.retrieveRelatedOffering(null, time);
            fail("relationType is null, no check.");
        }catch (Exception e){

        }

        //time is null
        try {
            Date timeNull = null;
            offering.retrieveRelatedOffering(ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), timeNull);
            fail("time is null, no check.");
        }catch (Exception e){

        }

        //retrieve time is in
        List<ProductOffering> productOffering =  offering.retrieveRelatedOffering(ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), time);
        assertEquals("retrieve productOfferingRelationship", 2, productOffering.size());

        //retrieve time is pre
        time = DateUtils.str2Date("2015-01-01 00:00:00", DateUtils.datetimeFormat);
        productOffering =  offering.retrieveRelatedOffering(ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), time);
        assertEquals("retrieve productOfferingRelationship", 0, productOffering.size());

        //retrieve time is after
        time = DateUtils.str2Date("2015-12-01 00:00:00", DateUtils.datetimeFormat);
        productOffering =  offering.retrieveRelatedOffering(ProdOfferingEnum.OfferingRelationshipType.AGGREGATION.getValue(), time);
        assertEquals("retrieve productOfferingRelationship", 0, productOffering.size());
    }
}