package com.digiwes.product.control;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.control.persistence.CatalogPersistence;
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
		CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
		ProductOffering exists = null;
		try{
			exists =offeringPersistence.getOfferByName(productOffering.getName());
			if(null == exists){

			}
			BusinessCode result = productCatalog.publish(exists, productOffering.getValidFor());
			this.dealResult(result);
			catalogPersistence.save(productCatalog);
		}   catch (Exception e){
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
	public void dealResult(BusinessCode result){
		try{
			if(BusinessCode.PROD_OFFERING_VALIDFOR_IS_NULL == result) {
				throw  new IllegalArgumentException(BusinessCode.PROD_OFFERING_VALIDFOR_IS_NULL.getMessage());
			}else if(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getCode() == result.getCode()){
				throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getMessage());
			}else if(BusinessCode.PROD_OFFERING_IS_NULL.getCode() == result.getCode()){
				throw  new IllegalArgumentException(BusinessCode.PROD_OFFERING_IS_NULL.getMessage());
			} else if(BusinessCode.PROD_OFFERING_PUBLISHED_VALIDPERIOD_NOT_IN_OFFERING_VALIDPERIOD.getCode() ==result.getCode()){
				throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_VALIDPERIOD_NOT_IN_OFFERING_VALIDPERIOD.getMessage());
			} else if(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getCode() == result.getCode()){
				throw  new Exception(BusinessCode.PROD_OFFERING_PUBLISHED_STARTTIME_LT_CURRENT.getMessage());
			} else if(BusinessCode.PROD_OFFERING_CATALOG_OFFERING_HAS_BEEN_PUBLISHED.getCode() == result.getCode()){
				throw  new Exception(BusinessCode.PROD_OFFERING_CATALOG_OFFERING_HAS_BEEN_PUBLISHED.getMessage());
			}
		}   catch (Exception e){
			e.printStackTrace();
		}


	}

}