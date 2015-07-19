package com.digiwes.product.control;

import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.impl.CatalogPersistenceSimpleImpl;
import com.digiwes.product.control.persistence.impl.ProductOfferingPersistenceSimpleImpl;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.Parameter.PublishedOffering;
import com.digiwes.product.resource.Parameter.RetiredOfferingResponse;

/**
 * Created by zhaoyp on 2015/7/19.
 */
public class ProductCatalogController {

    /**
     * TODO publishOffering
     */


    /**
     * TODO retiredOffering
     */
    public PublishedOffering retiredOffering(String prodCatalogId, String prodOfferingId) throws Exception{
        PublishedOffering publishedOffering = new PublishedOffering();
        CatalogPersistenceSimpleImpl catalogPersistence = new CatalogPersistenceSimpleImpl();
        ProductOfferingPersistenceSimpleImpl productOfferingPersistenceSimple = new ProductOfferingPersistenceSimpleImpl();

        ProductCatalog productCatalog = catalogPersistence.load(prodCatalogId);
        ProductOffering prodOffering = productOfferingPersistenceSimple.load(prodOfferingId);
        productCatalog.retired(prodOffering);

        prodOffering.setId(prodOffering.getId());
        prodOffering.setName(prodOffering.getName());

        return publishedOffering;
    }

    /**
     * TODO retrieveOffering
     */
}
