package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;

public class ProductCatalog {

    public TimePeriod validFor;
    public String id;
    public String name;
    public String type;
    public String href;
    public void convertFromProductCatalog(com.digiwes.product.offering.catalog.ProductCatalog catalog){
       if( null != catalog){
           this.id = catalog.getID();
           this.name = catalog.getName();
           this.validFor = catalog.getValidFor();
           this.href = "http://localhost:8080/marathon/catalog/"+catalog.getID();
       }
    }

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}