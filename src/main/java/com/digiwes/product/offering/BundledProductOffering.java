package com.digiwes.product.offering;

import java.util.*;
import com.digiwes.basetype.*;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;

public class BundledProductOffering extends ProductOffering {

    private List<BundledProdOfferOption> bundledProdOfferOption = new ArrayList<BundledProdOfferOption>();;

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
        if(lowerLimit>upperLimit){
            return ProdOfferingErrorCode.BUNDLED_PROD_OFFERING_LOWERLIMIT_GREATER_UPPERLIMIT.getCode();
        }
        BundledProdOfferOption offerOption = new BundledProdOfferOption(offering,lowerLimit,upperLimit);
        if(this.bundledProdOfferOption.contains(offerOption)){
            return ProdOfferingErrorCode.BUNDLED_PROD_OFFERING_ALREADY_COMPOSED.getCode();
        }
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

    public List<ProductOffering> retrieveOffering() {
        List<ProductOffering> offeringList = new ArrayList<ProductOffering>();
        for(BundledProdOfferOption offerOption:this.bundledProdOfferOption){
            offeringList.add(offerOption.getProductOffering());
        }
        return offeringList;
    }

    public String toString() {
        // TODO - implement BundledProductOffering.toString
        throw new UnsupportedOperationException();
    }

}