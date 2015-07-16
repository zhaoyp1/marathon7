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
    PROD_SPEC_CHAR_MAX_LESS_THAN_MAX(1007,"maxCardinality is less than minCardinality"),
    PROD_SPEC_CHAR_VALUE_IS_NULL(1008,"charVal is null"),
    PROD_SPEC_CHAR_TYPE_DIFFERENT_CHAR_VALUE_TYPE(1009,"The valueType of Character and the valueType of CharacterValue are the same");

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
