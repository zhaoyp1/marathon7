package com.digiwes.product.resource.Parameter;

import java.util.*;

public class PublishOfferingResponse {


    private String code;
    private String message;
    private List<PublishedOffering> publishedOffering;


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

    public List<PublishedOffering> getPublishedOffering() {
        return publishedOffering;
    }

    public void setPublishedOffering(List<PublishedOffering> publishedOffering) {
        this.publishedOffering = publishedOffering;
    }
}