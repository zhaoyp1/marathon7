package com.digiwes.product.offering.catalog;

import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.offering.*;
import java.util.*;
import com.digiwes.product.offering.price.*;
import com.digiwes.basetype.*;

/**
 * The appearance of a ProductOffering in a ProductCatalog.
 */
public class ProdCatalogProdOffer {

    private ProductOffering prodOffering;
    private List<ProductOfferingPrice> productOfferingPrice;
    /**
     * The period during which the ProductOffering appears in the ProductCatalog.
     */
    private TimePeriod validFor;

    public ProductOffering getProdOffering() {
        return prodOffering;
    }

    public List<ProductOfferingPrice> getProductOfferingPrice() {
        return productOfferingPrice;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {
        assert !ParameterUtil.checkParameterIsNull(offering):"ProductOffering must not be null";
        assert !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";

        this.prodOffering = offering;
        this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
         this(offering,validFor);
         this.productOfferingPrice = price;
    }

    /**
     * 
     * @param price
     */
    public int specifyPrice(ProductOfferingPrice price) {
        // TODO - implement ProdCatalogProdOffer.specifyPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param newPrice
     */
    public int alterPrice(ProductOfferingPrice[] newPrice) {
        // TODO - implement ProdCatalogProdOffer.alterPrice
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdCatalogProdOffer that = (ProdCatalogProdOffer) o;

        if (!prodOffering.equals(that.prodOffering)) return false;
        return validFor.equals(that.validFor);

    }

    @Override
    public int hashCode() {
        int result = prodOffering.hashCode();
        result = 31 * result + validFor.hashCode();
        return result;
    }

    //public String toString() {
        // TODO - implement ProdCatalogProdOffer.toString
       // throw new UnsupportedOperationException();
   // }

}