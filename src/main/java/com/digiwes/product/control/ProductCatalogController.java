package com.digiwes.product.control;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.control.persistence.impl.CatalogPersistenceSimpleImpl;
import com.digiwes.product.control.persistence.impl.ProductOfferingPersistenceSimpleImpl;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.Parameter.PublishOfferingRequest;
import com.digiwes.product.resource.Parameter.PublishedOffering;
import com.digiwes.product.resource.Parameter.RetiredOfferingResponse;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoyp on 2015/7/19.
 */
public class ProductCatalogController {

    /**
     *publishOffering()
     */
     public ProductCatalog publishOffering(String prodCatalogId,String prodOfferingId,TimePeriod validFor)throws Exception{
         CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
         ProductOfferingPersistence productOfferingPersistence= PersistenceFactory.getProdOfferingPersistence();
         ProductCatalog catalog=catalogPersistence.load(prodCatalogId);
         ProductOffering offering=productOfferingPersistence.load(prodOfferingId);
         catalog.publish(offering, validFor);
         catalogPersistence.save(catalog);
         catalog=catalogPersistence.load(prodCatalogId);
         return catalog;
     }

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
    public List<ProdCatalogProdOffer> retrieveOffering(String offeringName,Date time,String prodCatalogId) throws Exception{
        CatalogPersistenceSimpleImpl catalogPersistence = new CatalogPersistenceSimpleImpl();
        List<ProdCatalogProdOffer> prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        ProductCatalog productCatalog = catalogPersistence.load(prodCatalogId);
        if(null != time && StringUtils.isEmpty(offeringName)) {
            prodCatalogProdOffers = productCatalog.retrieveOffering(time);
        }
        if(!StringUtils.isEmpty(offeringName) ){
           prodCatalogProdOffers = productCatalog.retrieveOffering(offeringName);
        }
        return prodCatalogProdOffers;
    }
}
