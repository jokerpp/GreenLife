package cn.crane.application.greenlife.utils;

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class RefreshUtils {
	private static HashMap<String, String> hashMap;

	/*
	 * 刷新
	 */
	public static final String REFRESH_ACTION = "org.kaede.app.refresh";
	public static final String REFRESH_TYPE = "refresh_type";
	public static final String REFRESH_BUNDLE = "refresh_bundle";

	public static final int REFRESH_TYPE_ADDRESS_CHOOSE = 0; // 地址选择
	public static final int REFRESH_TYPE_ADDRESS_CHANGE = 1; // 地址改变
	public static final int REFRESH_TYPE_USER_CHANGE = 2; // 用户改变
	public static final int REFRESH_TYPE_RELATION_CHANGE = 3; // 关系改变
	public static final int REFRESH_TYPE_GRADE_CHANGE = 4; // 评分改变
	public static final int REFRESH_TYPE_ICON_CHANGE = 5; // 头像改变
	public static final int REFRESH_TYPE_ZONE_CHANGE = 6; // 区域改变
	public static final int REFRESH_TYPE_LOCATION_CHANGE = 7; // 位置改变
	public static final int REFRESH_TYPE_SHARE_SUCCESS = 8; // 分享成功
	public static final int REFRESH_TYPE_WECHAT_CHANGE = 9; // 微信改变
	public static final int REFRESH_TYPE_PAY_WECHAT_FAIL = 10; // 微信支付失败
	public static final int REFRESH_TYPE_PAY_WECHAT_SUCCESS = 11; // 微信支付成功
	public static final int REFRESH_TYPE_PAY_SUCCESS = 12; // 支付成功
	public static final int REFRESH_TYPE_ORDER_STATUS_CHANGE = 13; // 订单状态改变
	
	public static final int REFRESH_TYPE_COUNTRY_CODE_CHANGED = 14; // 订单状态改变

	public static final String REFRESH_BUNDLE_ADDRESS_INFO = "address_info";
	public static final String REFRESH_BUNDLE_LOCATION_INFO = "location_info";
	public static final String REFRESH_BUNDLE_GRADE_SCORE = "grade_score";
	public static final String REFRESH_BUNDLE_USER_ID = "user_id";
	public static final String REFRESH_BUNDLE_USER_RELATION = "user_relation";
	public static final String REFRESH_BUNDLE_ICON_BYTES = "icon_bytes";
	public static final String REFRESH_BUNDLE_ZONE_PROVINCE = "zone_province";
	public static final String REFRESH_BUNDLE_ZONE_CITY = "zone_city";
	public static final String REFRESH_BUNDLE_ZONE_COUNTY = "zone_county";
	public static final String REFRESH_BUNDLE_WECHAT_CODE = "wechat_code";
	public static final String REFRESH_BUNDLE_ORDER_ID = "order_id";
	public static final String REFRESH_BUNDLE_ORDER_STATUS = "order_status";
	
	public static final String REFRESH_BUNDLE_COUNTRY_INFO = "country_info";

	
//	// 国家改变
//	public static void sendRefreshCountryCodeChange(Context context,
//			CountryInfo countryInfo) {
//		hashMap = new HashMap<String, String>();
//		hashMap.put(REFRESH_BUNDLE_COUNTRY_INFO, gson.toJson(countryInfo));
//		sendRefreshReceiver(context,
//				RefreshUtils.REFRESH_TYPE_COUNTRY_CODE_CHANGED, hashMap);
//	}
	
	// 分享成功
		public static void sendRefreshShareSuccess(Context context) {
			sendRefreshReceiver(context, RefreshUtils.REFRESH_TYPE_SHARE_SUCCESS,
					null);
		}
	
	// 微信改变
	public static void sendRefreshWechatChange(Context context,
			String wechatCode) {
		hashMap = new HashMap<String, String>();
		hashMap.put(REFRESH_BUNDLE_WECHAT_CODE, wechatCode);
		sendRefreshReceiver(context, RefreshUtils.REFRESH_TYPE_WECHAT_CHANGE,
				hashMap);
	}

	// 微信支付失败
	public static void sendRefreshPayWechatFail(Context context) {
		sendRefreshReceiver(context, RefreshUtils.REFRESH_TYPE_PAY_WECHAT_FAIL,
				null);
	}

	// 微信支付成功
	public static void sendRefreshPayWechatSuccess(Context context) {
		sendRefreshReceiver(context,
				RefreshUtils.REFRESH_TYPE_PAY_WECHAT_SUCCESS, null);
	}

	// 支付成功
	public static void sendRefreshPaySuccess(Context context) {
		sendRefreshReceiver(context, RefreshUtils.REFRESH_TYPE_PAY_SUCCESS,
				null);
	}

	/**
	 * 发送刷新广播
	 */
	private static void sendRefreshReceiver(Context context, int refreshType,
			HashMap<String, String> map) {
		Bundle bundle = new Bundle();
		if (map != null) {
			for (String key : map.keySet()) {
				bundle.putString(key, map.get(key));
			}
		}

		Intent intent = new Intent(REFRESH_ACTION);
		intent.putExtra(REFRESH_TYPE, refreshType);
		intent.putExtra(REFRESH_BUNDLE, bundle);
		context.sendBroadcast(intent);
	}
}
