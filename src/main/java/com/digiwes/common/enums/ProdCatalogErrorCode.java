package com.digiwes.common.enums;

/**
 * Created by zhaoyp on 2015/7/16.
 */
public enum ProdCatalogErrorCode {
    PROD_CATALOG_ID_IS_NULL(3001,"productCatalog must not be null"),
    PROD_CATALOG_OFFERING_IS_PUBLISHED(3002,"offering has been published "),
    PROD_CATALOG_OFFERING_VALIDFOR_INVALID(3003,"publish offering'validFor is invalid");

    private  int code;
    private String message;

    private ProdCatalogErrorCode(int code,String message){
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
        for(CommonErrorCode errorCode : CommonErrorCode.values()){
            if (errorCode.getCode() == code ) {
                return errorCode.getMessage();
            }
        }
        return null;
    }
}
