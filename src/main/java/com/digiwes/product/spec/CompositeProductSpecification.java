package com.digiwes.product.spec;

import java.util.*;
import com.digiwes.basetype.*;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.sun.research.ws.wadl.Param;
import org.apache.commons.lang.StringUtils;

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
    public CompositeProductSpecification(String productNumber, String name, String brand) {
        super(productNumber,name,brand);
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
    public int composedOf(ProductSpecification prodSpec) {
        if(this.equals(prodSpec)){
            return ProdSpecErrorCode.PROD_SPEC_EQUALS_TO_CURRENT.getCode();
        }
        this.prodSpec.add(prodSpec);
        return CommonErrorCode.SUCCESS.getCode();

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
