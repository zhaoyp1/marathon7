package com.digiwes.product.spec;

import java.util.*;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonEnum;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */
public class ProductSpecCharacteristic {
    private static final Logger logger = Logger.getLogger(ProductSpecCharacteristic.class);

    private Set<ProductSpecCharacteristicValue> prodSpecCharValue = new HashSet<ProductSpecCharacteristicValue>();

    public List<ProductSpecCharRelationship> getProdSpecCharRelationship() {
        return prodSpecCharRelationship;
    }

    private List<ProductSpecCharRelationship> prodSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
    /**
     * A unique identifier for the ProductSpecCharacteristic.
     */
    private String ID;
    /**
     * A word, term, or phrase by which the characteristic is known and distinguished from characteristics.
     */
    private String name;
    /**
     * A narrative that explains the characteristic.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are; "unique while value is in effect" and "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for an Entity.
     */
    private boolean extensible;
    /**
     * A rule or principle represented in symbols, numbers, or letters, often in the form of an equation used to derive the value of a characteristic value.
     */
    private String derivationFormula;
    /**
     * The period of time for which a characteristic is applicable.
     */
    private TimePeriod validFor;

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
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

    public String getDerivationFormula() {
        return this.derivationFormula;
    }

    public void setDerivationFormula(String derivationFormula) {
        this.derivationFormula = derivationFormula;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public Set<ProductSpecCharacteristicValue> getProdSpecCharValue() {
        return prodSpecCharValue;
    }

    /**
     *  @param id
     * @param name
     * @param valueType
     * @param validFor
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor) {
        assert !StringUtils.isEmpty(id):"id must not be null";
        assert !StringUtils.isEmpty(name):"name must not be null";
        assert !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";
        this.ID = id;
        this.name = name;
        this.valueType = valueType;
        this.minCardinality = CommonEnum.MIN_NOT_LIMIT.getCode();
        this.maxCardinality = CommonEnum.MAX_NOT_LIMIT.getCode();
    }

    /**
     * 
     * @param id
     * @param name
     * @param valueType
     * @param validFor
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     * @param derivationFormula
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description, String derivationFormula) {
        this(id, name, valueType, validFor);
        assert (minCardinality > maxCardinality ? false : true) : "maxCardinality less than minCardinality.";
        this.unique = unique;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        this.extensible = extensible;
        this.description = description;
        this.derivationFormula = derivationFormula;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public BusinessCode specifyCardinality(int minCardinality, int maxCardinality) {
        if (minCardinality > maxCardinality) {
            logger.warn("maxCardinality is less than minCardinality.");
            return BusinessCode.PROD_SPEC_CHAR_CARDINALITY_MAX_LT_MIN;
        }
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param charVal
     */
    public BusinessCode assignValue(ProductSpecCharacteristicValue charVal) {
        if (ParameterUtil.checkParameterIsNull(charVal)) {
            logger.warn("charVal must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_VALUE_IS_NULL;
        }
        if (null != charVal.getValueType() && !this.getValueType().equals(charVal.getValueType())) {
            logger.warn("The valueType of Character and the valueType of CharacterValue are different.");
            return BusinessCode.PROD_SPEC_CHAR_VALUETYPE_DIFF_FROM_VALUE_VALUETYPE;
        }
        this.prodSpecCharValue.add(charVal);
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param charVal
     */
    public BusinessCode removeValue(ProductSpecCharacteristicValue charVal) {
        if (ParameterUtil.checkParameterIsNull(charVal)) {
            logger.warn("charValue must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_VALUE_IS_NULL;
        }
        if (null != prodSpecCharValue && prodSpecCharValue.size() > 0) {
            if (prodSpecCharValue.contains(charVal)) {
                prodSpecCharValue.remove(charVal);
                return BusinessCode.SUCCESS;
            }
            logger.warn("The charValue do not belong to this char.");
        }
        logger.warn("Current Char without this charValue.");
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecCharacteristicValue> retrieveValue(Date time) {
        List<ProductSpecCharacteristicValue> productSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
        ParameterUtil.checkParameterIsNulForException(time, "time");
        if ( null != this.prodSpecCharValue ) {
            for (ProductSpecCharacteristicValue charValue : prodSpecCharValue) {
                if (null != charValue.getValidFor() && charValue.getValidFor().isInTimePeriod(time)) {
                    productSpecCharValues.add(charValue);
                }
            }
        }
        return productSpecCharValues;
    }

    /**
     * 
     * @param defaultCharVal
     */
    public BusinessCode specifyDefaultValue(ProductSpecCharacteristicValue defaultCharVal) {
        return changeDefaultValue(defaultCharVal, true);
    }

    private BusinessCode changeDefaultValue(ProductSpecCharacteristicValue defaultCharVal, boolean isDefault) {
        if (null == defaultCharVal) {
            logger.error("charVal must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_VALUE_IS_NULL;
        }
        if (null == this.prodSpecCharValue || !prodSpecCharValue.contains(defaultCharVal)) {
            logger.warn("The charValue do not belong to this char.");
            return BusinessCode.PROD_SPEC_CHAR_VALUE_NOT_BELONG_TO_CHAR;
        }
        for (ProductSpecCharacteristicValue charValue : prodSpecCharValue) {
            if (charValue.equals(defaultCharVal)) {
                charValue.setIsDefault(isDefault);
                break;
            }
        }
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param value
     */
    public BusinessCode clearDefaultValue(ProductSpecCharacteristicValue value) {
        return changeDefaultValue(value, false);
    }

    public List<ProductSpecCharacteristicValue> retrieveDefaultValue() {
        List<ProductSpecCharacteristicValue> defaultSpecCharValue = new ArrayList<ProductSpecCharacteristicValue>();
        if (null != this.prodSpecCharValue) {
            for (ProductSpecCharacteristicValue charValue : prodSpecCharValue) {
                if (charValue.isIsDefault()) {
                    defaultSpecCharValue.add(charValue);
                }
            }
        }
        return defaultSpecCharValue;
    }

    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     */
    public BusinessCode associate(ProductSpecCharacteristic specChar, String type, TimePeriod validFor) {
        return associate(specChar, type, validFor, -1);
    }

    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     * @param charSpecSeq
     */
    public BusinessCode associate(ProductSpecCharacteristic specChar, String type, TimePeriod validFor, int charSpecSeq) {
        BusinessCode validResult = checkAssociateParam(specChar, type, validFor);
        if (BusinessCode.SUCCESS != validResult) {
            return validResult;
        }
        ProductSpecCharRelationship productSpecCharValueRelationShip = new ProductSpecCharRelationship(this, specChar, type, validFor, charSpecSeq);
        this.prodSpecCharRelationship.add(productSpecCharValueRelationShip);
        return BusinessCode.SUCCESS;
    }

    private BusinessCode checkAssociateParam(ProductSpecCharacteristic specChar, String type, TimePeriod validFor) {
        if (ParameterUtil.checkParameterIsNull(specChar)) {
            logger.warn("specChar must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_IS_NULL;
        }
        if (ParameterUtil.checkParameterIsNull(type)) {
            logger.warn("type must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_RELATIONSHIP_TYPE_IS_NULL;
        }
        if (this.equals(specChar)) {
            logger.warn("The srcChar and targetCharValue is the same.");
            return BusinessCode.PROD_SPEC_CHAR_ASSOCIATE_WITH_ITSELF;
        }
        List<ProductSpecCharRelationship> relationship = retrieveRelatedCharacteristicByChar(specChar);
        if (relationship != null) {
            //compare the time is valid
            for (ProductSpecCharRelationship relate : relationship){
                if (relate.getValidFor().isOverlap(validFor)) {
                    logger.warn("Characteristic has been established associate relationship in the specified time.");
                    return BusinessCode.PROD_SPEC_CHAR_RELATIONSHIP_EXISTED;
                }
            }
        }
        return BusinessCode.SUCCESS;
    }
    private List<ProductSpecCharRelationship> retrieveRelatedCharacteristicByChar(ProductSpecCharacteristic characteristic ){
        ParameterUtil.checkParameterIsNulForException(characteristic,"ProductSpecCharacteristic");
        List<ProductSpecCharRelationship> resultProdSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
        if (null !=prodSpecCharRelationship) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if( productSpecCharRelationship.getTargetProdSpecChar().equals(characteristic)){
                    resultProdSpecCharRelationship.add(productSpecCharRelationship);
                }
            }
        }
        return resultProdSpecCharRelationship;
    }

    /**
     * 
     * @param specChar
     */
    public BusinessCode dissociate(ProductSpecCharacteristic specChar) {
        if ( ParameterUtil.checkParameterIsNull(specChar) ) {
            logger.error("specChar must not be null");
            return BusinessCode.PROD_SPEC_CHAR_IS_NULL;
        }
        if ( ParameterUtil.checkParameterIsNull(this.prodSpecCharRelationship) ) {
            logger.warn("The association has not been created");
        }
        Iterator<ProductSpecCharRelationship> iterator = prodSpecCharRelationship.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getTargetProdSpecChar().equals(specChar)) {
                iterator.remove();
                break;
            }
        }
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param charRelationshipType
     */
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String charRelationshipType) {
        ParameterUtil.checkParameterIsNulForException(charRelationshipType, "charRelationshipType");
        List<ProductSpecCharacteristic>  characteristics = new ArrayList<ProductSpecCharacteristic>();;
        if (null !=prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if (charRelationshipType.equals(productSpecCharRelationship.getCharRelationshipType())) {
                    characteristics.add(productSpecCharRelationship.getTargetProdSpecChar());
                }
            }
        }
        return characteristics;
    }

    /**
     * 
     * @param charRelationshipType
     * @param time
     */
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String charRelationshipType, Date time) {
        ParameterUtil.checkParameterIsNulForException(charRelationshipType, "charRelationshipType");
        ParameterUtil.checkParameterIsNulForException(time, "time");
        List<ProductSpecCharacteristic>  characteristics = new ArrayList<ProductSpecCharacteristic>();;
        if (null !=prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if (charRelationshipType.equals(productSpecCharRelationship.getCharRelationshipType()) &&  productSpecCharRelationship.getValidFor().isInTimePeriod(time)) {
                    characteristics.add(productSpecCharRelationship.getTargetProdSpecChar());
                }
            }
        }
        return characteristics;
    }

    /**
     * 
     * @param prodSpecChar
     * @param validFor
     */
    public BusinessCode modifyRelationshipValidPeriod(ProductSpecCharacteristic prodSpecChar, TimePeriod oldValidFor, TimePeriod validFor) {
        if (ParameterUtil.checkParameterIsNull(prodSpecChar)) {
            logger.warn("prodSpecChar must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_IS_NULL;
        }
        if (ParameterUtil.checkParameterIsNull(oldValidFor)){
            logger.warn("oldValidFor must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_VALIDPERIOD_IS_NULL;
        }
        if (ParameterUtil.checkParameterIsNull(validFor) ){
            logger.warn("validFor must not be null.");
            return BusinessCode.PROD_SPEC_CHAR_VALIDPERIOD_IS_NULL;
        }

        List<ProductSpecCharRelationship> relationship = retrieveRelatedCharacteristicByChar(prodSpecChar);
        if (relationship != null) {
            //compare the time is valid
            for (ProductSpecCharRelationship relate : relationship){
                if (relate.getValidFor().isOverlap(validFor)) {
                    logger.warn("Characteristic has been established associate relationship in the specified time.");
                    return BusinessCode.PROD_SPEC_CHAR_RELATIONSHIP_EXISTED;
                }
            }
        }

        if ( null != this.prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecRelationship : this.prodSpecCharRelationship) {
                if ( productSpecRelationship.getTargetProdSpecChar().equals(prodSpecChar) && productSpecRelationship.getValidFor().equals(oldValidFor) ) {
                    productSpecRelationship.setValidFor(validFor);
                    return BusinessCode.SUCCESS;
                }
            }
        }
        logger.warn("the current Char has been to create associate relationships with the specified char.");
        return BusinessCode.PROD_SPEC_CHAR_RELATIONSHIP_EXISTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecCharacteristic that = (ProductSpecCharacteristic) o;

        return !(ID != null ? !ID.equals(that.ID) : that.ID != null);

    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    /**
     * Basic information of the class output into the Map
     */
    protected Map getBasicInfo() {
        // TODO - implement ProductSpecCharacteristic.getBasicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * Basic info of the class ouput to String
     */
    public String basicInfoToString() {
        // TODO - implement ProductSpecCharacteristic.basicInfoToString
        throw new UnsupportedOperationException();
    }

    public String toString() {
        // TODO - implement ProductSpecCharacteristic.toString
        throw new UnsupportedOperationException();
    }

}