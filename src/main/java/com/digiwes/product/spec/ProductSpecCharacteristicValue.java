package com.digiwes.product.spec;

import java.util.*;
import com.digiwes.basetype.*;

/**
 * A number or text that can be assigned to a ProductSpecCharacteristic.
 */
public class ProductSpecCharacteristicValue {

    public List<ProdSpecCharValueRelationship> prodSpecCharValueRelationship;
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
        // TODO - implement ProductSpecCharacteristicValue.ProductSpecCharacteristicValue
        throw new UnsupportedOperationException();
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
        // TODO - implement ProductSpecCharacteristicValue.ProductSpecCharacteristicValue
        throw new UnsupportedOperationException();
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
        // TODO - implement ProductSpecCharacteristicValue.associate
        throw new UnsupportedOperationException();
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
    public ProductSpecCharacteristicValue[] retrieveRelatedCharValue(String relationType) {
        // TODO - implement ProductSpecCharacteristicValue.retrieveRelatedCharValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public ProductSpecCharacteristicValue[] retrieveRelatedCharValue(String relationType, int time) {
        // TODO - implement ProductSpecCharacteristicValue.retrieveRelatedCharValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charValue
     */
    private ProdSpecCharValueRelationship retrieveCharValueRelationship(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharacteristicValue.retrieveCharValueRelationship
        throw new UnsupportedOperationException();
    }

    public int hashCode() {
        // TODO - implement ProductSpecCharacteristicValue.hashCode
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param o
     */
    public boolean equals(int o) {
        // TODO - implement ProductSpecCharacteristicValue.equals
        throw new UnsupportedOperationException();
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