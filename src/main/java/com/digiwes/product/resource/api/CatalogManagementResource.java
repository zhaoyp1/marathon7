package com.digiwes.product.resource.api;

import com.digiwes.product.control.CatalogManagementController;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.ProdOffering;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyp on 2015/7/28.
 */
@Path("/catalogManagement")
@Produces({ "application/json"})
public class CatalogManagementResource {



    @Path("/retrieveProductOffering")
    public ProdOffering retrieveProductOffering(@QueryParam("fields") String fields, @QueryParam("offeringName") String offeringName, @QueryParam("time") String time){
        //return value
        ProdOffering productOffering = new ProdOffering();

        //get the catalog
        ProductCatalog productCatalog = getManagementProductCatalog();

        //call the controller to get the return value
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        List<Map<String, Object>> productOfferingUnderCatalog = catalogManagementController.retrieveProductOffering(productCatalog, fields, offeringName, time);

        //TODO convert the return value to return json

        return productOffering;
    }

    private static ProductCatalog getManagementProductCatalog(){
        ProductCatalog productCatalog = null;
        try {
            CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
            productCatalog = catalogPersistence.load("catalog_1");
        }catch (Exception e){

        }
        return productCatalog;
    }

}
