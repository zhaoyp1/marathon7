package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.ProdOfferingEnum;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.offering.price.ProductOfferingPrice;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The presentation of one or more ProductSpecifications to the marketplace for sale, rental, or lease for a ProductOfferingPrice. A ProductOffering may target one or more MarketSegments, be included in one or more ProductCatalog, presented in support of one or more ProductStrategies, and made available in one or more Places. ProductOffering may represent a simple offering of a single ProductSpecification or could represent a bundling of one or more other ProductOffering.
 */
public abstract class ProductOffering{

    private List<ProductOfferingPrice> productOfferingPrice = new ArrayList<ProductOfferingPrice>();
    private List<ProductOfferingRelationship> prodOfferingRelationship = new ArrayList<ProductOfferingRelationship>();
    /**
     * A unique identifier for the ProductOffering.
     */
    private String id;
    /**
     * A word, term, or phrase by which the ProductOffeirng is known and distinguished from other ProductOfferings.
     */
    private String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;
    /**
     * The period during which the offering is applicable.
     */
    private TimePeriod validFor;
    /**
     * The condition in which the offering exists, such as planned, obsolete, active
     */
    private String status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getStatus() {
        return this.status;
    }

    public List<ProductOfferingRelationship> getProdOfferingRelationship() {
        return prodOfferingRelationship;
    }

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public ProductOffering(String id, String name, String description, TimePeriod validFor) {
        assert !StringUtils.isEmpty(id) : " id must not be null or empty .";
        assert !StringUtils.isEmpty(name) : " name must not be null or empty .";
        assert !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";
        this.id = id;
        this.name = name;
        this.description = description;
        this.validFor = validFor;
        this.status = ProdOfferingEnum.ProductOfferingStatus.ACTIVE.getValue();//TODO need modify
    }

    /**
     * 
     * @param offering
     * @param relationType
     * @param validFor
     */
    public BusinessCode associate(ProductOffering offering, String relationType, TimePeriod validFor) {
        if(ParameterUtil.checkParameterIsNull(offering)){
            return BusinessCode.PROD_OFFERING_IS_NULL;
        }
        if(StringUtils.isEmpty(relationType)){
            return BusinessCode.PROD_OFFERING_RELATIONSHIP_TYPE_IS_NULL_OR_EMPTY;
        }
        if(ParameterUtil.checkParameterIsNull(validFor)){
            return BusinessCode.PROD_OFFERING_VALIDPERIOD_IS_NULL;
        }
        if(this.equals(offering)){
            return BusinessCode.PROD_OFFERING_ASSOCIATE_WITH_ITSELF;
        }
        ProductOfferingRelationship offeringRelationship = new ProductOfferingRelationship(this,offering,relationType,validFor);
        if(this.prodOfferingRelationship.contains(offeringRelationship)){
            return BusinessCode.PROD_OFFERING_RELATIONSHIP_EXISTED;
        }
        this.prodOfferingRelationship.add(offeringRelationship);
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param offering
     */
    public int dissociate(ProductOffering offering) {
        // TODO - implement ProductOffering.dissociate
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param relationType
     */
    public List<ProductOffering> retrieveRelatedOffering(String relationType) {
        List<ProductOffering> offeringList = new ArrayList<ProductOffering>();
        if(ParameterUtil.checkParameterIsNull(relationType)){
            return offeringList;
        }
        for(ProductOfferingRelationship relationship : this.prodOfferingRelationship){
            if(relationType.equals( relationship.getTypeRelationship())){
                offeringList.add(relationship.getTargetOffering());
            }
        }
        return offeringList;
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public List<ProductOffering> retrieveRelatedOffering(String relationType, Date time) {
        ParameterUtil.checkParameterIsNulForException(time, "time");
        List<ProductOffering> offeringList = new ArrayList<ProductOffering>();
        if(ParameterUtil.checkParameterIsNull(relationType)){
            return offeringList;
        }
        for(ProductOfferingRelationship relationship : this.prodOfferingRelationship){
            if(relationType.equals( relationship.getTypeRelationship()) && relationship.getValidFor().isInTimePeriod(time)){
                offeringList.add(relationship.getTargetOffering());
            }
        }
        return offeringList;
    }

    /**
     * 
     * @param price
     */
    public int specifyPrice(ProductOfferingPrice price) {
        // TODO - implement ProductOffering.specifyPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    private int removePrice(ProductOfferingPrice price) {
        // TODO - implement ProductOffering.removePrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param newPrice
     */
    public void alterPrice(ProductOfferingPrice[] newPrice) {
        // TODO - implement ProductOffering.alterPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductOfferingPrice[] retrievePrice(Date time) {
        // TODO - implement ProductOffering.retrievePrice
        throw new UnsupportedOperationException();
    }

    /**
     * Basic information of the class output into the Map
     */
    protected Map getBasicInfo() {
        // TODO - implement ProductOffering.getBasicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * Basic info of the class ouput to String
     */
    public String basicInfoToString() {
        // TODO - implement ProductOffering.basicInfoToString
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;

        ProductOffering that = (ProductOffering) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}