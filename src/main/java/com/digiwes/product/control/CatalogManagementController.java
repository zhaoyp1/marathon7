package com.digiwes.product.control;

import com.digiwes.common.BusinessCode;
import com.digiwes.common.utils.ParameterUtil;
import com.digiwes.common.utils.TimeUtils;
import com.digiwes.product.control.persistence.CatalogPersistence;
import com.digiwes.product.control.persistence.PersistenceFactory;
import com.digiwes.product.control.persistence.ProductOfferingPersistence;
import com.digiwes.product.offering.BundledProdOfferOption;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.catalog.ProductCatalog;
import com.digiwes.product.resource.response.BundledProductOffering;
import com.digiwes.product.resource.response.ProdOffering;

import java.util.Date;
import java.util.List;

public class CatalogManagementController {

	/**
	 * 
	 * @param productOffering
	 */
	public BusinessCode  publishOffering(ProductCatalog productCatalog, ProdOffering productOffering){
		if (null == productOffering ){
			  return BusinessCode.PROD_OFFERING_IS_NULL;
		}
		ProductOfferingPersistence offeringPersistence = PersistenceFactory.getProdOfferingPersistence();
		CatalogPersistence catalogPersistence = PersistenceFactory.getCatalogPersistence();
		ProductOffering exists = null;
		boolean flag=false;//used to judge bundledOffering is right
		BusinessCode result =BusinessCode.SUCCESS;
		try{
			exists =offeringPersistence.getOfferByName(productOffering.getName());
			if(null == exists){
				return BusinessCode.PROD_OFFERING_NOT_EXISTED;
			}else{
				if(exists instanceof com.digiwes.product.offering.BundledProductOffering){
					List<BundledProductOffering> boundledProductOffeings=productOffering.getBundledProductOffering();
					List<BundledProdOfferOption> bundledProdOfferOptions=((com.digiwes.product.offering.BundledProductOffering) exists).getBundledProdOfferOption();
					if(null == boundledProductOffeings || null ==bundledProdOfferOptions ||boundledProductOffeings.size()!=bundledProdOfferOptions.size() ) {
						return BusinessCode.PROD_OFFERING_BUNDLED_SIZE_IS_DIFFERENT;
					} else{
					   for(BundledProductOffering bundledProductOffering: boundledProductOffeings){
						   ProductOffering offering=offeringPersistence.load(bundledProductOffering.getId());
						   if(null !=offering){
							   flag=false;
							   for ( BundledProdOfferOption bundledProdOfferOption : bundledProdOfferOptions){
								   if (bundledProdOfferOption.getProductOffering().getId().equals(offering.getId())){
									   flag=true;
								   }
							   }
							   if(!flag){
								  return BusinessCode.PROD_OFFERING_BUNDLED_OFFERING_IS_DIFFERENT;
							   }
						   }else{
							   return BusinessCode.PROD_OFFERING_BUNDLED_OFFERING_NOT_EXISTED;
						   }

					   }
					}
				}
				result = productCatalog.publish(exists, productOffering.getValidFor());
				catalogPersistence.save(productCatalog);
				productOffering.setId(exists.getId());
				productOffering.setHref("");

			}

		}   catch (Exception e){
			e.printStackTrace();
		}
		return result;
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
			if(!ParameterUtil.checkParameterIsNull(time)){
				offeringTime = TimeUtils.parseDate(time, TimeUtils.Y4_MM_DD_TIME);
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