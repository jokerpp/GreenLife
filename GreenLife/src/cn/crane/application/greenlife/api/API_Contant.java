package cn.crane.application.greenlife.api;

import java.util.Date;
import java.util.HashMap;


public class API_Contant {
	public static final String DEVICE_ANDROID = "1";
	public static final String DEVICE_IOS = "2";
	
	public static final String USERTYPE_USER = "0";
	public static final String USERTYPE_MERCHANT = "4";
	public static final String USERTYPE_DELIVERY = "6";
	
	public static final String MERCHANT_TYPE_FOOD = "1";
	public static final String MERCHANT_TYPE_LIFE = "2";
	public static final String MERCHANT_TYPE_HOTEL = "3";
	
	//1营业中、2预定中、0休息中
	public static final String MERCHANT_STATE_OPENING = "1";
	public static final String MERCHANT_STATE_ORDERING = "2";
	public static final String MERCHANT_STATE_REST = "0";
	
	
	public static final String PAYTYPE_DEFAULT = "1";
	public static final String PAYTYPE_ALIPAY = "2";
	public static final String PAYTYPE_WECHAT = "3";
	
	public static final String COUPON_UNUSED = "1";
	public static final String COUPON_OUTDATE = "2";
	public static final String COUPON_USED = "3";
	
	
	public static final String GENDER_MAN = "1";
	public static final String GENDER_WOMAN = "2";
	
	public static final String COLLECT_MERCHANT = "1";
	public static final String COLLECT_FOOD = "2";
	
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	
	
	public static final String TYPE = "type";

	/**
	 * Userinfo changed
	 */
	public static final String ACTION_USERINFO_CHANGED = "action_userinfo_changed";

	public static String getSystemToken() {
		return "1";
	}

	public static String getSystemOSName() {
		return android.os.Build.MANUFACTURER;
	}
	
	public static String getTimeStamp() {
		return new Date().getTime() +"";
	}
	
	/**
	 * Common Parameter
	 * @return
	 */
	
	public static HashMap<String, String> getCommonParameter() {
//		项目编号	zjId	必填	String	1-用户端、2-配送端、3-商户端
//		客户端类型	clientType	必填	int	"1-Android；2-ios;
//		3-ipad;4-pad"
//		客户端版名称	versionName	必填	String	
//		客户端版本号	versionLevel	必填	String	
//		经度	longitudeStr	必填	String	
//		纬度	latitudeStr	必填	String	
//		手机信息	mobileInfo	必填	String	
//		UI页码信息	pageCode	必填	String	
		HashMap<String, String> hashMap = new HashMap<String, String>();
//		hashMap.put("zjId", "1");
//		hashMap.put("clientType", DEVICE_ANDROID);
//		hashMap.put("versionName", App.getVersionName());
//		hashMap.put("versionLevel", App.getVersionCode() + "");
//		hashMap.put("longitudeStr", "");
//		hashMap.put("latitudeStr", "");
//		hashMap.put("mobileInfo", App.getDeviceNo());
//		hashMap.put("pageCode","");
		return hashMap;
	}

}
