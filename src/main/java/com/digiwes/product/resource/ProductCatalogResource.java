package com.digiwes.product.resource;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.utils.TimeUtils;
import com.digiwes.product.control.ProductCatalogController;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.control.persistence.impl.CatalogPersistenceSimpleImpl;
import com.digiwes.product.offering.*;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.Parameter.*;
import com.digiwes.product.resource.Parameter.ProductOffering;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyp on 2015/7/19.
 */
@Service
@Path("/catalogManagement")
public class ProductCatalogResource {

    private static ProductCatalogController prodCatalogController = new ProductCatalogController();
    /**
     * publishOffering
     */
    @Path("/publishOffering")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    public PublishOfferingResponse publishOffering(PublishOfferingRequest requestParame) throws Exception {
        requestParame.validFor =  new TimePeriod(TimeUtils.parseDate("2015-08-21 23:59:59","yyyy-MM-dd hh:mm:ss"),TimeUtils.parseDate("2015-11-01 23:59:59","yyyy-MM-dd hh:mm:ss"));
        PublishOfferingResponse resultResponse = new PublishOfferingResponse();
        resultResponse.setCode( String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        resultResponse.setMessage(CommonErrorCode.SUCCESS.getMessage());
        try {
            ProductCatalog productCatalog = prodCatalogController.publishOffering(requestParame.catalogId, requestParame.prodOfferingId, requestParame.validFor);
            com.digiwes.product.resource.Parameter.ProductCatalog catalogResponse = new com.digiwes.product.resource.Parameter.ProductCatalog();
            List<PublishedOffering> productOfferings = new ArrayList<PublishedOffering>();
            catalogResponse.convertFromProductCatalog(productCatalog);

            if (null != productCatalog) {
                List<ProdCatalogProdOffer> prodCatalogProdOffer = productCatalog.getProdCatalogProdOffer();
                for (ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                    PublishedOffering publishedOffering = new PublishedOffering();
                    publishedOffering.convertFromProdCatalogProdOffeing(catalogProdOffer);
                    publishedOffering.existInProdCatalog = catalogResponse;
                    productOfferings.add(publishedOffering);
                }
                resultResponse.setPublishedOffering(productOfferings);
            }
        } catch (Exception e) {
            resultResponse.setCode( String.valueOf(CommonErrorCode.FAIL.getCode()));
            resultResponse.setMessage(CommonErrorCode.FAIL.getMessage());
        }
        return resultResponse;
    }

    /**
     * retiredOffering
     */
    @Path("/retiredOffering")
    @POST
    @Consumes({"application/json","application/xml"})
    @Produces({ "application/json", "application/xml" })
    public RetiredOfferingResponse retiredOffering(RetiredOfferingRequest requestParam) throws Exception{
        requestParam.validFor =  new TimePeriod(TimeUtils.parseDate("2015-07-21 23:59:59","yyyy-MM-dd hh:mm:ss"),TimeUtils.parseDate("2015-11-01 23:59:59","yyyy-MM-dd hh:mm:ss"));
        RetiredOfferingResponse retiredOfferingResult =  new RetiredOfferingResponse();
        retiredOfferingResult.setCode(String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        retiredOfferingResult.setMessage(CommonErrorCode.SUCCESS.getMessage());

        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        ProductCatalogController prodCatalogController = new ProductCatalogController();
        try {

            CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
            ProductCatalog prodCatalog = catalogPersistence.load(requestParam.getCatalogId());
            ProdCatalogProdOffer prodCatalogProdOffer = prodCatalogController.retiredOffering(prodCatalog, requestParam.getProdOfferingId(), requestParam.getValidFor());

            PublishedOffering publishedOffering = new PublishedOffering();
            if(null != prodCatalogProdOffer){
                publishedOffering.setValidFor(prodCatalogProdOffer.getValidFor());
                com.digiwes.product.resource.Parameter.ProductOffering productOfferingResource = new com.digiwes.product.resource.Parameter.ProductOffering();
                productOfferingResource.convertFromProductOffering(prodCatalogProdOffer.getProdOffering());
                publishedOffering.setProductOffering(productOfferingResource);
                com.digiwes.product.resource.Parameter.ProductCatalog productCatalog = new com.digiwes.product.resource.Parameter.ProductCatalog();
                productCatalog.convertFromProductCatalog(prodCatalog);
                publishedOffering.setExistInProdCatalog(productCatalog);
                publishedOfferingList.add(publishedOffering);
                retiredOfferingResult.setPublishedOffering(publishedOfferingList);
            }
        }catch (Exception e){
            // Error
            retiredOfferingResult.setCode(String.valueOf(CommonErrorCode.FAIL.getCode()));
            retiredOfferingResult.setMessage(CommonErrorCode.FAIL.getMessage());
        }
        return retiredOfferingResult;
    }


    /**
     * retrieveOffering
     */
    @Path("/ProductOffering")
    @POST
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public RetrieveOfferingResponse retrieveOffering(RetrieveOfferingRequest requestParam) throws Exception {
        RetrieveOfferingResponse retrieveOfferingResult =  new RetrieveOfferingResponse();
        retrieveOfferingResult.setCode(String.valueOf(CommonErrorCode.SUCCESS.getCode()));
        retrieveOfferingResult.setMessage(CommonErrorCode.SUCCESS.getMessage());

        List<ProdCatalogProdOffer> prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        List<PublishedOffering> publishedOfferingList = new ArrayList<PublishedOffering>();
        ProductCatalogController prodCatalogController = new ProductCatalogController();
        ProductCatalog productCatalog = prodCatalogController.retrieveCatalog(requestParam.getCatalogId());
        try {
            prodCatalogProdOffers = prodCatalogController.retrieveOffering(requestParam.getOfferingName(),requestParam.getTime(),productCatalog);
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffers){
                PublishedOffering publishedOffering = new PublishedOffering();
                publishedOffering.convertFromProdCatalogProdOffeing(catalogProdOffer);
                publishedOffering.convertFromProductCatalog(productCatalog);
                publishedOfferingList.add(publishedOffering);
            }
            retrieveOfferingResult.setPublishedOfferings(publishedOfferingList);
        }catch (Exception e){
            retrieveOfferingResult.setCode(String.valueOf(CommonErrorCode.FAIL.getCode()));
            retrieveOfferingResult.setMessage(CommonErrorCode.FAIL.getMessage());
        }
        return retrieveOfferingResult;
    }
}
