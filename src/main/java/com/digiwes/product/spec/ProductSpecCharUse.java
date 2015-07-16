package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecCharUse {
    private static final Logger logger = Logger.getLogger(ProductSpecCharUse.class);

    private ProductSpecCharacteristic prodSpecChar;
    private List<ProdSpecCharValueUse> prodSpecCharValue = new ArrayList<ProdSpecCharValueUse>();
    /**
     * A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     */
    private String name;
    /**
     * A narrative that explains the CharacteristicSpecification.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * An indicator that specifies if the associated CharacteristicSpecification is a composite.
     */
    private boolean isPackage;
    /**
     * An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     */
    private boolean canBeOveridden;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     */
    private boolean extensible;

    private TimePeriod validFor;

    public boolean isPackage() {
        return isPackage;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductSpecCharacteristic getProdSpecChar() {
        return this.prodSpecChar;
    }

    public void setProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
        this.prodSpecChar = prodSpecChar;
    }

    public List<ProdSpecCharValueUse> getProdSpecCharValue() {
        return this.prodSpecCharValue;
    }

    public void setProdSpecCharValue(List<ProdSpecCharValueUse> prodSpecCharValue) {
        this.prodSpecCharValue = prodSpecCharValue;
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

    public String getUnique() {
        return this.unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public boolean isIsPackage() {
        return this.isPackage;
    }

    public void setIsPackage(boolean isPackage) {
        this.isPackage = isPackage;
    }

    public boolean isCanBeOveridden() {
        return this.canBeOveridden;
    }

    public void setCanBeOveridden(boolean canBeOveridden) {
        this.canBeOveridden = canBeOveridden;
    }

    public int getMinCardinality() {
        return this.minCardinality;
    }

    public void setMinCardinality(int minCardinality) {
        this.minCardinality = minCardinality;
    }

    public int getMaxCardinality() {
        return this.maxCardinality;
    }

    public void setMaxCardinality(int maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public boolean isExtensible() {
        return this.extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }

    /**
     * 
     * @param specChar
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     * @param name
     */
    public ProductSpecCharUse(ProductSpecCharacteristic specChar,String name, boolean canBeOveridden, boolean isPackage, TimePeriod validFor ) {
        assert !ParameterUtil.checkParameterIsNull(specChar) :"specChar must not be null";
        assert !StringUtils.isEmpty(name) : "name must not be null";
        this.name = name;
        this.prodSpecChar =specChar;
        this.canBeOveridden=canBeOveridden;
        this.isPackage = isPackage;
        this.validFor = validFor;
    }

    /**
     * 
     * @param specChar
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     * @param name
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     */
    public ProductSpecCharUse(ProductSpecCharacteristic specChar, String name,boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        this(specChar, name, canBeOveridden, isPackage, validFor);
        this.unique = unique;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public int specifyCardinality(int minCardinality, int maxCardinality) {
        if(minCardinality > maxCardinality){
            logger.error("maxCardinality is less than minCardinality.");
            return ProdSpecErrorCode.PROD_SPEC_CHAR_MAX_LESS_THAN_MAX.getCode();
        }
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param charValue
     * @param isDefault
     * @param validFor
     */
    public int assignValue(ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        if(ParameterUtil.checkParameterIsNull(charValue)){
            return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode();
        }
        if(null != this.prodSpecChar.getProdSpecCharValue() && !this.prodSpecChar.getProdSpecCharValue().contains(charValue)){
            return ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode();
        }
        ProdSpecCharValueUse prodSpecCharValueUse = new ProdSpecCharValueUse(charValue, isDefault, validFor);
        this.prodSpecCharValue.add(prodSpecCharValueUse);
        return CommonErrorCode.SUCCESS.getCode();
    }

    /**
     * 
     * @param charValue
     */
    public int removeValue(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharUse.removeValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param defaultValue
     */
    public int specifyDefaultCharacteristicValue(ProductSpecCharacteristicValue defaultValue) {
        if(ParameterUtil.checkParameterIsNull(defaultValue)){
            return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode();
        }
        if(null != this.prodSpecCharValue){
            if(null != this.prodSpecChar.getProdSpecCharValue() && !this.prodSpecChar.getProdSpecCharValue().contains(defaultValue)){
                return ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode();
            }
            for(ProdSpecCharValueUse prodSpecCharValueUse : this.prodSpecCharValue){
                if(prodSpecCharValueUse.getProdSpecCharValue().equals(defaultValue)){
                    prodSpecCharValueUse.setIsDefault(true);
                    return CommonErrorCode.SUCCESS.getCode();
                }
            }

        }
        logger.warn("this value is not belong to current char.");
        return ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode();
    }

    public List<ProdSpecCharValueUse> retrieveDefaultValueUse() {
        // TODO - implement ProductSpecCharUse.retrieveDefaultValueUse
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param defaultValue
     */
    public int clearDefaultValueUse(ProductSpecCharacteristicValue defaultValue) {
        if(ParameterUtil.checkParameterIsNull(defaultValue)){
            return ProdSpecErrorCode.PROD_SPEC_CHAR_VALUE_IS_NULL.getCode();
        }
        if(null != this.prodSpecCharValue){
            if(null != this.prodSpecChar.getProdSpecCharValue() && !this.prodSpecChar.getProdSpecCharValue().contains(defaultValue)){
                return ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode();
            }
            for(ProdSpecCharValueUse prodSpecCharValueUse : this.prodSpecCharValue){
                if(prodSpecCharValueUse.getProdSpecCharValue().equals(defaultValue)){
                    prodSpecCharValueUse.setIsDefault(false);
                    return CommonErrorCode.SUCCESS.getCode();
                }
            }

        }
        logger.warn("this value is not belong to current char.");
        return ProdSpecErrorCode.PROD_SPEC_CHAR_NOT_INCLUDE_VALUE.getCode();
    }

    /**
     * 
     * @param charValue
     */
    private ProdSpecCharValueUse retrieveProdSpecCharValueUse(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharUse.retrieveProdSpecCharValueUse
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecCharUse that = (ProductSpecCharUse) o;

        if (prodSpecChar != null ? !prodSpecChar.equals(that.prodSpecChar) : that.prodSpecChar != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = prodSpecChar != null ? prodSpecChar.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String toString() {
        // TODO - implement ProductSpecCharUse.toString
        throw new UnsupportedOperationException();
    }

}