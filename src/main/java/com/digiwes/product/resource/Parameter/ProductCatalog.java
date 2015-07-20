package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

public class ProductCatalog {

    public TimePeriod validFor;
    public String id;
    public String name;
    public String type;
    public int href;

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHref() {
        return href;
    }

    public void setHref(int href) {
        this.href = href;
    }
}