package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class PublishOfferingRequest {


    //public  List<ProductOfferingPrice> advicePrice;
    public String catalogId;
    public TimePeriod validFor;
    public String prodOfferingId;

    public String getCatalogId() {
        return this.catalogId;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getProdOfferingId() {
        return prodOfferingId;
    }

    public void setProdOfferingId(String prodOfferingId) {
        this.prodOfferingId = prodOfferingId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

}