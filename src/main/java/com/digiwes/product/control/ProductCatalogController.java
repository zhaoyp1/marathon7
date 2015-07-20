package com.digiwes.product.control;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;
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
     * method of retiredOffering
     */
    public PublishedOffering retiredOffering(String prodCatalogId, String prodOfferingId, TimePeriod validFor) throws Exception{
        PublishedOffering publishedOffering = new PublishedOffering();
        CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
        ProductOfferingPersistence productOfferingPersistence = PersistenceFactory.getProdOfferingPersistence();

        ProductCatalog prodCatalog = catalogPersistence.load(prodCatalogId);
        ProductOffering prodOffering = productOfferingPersistence.load(prodOfferingId);
        int resultCode = prodCatalog.retired(prodOffering, validFor);

        publishedOffering.setId(prodOffering.getId());
        publishedOffering.setName(prodOffering.getName());

        com.digiwes.product.resource.Parameter.ProductOffering productOffering = new com.digiwes.product.resource.Parameter.ProductOffering();
        productOffering.convertFromProductOffering(prodOffering);
        publishedOffering.setProductOffering(productOffering);

        com.digiwes.product.resource.Parameter.ProductCatalog productCatalog = new com.digiwes.product.resource.Parameter.ProductCatalog();
        productCatalog.convertFromProductCatalog(prodCatalog);
        publishedOffering.setProductCatalog(productCatalog);

        catalogPersistence.save(prodCatalog);
        return publishedOffering;
    }

    /**
     *  retrieveOffering
     */
    public List<ProdCatalogProdOffer> retrieveOffering(String offeringName,Date time,ProductCatalog productCatalog) throws Exception{
        List<ProdCatalogProdOffer> prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        ParameterUtil.checkParameterIsNulForException(productCatalog,"productCatalog");
        if(null != time && StringUtils.isEmpty(offeringName)) {
            prodCatalogProdOffers = productCatalog.retrieveOffering(time);
        }
        if(!StringUtils.isEmpty(offeringName) ){
           prodCatalogProdOffers = productCatalog.retrieveOffering(offeringName);
        }
        return prodCatalogProdOffers;
    }

    /**
     * retrieveCatalog
     */
    public ProductCatalog retrieveCatalog(String catalogId) throws Exception{
        CatalogPersistenceSimpleImpl catalogPersistence = new CatalogPersistenceSimpleImpl();
        return  catalogPersistence.load(catalogId);

    }
}
