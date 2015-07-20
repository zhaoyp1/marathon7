package com.digiwes.product.resource.Parameter;

public class ProductOffering {

    public String id;
    public String name;
    /**
     * The condition in which the offering exists, such as planned, obsolete, active
     */
    public String status;
    /**
     * A narrative that explains what the offering is.
     */
    public String description;
    public String href;

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void convertFromProductOffering(com.digiwes.product.offering.ProductOffering offering){
       if( null != offering){
        this.id = offering.getId();
        this.name = offering.getName();
        this.status =offering.getStatus();
        this.href ="http://localhost:8080/marathon/ProductOffering/"+offering.getId();
        this.description =offering.getDescription();
       }
    }

}