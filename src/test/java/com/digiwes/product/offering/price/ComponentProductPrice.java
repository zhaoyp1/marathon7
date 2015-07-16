package com.digiwes.product.offering.price;

import com.digiwes.basetype.TimePeriod;

/**
 * Created by Nisx on 2015/7/16.
 */
public class ComponentProductPrice extends ProductOfferingPrice {
    private String value;
    /**
     * @param name
     * @param validFor
     */
    public ComponentProductPrice(String name, TimePeriod validFor,String value) {
        super(name, validFor);
        this.value = value;
    }
}
