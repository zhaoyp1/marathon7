package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

import java.util.*;

public class PublishOfferingRequest {

    public TimePeriod validFor;
    public  List<ProductOfferingPrice> advicePrice;
    public String catalogId;
    public String prodOfferingId;

    public String getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

}