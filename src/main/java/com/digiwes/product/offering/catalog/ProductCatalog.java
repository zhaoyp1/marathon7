package com.digiwes.product.offering.catalog;

import com.digiwes.common.catalog.*;

import java.text.SimpleDateFormat;
import java.util.*;

import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.common.utils.TimeUtils;
import com.digiwes.product.offering.*;
import com.digiwes.product.offering.price.*;
import com.digiwes.basetype.TimePeriod;
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
        if (CommonErrorCode.SUCCESS.getCode() != code){
            return code;
        }
        ProdCatalogProdOffer catalogProdOffer=new ProdCatalogProdOffer(offering,validFor);
        prodCatalogProdOffer.add(catalogProdOffer);
        return CommonErrorCode.SUCCESS.getCode();

    }



    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public int publish(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
        int code = checkOffering(offering, validFor);
        if (CommonErrorCode.SUCCESS.getCode() != code){
            return code;
        }
        ProdCatalogProdOffer catalogProdOffer=new ProdCatalogProdOffer(offering,validFor,price);
        prodCatalogProdOffer.add(catalogProdOffer);
        return CommonErrorCode.SUCCESS.getCode();
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
        ProdCatalogProdOffer prodCatalogProdOffer = this.retrieveProdCatalogProdOffer(offering, validFor);
        if( null == prodCatalogProdOffer) {
            logger.warn("offering have not been publish");
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_NOT_BE_PUBLISH.getCode();
        }
        validFor.setEndDateTime(new Date());
        prodCatalogProdOffer.setValidFor(validFor);
        return CommonErrorCode.SUCCESS.getCode() ;

    }

    /**
     * 
     * @param offering
     */
    public int retired(ProductOffering offering) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode();
        }
        boolean isUsed = false;
        for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
            if(prodCatalogProdOffer.getProdOffering().equals(offering) ){
                isUsed = true;
                if(new Date().compareTo(prodCatalogProdOffer.getValidFor().getEndDateTime()) == -1){
                    prodCatalogProdOffer.getValidFor().setEndDateTime(new Date());
                }

            }
        }

        if(isUsed){
            return CommonErrorCode.SUCCESS.getCode();
        }else{
            logger.warn("offering have not been publish");
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_NOT_BE_PUBLISH.getCode();
        }

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
    public ProdCatalogProdOffer retrieveProdOffering(String id){
        ParameterUtil.checkParameterIsNulForException(id,"id");
        List<ProdCatalogProdOffer> validProdCatalogProdOffer =new ArrayList<ProdCatalogProdOffer>();
        for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
            if(id.equals(prodCatalogProdOffer.getProdOffering().getId())){
                return  prodCatalogProdOffer;
            }
        }
        return null;
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
     * @param offeringName
     * @param time
     * @return
     */
    public List<ProdCatalogProdOffer> retrieveOffering(String offeringName, Date time) {
        if(ParameterUtil.checkParameterIsNull(time)){
            if(ParameterUtil.checkParamIsNullOrEmpty(offeringName)){
                return this.prodCatalogProdOffer;
            }else{
                return retrieveOffering(offeringName);
            }
        }else{
            if(ParameterUtil.checkParamIsNullOrEmpty(offeringName)){
                return retrieveOffering(time);
            }else{
                List<ProdCatalogProdOffer> validProdCatalogProdOffer =new ArrayList<ProdCatalogProdOffer>();
                for (ProdCatalogProdOffer prodCatalogProdOffer:this.prodCatalogProdOffer){
                    if(prodCatalogProdOffer.getValidFor().isInTimePeriod(time)){
                        if(prodCatalogProdOffer.getProdOffering().getName().equals(offeringName)){
                            validProdCatalogProdOffer.add(prodCatalogProdOffer);
                        }
                    }
                }
                return validProdCatalogProdOffer;
            }
        }
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    private ProdCatalogProdOffer retrieveProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {

        for(ProdCatalogProdOffer catalogProdOffer:prodCatalogProdOffer){
               if(offering.equals(catalogProdOffer.getProdOffering()) && (catalogProdOffer.getValidFor().equals(validFor))){
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
        for(ProdCatalogProdOffer catalogProdOffer:prodCatalogProdOffer){
            if(offering.equals(catalogProdOffer.getProdOffering()) && (catalogProdOffer.getValidFor().isOverlap(validFor))){
                return true;
            }
        }
            return false;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = TimeUtils.truncDate(new Date());
        if( 1 == now.compareTo(validFor.getStartDateTime())){
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_VALIDFOR_INVALID.getCode();
        }
        if (contains(offering, validFor)){
            return ProdCatalogErrorCode.PROD_CATALOG_OFFERING_IS_PUBLISHED.getCode();
        }

        if(!validFor.isInTimePeriod(offering.getValidFor())){
            return ProdCatalogErrorCode.PROD_CATALOG_PUBLISH_OFFERING_VALIDFOR_IS_INVALID.getCode();
        }

        return CommonErrorCode.SUCCESS.getCode();
    }


}