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

}