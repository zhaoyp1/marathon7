package com.digiwes.product.control;

import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.product.resource.response.ProductOffering;

import java.util.List;
import java.util.Map;

public class CatalogManagementController {

	/**
	 * 
	 * @param productOffering
	 */
	public ProductOffering publishOffering(ProductOffering productOffering) {
		// TODO - implement CatalogManagementController.publishOffering
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fields
	 * @param offeringName
	 * @param time
	 */
	public List<Map<String, Object>> retrieveProductOffering(String fields, String offeringName, String time) {
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
	public Map<String, Object> retrieveOffering(String id, String fields) {
		// TODO - implement CatalogManagementController.retrieveOffering
		throw new UnsupportedOperationException();
	}

}