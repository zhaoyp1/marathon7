package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;

/**
 * A migration, substitution, dependency, or exclusivity relationship between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    private ProductSpecification targetProdSpec;
    private ProductSpecification sourceSpec;

    public void setType(String type) {
        this.type = type;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductSpecification getTargetProdSpec() {
        return targetProdSpec;
    }

    public ProductSpecification getSourceSpec() {
        return sourceSpec;
    }

    public String getType() {
        return type;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     * A categorization of the relationship, such as migration, substitution, dependency, exclusivity.
     */
    private String type;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    /**
     * 
     * @param sourceSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification sourceSpec, ProductSpecification targetSpec, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecificationRelationship.ProductSpecificationRelationship
        throw new UnsupportedOperationException();
    }

    public int hashCode() {
        // TODO - implement ProductSpecificationRelationship.hashCode
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param o
     */
    public boolean equals(Object o) {
        // TODO - implement ProductSpecificationRelationship.equals
        throw new UnsupportedOperationException();
    }

}