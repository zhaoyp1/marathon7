package com.digiwes.product.spec;

import java.util.*;

import com.digiwes.basetype.*;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A number or text that can be assigned to a ProductSpecCharacteristic.
 */
public class ProductSpecCharacteristicValue {
    private static final Logger logger = Logger.getLogger(ProductSpecCharacteristicValue.class);

    private List<ProdSpecCharValueRelationship> prodSpecCharValueRelationship = new ArrayList<ProdSpecCharValueRelationship>();
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * A discrete value that the characteristic can take on.
     */
    private String value;
    /**
     * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. Iin general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
     */
    private String unitOfMeasure;
    /**
     * The low range value that a characteristic can take on.
     */
    private String valueFrom;
    /**
     * The upper range value that a characteristic can take on.
     */
    private String valueTo;
    /**
     * An indicator that specifies the inclusion or exclusion of the valueFrom and valueTo attributes.
     * 
     * Possible values are "open", "closed", "closedBottom" and "closedTop".
     */
    private String rangeInterval;
    /**
     * The period of time for which a value is applicable.
     */
    private TimePeriod validFor;

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getValueFrom() {
        return this.valueFrom;
    }

    public void setValueFrom(String valueFrom) {
        this.valueFrom = valueFrom;
    }

    public String getValueTo() {
        return this.valueTo;
    }

    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }

    public String getRangeInterval() {
        return this.rangeInterval;
    }

    public void setRangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param valueType
     * @param isDefault
     * @param unitOfMeasure
     * @param validFor
     * @param value
     */
    public ProductSpecCharacteristicValue(String valueType, boolean isDefault, String unitOfMeasure, TimePeriod validFor, String value) {
        assert  !StringUtils.isEmpty(valueType):"valueType must not be null.";
        assert  !StringUtils.isEmpty(value):"value must not be null.";
        assert  !ParameterUtil.checkParameterIsNull(validFor):"valid must not be null";

        this.valueType = valueType;
        this.unitOfMeasure = unitOfMeasure;
        this.validFor = validFor;
        this.isDefault = isDefault;
        this.value = value;
    }

    /**
     * 
     * @param valueType
     * @param isDefault
     * @param unitOfMeasure
     * @param validFor
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public ProductSpecCharacteristicValue(String valueType, boolean isDefault, String unitOfMeasure, TimePeriod validFor, String valueFrom, String valueTo, String rangeInterval) {
        assert  !StringUtils.isEmpty(valueType):"valueType must not be null.";
        assert  !ParameterUtil.checkParameterIsNull(validFor):"valid must not be null";
        assert  !(StringUtils.isEmpty(valueFrom) && StringUtils.isEmpty(valueTo)):"valueFrom and valueTo must not be null at the same time.";
        assert  !StringUtils.isEmpty(valueFrom) :"valueFrom must not be null.";
        if(StringUtils.isEmpty(valueTo)){
            valueTo=valueFrom;
        }
        this.valueType = valueType;
        this.unitOfMeasure = unitOfMeasure;
        this.validFor = validFor;
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
        this.rangeInterval=rangeInterval;
    }

    /**
     * 
     * @param unitOfMeasure
     * @param value
     */
    public int specifyValue(String unitOfMeasure, String value) {
        // TODO - implement ProductSpecCharacteristicValue.specifyValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param unitOfMeasure
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public int specifyValue(String unitOfMeasure, String valueFrom, String valueTo, String rangeInterval) {
        // TODO - implement ProductSpecCharacteristicValue.specifyValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charValue
     * @param relationType
     * @param validFor
     */
    public int associate(ProductSpecCharacteristicValue charValue, String relationType, TimePeriod validFor) {
        if (ParameterUtil.checkParameterIsNull(relationType)) {
            logger.warn(" relationType must not be null.");
            return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_RELATIONSHIP_TYPE_IS_NULL.getCode();
        }
        if(this.equals(charValue)){
            logger.warn("can not create relationship whit itself.");
            return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_EQUALS_TO_CURRENT.getCode();
        }
        ProdSpecCharValueRelationship productSpecCharValueRelationShip = this.retrieveRelatedCharacteristicValue(charValue);
        if(null!=productSpecCharValueRelationShip){
            //compare
            if(productSpecCharValueRelationShip.getValidFor().isOverlap(validFor)){
                logger.warn("CharacteristicValue have been created in the specified time");
                return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_HAS_RELATED_TO_CURRENT.getCode();

            }
        }
        ProdSpecCharValueRelationship specCharValueRelationShip = new ProdSpecCharValueRelationship(this,charValue, relationType, validFor);
        this.prodSpecCharValueRelationship.add( specCharValueRelationShip );
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param charValue
     */
    public int dissociate(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharacteristicValue.dissociate
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param relationType
     */
    public List<ProductSpecCharacteristicValue> retrieveRelatedCharValue(String relationType) {
        ParameterUtil.checkParameterIsNulForException(relationType, "relationType");
        List<ProductSpecCharacteristicValue> prodSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
        if ( null != prodSpecCharValueRelationship  && prodSpecCharValueRelationship.size() > 0) {
            for (ProdSpecCharValueRelationship relationship : prodSpecCharValueRelationship) {
                if ( null != relationship.getCharValueRelationshipType() && relationType.equals(relationship.getCharValueRelationshipType())) {
                    prodSpecCharValues.add(relationship.getProductSpecCharacteristicValue());
                }
            }
        }
        return prodSpecCharValues;
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public List<ProductSpecCharacteristicValue> retrieveRelatedCharValue(String relationType, Date time) {
        ParameterUtil.checkParameterIsNulForException(relationType, "relationType");
        ParameterUtil.checkParameterIsNulForException(time, "time");
        List<ProductSpecCharacteristicValue> prodSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
        if ( null != prodSpecCharValueRelationship  && prodSpecCharValueRelationship.size() > 0) {
            for (ProdSpecCharValueRelationship relationship : prodSpecCharValueRelationship) {
                if (relationship.getCharValueRelationshipType() != null
                        && relationType.equals(relationship.getCharValueRelationshipType())
                        && (relationship.getValidFor() == null ||   relationship.getValidFor().isInTimePeriod(time))) {
                    prodSpecCharValues.add(relationship.getProductSpecCharacteristicValue());
                }
            }
        }
        return prodSpecCharValues;
    }

    /**
     * 
     * @param charValue
     */
    private ProdSpecCharValueRelationship retrieveRelatedCharacteristicValue(ProductSpecCharacteristicValue charValue ){
        ParameterUtil.checkParameterIsNulForException(charValue,"ProductSpecCharacteristicValue");
        if (null != this.prodSpecCharValueRelationship) {
            for (ProdSpecCharValueRelationship productSpecCharRelationship : prodSpecCharValueRelationship) {
                if ( productSpecCharRelationship.getProductSpecCharacteristicValue().equals(charValue)) {
                    return productSpecCharRelationship;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecCharacteristicValue that = (ProductSpecCharacteristicValue) o;

        if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (unitOfMeasure != null ? !unitOfMeasure.equals(that.unitOfMeasure) : that.unitOfMeasure != null)
            return false;
        if (valueFrom != null ? !valueFrom.equals(that.valueFrom) : that.valueFrom != null) return false;
        if (valueTo != null ? !valueTo.equals(that.valueTo) : that.valueTo != null) return false;
        if (rangeInterval != null ? !rangeInterval.equals(that.rangeInterval) : that.rangeInterval != null)
            return false;
        return !(validFor != null ? !validFor.equals(that.validFor) : that.validFor != null);

    }

    @Override
    public int hashCode() {
        int result = valueType != null ? valueType.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (unitOfMeasure != null ? unitOfMeasure.hashCode() : 0);
        result = 31 * result + (valueFrom != null ? valueFrom.hashCode() : 0);
        result = 31 * result + (valueTo != null ? valueTo.hashCode() : 0);
        result = 31 * result + (rangeInterval != null ? rangeInterval.hashCode() : 0);
        result = 31 * result + (validFor != null ? validFor.hashCode() : 0);
        return result;
    }

    /**
     * Basic information of the class output into the Map
     */
    protected Map getBasicInfo() {
        // TODO - implement ProductSpecCharacteristicValue.getBasicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * Basic info of the class ouput to String
     */
    public String basicInfoToString() {
        // TODO - implement ProductSpecCharacteristicValue.basicInfoToString
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement ProductSpecCharacteristicValue.toString
        throw new UnsupportedOperationException();
    }

}
