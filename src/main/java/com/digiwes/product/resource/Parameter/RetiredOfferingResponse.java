package com.digiwes.product.resource.Parameter;

import java.util.*;

public class RetiredOfferingResponse {

    public String code;
    public String message;
    public List<PublishedOffering> publishedOffering;

    public List<PublishedOffering> getPublishedOffering() {
        return publishedOffering;
    }

    public void setPublishedOffering(List<PublishedOffering> publishedOffering) {
        this.publishedOffering = publishedOffering;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}