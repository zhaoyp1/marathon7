package com.digiwes.product.offering.catalog;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.spec.AtomicProductSpecification;
import com.digiwes.utils.DateUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liurl3 on 2015/7/16.
 */
public class ProductCatalogTest extends TestCase {
    private ProductOffering productOffering;
    private ProductCatalog catalog;
    private TimePeriod validFor;
    private AtomicProductSpecification atomicProdSpec;
    @Before
    public void   setUp() throws ParseException {
        validFor = new TimePeriod(DateUtils.datetimeFormat.parse("2015-02-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-11-01 23:59:59"));
        atomicProdSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validFor);
        productOffering =new SimpleProductOffering("1","13-inch MacBook Pro","13-inch MacBook Pro", validFor,atomicProdSpec) ;
        catalog = new ProductCatalog("1","MAC","TEST", validFor);

    }

    @Test
    public void testPublish() throws Exception {
        List<ProdCatalogProdOffer> expectProdCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();

        int errorCode =  catalog.publish(null,validFor);
        assertEquals("publish a offering but it's null ,compare to return errorCode.", ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode(),errorCode);
        assertEquals("publish a offering but it's null ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        errorCode =  catalog.publish(productOffering,null);
        assertEquals("publish a offering but it's validFor is null ,compare to return errorCode.", CommonErrorCode.VALIDFOR_IS_NULL.getCode(),errorCode);
        assertEquals("publish a offering but it's validFor is null ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        TimePeriod validFor1 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-07-21 23:59:59"));
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(productOffering,validFor1);
        expectProdCatalogProdOffer.add(catalogProdOffer);
        errorCode =  catalog.publish(productOffering,validFor1);
        assertEquals("publish a offering ,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("publish a offering ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        SimpleProductOffering diffProductOffering =new SimpleProductOffering("5","17-inch MacBook Pro","17-inch MacBook Pro", validFor,atomicProdSpec) ;
        ProdCatalogProdOffer diffcatalogProdOffer = new ProdCatalogProdOffer(diffProductOffering,validFor1);
        expectProdCatalogProdOffer.add(diffcatalogProdOffer);
        errorCode =  catalog.publish(diffProductOffering,validFor1);
        assertEquals("publish a different offering but the validFor is same,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("publish a different offering but the validFor is same ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        TimePeriod validFor11 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-07-25 23:59:59"),DateUtils.datetimeFormat.parse("2015-08-15 23:59:59"));
        SimpleProductOffering sameProductOffering =new SimpleProductOffering("1","13-inch MacBook Pro","13-inch MacBook Pro", validFor,atomicProdSpec) ;
        ProdCatalogProdOffer catalogProdOffer2 = new ProdCatalogProdOffer(sameProductOffering,validFor11);
        expectProdCatalogProdOffer.add(catalogProdOffer2);
        errorCode =  catalog.publish(sameProductOffering,validFor11);
        assertEquals("publish a exists offering but the validFor is different,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("publish a exists offering but the validFor is different ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        errorCode =  catalog.publish(sameProductOffering,validFor1);
        assertEquals("publish a exists offering ,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode(), errorCode);
        assertEquals("publish a exists offering ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor12 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-07-26 23:59:59"),DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"));
        errorCode =  catalog.publish(sameProductOffering,validFor12);
        assertEquals("publish a exists offering but the validFor within the exists offering's publish time,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode(), errorCode);
        assertEquals("publish a exists offering but the validFor within the exists offering's publish time ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor13 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-07-22 23:59:59"),DateUtils.datetimeFormat.parse("2015-08-20 23:59:59"));
        errorCode =  catalog.publish(sameProductOffering,validFor13);
        assertEquals("publish a exists offering but the validFor include the exists offering's publish time,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode(), errorCode);
        assertEquals("publish a exists offering but the validFor include the exists offering's publish time ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor14 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-07-22 23:59:59"),DateUtils.datetimeFormat.parse("2015-08-13 23:59:59"));
        errorCode =  catalog.publish(sameProductOffering,validFor14);
        assertEquals("publish a exists offering but the validFor's endTime include the exists offering's publish time,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode(), errorCode);
        assertEquals("publish a exists offering but the validFor's endTime include the exists offering's publish time ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor15 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-07-27 23:59:59"),DateUtils.datetimeFormat.parse("2015-08-25 23:59:59"));
        errorCode =  catalog.publish(sameProductOffering,validFor15);
        assertEquals("publish a exists offering but the validFor's startTime include the exists offering's publish time,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode(), errorCode);
        assertEquals("publish a exists offering but the validFor's startTime include the exists offering's publish time ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-02-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-07-01 23:59:59"));
        SimpleProductOffering invalidProductOffering =new SimpleProductOffering("2","15-inch MacBook Pro","15-inch MacBook Pro", validFor2,atomicProdSpec) ;
        errorCode =  catalog.publish(invalidProductOffering,validFor1);
        assertEquals("publish a invalid offering ,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getCode(), errorCode);
        assertEquals("publish a invalid offering ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor3 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2016-07-01 23:59:59"));
        SimpleProductOffering productOffering3 =new SimpleProductOffering("3","11-inch MacBook Pro","11-inch MacBook Pro", validFor,atomicProdSpec) ;
        errorCode =  catalog.publish(productOffering3,validFor3);
        assertEquals("publish a offering but the release time is greater than offering's endDateTime,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getCode(), errorCode);
        assertEquals("publish a offering but the release time is greater than offering's endDateTime,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

        TimePeriod validFor4 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-12-10 23:59:59"),DateUtils.datetimeFormat.parse("2016-07-01 23:59:59"));
        SimpleProductOffering productOffering4 =new SimpleProductOffering("3","11-inch MacBook Pro","11-inch MacBook Pro", validFor,atomicProdSpec) ;
        errorCode =  catalog.publish(productOffering4,validFor4);
        assertEquals("publish a offering but the release time is greater than offering's validFor,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getCode(), errorCode);
        assertEquals("publish a offering but the release time is greater than offering's validFor,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.",expectProdCatalogProdOffer,catalog.getProdCatalogProdOffer());

    }


    @Test
    public void testRetrieveOffering() throws Exception {
        TimePeriod validFor1 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-08-01 23:59:59"));
        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        catalog.publish(productOffering, validFor1);
        catalog.publish(productOffering, validFor2);
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(productOffering,validFor1);
        ProdCatalogProdOffer catalogProdOffer2 = new ProdCatalogProdOffer(productOffering,validFor2);
        List<ProdCatalogProdOffer> prodCatalogProdOffer = null;

        try{
            Date time = null;
            prodCatalogProdOffer = catalog.retrieveOffering(time);
            fail("retrieve offerings by time but the parameter time is null.");
        }catch(IllegalArgumentException E){}

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        expectProdCatalogProdOffer.add(catalogProdOffer);
        prodCatalogProdOffer = catalog.retrieveOffering(new Date());
        assertEquals("retrieve currently valid offerings ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, prodCatalogProdOffer);

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer2 = new ArrayList<ProdCatalogProdOffer>();
        expectProdCatalogProdOffer2.add(catalogProdOffer2);
        prodCatalogProdOffer = catalog.retrieveOffering(DateUtils.datetimeFormat.parse("2015-08-15 23:59:59"));
        assertEquals("retrieve next month valid offerings ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer2, prodCatalogProdOffer);

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer3 = new ArrayList<ProdCatalogProdOffer>();
        prodCatalogProdOffer = catalog.retrieveOffering(DateUtils.datetimeFormat.parse("2015-10-15 23:59:59"));
        assertEquals("retrieve haven't valid offerings in the time,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer3, prodCatalogProdOffer);

    }

    @Test
    public void testRetrieveOffering1() throws Exception {
        TimePeriod validFor1 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-08-01 23:59:59"));
        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        catalog.publish(productOffering, validFor1);
        catalog.publish(productOffering, validFor2);
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(productOffering,validFor1);
        ProdCatalogProdOffer catalogProdOffer2 = new ProdCatalogProdOffer(productOffering,validFor2);
        List<ProdCatalogProdOffer> prodCatalogProdOffer = null;

        try{
            String name = null;
            prodCatalogProdOffer = catalog.retrieveOffering(name);
            fail("retrieve offerings by name but the parameter name is null.");
        }catch(IllegalArgumentException E){}

        try{
            prodCatalogProdOffer = catalog.retrieveOffering("");
            fail("retrieve offerings by name but the parameter name is empty.");
        }catch(IllegalArgumentException E){}

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        expectProdCatalogProdOffer.add(catalogProdOffer);
        expectProdCatalogProdOffer.add(catalogProdOffer2);
        prodCatalogProdOffer = catalog.retrieveOffering("13-inch MacBook Pro");
        assertEquals("retrieve offerings by name ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, prodCatalogProdOffer);

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer2 = new ArrayList<ProdCatalogProdOffer>();
        prodCatalogProdOffer = catalog.retrieveOffering("15-inch MacBook Pro");
        assertEquals("retrieve offerings by name but the catalog haven't this offering's name ,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer2, prodCatalogProdOffer);

    }

    @Test
    public void testRetired() throws Exception {
        TimePeriod validFor1 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-08-01 23:59:59"));
        TimePeriod validFor2 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-10 23:59:59"),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        List<ProdCatalogProdOffer> expectProdCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        catalog.publish(productOffering, validFor1);
        catalog.publish(productOffering, validFor2);
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(productOffering,validFor1);
        ProdCatalogProdOffer catalogProdOffer2 = new ProdCatalogProdOffer(productOffering,validFor2);
        expectProdCatalogProdOffer.add(catalogProdOffer);
        expectProdCatalogProdOffer.add(catalogProdOffer2);

        int errorCode = catalog.retired(null);
        assertEquals("retired a offering but it's null,compare to return errorCode.", ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode(), errorCode);
        assertEquals("retired a offering but it's null,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        SimpleProductOffering productOffering4 =new SimpleProductOffering("3","11-inch MacBook Pro","11-inch MacBook Pro", validFor,atomicProdSpec) ;
        errorCode = catalog.retired(productOffering4);
        assertEquals("retired a offering but it's not belong to the catalog,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_NOT_BE_PUBLISH.getCode(), errorCode);
        assertEquals("retired a offering but it's not belong to the catalog,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer2 = new ArrayList<ProdCatalogProdOffer>();
        errorCode = catalog.retired(productOffering);
        assertEquals("retired a offering ,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("retired a offering ,expectSize compare to size.", 2, catalog.getProdCatalogProdOffer().size());
        assertEquals("retired a offering ,expectProdCatalogProdOffer compare to catalog's currently valid offerings.", expectProdCatalogProdOffer2, catalog.retrieveOffering(new Date()));

    }

    @Test
    public void testRetired1() throws Exception {
        TimePeriod validFor1 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-08-01 23:59:59"));
        TimePeriod validFor2 = new TimePeriod(new Date(),DateUtils.datetimeFormat.parse("2015-09-01 23:59:59"));
        SimpleProductOffering productOffering2 =new SimpleProductOffering("2","11-inch MacBook Pro","11-inch MacBook Pro", validFor,atomicProdSpec) ;
        List<ProdCatalogProdOffer> expectProdCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        catalog.publish(productOffering, validFor1);
        catalog.publish(productOffering2, validFor2);
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(productOffering,validFor1);
        ProdCatalogProdOffer catalogProdOffer2 = new ProdCatalogProdOffer(productOffering2,validFor2);
        expectProdCatalogProdOffer.add(catalogProdOffer);
        expectProdCatalogProdOffer.add(catalogProdOffer2);

        int errorCode = catalog.retired(null,validFor1);
        assertEquals("retired a offering but it's null,compare to return errorCode.", ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode(), errorCode);
        assertEquals("retired a offering but it's null,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        errorCode = catalog.retired(productOffering,null);
        assertEquals("retired a offering but it's validFor is null,compare to return errorCode.", CommonErrorCode.VALIDFOR_IS_NULL.getCode(), errorCode);
        assertEquals("retired a offering but it's validFor is null,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        SimpleProductOffering productOffering4 =new SimpleProductOffering("3","11-inch MacBook Pro","11-inch MacBook Pro", validFor,atomicProdSpec) ;
        errorCode = catalog.retired(productOffering4,validFor1);
        assertEquals("retired a offering but it's not belong to the catalog,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_NOT_BE_PUBLISH.getCode(), errorCode);
        assertEquals("retired a offering but it's not belong to the catalog,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        TimePeriod validFor3 = new TimePeriod(DateUtils.datetimeFormat.parse("2015-08-05 23:59:59"),DateUtils.datetimeFormat.parse("2015-10-01 23:59:59"));
        errorCode = catalog.retired(productOffering,validFor3);
        assertEquals("retired a offering but it's not belong to the catalog in the time,compare to return errorCode.", ProdCatalogErrorCode.PROD_CATALOG_OFFERING_NOT_BE_PUBLISH.getCode(), errorCode);
        assertEquals("retired a offering but it's not belong to the catalog in the time,expectProdCatalogProdOffer compare to catalog's prodCatalogProdOffer.", expectProdCatalogProdOffer, catalog.getProdCatalogProdOffer());

        List<ProdCatalogProdOffer> expectProdCatalogProdOffer2 = new ArrayList<ProdCatalogProdOffer>();
        expectProdCatalogProdOffer2.add(catalogProdOffer2);
        errorCode = catalog.retired(productOffering,validFor1);
        assertEquals("retired a offering ,compare to return errorCode.", CommonErrorCode.SUCCESS.getCode(), errorCode);
        assertEquals("retired a offering ,expectSize compare to size.", 2, catalog.getProdCatalogProdOffer().size());
        assertEquals("retired a offering ,expectProdCatalogProdOffer compare to catalog's currently valid offerings.", expectProdCatalogProdOffer2, catalog.retrieveOffering(new Date()));

    }

}