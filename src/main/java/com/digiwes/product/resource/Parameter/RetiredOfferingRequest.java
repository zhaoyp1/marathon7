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


    public String getProdOfferingId() {
        return prodOfferingId;
    }

    public void setProdOfferingId(String prodOfferingId) {
        this.prodOfferingId = prodOfferingId;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }
}