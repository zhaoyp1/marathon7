package com.digiwes.product.control;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.resource.utils.ConvertUtil;
import com.digiwes.product.resource.utils.DateAdapter;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CatalogManagementController {

	/**
	 * 
	 * @param productOffering
	 */
	public ProdOffering  publishOffering(ProductCatalog productCatalog, ProdOffering productOffering){
		if (null == productOffering ){

		}
		ProductOfferingPersistence offeringPersistence = PersistenceFactory.getProdOfferingPersistence();
		ProductOffering exists = null;
		try{

			exists =offeringPersistence.getOfferByName(productOffering.getName());
			if(null == exists){

			}
			BusinessCode result = productCatalog.publish(exists, productOffering.getValidFor());
		}catch (Exception e)  {
		   e.printStackTrace();
		}
		productOffering.setId(exists.getId());
		productOffering.setHref("");
		return productOffering;
	}

	/**
	 * 
	 * @param offeringName
	 * @param time
	 */
	public List<ProdCatalogProdOffer> retrieveProductOffering(ProductCatalog productCatalog, String offeringName, String time) {
		//choice
		Date offeringTime = null;
		try {
			DateAdapter dateAdapter = new DateAdapter();
			if(!ParameterUtil.checkParameterIsNull(time)){
				offeringTime = dateAdapter.unmarshal(time);
			}
		}catch (Exception e){

		}
		List<ProdCatalogProdOffer> prodCatalogProdOfferList = productCatalog.retrieveOffering(offeringName, offeringTime);

		return prodCatalogProdOfferList;
	}

	/**
	 * 
	 * @param id
	 *
	 */
	public ProdCatalogProdOffer retrieveOffering(ProductCatalog productCatalog,String id) {
		ProdCatalogProdOffer prodCatalogProdOffer =productCatalog.retrieveProdOffering(id);
		return  prodCatalogProdOffer;
	}

}