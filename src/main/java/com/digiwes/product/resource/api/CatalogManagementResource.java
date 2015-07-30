package com.digiwes.product.resource.api;

import com.digiwes.common.BusinessCode;
import com.digiwes.product.control.CatalogManagementController;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.utils.ConvertUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

/**
 * Created by zhaoyp on 2015/7/28.
 */
@Path("catalogManagement")
@Produces({ "application/json"})
public class CatalogManagementResource {

    @GET
    @Path("/productOffering")
    public List<Map<String, Object>> retrieveProductOffering(@QueryParam("fields") String fields, @QueryParam("name") String offeringName, @QueryParam("time") String time){
        //get the catalog
        ProductCatalog productCatalog = getManagementProductCatalog();
        //call the controller to get the return value
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        List<ProdCatalogProdOffer> productOfferingUnderCatalog = catalogManagementController.retrieveProductOffering(productCatalog, offeringName, time);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if(null != productOfferingUnderCatalog && productOfferingUnderCatalog.size()>0){
            for(ProdCatalogProdOffer prodCatalogProdOffer : productOfferingUnderCatalog){
                ProdOffering prodOffering = ConvertUtil.convertToProdOffering(prodCatalogProdOffer);
                Map<String,Object> map = ConvertUtil.convertObjectToMap(prodOffering, fields, "id");
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
    public Response publishOffering(ProdOffering prodOffering)throws  Exception{
        ProductCatalog catalog =getManagementProductCatalog();
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        Map<String,Object> result=new TreeMap<String, Object>();
        try {
            BusinessCode businessCode=catalogManagementController.publishOffering(catalog, prodOffering);
            if(businessCode == BusinessCode.SUCCESS){
                result.put("code",201);
                result.put("message",businessCode.getMessage());
                result.put("prodOffering",prodOffering);
            } else{
                result.put("code",500);
                result.put("message",businessCode.getMessage());
            }
        }catch (Exception e){
         e.printStackTrace();
        }
       // return Response.created(new URI("http://www.baidu.com")).entity(result).build();
        return Response.ok(result).build();
    }

    @GET
    @Path("/test")
    @Consumes({"application/json"})
    public String test(){
        return "aaaaaa";
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
