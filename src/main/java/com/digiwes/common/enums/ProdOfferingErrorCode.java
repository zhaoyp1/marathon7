package com.digiwes.common.enums;

/**
 * Created by liurl3 on 2015/7/16.
 */
public enum ProdOfferingErrorCode {
    PROD_OFFERING_NAME_IS_NULL_OR_EMPTY(2001,"name is null or empty"),
    PROD_OFFERING_OFFERING_IS_NULL(2002,"offering is null or empty"),
    PROD_OFFERING_RELATIONSHIP_TYPE_IS_NULL_OR_EMPTY(2003,"relationshpType is null or empty"),
    PROD_OFFERING_RELATIONSHIP_ALREADY_EXISTING(2004,"relationship is already existing"),
    PROD_OFFERING_ASSOCIATE_ITSELF(2005,"Its associated himself"),
    BUNDLED_PROD_OFFERING_LOWERLIMIT_GREATER_UPPERLIMIT(2006,"lowerlimit is greater than upperlimit"),
    BUNDLED_PROD_OFFERING_ALREADY_COMPOSED(2007,"bundledProdOffering is already composed of offering"),
    PROD_OFFERING_OFFERING_IS_INVALID(2008,"Offering is invalid");
    private  int code;
    private String message;
    private ProdOfferingErrorCode(int code,String message){
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
