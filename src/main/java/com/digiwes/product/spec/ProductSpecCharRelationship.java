package com.digiwes.product.spec;

import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;

/**
 * A aggregation, migration, substitution, dependency, or exclusivity relationship between/among ProductSpecCharacteristics.
 */
public class ProductSpecCharRelationship {

    private ProductSpecCharacteristic targetProdSpecChar;
    private ProductSpecCharacteristic sourceProdSpecChar;
    /**
     * A categorization of the relationship, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charRelationshipType;
    /**
     * The order in which a CharacteristicSpecification appears within another CharacteristicSpecification that defines a grouping of CharacteristicSpecifications.
     * 
     * For example, a grouping may represent the name of an individual. The given name is first, the middle name is second, and the last name is third.
     */
    private int charSpecSeq;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public String getCharRelationshipType() {
        return this.charRelationshipType;
    }

    public void setCharRelationshipType(String charRelationshipType) {
        this.charRelationshipType = charRelationshipType;
    }

    public int getCharSpecSeq() {
        return this.charSpecSeq;
    }

    public void setCharSpecSeq(int charSpecSeq) {
        this.charSpecSeq = charSpecSeq;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductSpecCharacteristic getTargetProdSpecChar() {
        return targetProdSpecChar;
    }

    /**
     *
     * @param sourceSpecChar
     * @param targetSpecChar
     * @param relationType
     * @param validFor
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic sourceSpecChar, ProductSpecCharacteristic targetSpecChar, String relationType, TimePeriod validFor) {
        assert !ParameterUtil.checkParameterIsNull(sourceSpecChar):"sourceSpecChar must not be null.";
        assert !ParameterUtil.checkParameterIsNull(targetSpecChar):"targetSpecChar must not be null.";
        assert !sourceSpecChar.equals(targetSpecChar):"the designated sourceSpecChar equal to targetSpecChar.";
        this.sourceProdSpecChar = sourceSpecChar;
        this.targetProdSpecChar = targetSpecChar;
        this.charRelationshipType = relationType;
        this.validFor = validFor;
    }

    /**
     *
     * @param sourceSpecChar
     * @param targetSpecChar
     * @param relationType
     * @param validFor
     * @param specSeq
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic sourceSpecChar, ProductSpecCharacteristic targetSpecChar, String relationType, TimePeriod validFor, int specSeq) {
        this(sourceSpecChar, targetSpecChar, relationType, validFor);
        this.charSpecSeq = specSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecCharRelationship that = (ProductSpecCharRelationship) o;

        if (targetProdSpecChar != null ? !targetProdSpecChar.equals(that.targetProdSpecChar) : that.targetProdSpecChar != null)
            return false;
        if (charRelationshipType != null ? !charRelationshipType.equals(that.charRelationshipType) : that.charRelationshipType != null)
            return false;
        return !(validFor != null ? !validFor.equals(that.validFor) : that.validFor != null);

    }

    @Override
    public int hashCode() {
        int result = targetProdSpecChar != null ? targetProdSpecChar.hashCode() : 0;
        result = 31 * result + (charRelationshipType != null ? charRelationshipType.hashCode() : 0);
        result = 31 * result + (validFor != null ? validFor.hashCode() : 0);
        return result;
    }

    public String toString() {
        // TODO - implement ProductSpecCharRelationship.toString
        throw new UnsupportedOperationException();
    }

}