package com.digiwes.product.offering;

import java.util.*;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonEnum;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;

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
    public BusinessCode composedOf(ProductOffering offering) {
       return composeOf(offering,CommonEnum.MIN_NOT_LIMIT.getCode(), CommonEnum.MAX_NOT_LIMIT.getCode());
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public BusinessCode composeOf(ProductOffering offering, int lowerLimit, int upperLimit) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return BusinessCode.PROD_OFFERING_IS_NULL;
        }
        if(CommonEnum.MIN_NOT_LIMIT.getCode() != lowerLimit && CommonEnum.MAX_NOT_LIMIT.getCode() !=  upperLimit  ){
            if(lowerLimit>upperLimit){
                return BusinessCode.PROD_OFFERING_BUNDLED_OPTION_LOWERLIMIT_GT_UPPERLIMIT;
            }
        }
        BundledProdOfferOption offerOption = new BundledProdOfferOption(offering,lowerLimit,upperLimit);
        if(this.bundledProdOfferOption.contains(offerOption)){
            return BusinessCode.PROD_OFFERING_BUNDLED_OFFERING_EXISTED;
        }
        bundledProdOfferOption.add(offerOption);
        return BusinessCode.SUCCESS;
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