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

}