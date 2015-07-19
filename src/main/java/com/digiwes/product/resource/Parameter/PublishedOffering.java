package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

import java.util.*;

public class PublishedOffering {

    public ProductOffering productOffering;
    public TimePeriod validFor;
    public ProductCatalog productCatalog;
    public  List<ProductOfferingPrice> advicePrice;
    public String id;
    public String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}