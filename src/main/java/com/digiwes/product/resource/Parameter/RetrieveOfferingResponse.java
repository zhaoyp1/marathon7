package com.digiwes.product.resource.Parameter;

import java.util.ArrayList;
import java.util.List;

public class RetrieveOfferingResponse {

    private String code;
    private String message;
    private List<PublishedOffering> publishedOfferings = new ArrayList<PublishedOffering>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PublishedOffering> getPublishedOfferings() {
        return publishedOfferings;
    }

    public void setPublishedOfferings(List<PublishedOffering> publishedOfferings) {
        this.publishedOfferings = publishedOfferings;
    }
}