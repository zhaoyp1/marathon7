package com.digiwes.product.spec;

import java.util.*;
import com.digiwes.basetype.*;
import com.digiwes.common.BusinessCode;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdSpecErrorCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.basetype.TimePeriod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A detailed description of a tangible or intangible object made available externally in the form of a ProductOffering to Customers or other Parties playing a PartyRole. A ProductSpecification may consist of other ProductSpecifications supplied together as a collection. Members of the collection may be offered in their own right. ProductSpecifications may also exist within groupings, such as ProductCategories, ProductLines, and ProductTypes.
 */
public abstract class ProductSpecification {

    private static Logger logger= Logger.getLogger(ProductSpecification.class);
    private List<ProductSpecificationCost> productSpecificationCost;
    private List<ProductSpecificationRelationship> prodSpecRelationship = new ArrayList<ProductSpecificationRelationship>();
    private List<ProductSpecificationVersion> prodSpecVersion;
    private List<ProductSpecCharUse> prodSpecChar = new ArrayList<ProductSpecCharUse>();

    public List<ProductSpecCharUse> getProdSpecChar() {
        return prodSpecChar;
    }

    public List<ProductSpecificationRelationship> getProdSpecRelationship() {
        return prodSpecRelationship;
    }



    /**
     * The name of the product specification.
     */
    private String name;
    /**
     * The manufacturer or trademark of the specification.
     */
    private String brand;
    /**
     * A narrative that explains in detail what the product spec is.
     */
    private String description;
    /**
     * An identification number assigned to uniquely identify the specification.
     */
    private String productNumber;
    /**
     * The period for which the product specification is valid.
     */
    private TimePeriod validFor;
    /**
     * The condition of the product specification, such as active, inactive, planned.
     */
    private String lifecycleStatus;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductNumber() {
        return this.productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getLifecycleStatus() {
        return this.lifecycleStatus;
    }

    /**
     * Initializes a newly created {@code ProductSpecification} object so that it represents the all information. When the specification is a new one, the state of the specification will be initialized as "planned"
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param name The name of the product specification.
     * @param brand The manufacturer or trademark of the specification.
     */
    public ProductSpecification(String productNumber, String name, String brand,TimePeriod validFor) {
        assert !StringUtils.isEmpty(productNumber):"productNumber must not be null";
        assert !StringUtils.isEmpty(name):"name must not be null";
        assert !StringUtils.isEmpty(brand):"brand must not be null";
        assert !ParameterUtil.checkParameterIsNull(validFor):"validFor must not be null";

        this.productNumber=productNumber;
        this.name=name;
        this.brand=brand;
        this.validFor=validFor;
    }

    /**
     * Initializes a newly created {@code ProductSpecification} object so that it represents the all information. When the specification is a new one, the state of the specification will be initialized as "planned"
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param name The name of the product specification.
     * @param brand The manufacturer or trademark of the specification.
     * @param validFor The period of time for which the use of the ProductSpecification is applicable.
     * @param description A narrative that explains in detail what the product spec is.
     */
    public ProductSpecification(String productNumber, String name, String brand, TimePeriod validFor, String description) {
        this(productNumber,name,brand,validFor);
        this.description=description;
    }

    /**
     * 
     * @param charName
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite. true a composite one
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     */
    public BusinessCode attachCharacteristic(String charName, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
        BusinessCode errorCode = checkCharacteristic(charName, specChar, validFor);
        if(BusinessCode.SUCCESS != errorCode){
            return errorCode;
        }
        ProductSpecCharUse productSpecCharUse = new ProductSpecCharUse(specChar,charName,canBeOveridden,isPackage,validFor);
        this.prodSpecChar.add(productSpecCharUse);
        return BusinessCode.SUCCESS;

    }

    /**
     * 
     * @param charName A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public BusinessCode attachCharacteristic(String charName, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        BusinessCode errorCode = checkCharacteristic(charName, specChar, validFor);
        if(BusinessCode.SUCCESS != errorCode ){
            return errorCode;
        }
        ProductSpecCharUse productSpecCharUse = new ProductSpecCharUse(specChar,name,canBeOveridden,isPackage,validFor,unique,minCardinality,maxCardinality,extensible,description);
        this.prodSpecChar.add(productSpecCharUse);
        return BusinessCode.SUCCESS;


    }

    private BusinessCode checkCharacteristic(String charName, ProductSpecCharacteristic specChar, TimePeriod validFor) {
        if(StringUtils.isEmpty(charName)){
           return BusinessCode.PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY;
        }
        if(ParameterUtil.checkParameterIsNull(specChar)){
            return BusinessCode.PROD_SPEC_CHAR_IS_NULL;
        }
        if( !validFor.isInTimePeriod(specChar.getValidFor())){
             return BusinessCode.PROD_SPEC_CHAR_USE_VALIDPERIOD_NOT_IN_CHAR_VALIDPERIOD;
        }
        for(ProductSpecCharUse productSpecCharUse:this.prodSpecChar){
            if(specChar.equals(productSpecCharUse.getProdSpecChar()) && productSpecCharUse.getName().equals(charName) && productSpecCharUse.getValidFor().isOverlap(validFor)){
                return BusinessCode.PROD_SPEC_CHAR_HAS_BEEN_ATTACHED_TO_SPEC;
            }
        }
        return BusinessCode.SUCCESS;
    }

    /**
     * 
     * @param charName
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic before.
     */
    public int detachCharacteristic(String charName, ProductSpecCharacteristic specChar) {
        // TODO - implement ProductSpecification.detachCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic.
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public int modifyCharacteristicInfo(String charName, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        // TODO - implement ProductSpecification.modifyCharacteristicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true：is default value
     * @param validFor The period of time for which the use of the CharacteristicValue is applicable.
     */
    public BusinessCode assignCharacteristicValue(String charName, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        BusinessCode errorCode = validAttachCharValueParameter(charName, specChar, charValue);
        if(ParameterUtil.checkParameterIsNull(validFor)){
            return BusinessCode.PROD_SPEC_CHAR_VALUE_VALIDPERIOD_IS_NULL;
        }
        if( BusinessCode.SUCCESS != errorCode){
           return errorCode;
        }
        ProductSpecCharUse productSpecCharUse = retrieveProdSpecCharUse(charName, specChar);
        if( null == productSpecCharUse){
            return   BusinessCode.PROD_SPEC_NOT_ATTACH_CHAR;
        }
        if(!validFor.isInTimePeriod(charValue.getValidFor())){
            return BusinessCode.PROD_SPEC_CHAR_VALUE_USE_VALIDPERIOD_NOT_IN_CHAR_VALUE_VALIDPERIOD;
        }
        return productSpecCharUse.assignValue(charValue, isDefault, validFor);
    }

    private BusinessCode validAttachCharValueParameter(String charName, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue) {
        if(StringUtils.isEmpty(charName)){
            return BusinessCode.PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY;
        }
        if(ParameterUtil.checkParameterIsNull(specChar)){
            return BusinessCode.PROD_SPEC_CHAR_IS_NULL;
        }
        if(ParameterUtil.checkParameterIsNull(charValue)){
            return BusinessCode.PROD_SPEC_CHAR_VALUE_IS_NULL;
        }
        return BusinessCode.SUCCESS;
    }

    private ProductSpecCharUse retrieveProdSpecCharUse(String charName, ProductSpecCharacteristic specChar) {
        for (ProductSpecCharUse productSpecCharUse :this.prodSpecChar){
            if(productSpecCharUse.getName().equals(charName) && productSpecCharUse.getProdSpecChar().equals(specChar)){
                return  productSpecCharUse;
            }
        }
        return null;
    }

    /**
     * 
     * @param charName
     * @param specChar
     * @param charValue
     */
    public int removeCharacteristicValue(String charName, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecification.removeCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param specChar
     * @param defaultCharValue
     */
    public BusinessCode specifyDefaultCharacteristicValue(String charName, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue defaultCharValue) {
        BusinessCode errorCode = this.validAttachCharValueParameter(charName, specChar, defaultCharValue);
        if( BusinessCode.SUCCESS == errorCode ){
            ProductSpecCharUse charUse = retrieveProdSpecCharUse(charName,specChar);
            charUse.specifyDefaultValue(defaultCharValue);
        }
        return  errorCode;

    }

    /**
     * 
     * @param charName
     * @param characteristic
     * @param defaultValue
     */
    public int clearDefaultCharacteristicValue(String charName, ProductSpecCharUse characteristic, ProductSpecCharacteristicValue defaultValue) {
        // TODO - implement ProductSpecification.clearDefaultCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param characteristic
     */
    public List<ProdSpecCharValueUse> retrieveDefaultValue(String charName, ProductSpecCharacteristic characteristic) {
       ParameterUtil.checkParameterIsNulForException(charName,"charName") ;
       ParameterUtil.checkParameterIsNulForException(characteristic,"ProductSpecCharacteristic");
       ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(charName, characteristic) ;
        List<ProdSpecCharValueUse> defaults= new ArrayList<ProdSpecCharValueUse>();
       if( null != charUse){
          defaults= charUse.retrieveDefaultValue();
        }
       return defaults;
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecCharUse> retrieveCharacteristic(Date time) {
       ParameterUtil.checkParameterIsNulForException(time,"time");
       List<ProductSpecCharUse> productSpecCharUseList=new ArrayList<ProductSpecCharUse>();
       for(ProductSpecCharUse charUse : prodSpecChar ){
           if(charUse.getValidFor().isInTimePeriod(time)){
               productSpecCharUseList.add(charUse);
           }
       }
        return productSpecCharUseList;
    }

    /**
     * 
     * @param charName
     * @param specChar
     * @param time
     */
    public List<ProdSpecCharValueUse> retrieveCharacteristicValue(String charName, ProductSpecCharacteristic specChar, Date time) {
        ParameterUtil.checkParameterIsNulForException(charName,"charName") ;
        ParameterUtil.checkParameterIsNulForException(specChar,"ProductSpecCharacteristic");
        ParameterUtil.checkParameterIsNulForException(time,"time");
        List<ProdSpecCharValueUse>   validProdSpecCharValueUse= new ArrayList<ProdSpecCharValueUse>();
        ProductSpecCharUse productSpecCharUse=this.retrieveProdSpecCharUse(charName, specChar);
        if( null != productSpecCharUse) {
            if(productSpecCharUse.getValidFor().isInTimePeriod(time)){
                for (ProdSpecCharValueUse prodSpecCharValueUse : productSpecCharUse.getProdSpecCharValue()) {
                    if(prodSpecCharValueUse.getValidFor().isInTimePeriod(time)){
                        validProdSpecCharValueUse.add(prodSpecCharValueUse);
                    }
                }
            }
        }
        return  validProdSpecCharValueUse ;
    }

    public ProductSpecCharUse[] retrieveRootCharacteristic() {
        // TODO - implement ProductSpecification.retrieveRootCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param specChar
     * @param time
     */
    public ProductSpecCharUse[] retrieveLeafCharacteristic(String charName, ProductSpecCharacteristic specChar, Date time) {
        // TODO - implement ProductSpecification.retrieveLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charName
     * @param specChar
     * @param minCardinality
     * @param maxCardinality
     */
    public int specifyCardinality(String charName, ProductSpecCharacteristic specChar, int minCardinality, int maxCardinality) {
        // TODO - implement ProductSpecification.specifyCardinality
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param verType
     * @param curTypeVersion
     * @param description
     * @param revisionDate
     * @param validFor
     */
    private int specifyVersion(String verType, String curTypeVersion, String description, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecification.specifyVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     */
    public int specifyVersion(String version, String description, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecification.specifyVersion
        throw new UnsupportedOperationException();
    }

    public ProductSpecificationVersion[] retrieveCurrentVersion() {
        // TODO - implement ProductSpecification.retrieveCurrentVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param majorVersion
     * @param description
     * @param revisionDate
     */
    public String modifyMajorVersion(String majorVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.modifyMajorVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param minorVersion
     * @param description
     * @param revisionDate
     */
    public String modifyMinorVersion(String minorVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.modifyMinorVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param patchVersion
     * @param description
     * @param revisionDate
     */
    public String modifyPatchVersion(String patchVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.modifyPatchVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public int incurCost(Money cost, TimePeriod validFor) {
        // TODO - implement ProductSpecification.incurCost
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public int modifyCostPeriod(ProductSpecificationCost cost, TimePeriod validFor) {
        // TODO - implement ProductSpecification.modifyCostPeriod
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecificationCost[] retrieveCost(Date time) {
        // TODO - implement ProductSpecification.retrieveCost
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     * @param type
     * @param validFor
     */
    public BusinessCode associate(ProductSpecification prodSpec, String type, TimePeriod validFor) {
        if (StringUtils.isEmpty(type)) {
            return BusinessCode.PROD_SPEC_RELATIONSHIP_TYPE_IS_NULL;
        }
        if(!ParameterUtil.checkParameterIsNull(prodSpec)) {
            return BusinessCode.PROD_SPEC_SPEC_IS_NULL;
        }
        if(this.equals(prodSpec)){
            return BusinessCode.PROD_SPEC_ASSOCIATE_WITH_ITSELF;
        }
        //checkout time is in period
        ProductSpecificationRelationship prodSpecRealtionship = this.retrieveRelatedProdSpecBySpec(prodSpec);
        if(null != prodSpecRealtionship){
            if(prodSpecRealtionship.getValidFor().isOverlap(validFor)){
                return BusinessCode.PROD_SPEC_RELATIONSHIP_EXISTED;
            }
        }
        ProductSpecificationRelationship ship =new ProductSpecificationRelationship(this, prodSpec, type, validFor);
        this.prodSpecRelationship.add(ship);
        return BusinessCode.SUCCESS;
    }
    private ProductSpecificationRelationship retrieveRelatedProdSpecBySpec(ProductSpecification targetProdSpec){
        if(null != this.prodSpecRelationship){
            for(ProductSpecificationRelationship prodSpecShip : prodSpecRelationship){
                if(prodSpecShip.getTargetProdSpec().equals(targetProdSpec)){
                    return prodSpecShip;
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param prodSpec
     */
    public int dissociate(ProductSpecification prodSpec) {
        // TODO - implement ProductSpecification.dissociate
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param type
     */
    public List<ProductSpecification> retrieveRelatedProdSpec(String type) {
        ParameterUtil.checkParameterIsNulForException(type,"type");
        List<ProductSpecification> result = new ArrayList<ProductSpecification>();
        if(null != this.prodSpecRelationship){
            for (ProductSpecificationRelationship prodSpecRelate : this.prodSpecRelationship) {
                if(type.equals(prodSpecRelate.getType())){
                    result.add(prodSpecRelate.getTargetProdSpec());
                }
            }
        }
        return result;
    }

    /**
     * 
     * @param type
     * @param time
     */
    public List<ProductSpecification> retrieveRelatedProdSpec(String type, Date time) {
        ParameterUtil.checkParameterIsNulForException(type,"type");
        ParameterUtil.checkParameterIsNulForException(time,"time");
        List<ProductSpecification> result = new ArrayList<ProductSpecification>();
        for (ProductSpecificationRelationship prodSpecRelate : this.prodSpecRelationship) {
            if(prodSpecRelate.getValidFor().isInTimePeriod(time) && type.equals(prodSpecRelate.getType())){
                 result.add(prodSpecRelate.getTargetProdSpec());
            }
        }
        return result;
    }


    /**
     * 
     * @param charName
     * @param prodSpec
     * @param validFor
     */
    public int modifyRelatedSpecValidPeriod(String charName, ProductSpecification prodSpec, TimePeriod validFor) {
        // TODO - implement ProductSpecification.modifyRelatedSpecValidPeriod
        throw new UnsupportedOperationException();
    }


    /**
     * 
     * @param charName
     * @param characteristic
     */
    private boolean contains(String charName, ProductSpecCharacteristic characteristic) {
        for (ProductSpecCharUse productSpecCharUse :this.prodSpecChar){
            if(productSpecCharUse.getName().equals(charName) && productSpecCharUse.getProdSpecChar().equals(characteristic)){
                return  true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecification that = (ProductSpecification) o;

        if (!prodSpecVersion.equals(that.prodSpecVersion)) return false;
        return productNumber.equals(that.productNumber);

    }

    @Override
    public int hashCode() {
        int result = prodSpecVersion.hashCode();
        result = 31 * result + productNumber.hashCode();
        return result;
    }

    /**
     * Basic information of the class output into the Map
     */
    protected Map getBasicInfo() {
        // TODO - implement ProductSpecification.getBasicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * Basic info of the class ouput to String
     */
    public String basicInfoToString() {
        // TODO - implement ProductSpecification.basicInfoToString
        throw new UnsupportedOperationException();
    }

    /**
     * Basic info of the class ouput to String
     */
    public String toString() {
        // TODO - implement ProductSpecification.toString
        throw new UnsupportedOperationException();
    }

}