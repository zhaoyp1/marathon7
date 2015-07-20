package com.digiwes.product.control;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
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
         int result =catalog.publish(offering, validFor);
         if(CommonErrorCode.VALIDFOR_IS_NULL.getCode() == result) {
             throw  new Exception(CommonErrorCode.VALIDFOR_IS_NULL.getMessage());
         }else if(ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getCode() == result){
             throw  new Exception(ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getMessage());
         }else if(ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode() == result){
             throw  new IllegalArgumentException(ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getMessage());
         } else if(ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getCode() ==result ){
             throw  new Exception(ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getMessage());
         } else if(ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getCode() == result){
             throw  new Exception(ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getMessage());
         }
         catalogPersistence.save(catalog);
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
    public List<PublishedOffering> retrieveOffering(String offeringName,Date time,String prodCatalogId) throws Exception{
        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        CatalogPersistenceSimpleImpl catalogPersistence = new CatalogPersistenceSimpleImpl();
        List<ProdCatalogProdOffer> prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        ProductOfferingPersistenceSimpleImpl productOfferingPersistenceSimple = new ProductOfferingPersistenceSimpleImpl();

        ProductCatalog productCatalog = catalogPersistence.load(prodCatalogId);
        ProductOffering prodOffering = productOfferingPersistenceSimple.load(prodCatalogId);
        if(null != time) {
            prodCatalogProdOffers = productCatalog.retrieveOffering(time);
        }
        if(StringUtils.isEmpty(offeringName)){
           prodCatalogProdOffers = productCatalog.retrieveOffering(offeringName);
        }
        for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffers){
            PublishedOffering publishedOffering = new PublishedOffering();
            publishedOffering.id = catalogProdOffer.getProdOffering().getId();
            publishedOffering.name = catalogProdOffer.getProdOffering().getName();
            publishedOffering.validFor = catalogProdOffer.getValidFor();
            com.digiwes.product.resource.Parameter.ProductCatalog prodCatalog = new com.digiwes.product.resource.Parameter.ProductCatalog();
            prodCatalog.id = productCatalog.getID();
            prodCatalog.name = productCatalog.getName();
            prodCatalog.type = productCatalog.getType();
            publishedOffering.productCatalog = prodCatalog;
          //  publishedOffering.productOffering = catalogProdOffer.getProdOffering();
            publishedOfferingList.add(publishedOffering);

        }
        return publishedOfferingList;
    }
}
