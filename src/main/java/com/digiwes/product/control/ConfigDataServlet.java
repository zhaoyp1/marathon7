package com.digiwes.product.control;

import com.digiwes.product.control.data.*;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by zhaoyp on 2015/7/30.
 */
public class ConfigDataServlet extends HttpServlet{
    public void init(){
        SpecCharData.init();
        SpecData.init();
        OfferingData.init();
        // PriceData.init();
        CatalogData.init();
    }

}
