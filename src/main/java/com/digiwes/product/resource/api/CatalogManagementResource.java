package com.digiwes.product.resource.api;

import com.digiwes.product.control.CatalogManagementController;
import com.digiwes.product.resource.response.ProductOffering;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by zhaoyp on 2015/7/28.
 */
@Path("/catalogManagement")
@Produces({ "application/json"})
public class CatalogManagementResource {


    @Path("/retrieveProductOffering")
    public ProductOffering retrieveProductOffering(@QueryParam("fields") String fields, @QueryParam("offeringName") String offeringName, @QueryParam("time") String time){
        ProductOffering productOffering = new ProductOffering();

        //call the controller to get the return value
        CatalogManagementController catalogManagementController = new CatalogManagementController();
        catalogManagementController.retrieveProductOffering(fields, offeringName, time);


        return productOffering;
    }

}
