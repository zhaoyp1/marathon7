package com.digiwes.product.resource.Parameter;

import com.digiwes.product.resource.utils.DateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

public class RetrieveOfferingRequest {

    private String offeringName;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date time;
    private String catalogId;

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}