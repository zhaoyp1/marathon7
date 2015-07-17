package com.digiwes.common.enums;

import com.digiwes.product.spec.ProdSpecCharValueUse;

/**
 * Created by zhaoyp on 2015/7/14.
 */
public enum ProdSpecErrorCode{
    PROD_SPEC_NAME_IS_NULL_OR_EMPTY(1001,"name is null or empty"),
    PROD_SPEC_IS_NULL(1004,"productSpecification is null"),
    PROD_SPEC_RELATIONSHIP_TYPE_IS_NULL(1003,"relationshipType is null"),
    PROD_SPEC_EQUALS_TO_CURRENT(1005,"the designated productSpecification equal to current value"),
    PROD_SPEC_HAS_RELATED_TO_CURRENT(1006,"the designated productSpecification equal to current value"),
    PROD_SPEC_CHAR_IS_NULL(1007,"Char is null"),
    PROD_SPEC_CHAR_MAX_LESS_THAN_MIX(1008,"maxCardinality is less than minCardinality"),
    PROD_SPEC_CHAR_TYPE_DIFFERENT_CHAR_VALUE_TYPE(1009,"The valueType of Character and the valueType of CharacterValue are the same"),
    PROD_SPEC_CHAR_VALUE_IS_NULL(1010,"charVal is null"),
    PROD_SPEC_CHAR_VALUE_NOT_BELONG_TO_CHAR(1011,"The charValue do not belong to this char"),
    PROD_SPEC_CHAR_HAS_NO_CHAR_VALUE(1012,"no charValue under the current char"),
    PROD_SPEC_CHAR_RELATIONSHIP_TYPE_IS_NULL(1013,"relationshipType is null"),
    PROD_SPEC_CHAR_EQUALS_TO_CURRENT(1014,"the designated productSpecCharacteristic equal to current value"),
    PROD_SPEC_CHAR_HAS_RELATED_TO_CURRENT(1015,"Characteristic has been established associate relationship in the specified time"),
    PROD_SPEC_CHAR_VALUE_VALUE_IS_NULL(1016,"value is null"),
    PROD_SPEC_CHAR_VALUE_RELATIONSHIP_TYPE_IS_NULL(1017,"relationshipType is null"),
    PROD_SPEC_CHAR_VALUE_EQUALS_TO_CURRENT(1018,"the designated productSpecCharacteristicValue equal to current value"),
    PROD_SPEC_CHAR_VALUE_HAS_RELATED_TO_CURRENT(1019,"CharacteristicValue has been established associate relationship in the specified time"),
    PROD_SPEC_CHAR_USE_NAME_IS_NULL_OR_EMPTY(1020,"ProductSpecCharUse'name is null or empty"),
    PROD_SPEC_CHAR_USE_TIME_NOT_BELONG_OF_CHAR_TIME(1021,"the specify time not belong of ProductSPecifcationChar's validFor"),
    PROD_SPEC_CHAR_HAS_ATTACHED_TO_SPEC(1022,"the char has benn attached to the ProdSpec"),
    PROD_SPEC_CHAR_VALUE_USE_TIME_NOT_BELONG_OF_CHARVALUE_TIME(1023,"the specify time not belong of ProductSPecifcationCharValue's validFor"),
    PROD_SPEC_NOT_USED_CURRENT_CHAR(1024,"the char not be used by this Spec"),
    PROD_SPEC_CHAR_NOT_INCLUDE_VALUE(1025,"the char not include this value"),
    PROD_SPEC_CHAR_VALUE_USE_NOT_IN_VALUE(1025,"validFor of prodSpecCharValueUse  is not in prodSpecCharValue's validFor"),
    PROD_SPEC_CHAR_VALUE_HAS_BEEN_USED(1026,"prodSpecCharValue has been used");


    private  int code;
    private String message;
    private ProdSpecErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }
    public int getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
    public String getMessageByCode(int code){
        for(ProdSpecErrorCode errorCode : ProdSpecErrorCode.values()){
            if (errorCode.getCode() == code ) {
                return errorCode.getMessage();
            }
        }
        return null;
    }
}
