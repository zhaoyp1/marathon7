package com.digiwes.utils;

import com.digiwes.product.spec.data.CatalogData;
import com.digiwes.product.spec.data.OfferingData;
import com.digiwes.product.resource.ProductCatalogResource;
import com.digiwes.product.spec.data.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author ""
 */
public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/marathon/");

    public  static void main(String[] args) {
        try {

            Map<String, String> initParams = new HashMap<String, String>();
            initParams.put(ServerProperties.PROVIDER_PACKAGES, ProductCatalogResource.class.getPackage().getName());
            
            final HttpServer server = GrizzlyWebContainerFactory.create(BASE_URI, ServletContainer.class, initParams);
            initData();
            System.out.println("Application started. Try out Hit enter to stop it...");
            System.in.read();
            server.shutdownNow();

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void initData(){
        SpecCharData.init();       // create char
        SpecData.init();           //create spec
        OfferingData.init();
        CatalogData.init();
    }
}
