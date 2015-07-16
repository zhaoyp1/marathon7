package com.digiwes.common.utils;

import org.apache.commons.lang.StringUtils;

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
    public static void checkParameterIsNulForException(Object obj,String parameterName){
       if(checkParameterIsNull(obj)){
           throw new IllegalArgumentException(parameterName+"must not be null");
       }
       if(obj instanceof String ){
          if(StringUtils.isEmpty((String )obj)){
              throw new IllegalArgumentException(parameterName+"must not be null");
          }
       }
    }
}
