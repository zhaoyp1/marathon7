package com.digiwes.product.offering;

import com.digiwes.basetype.*;
import com.digiwes.common.utils.ParameterUtil;
import org.apache.commons.lang.StringUtils;

/**
 * A significant connection or similarity between two or more ProductOfferings. For example, the relationship between a provider's ProductOffering and a supplier/partner's ProductOffering used to fulfill the provider's ProductOffering; a service provider offers various photos for download and printing...a print shop prints them for the provider and considers one photo (ProductOffering) the same as any other from a pricing perspective...one partners' photo offering is related to many of the provider's photos.
 */
public class ProductOfferingRelationship {

    private ProductOffering targetOffering;

    private ProductOffering sourceOffering;
    /**
     * A categorization of the relationship, such as supplier/partner equivalent, alternate, and so forth.
     */
    private String typeRelationship;
    /**
     * The period during which the relationship is applicable.
     */
    private TimePeriod validFor;

    public String getTypeRelationship() {
        return this.typeRelationship;
    }

    public void setTypeRelationship(String typeRelationship) {
        this.typeRelationship = typeRelationship;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductOffering getSourceOffering() {
        return sourceOffering;
    }

    public ProductOffering getTargetOffering() {
        return targetOffering;
    }

    /**
     * 
     * @param sourceOffering
     * @param targetOffering
     * @param type
     * @param validFor
     */
    public ProductOfferingRelationship(ProductOffering sourceOffering, ProductOffering targetOffering, String type, TimePeriod validFor) {
        assert  !ParameterUtil.checkParameterIsNull(sourceOffering):"sourceOffering must not be null .";
        assert  !ParameterUtil.checkParameterIsNull(targetOffering):"targetOffering must not be null .";
        assert  !StringUtils.isEmpty(type):"type must not be null .";
        assert  !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";
        this.sourceOffering = sourceOffering;
        this.targetOffering = targetOffering;
        this.typeRelationship = type;
        this.validFor = validFor;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOfferingRelationship that = (ProductOfferingRelationship) o;

        if (!targetOffering.equals(that.targetOffering)) return false;
        if (!sourceOffering.equals(that.sourceOffering)) return false;
        if (!typeRelationship.equals(that.typeRelationship)) return false;
        return validFor.equals(that.validFor);

    }

    @Override
    public int hashCode() {
        int result = targetOffering.hashCode();
        result = 31 * result + sourceOffering.hashCode();
        result = 31 * result + typeRelationship.hashCode();
        result = 31 * result + validFor.hashCode();
        return result;
    }

    public String toString() {
        // TODO - implement ProductOfferingRelationship.toString
        throw new UnsupportedOperationException();
    }

}