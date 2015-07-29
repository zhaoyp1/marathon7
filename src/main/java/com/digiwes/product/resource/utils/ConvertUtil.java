package com.digiwes.product.resource.utils;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.offering.BundledProdOfferOption;
import com.digiwes.product.offering.BundledProductOffering;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.response.ProductOfferingPrice;
import com.digiwes.product.resource.response.ProductSpecificationRef;
import com.digiwes.product.spec.ProductSpecification;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp on 2015/7/28.
 */
public class ConvertUtil {
       //covert prodCatalogProdOffering to prodOffering
    public static  ProdOffering convertToProdOffering(ProdCatalogProdOffer prodCatalogProdOffer){
        ProdOffering prodOffering = null;
        if( null != prodCatalogProdOffer){
            prodOffering = new ProdOffering();
            prodOffering.setId(prodCatalogProdOffer.getProdOffering().getId());
            prodOffering.setValidFor(prodCatalogProdOffer.getValidFor());
            prodOffering.setName(prodCatalogProdOffer.getProdOffering().getName());
            prodOffering.setDescription(prodCatalogProdOffer.getProdOffering().getDescription());
            prodOffering.setLastUpdate(null);
            prodOffering.setLifecycleStatus(prodCatalogProdOffer.getProdOffering().getStatus());
            prodOffering.setVersion("1.0.0");
            //price
            List<ProductOfferingPrice>  prodOfferingPriceResult = new ArrayList<ProductOfferingPrice>();
            List<com.digiwes.product.offering.price.ProductOfferingPrice> productOfferingPriceList = prodCatalogProdOffer.getProductOfferingPrice();
            if(null!= productOfferingPriceList && productOfferingPriceList.size()>0){
                for(com.digiwes.product.offering.price.ProductOfferingPrice productOfferingPrice : productOfferingPriceList){
                    ProductOfferingPrice prodOfferingPrice = convertToProductOfferingPrice(productOfferingPrice);
                    prodOfferingPriceResult.add(prodOfferingPrice);
                }
            }
            prodOffering.setProductOfferingPrice(prodOfferingPriceResult);
            if(prodCatalogProdOffer.getProdOffering() instanceof SimpleProductOffering){
                prodOffering.setProductSpecification(convertToProdSpecRef((SimpleProductOffering) prodCatalogProdOffer.getProdOffering()));
            }else{
                prodOffering.setProductSpecification(null);
            }
        }

       return prodOffering;
    }

    private static  ProductOfferingPrice convertToProductOfferingPrice(com.digiwes.product.offering.price.ProductOfferingPrice productOfferingPrice){
        ProductOfferingPrice prodOfferingPrice = new ProductOfferingPrice();
        prodOfferingPrice.setVersion("1.0.0");
        prodOfferingPrice.setDescription(productOfferingPrice.getDescription());
        prodOfferingPrice.setName(productOfferingPrice.getName());
        prodOfferingPrice.setPriceType("");
        return prodOfferingPrice;
    }

    private static ProductSpecificationRef convertToProdSpecRef(SimpleProductOffering simpleProdOffering){
        ProductSpecificationRef prodSpecRef = new ProductSpecificationRef();
        prodSpecRef.setId(simpleProdOffering.getId());
        prodSpecRef.setName(simpleProdOffering.getName());
        prodSpecRef.setHref("");
        return prodSpecRef;
    }

    public static Map<String,Object> convertObjectToMap(Object thisObj,String fields,String requiredField)
    {
        Map map = new HashMap();
        Class c;
        String [] fieldArray = null;
        if(!StringUtils.isEmpty(fields)){
            String allFields= fields;
            if(!StringUtils.isEmpty(requiredField)){
                allFields=fields+","+requiredField;

            }
            fieldArray=allFields.split(",");
        }

        try
        {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getMethods();
            Field[] f=c.getFields();
            for (int i = 0; i < m.length; i++)
            {
                String method = m[i].getName();
                if (method.startsWith("get") && !method.equals("getClass"))
                {
                    try{
                        Object value = m[i].invoke(thisObj);
                        if (value != null)
                        {
                            if(!StringUtils.isEmpty(fields)){


                                for(int j=0 ; j<fieldArray.length ; j++){

                                    String key=method.substring(3);
                                    key=key.substring(0,1).toLowerCase()+key.substring(1);
                                    if(key.equals(fieldArray[j])){
                                        map.put(key, value);
                                        break;
                                    }

                                }
                            }else{
                                String key=method.substring(3);
                                key=key.substring(0,1).toLowerCase()+key.substring(1);
                                map.put(key, value);
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