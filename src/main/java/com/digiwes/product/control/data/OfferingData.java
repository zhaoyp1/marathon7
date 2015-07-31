package com.digiwes.product.control.data;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.offering.BundledProductOffering;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.SimpleProductOffering;
import com.digiwes.product.offering.price.ProductOfferingPrice;
import org.apache.log4j.Logger;

/**
 * Created by Nisx on 2015/7/10.
 */
public class OfferingData {
    private static  Logger logger= Logger.getLogger(OfferingData.class);

    //offerId, OfferName, SpecId
    private final static int OFFER_ID = 0;
    private final static int OFFER_NAME = 1;
    private final static int OFFER_SPEC_ID = 2;
    private final static int BUNDLE_OFFER_ID = 1;
    private final static int NUMBER_REL_OFFER_LOWER_LIMIT=2;
    private final static int NUMBER_REL_OFFER_UPPER_LIMIT=3;

    private static  String[][] offeringData = {
            {"off_1", "11-inch MacBook Air", "spec_1"},
            {"off_2", "13-inch MacBook Air", "spec_2"},
    };
    private static  String[][] bundledOfferingData={
            {"bundOff_1","MacBookAir"}
    };
    private static  String[][] bundledOfferingOption={
            {"bundOff_1","off_1","1","1"}
    };
    //offerID, price_id
    private final  static int OFFER_PRICE_OFFER_ID = 0;
    private final  static int OFFER_PRICE_PRICE_ID = 1;
    private static String[][] offeringPrice = {
            {"off_1", "price_1"},
            {"off_1", "price_2"},
            {"off_2", "price_3"},
            {"off_2", "price_4"},
    };
    private static TimePeriod validFor = TimeUtil.creatTimePeriod("2015-06-01 00:00:00", "2015-12-01 23:59:59");
    private static  Boolean isInit = false;
    public static synchronized void init() {
        if (!isInit) {
            try {
                initOffering();
                initBundledOffering();
               bundledOfferingOption();
            } catch (Exception e) {
                logger.error("Offering init error.", e);
            }
            isInit = true;
        }
    }
    public static void initOffering() throws Exception {
        for (String[] offeringItem : offeringData) {
            ProductOffering offer = new SimpleProductOffering(offeringItem[OFFER_ID],
                    offeringItem[OFFER_NAME],
                    offeringItem[OFFER_NAME],
                    validFor,
                    SpecData.getSpec(offeringItem[OFFER_SPEC_ID]));
            PersistenceFactory.getProdOfferingPersistence().save(offer);
        }
        //specifyPrice
       // specifyPrice();
    }
    public  static  void initBundledOffering()throws  Exception{
        for (String[] offeringItem : bundledOfferingData) {
            BundledProductOffering offer = new BundledProductOffering(offeringItem[OFFER_ID],
                    offeringItem[OFFER_NAME],
                    offeringItem[OFFER_NAME],
                    validFor);
            PersistenceFactory.getProdOfferingPersistence().save(offer);
          }
    }
    public static  void bundledOfferingOption()throws  Exception{
        for (String[] offeringItem : bundledOfferingOption) {
            ProductOffering bundleOffering=PersistenceFactory.getProdOfferingPersistence().load(offeringItem[OFFER_ID]);
            if(bundleOffering instanceof  BundledProductOffering){
                ProductOffering offering=PersistenceFactory.getProdOfferingPersistence().load(offeringItem[BUNDLE_OFFER_ID]);
                if(null !=offering){
                    ((BundledProductOffering)bundleOffering).composeOf(offering, Integer.parseInt(offeringItem[NUMBER_REL_OFFER_LOWER_LIMIT]), Integer.parseInt(offeringItem[NUMBER_REL_OFFER_UPPER_LIMIT]));
                    PersistenceFactory.getProdOfferingPersistence().save(bundleOffering);
                }
            }
         }
    }

    private static void specifyPrice() throws Exception {
        //specify price
        for (String[] item : offeringPrice) {
            ProductOffering offer = PersistenceFactory.getProdOfferingPersistence().load(item[OFFER_PRICE_OFFER_ID]);
            ProductOfferingPrice price = PriceData.getPrice(item[OFFER_PRICE_PRICE_ID]);
            if (null != offer && null != price) {
                offer.specifyPrice(price);
            }
        }
    }
}
