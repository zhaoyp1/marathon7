package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

public class RetiredOfferingRequest {

    public TimePeriod validFor;
    public String catalogId;
    public String prodOfferingId;

    public String getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

}