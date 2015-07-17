package com.digiwes.product.spec;

import com.digiwes.basetype.*;
import com.digiwes.common.utils.ParameterUtil;

/**
 * A use of the ProdSpecCharacteristicValue by an ProductSpecification to which additional properties (attributes) apply or override the properties of similar properties contained in ProdSpecCharacteristicValue.
 */
public class ProdSpecCharValueUse {

    private ProductSpecCharacteristicValue prodSpecCharValue;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * The period of time for which the use of the CharacteristicSpecificationValue is applicable.
     */
    private TimePeriod validFor;

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductSpecCharacteristicValue getProdSpecCharValue() {
        return prodSpecCharValue;
    }

    public boolean isDefault() {
        return isDefault;
    }

    /**
     * 
     * @param charVal
     * @param isDefault
     * @param validFor
     */
    public ProdSpecCharValueUse(ProductSpecCharacteristicValue charVal, boolean isDefault, TimePeriod validFor) {
        assert  !ParameterUtil.checkParameterIsNull(charVal) :"charValue must not be null";
        assert  !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";

        this.prodSpecCharValue = charVal;
        this.isDefault = isDefault;
        this.validFor = validFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSpecCharValueUse that = (ProdSpecCharValueUse) o;

        if (prodSpecCharValue != null ? !prodSpecCharValue.equals(that.prodSpecCharValue) : that.prodSpecCharValue != null)
            return false;
        return !(validFor != null ? !validFor.equals(that.validFor) : that.validFor != null);

    }

    @Override
    public int hashCode() {
        int result = prodSpecCharValue != null ? prodSpecCharValue.hashCode() : 0;
        result = 31 * result + (validFor != null ? validFor.hashCode() : 0);
        return result;
    }

    public String toString() {
        // TODO - implement ProdSpecCharValueUse.toString
        throw new UnsupportedOperationException();
    }

}