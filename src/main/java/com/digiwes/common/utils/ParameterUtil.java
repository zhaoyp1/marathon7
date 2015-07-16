package com.digiwes.common.utils;

/**
 * Created by liurl3 on 2015/7/16.
 */
public class ParameterUtil {
    public static boolean checkParameterIsNull(Object obj){
        if(null == obj){
            return true;
        }
        return false;
    }
}
