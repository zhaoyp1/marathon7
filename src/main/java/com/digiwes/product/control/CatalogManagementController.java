package com.digiwes.product.control;

import com.digiwes.common.enums.CommonErrorCode;
import com.digiwes.common.enums.ProdCatalogErrorCode;
import com.digiwes.common.enums.ProdOfferingErrorCode;
import com.digiwes.common.utils.ParameterUtil;

import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.resource.Enums.ApiErrorCode;
import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.offering.catalog.*;
import com.digiwes.product.resource.utils.ConvertUtil;

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
			int result = productCatalog.publish(exists, productOffering.getValidFor());
		}catch (Exception e)  {
		   e.printStackTrace();
		}
		productOffering.setId(exists.getId());
		productOffering.setHref("");
		return productOffering;
	}

	/**
	 * 
	 * @param fields
	 * @param offeringName
	 * @param time
	 */
	public List<Map<String, Object>> retrieveProductOffering(ProductCatalog productCatalog,String fields, String offeringName, String time) {
		// TODO - implement CatalogManagementController.retrieveProductOffering
		if(ParameterUtil.checkParamIsNullOrEmpty(fields)){

		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param fields
	 */
	public Map<String, Object> retrieveOffering(ProductCatalog productCatalog,String id, String fields) {
		// TODO - implement CatalogManagementController.retrieveOffering
		throw new UnsupportedOperationException();
	}

}