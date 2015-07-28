package com.digiwes.product.resource.api;

import com.digiwes.product.control.CatalogManagementController;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.utils.ConvertUtil;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp on 2015/7/28.
 */
@Path("/catalogManagement")
@Produces({ "application/json"})
public class CatalogManagementResource {

    @GET
    @Path("/productOffering")
    public List<Map<String, Object>> retrieveProductOffering(@QueryParam("fields") String fields, @QueryParam("offeringName") String offeringName, @QueryParam("time") String time){
        //return value
        ProdOffering productOffering = new ProdOffering();

        //get the catalog
        ProductCatalog productCatalog = getManagementProductCatalog();

        //call the controller to get the return value
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        List<ProdCatalogProdOffer> productOfferingUnderCatalog = catalogManagementController.retrieveProductOffering(productCatalog, offeringName, time);

        List<ProdOffering> resultProdOfferingList = new ArrayList<ProdOffering>();
        if(null != productOfferingUnderCatalog && productOfferingUnderCatalog.size()>0){
            for(ProdCatalogProdOffer prodCatalogProdOffer : productOfferingUnderCatalog){
                ProdOffering prodOffering = ConvertUtil.convertToProdOffering(prodCatalogProdOffer);
                resultProdOfferingList.add(prodOffering);
            }
        }

       List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
       if(null != resultProdOfferingList && resultProdOfferingList.size()>0){
           for(ProdOffering resultProdOffering :resultProdOfferingList){
               Map<String,Object> map = ConvertUtil.convertObjectToMap(resultProdOffering, fields,"id");
               resultList.add(map);
           }
       }

       return resultList;
    }
    @GET
    @Path("/productOffering/{id}")
    public  Map<String,Object> retrieveProdOffering(@PathParam("id")String id,@QueryParam("fields") String fields){
        ProductCatalog catalog =getManagementProductCatalog();
        Map<String,Object> result = null ;
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        ProdCatalogProdOffer prodCatalogProdOffer = catalogManagementController.retrieveOffering(catalog, id);
        ProdOffering offering =ConvertUtil.convertToProdOffering(prodCatalogProdOffer);
        if( null != offering){
            result = ConvertUtil.convertObjectToMap(offering, fields,"id");
        }
        return  result;
    }

    @POST
    @Path("/productOffering")
    @Consumes({"application/json"})
    public ProdOffering publishOffering(ProdOffering prodOffering){
        ProductCatalog catalog =getManagementProductCatalog();
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        catalogManagementController.publishOffering(catalog,prodOffering);
        return prodOffering;
    }

    private  ProductCatalog getManagementProductCatalog(){
        ProductCatalog productCatalog = null;
        try {
            CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
            productCatalog = catalogPersistence.load("catalog_1");
        }catch (Exception e){

        }
        return productCatalog;
    }

}
