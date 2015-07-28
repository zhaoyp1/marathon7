/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: huxw
 * License Type: Purchased
 */
package com.digiwes.product.resource.response;

import com.digiwes.basetype.TimePeriod;

public class ProductOfferingPrice {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  private String priceType;

  public String getPriceType() {
    return priceType;
  }

  public void setPriceType(String value) {
    this.priceType = value;
  }

  private String unitOfMeasure;

  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String value) {
    this.unitOfMeasure = value;
  }

  private String recurringChargePeriod;

  public String getRecurringChargePeriod() {
    return recurringChargePeriod;
  }

  public void setRecurringChargePeriod(String value) {
    this.recurringChargePeriod = value;
  }

  private String version;

  public String getVersion() {
    return version;
  }

  public void setVersion(String value) {
    this.version = value;
  }

  private ProductOfferPriceAlteration productOfferPriceAlteration;

  public ProductOfferPriceAlteration getProductOfferPriceAlteration() {
    return productOfferPriceAlteration;
  }

  public void setProductOfferPriceAlteration(ProductOfferPriceAlteration value) {
    this.productOfferPriceAlteration = value;
  }

  private Rate rate;

  public Rate getRate() {
    return rate;
  }

  public void setRate(Rate value) {
    this.rate = value;
  }

  private TimePeriod validFor;

  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod value) {
    this.validFor = value;
  }

}