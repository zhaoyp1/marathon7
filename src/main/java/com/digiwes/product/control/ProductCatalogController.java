package com.digiwes.product.control;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.control.persistence.impl.CatalogPersistenceSimpleImpl;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.Parameter.PublishedOffering;
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
         BusinessCode result =catalog.publish(offering, validFor);
         if(BusinessCode.PROD_OFFERING_VALIDFOR_IS_NULL.getCode() == result.getCode()) {
             throw  new IllegalArgumentException(BusinessCode.PROD_OFFERING_VALIDFOR_IS_NULL.getMessage());
         }else if(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getCode() == result.getCode()){
             throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getMessage());
         }else if(BusinessCode.PROD_OFFERING_IS_NULL.getCode() == result.getCode()){
             throw  new IllegalArgumentException(BusinessCode.PROD_OFFERING_IS_NULL.getMessage());
         } else if(BusinessCode.PROD_OFFERING_PUBLISHED_VALIDPERIOD_NOT_IN_OFFERING_VALIDPERIOD.getCode() ==result.getCode()){
             throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_VALIDPERIOD_NOT_IN_OFFERING_VALIDPERIOD.getMessage());
         } else if(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getCode() == result.getCode()){
             throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getMessage());
         } else if(BusinessCode.PROD_OFFERING_CATALOG_OFFERING_HAS_BEEN_PUBLISHED.getCode() == result.getCode()){
             throw  new Exception(BusinessCode.PROD_OFFERING_CATALOG_OFFERING_HAS_BEEN_PUBLISHED.getMessage());
         }
         catalogPersistence.save(catalog);
         return catalog;
     }

    /**
     * method of retiredOffering
     */
    public ProdCatalogProdOffer retiredOffering(ProductCatalog prodCatalog, String prodOfferingId, TimePeriod validFor) throws Exception{
        CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
        ProductOfferingPersistence productOfferingPersistence = PersistenceFactory.getProdOfferingPersistence();
        ProductOffering prodOffering = productOfferingPersistence.load(prodOfferingId);

        if(null != prodCatalog.getProdCatalogProdOffer()){
            for(ProdCatalogProdOffer prodCatalogProdOffer : prodCatalog.getProdCatalogProdOffer()){
                if(prodCatalogProdOffer.getProdOffering().getId().equals(prodOfferingId) && prodCatalogProdOffer.getValidFor().equals(validFor)){
                    prodCatalog.retired(prodOffering, validFor);
                    catalogPersistence.save(prodCatalog);
                    return prodCatalogProdOffer;
                }
            }
        }
        return null;
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
        CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
        return  catalogPersistence.load(catalogId);

    }
}
