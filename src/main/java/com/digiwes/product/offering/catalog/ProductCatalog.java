package com.digiwes.product.offering.catalog;

import com.digiwes.common.catalog.*;
import java.util.*;
import com.digiwes.basetype.*;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.offering.*;
import com.digiwes.product.offering.price.*;
import org.apache.log4j.Logger;

/**
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 */
public class ProductCatalog extends Catalog {
    private static final Logger logger = Logger.getLogger(ProductCatalog.class);

    private List<ProdCatalogProdOffer> prodCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();

    public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
        return prodCatalogProdOffer;
    }
    /**
     * 
     * @param id
     * @param name
     * @param type
     */
    public ProductCatalog(String id, String name, String type) {
      super(id, name, type, null);
    }

    /**
     * 
     * @param id
     * @param name
     * @param type
     * @param validFor
     */
    public ProductCatalog(String id, String name, String type, TimePeriod validFor) {
        super(id, name, type, validFor);
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public int publish(ProductOffering offering, TimePeriod validFor) {
        int code = checkOffering(offering, validFor);
        if (CommonErrorCode.SUCCESS.getCode() == code){
            ProdCatalogProdOffer catalogProdOffer=new ProdCatalogProdOffer(offering,validFor);
            prodCatalogProdOffer.add(catalogProdOffer);
            return CommonErrorCode.SUCCESS.getCode();
        }
        return code;
    }



    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public int publish(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
        int code = checkOffering(offering, validFor);
        if (CommonErrorCode.SUCCESS.getCode() == code){
            ProdCatalogProdOffer catalogProdOffer=new ProdCatalogProdOffer(offering,validFor,price);
            prodCatalogProdOffer.add(catalogProdOffer);
            return CommonErrorCode.SUCCESS.getCode();
        }
        return code;
    }



    /**
     * 
     * @param offering
     * @param validFor
     */
    public int retired(ProductOffering offering, TimePeriod validFor) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode();
        }
        if(ParameterUtil.checkParameterIsNull(validFor))  {
            return CommonErrorCode.VALIDFOR_IS_NULL.getCode();
        }
        ProdCatalogProdOffer prodCatalogProdOffer = this.retrieveProdCatalogProdOffer(offering,validFor);
        if( null != prodCatalogProdOffer) {
            validFor.setEndDateTime(new Date());
            prodCatalogProdOffer.setValidFor(validFor);
        }
        logger.warn("offering have not been publish");
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param offering
     */
    public int retired(ProductOffering offering) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode();
        }
        for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
            if(prodCatalogProdOffer.getProdOffering().equals(offering) ){
                prodCatalogProdOffer.getValidFor().setEndDateTime(new Date());
                prodCatalogProdOffer.setValidFor(validFor);
            }
        }
        logger.warn("offering have not been publish");
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param offering
     * @param time
     */
    public ProductOfferingPrice[] retrieveOfferingPrice(ProductOffering offering, int time) {

        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param price
     */
    public int specifyOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice price) {
        // TODO - implement ProductCatalog.specifyOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param newPrice
     */
    public int alterOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice[] newPrice) {
        // TODO - implement ProductCatalog.alterOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public List<ProdCatalogProdOffer> retrieveOffering(Date time) {
        ParameterUtil.checkParameterIsNulForException(time,"time");
        List<ProdCatalogProdOffer> validProdCatalogProdOffer =new ArrayList<ProdCatalogProdOffer>();
       for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
             if(prodCatalogProdOffer.getValidFor().isInTimePeriod(time)){
                 validProdCatalogProdOffer.add(prodCatalogProdOffer);
             }
       }
        return validProdCatalogProdOffer;
    }

    /**
     * 
     * @param offeringName
     */
    public List<ProdCatalogProdOffer> retrieveOffering(String offeringName) {

        ParameterUtil.checkParameterIsNulForException(offeringName,"offeringName");
        List<ProdCatalogProdOffer> validProdCatalogProdOffer =new ArrayList<ProdCatalogProdOffer>();
        for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
            if(offeringName.equals(prodCatalogProdOffer.getProdOffering().getName())){
                validProdCatalogProdOffer.add(prodCatalogProdOffer);
            }
        }
        return validProdCatalogProdOffer;
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    private ProdCatalogProdOffer retrieveProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {

        for(ProdCatalogProdOffer catalogProdOffer:prodCatalogProdOffer){
               if(offering.equals(catalogProdOffer.getProdOffering()) && (catalogProdOffer.getValidFor().isOverlap(validFor))){
                   return catalogProdOffer;
               }
           }
        return  null;
    }

    /**
     * 
     * @param offering
     */
    private ProdCatalogProdOffer[] retrieveProdCatalogProdOffer(ProductOffering offering) {
        // TODO - implement ProductCatalog.retrieveProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    private boolean contains(ProductOffering offering, TimePeriod validFor) {
        if( null == retrieveProdCatalogProdOffer(offering,validFor) ){
            return false;
        }
        return true;
    }

    /**
     * 
     * @param offering
     * @param oldValidFor
     * @param newValidFor
     */
    public int modifyOfferingValidTime(ProductOffering offering, TimePeriod oldValidFor, TimePeriod newValidFor) {
        // TODO - implement ProductCatalog.modifyOfferingValidTime
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement ProductCatalog.toString
        throw new UnsupportedOperationException();
    }

    private int checkOffering(ProductOffering offering, TimePeriod validFor) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode();
        }
        if(ParameterUtil.checkParameterIsNull(validFor)) {
            return CommonErrorCode.VALIDFOR_IS_NULL.getCode();
        }
        Date now =new Date();
        if(now.compareTo(validFor.getStartDateTime()) ==1){
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getCode();
        }
        if (contains(offering, validFor)){
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode();
        }

        if(!offering.getValidFor().isInTimePeriod(validFor)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_INVALID.getCode();
        }
        return CommonErrorCode.SUCCESS.getCode();
    }


}