package com.digiwes.product.spec;

import java.util.*;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;

/**
 * A type of ProductSpecification that is formed by aggregating other ProductSpecifications, which may be Composite or Atomic ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    private List<ProductSpecification> prodSpec  = new ArrayList<ProductSpecification>();

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     */
    public CompositeProductSpecification(String productNumber, String name, String brand,TimePeriod validFor) {
        super(productNumber,name,brand,validFor);
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param description
     * @param validFor
     */
    public CompositeProductSpecification(String productNumber, String name, String brand, String description, TimePeriod validFor) {
        super(productNumber, name, brand, validFor, description);
    }

    /**
     * 
     * @param prodSpec
     */
    public BusinessCode composedOf(ProductSpecification prodSpec) {
        if(this.equals(prodSpec)){
            return BusinessCode.PROD_SPEC_COMPOSE_OF_ITSELF;
        }
        this.prodSpec.add(prodSpec);
        return BusinessCode.SUCCESS;

    }

    /**
     * 
     * @param status
     */
    public List<ProductSpecification> retrieveProductSpec(String status) {
        ParameterUtil.checkParameterIsNulForException(status,"lifecycleStatus"); //validParameter
        List<ProductSpecification> validProductSpecification = new ArrayList<ProductSpecification>();
        if( null != this.prodSpec && this.prodSpec.size() >0){
             for (ProductSpecification productSpecification:this.prodSpec){
                 if(status.equals(productSpecification.getLifecycleStatus())){
                     validProductSpecification.add(productSpecification);
                 }
             }
        }
        return validProductSpecification;
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecification> retrieveProductSpec(Date time) {

        ParameterUtil.checkParameterIsNulForException(time,"time"); //validParameter
        List<ProductSpecification> validProductSpecification = new ArrayList<ProductSpecification>();
        if( null != this.prodSpec && this.prodSpec.size() >0){
            for (ProductSpecification productSpecification:this.prodSpec){
                if( null == productSpecification.getValidFor() || productSpecification.getValidFor().isInTimePeriod(time) ){
                    validProductSpecification.add(productSpecification);
                }
            }
        }
       return validProductSpecification;
    }

    public String toString() {
         //TODO
        throw new UnsupportedOperationException();
    }

}
