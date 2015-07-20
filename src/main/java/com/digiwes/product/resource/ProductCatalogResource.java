package com.digiwes.product.resource;

import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.product.control.ProductCatalogController;
import com.digiwes.product.resource.Parameter.*;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyp on 2015/7/19.
 */
@Service
@Path("/catalogManagement/ProductOffering")
public class ProductCatalogResource {
    /**
     * TODO publishOffering
     */


    /**
     * TODO retiredOffering
     */
    @POST
    @Consumes({"application/json","application/xml"})
    public RetiredOfferingResponse retiredOffering(RetiredOfferingRequest requestParam){
        RetiredOfferingResponse retiredOfferingResult =  new RetiredOfferingResponse();
        retiredOfferingResult.setCode(String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        retiredOfferingResult.setMessage(CommonErrorCode.SUCCESS.getMessage());

        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        ProductCatalogController prodCatalogController = new ProductCatalogController();
        try {
            PublishedOffering publishedOffering = prodCatalogController.retiredOffering(requestParam.getCatalogId(), requestParam.getProdOfferingId(),requestParam.getValidFor());
            publishedOfferingList.add(publishedOffering);
            retiredOfferingResult.setPublishedOffering(publishedOfferingList);
        }catch (Exception e){
            // Error
            retiredOfferingResult.setCode(String.valueOf(CommonErrorCode.FAIL.getCode()));
            retiredOfferingResult.setMessage(CommonErrorCode.FAIL.getMessage());
        }

        return retiredOfferingResult;
    }


    /**
     * TODO retrieveOffering
     */
    @GET
    @Consumes({ "application/json", "application/xml" })
    public RetrieveOfferingResponse retrieveOffering(RetrieveOfferingRequest requestParam){
        RetrieveOfferingResponse retrieveOfferingResult =  new RetrieveOfferingResponse();
        retrieveOfferingResult.setCode(String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        retrieveOfferingResult.setMessage(CommonErrorCode.SUCCESS.getMessage());

        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        ProductCatalogController prodCatalogController = new ProductCatalogController();
        try {
            publishedOfferingList = prodCatalogController.retrieveOffering(requestParam.getOfferingName(),requestParam.getTime(),requestParam.getCatalogId());
            retrieveOfferingResult.setPublishedOfferings(publishedOfferingList);
        }catch (Exception e){
            retrieveOfferingResult.setCode(String.valueOf(CommonErrorCode.FAIL.getCode()));
            retrieveOfferingResult.setMessage(CommonErrorCode.FAIL.getMessage());
        }
        return retrieveOfferingResult;
    }
}
