package com.digiwes.product.resource.utils;

import com.digiwes.product.offering.BundledProdOfferOption;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.resource.response.BundledProductOffering;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.response.ProductSpecificationRef;
import com.digiwes.product.spec.ProductSpecification;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp on 2015/7/28.
 */
public class ConvertUtil {
    public static  boolean isEquals(ProductOffering existsOffering,ProdOffering offering){
        if(existsOffering instanceof SimpleProductOffering){
            ProductSpecification sepc=((SimpleProductOffering) existsOffering).getProductSpecification();
            ProductSpecificationRef productSpecificationRef = offering.getProductSpecification();
            if( null != sepc || productSpecificationRef != null || !productSpecificationRef.getName().equals(sepc.getName()))  {
                   return false;
            }
        } else if (existsOffering instanceof com.digiwes.product.offering.BundledProductOffering){
            List<BundledProdOfferOption> bundledProductOfferings = ((com.digiwes.product.offering.BundledProductOffering) existsOffering).getBundledProdOfferOption();
            List<BundledProductOffering> bundledOffers=offering.getBundledProductOffering();
            if( null == bundledProductOfferings || null == bundledOffers ||bundledProductOfferings.size()!=bundledOffers.size()){
                return false;
            }
            for (int i = 0 ; i<bundledOffers.size() ; i++){
                BundledProductOffering bundledOffer =bundledOffers.get(i);
                boolean flag=false;
                for (int j= 0 ; j<bundledProductOfferings.size() ; j++){
                    BundledProdOfferOption offerOption=bundledProductOfferings.get(j);
                    ProductOffering prodOffering=offerOption.getProductOffering();
                    if(prodOffering.getName().equals(bundledOffer.getName())){
                        flag =true;
                    }
                }
                if(!flag){
                    return flag;
                }
            }
        }
        return true;

    }
    public static  Map<String,Object> convertObjectToMap(Object thisObj,String fields)
    {
        Map map = new HashMap();
        Class c;
        String [] fieldArray = null;
        if(StringUtils.isEmpty(fields)){
            fieldArray = fields.split(",");
        }
        try
        {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++)
            {
                String method = m[i].getName();
                if (method.startsWith("get"))
                {
                    try{
                        Object value = m[i].invoke(thisObj);
                        if (value != null)
                        {
                            if(null != fieldArray){
                                for(int j=0 ; j<fieldArray.length ; j++){

                                    String key=method.substring(3);
                                    if(key.equals(fieldArray[j])){
                                        key=key.substring(0,1)+key.substring(1);
                                        map.put(method, value);
                                        break;
                                    }

                                }
                            }else{
                                String key=method.substring(3);
                                key=key.substring(0,1).toUpperCase()+key.substring(1);
                                map.put(method, value);
                            }

                        }
                    }catch (Exception e) {
                        System.out.println("error:"+method);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }

}
