package com.digiwes.product.spec;

import java.util.*;
import com.digiwes.basetype.*;

/**
 * A type of ProductSpecification that is formed by aggregating other ProductSpecifications, which may be Composite or Atomic ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    public List<ProductSpecification> prodSpec;

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
        super(productNumber,name,brand,validFor,description);
    }

    /**
     * 
     * @param prodSpec
     */
    public int composedOf(ProductSpecification prodSpec) {
        // TODO - implement CompositeProductSpecification.composedOf
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param status
     */
    public ProductSpecification[] retrieveProductSpec(String status) {
        // TODO - implement CompositeProductSpecification.retrieveProductSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecification[] retrieveProductSpec(Date time) {
        // TODO - implement CompositeProductSpecification.retrieveProductSpec
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement CompositeProductSpecification.toString
        throw new UnsupportedOperationException();
    }

}