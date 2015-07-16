package com.digiwes.product.offering;

import java.util.*;
import com.digiwes.basetype.*;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;

public class BundledProductOffering extends ProductOffering {

    private List<BundledProdOfferOption> bundledProdOfferOption;

    public List<BundledProdOfferOption> getBundledProdOfferOption() {
        return this.bundledProdOfferOption;
    }

    public void setBundledProdOfferOption(List<BundledProdOfferOption> bundledProdOfferOption) {
        this.bundledProdOfferOption = bundledProdOfferOption;
    }

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public BundledProductOffering(String id, String name, String description, TimePeriod validFor) {
         super(id,name,description,validFor);
    }

    /**
     * 
     * @param offering
     */
    public int composedOf(ProductOffering offering) {
       return composeOf(offering, -1, -1);
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public int composeOf(ProductOffering offering, int lowerLimit, int upperLimit) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return ProdOfferingErrorCode.PROD_OFFERING_OFFERING_IS_NULL.getCode();
        }
        if(lowerLimit>0){
            if(lowerLimit>upperLimit){

            }
        }
        BundledProdOfferOption offerOption = new BundledProdOfferOption(offering,lowerLimit,upperLimit);
        bundledProdOfferOption.add(offerOption);
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public int modifyOption(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProductOffering.modifyOption
        throw new UnsupportedOperationException();
    }

    public ProductOffering[] retrieveOffering() {
        // TODO - implement BundledProductOffering.retrieveOffering
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement BundledProductOffering.toString
        throw new UnsupportedOperationException();
    }

}