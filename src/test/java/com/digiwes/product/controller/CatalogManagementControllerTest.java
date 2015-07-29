package com.digiwes.product.controller;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.control.CatalogManagementController;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.response.ProductSpecificationRef;
import com.digiwes.product.spec.AtomicProductSpecification;
import com.digiwes.product.spec.ProductSpecification;
import com.digiwes.product.spec.data.CatalogData;
import com.digiwes.product.spec.data.OfferingData;
import com.digiwes.product.spec.data.SpecCharData;
import com.digiwes.product.spec.data.SpecData;
import com.digiwes.utils.DateUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by dongwh on 2015-7-29.
 */

public class CatalogManagementControllerTest {

    private TimePeriod validFor = null;

    @Before
    public void setUp() throws Exception {

        SpecCharData.init();       // create char
        SpecData.init();           //create spec
        OfferingData.init();
        CatalogData.init();

        Date startDateTime = DateUtils.str2Date("2015-06-08 00:00:00",DateUtils.datetimeFormat);
        Date endDateTime = DateUtils.str2Date("2015-06-12 00:00:00",DateUtils.datetimeFormat);
        validFor = new TimePeriod(startDateTime, endDateTime);
    }

    @Ignore
    public void testPublishOffering() throws Exception {

    }

    @Test
    public void testRetrieveProductOffering() throws Exception {
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        ProductCatalog productCatalog = getManagementProductCatalog();
        productCatalog.publish(getProductOffering(), validFor);
        CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
        catalogPersistence.save(productCatalog);

        List<ProdCatalogProdOffer> prodCatalogProdOffers = null;
        //productCatalog is null
        try {
            prodCatalogProdOffers = catalogManagementController.retrieveProductOffering(null, "", "");
            fail("productCatalog is null");
        }catch (Exception e){

        }

        //offeringName is null or empty, time is null or empty
        prodCatalogProdOffers = catalogManagementController.retrieveProductOffering(productCatalog, "", "");
        assertEquals("productCatalog is null", 0, prodCatalogProdOffers.size());

        //
    }

    @Ignore
    public void testRetrieveOffering() throws Exception {

    }

    private  ProductCatalog getManagementProductCatalog(){
        ProductCatalog productCatalog = null;
        try {
            CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
            productCatalog = catalogPersistence.load("catalog_1");
        }catch (Exception e){

        }
        return productCatalog;
    }

    private ProductOffering getProductOffering(){
        ProductOffering productOffering = null;
        try {
            ProductOfferingPersistence offeringPersistence = PersistenceFactory.getProdOfferingPersistence();
            productOffering = offeringPersistence.load("off_1");
        }catch (Exception e){

        }
        return productOffering;
    }


}