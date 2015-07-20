package com.digiwes.product.resource.Parameter;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;

import java.util.*;

public class PublishedOffering {

    public TimePeriod validFor;
    public ProductOffering productOffering;
    public ProductCatalog existInProdCatalog;
   // public  List<ProductOfferingPrice> advicePrice;

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

    public ProductCatalog getExistInProdCatalog() {
        return existInProdCatalog;
    }

    public void setExistInProdCatalog(ProductCatalog existInProdCatalog) {
        this.existInProdCatalog = existInProdCatalog;
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