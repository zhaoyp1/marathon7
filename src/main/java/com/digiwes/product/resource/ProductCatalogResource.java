package com.digiwes.product.resource;

import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.product.control.ProductCatalogController;
import com.digiwes.product.resource.Parameter.PublishedOffering;
import com.digiwes.product.resource.Parameter.RetiredOfferingRequest;
import com.digiwes.product.resource.Parameter.RetiredOfferingResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyp on 2015/7/19.
 */
public class ProductCatalogResource {
    /**
     * TODO publishOffering
     */


    /**
     * TODO retiredOffering
     */
    public RetiredOfferingResponse retiredOffering(RetiredOfferingRequest requestParam){
        RetiredOfferingResponse retiredOfferingResult =  new RetiredOfferingResponse();
        retiredOfferingResult.setCode(String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        retiredOfferingResult.setMessage(CommonErrorCode.SUCCESS.getMessage());

        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        ProductCatalogController prodCatalogController = new ProductCatalogController();
        try {
            PublishedOffering publishedOffering = prodCatalogController.retiredOffering(requestParam.getCatalogId(), requestParam.getProdOfferingId());
            publishedOfferingList.add(publishedOffering);
            retiredOfferingResult.setPublishedOffering(publishedOfferingList);
        }catch (Exception e){
            // Error
        }

        return retiredOfferingResult;
    }


    /**
     * TODO retrieveOffering
     */
}
