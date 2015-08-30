package cn.crane.application.greenlife.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class API {
//	public static final String IP = "http://42.96.167.31:8080";
	public static final String IP = "http://123.57.80.185";
	public static final String PORT = "";
	
//	public static final String HOST_DEFAULT = "http://www.cheweilaile.com";
//	public static final String HOST_DEFAULT = "http://www.meifadian.net";
	public static final String HOST_DEFAULT = IP;
	public static  String HOST = HOST_DEFAULT;
	
//	public static final String API_ADDRESS = IP + PROJECT_NAME + NAMESPACE;
	public static final String API_ADDRESS = HOST + "/";
	
	
	public static final String API_advertisedMerchantList = "zj/json/advertisedMerchantList.action";
	
	public static final String API_getNewsGroupList = "zj/json/getNewsGroupList.action";
	public static final String API_getNewsList = "zj/json/getNewsList.action";
	public static final String API_getNewsDetails = "zj/json/getNewsDetails.action";
	public static final String API_getMerchantGroupList = "zj/json/getMerchantGroupList.action";
	
	public static final String API_startPicture = "zj/json/startPicture.action";
	public static final String API_guidePicturesList = "zj/json/guidePicturesList.action";
	
	public static final String API_getActiveCitiesList = "zj/json/getActiveCitiesList.action";
	public static final String API_getCitiesStreetList = "zj/json/getCitiesStreetList.action";
	
	public static final String API_loginForUser = "zj/json/loginForUser.action";
	public static final String API_regForUser = "zj/json/regForUser.action";
	public static final String API_regForGreenLife = "zj/json/regForGreenLife.action";
	
	
	public static final String API_sendValidateCode = "zj/json/sendValidateCode.action";
	public static final String API_getInfoForUser = "zj/json/getInfoForUser.action";
	public static final String API_updatePassForUser = "zj/json/updatePassForUser.action";
	public static final String API_findForgetPassForUser = "zj/json/findForgetPassForUser.action";
	public static final String API_findForgetPassForGreenLife = "zj/json/findForgetPassForGreenLife.action";
	
	public static final String API_getMyDeliveryAddressList = "zj/json/getMyDeliveryAddressList.action";
	public static final String API_getMyDeliveryAddressDetails = "zj/json/getMyDeliveryAddressDetails.action";
	public static final String API_addDeliveryAddress = "zj/json/addDeliveryAddress.action";
	public static final String API_updateDeliveryAddress = "zj/json/updateDeliveryAddress.action";
	public static final String API_deleteMyDeliveryAddress = "zj/json/deleteMyDeliveryAddress.action";
	public static final String API_getMyCommentsList = "zj/json/getMyCommentsList.action";
	public static final String API_getMyCollectionMerchantList = "zj/json/getMyCollectionMerchantList.action";
	public static final String API_getMyCollectionDeshesList = "zj/json/getMyCollectionDeshesList.action";
	public static final String API_addItemToMyCollection = "zj/json/addItemToMyCollection.action";
	public static final String API_deleteItemFromMyCollection = "zj/json/deleteItemFromMyCollection.action";
	public static final String API_getMyCouponList = "zj/json/getMyCouponList.action";
	public static final String API_validateMyCoupon = "zj/json/validateMyCoupon.action";
	public static final String API_getMyCouponDetail = "zj/json/getMyCouponDetail.action";
	
	
	public static final String API_getMerchantsList = "zj/json/getMerchantsList.action";
	public static final String API_getMerchantsDetailsInfo = "zj/json/getMerchantsDetailsInfo.action";
	public static final String API_getMerchantUserNotice = "zj/json/getMerchantUserNotice.action";
	public static final String API_getMerchantsListForMap = "zj/json/getMerchantsListForMap.action";
	public static final String API_getMerchantDishesGroupList = "zj/json/getMerchantDishesGroupList.action";
	public static final String API_getMerchantDishesList = "zj/json/getMerchantDishesList.action";
	public static final String API_getMerchantDishesDetails = "zj/json/getMerchantDishesDetails.action";
	public static final String API_getMerchantScores = "zj/json/getMerchantScores.action";
	public static final String API_getMerchantCommentsList = "zj/json/getMerchantCommentsList.action";
	public static final String API_doScoreAndCommentToMerchant = "zj/json/doScoreAndCommentToMerchant.actionn";
	public static final String API_doComplainMerchantByOrder = "zj/json/doComplainMerchantByOrder.action";
	
	public static final String API_getMyOrderList = "zj/json/getMyOrderList.action";
	public static final String API_getMyOrderStatusList = "zj/json/getMyOrderStatusList.action";
	public static final String API_getMyOrderDetailsForUser = "zj/json/getMyOrderDetailsForUser.action";
	public static final String API_generateOrder = "zj/json/generateOrder.action";
	public static final String API_cancelOrder = "zj/json/cancelOrder.action";
	public static final String API_updateOrder = "zj/json/updateOrder.action";
	
	
	public static final String API_searchMerchantList = "zj/json/searchMerchantList.action";
	public static final String API_searchDishesList = "zj/json/searchDishesList.action";
	public static final String API_searchDishesGroupList = "zj/json/searchDishesGroupList.action";
	public static final String API_recommendMerchantList = "zj/json/recommendMerchantList.action";
	
	
	public static final String API_getTopNews = "zj/json/getTopNews.action";
	
	
	//配送员
	public static final String API_getNearbyOrdersList = "zj/json/getNearbyOrdersList.action";
	public static final String API_getDeliveryerOrdersList = "zj/json/getDeliveryerOrdersList.action";
	public static final String API_setDeliveryOrderStatus = "zj/json/setDeliveryOrderStatus.action";
	public static final String API_setDeliveryerStatus = "zj/json/setDeliveryerStatus.action";
	
	//商家
//	public static final String API_submitSuggestion = "zj/json/submitSuggestion.action";
//	public static final String API_validateApp = "zj/json/validateApp.action";
//	public static final String API_validateApp = "zj/json/validateApp.action";
	public static final String API_setMerchantOrderStatus = "zj/json/setMerchantOrderStatus.action";
	public static final String API_setMerchantBusinesss = "zj/json/setMerchantBusiness.action";
	
	//Common
	public static final String API_submitSuggestion = "zj/json/submitSuggestion.action";
	public static final String API_validateApp = "zj/json/validateApp.action";
	public static final String API_getLatestVersion = "zj/json/getLatestVersion.action";
	
	

	
	public static String getApiUrl(String apiName) {
		return  HOST + "/" + apiName;
	}
	
	

	private static String getDataFromServer(HashMap<String, String> map,String url) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			parameters.add(new BasicNameValuePair(entry.getKey()+"", entry.getValue()+""));
		}
		return  HttpTools.post(url, parameters);
	}
	
	  


}
