package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;

import java.util.*;

public class PublishedOffering {

    public ProductOffering productOffering;
    public TimePeriod validFor;
    public ProductCatalog productCatalog;
   // public  List<ProductOfferingPrice> advicePrice;
    public String id;
    public String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
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

    public void  convertFromProdCatalogProdOffeing(ProdCatalogProdOffer prodCatalogProdOffer){
       if( null !=prodCatalogProdOffer){
           validFor = prodCatalogProdOffer.getValidFor();
           if( null != prodCatalogProdOffer.getProdOffering()) {
               ProductOffering offering =new ProductOffering();
               offering.convertFromProductOffering(prodCatalogProdOffer.getProdOffering());
               this.productOffering = offering;
           }

       }
    }
    
}