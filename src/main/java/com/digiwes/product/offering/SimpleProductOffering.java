package com.digiwes.product.offering;

import com.digiwes.product.spec.*;
import com.digiwes.basetype.*;

/**
 * A type of ProductOffering that does not have any subordinate ProductOfferings, that is, an SimpleProductOffering is a leaf-level ProductOffering.
 */
public class SimpleProductOffering extends ProductOffering {

    private ProductSpecification productSpecification;

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     * @param prodSpec
     */
    public SimpleProductOffering(String id, String name, String description, TimePeriod validFor, ProductSpecification prodSpec) {
         super(id,name,description,validFor);
        this.productSpecification = prodSpec;
    }

    /**
     * Basic information of the class output into the Map
     */
    protected Map getBasicInfo() {
        // TODO - implement SimpleProductOffering.getBasicInfo
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement SimpleProductOffering.toString
        throw new UnsupportedOperationException();
    }

}