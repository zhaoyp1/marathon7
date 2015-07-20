package com.digiwes.product.spec;

import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;
import org.apache.commons.lang.StringUtils;

public class ProdSpecCharValueRelationship {

    private ProductSpecCharacteristicValue sourceCharValue;
    private ProductSpecCharacteristicValue productSpecCharacteristicValue;
    /**
     * A categorization of the relationship between values, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charValueRelationshipType;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecCharacteristicValue getProductSpecCharacteristicValue() {
        return productSpecCharacteristicValue;
    }

    public ProductSpecCharacteristicValue getSourceCharValue() {
        return sourceCharValue;
    }

    public String getCharValueRelationshipType() {
        return this.charValueRelationshipType;
    }

    public void setCharValueRelationshipType(String charValueRelationshipType) {
        this.charValueRelationshipType = charValueRelationshipType;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srourceCharValue
     * @param targetCharValue
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(ProductSpecCharacteristicValue srourceCharValue, ProductSpecCharacteristicValue targetCharValue, String relationType, TimePeriod validFor) {
        assert !ParameterUtil.checkParameterIsNull(srourceCharValue):"sourceCharValue must not be null";
        assert !ParameterUtil.checkParameterIsNull(targetCharValue):"targetCharValue must not be null";
        assert !StringUtils.isEmpty(relationType):"relationType must not be null";
        assert !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";
        this.sourceCharValue = srourceCharValue;
        this.productSpecCharacteristicValue =targetCharValue;
        this.charValueRelationshipType = relationType ;
        this.validFor = validFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSpecCharValueRelationship that = (ProdSpecCharValueRelationship) o;

        if (!sourceCharValue.equals(that.sourceCharValue)) return false;
        if (!productSpecCharacteristicValue.equals(that.productSpecCharacteristicValue)) return false;
        if (!charValueRelationshipType.equals(that.charValueRelationshipType)) return false;
        return validFor.equals(that.validFor);

    }

    @Override
    public int hashCode() {
        int result = sourceCharValue.hashCode();
        result = 31 * result + productSpecCharacteristicValue.hashCode();
        result = 31 * result + charValueRelationshipType.hashCode();
        result = 31 * result + validFor.hashCode();
        return result;
    }

    public String toString() {
        // TODO - implement ProdSpecCharValueRelationship.toString
        throw new UnsupportedOperationException();
    }

}