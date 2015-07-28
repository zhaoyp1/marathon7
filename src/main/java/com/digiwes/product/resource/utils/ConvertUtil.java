package com.digiwes.product.resource.utils;

import com.digiwes.product.offering.BundledProdOfferOption;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.resource.response.BundledProductOffering;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.response.ProductSpecificationRef;
import com.digiwes.product.spec.ProductSpecification;

import java.util.List;

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


}
