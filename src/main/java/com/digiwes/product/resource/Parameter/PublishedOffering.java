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