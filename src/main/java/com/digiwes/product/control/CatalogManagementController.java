package com.digiwes.product.control;

import com.digiwes.common.utils.ParameterUtil;

import com.digiwes.product.resource.response.ProdOffering;
import com.digiwes.product.offering.catalog.*;
import com.digiwes.product.resource.utils.DateAdapter;

import java.util.*;

public class CatalogManagementController {

	/**
	 * 
	 * @param productOffering
	 */
	public ProdOffering publishOffering(ProductCatalog productCatalog, ProdOffering productOffering) {

		throw new UnsupportedOperationException();
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

		/*//screening
		if(ParameterUtil.checkParamIsNullOrEmpty(fields)){
			if(null != prodCatalogProdOfferList && prodCatalogProdOfferList.size()>0){
				for(ProdCatalogProdOffer prodCatalogProdOffer : prodCatalogProdOfferList){
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("prodOffering", prodCatalogProdOffer.getProdOffering());
					resultMap.put("productOfferingPrice", prodCatalogProdOffer.getProductOfferingPrice());
					resultMap.put("validFor", prodCatalogProdOffer.getValidFor());
					resultList.add(resultMap);
				}
			}
		}
		throw new UnsupportedOperationException();*/
		return prodCatalogProdOfferList;
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