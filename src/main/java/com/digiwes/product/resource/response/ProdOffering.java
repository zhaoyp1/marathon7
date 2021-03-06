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

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class ProdOffering {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String value) {
    this.id = value;
  }

  private String href;

  public String getHref() {
    return href;
  }

  public void setHref(String value) {
    this.href = value;
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  private String version;

  public String getVersion() {
    return version;
  }

  public void setVersion(String value) {
    this.version = value;
  }

  private String lastUpdate;

  public String getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(String value) {
    this.lastUpdate = value;
  }

  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  private boolean isBunlde;

  public boolean getIsBunlde() {
    return isBunlde;
  }

  public void setIsBunlde(boolean value) {
    this.isBunlde = value;
  }

  private String lifecycleStatus;

  public String getLifecycleStatus() {
    return lifecycleStatus;
  }

  public void setLifecycleStatus(String value) {
    this.lifecycleStatus = value;
  }

  private List<ChannelRef > channel = new ArrayList<ChannelRef>();
  @XmlElement
  public List<ChannelRef> getChannel() {
    return channel;
  }

  public void setChannel(List<ChannelRef > value) {
    this.channel = value;
  }

  private ProductSpecificationRef productSpecification;

  public boolean isBunlde() {
    return isBunlde;
  }

  public ProductSpecificationRef getProductSpecification() {
    return productSpecification;
  }

  public void setProductSpecification(ProductSpecificationRef productSpecification) {
    this.productSpecification = productSpecification;
  }

  private List<PlaceRef> place = new ArrayList<PlaceRef>();
  @XmlElement
  public List<PlaceRef> getPlace() {
    return place;
  }

  public void setPlace(List<PlaceRef> value) {
    this.place = value;
  }

  private ServiceCandidateRef serviceCandidate;

  public ServiceCandidateRef getServiceCandidate() {
    return serviceCandidate;
  }

  public void setServiceCandidate(ServiceCandidateRef value) {
    this.serviceCandidate = value;
  }

  private ServiceLevelAgreementRef serviceLevelAgreement;

  public ServiceLevelAgreementRef getServiceLevelAgreement() {
    return serviceLevelAgreement;
  }

  public void setServiceLevelAgreement(ServiceLevelAgreementRef value) {
    this.serviceLevelAgreement = value;
  }

  private List<BundledProductOffering> bundledProductOffering =new ArrayList<BundledProductOffering>();
  @XmlElement
  public List<BundledProductOffering> getBundledProductOffering() {
    return bundledProductOffering;
  }

  public void setBundledProductOffering(List<BundledProductOffering> value) {
    this.bundledProductOffering = value;
  }

  private ResourceCandidateRef resourceCandidate ;

  public ResourceCandidateRef getResourceCandidate() {
    return resourceCandidate;
  }

  public void setResourceCandidate(ResourceCandidateRef value) {
    this.resourceCandidate = value;
  }

  private List<ProductOfferingTerm> productOfferingTerm =new ArrayList<ProductOfferingTerm>();
  @XmlElement
  public List<ProductOfferingTerm> getProductOfferingTerm() {
    return productOfferingTerm;
  }

  public void setProductOfferingTerm(List<ProductOfferingTerm> value) {
    this.productOfferingTerm = value;
  }

  private TimePeriod validFor;

  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod value) {
    this.validFor = value;
  }

  private List<CategoryRef> category =new ArrayList<CategoryRef>();
  @XmlElement
  public List<CategoryRef> getCategory() {
    return category;
  }

  public void setCategory(List<CategoryRef> value) {
    this.category = value;
  }

  private List<ProductOfferingPrice> productOfferingPrice =new ArrayList<ProductOfferingPrice>();
  @XmlElement
  public List<ProductOfferingPrice> getProductOfferingPrice() {
    return productOfferingPrice;
  }

  public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
    this.productOfferingPrice = productOfferingPrice;
  }
}