package com.digiwes.common.enums;

/**
 * Created by dongwh on 2015-7-17.
 */
public enum CommonEnum {

    MIN_NOT_LIMIT(-1,"Least not limit"),
    MAX_NOT_LIMIT(-1,"Most not limit");

    private  int code;
    private String value;

    private CommonEnum(int code,String value){
        this.code = code;
        this.value = value;
    }
    public int getCode(){
        return this.code;
    }
    public String getValue(){
        return this.value;
    }

}
