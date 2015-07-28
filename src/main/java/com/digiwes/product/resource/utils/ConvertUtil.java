package com.digiwes.product.resource.utils;

import com.digiwes.product.offering.BundledProdOfferOption;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.resource.response.BundledProductOffering;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.response.ProductOfferingPrice;
import com.digiwes.product.resource.response.ProductSpecificationRef;
import com.digiwes.product.spec.ProductSpecification;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
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

    //covert prodCatalogProdOffering to prodOffering
    public static  ProdOffering convertToProdOffering(ProdCatalogProdOffer prodCatalogProdOffer){
        ProdOffering prodOffering = new ProdOffering();
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
    public static Map<String,Object> convertObjectToMap(Object thisObj,String fields)
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
