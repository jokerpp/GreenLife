package cn.crane.application.greenlife.utils.pay.wechat;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;

import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.R;
import cn.crane.framework.utils.DialogSelector;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WechatHelper {
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

	private static final int THUMB_SIZE = 75;
	private static WechatHelper wechatHelper;
	private IWXAPI wxApi;

	public static WechatHelper getInstance(Activity activity) {
		if (null == wechatHelper) {
			synchronized (WechatHelper.class) {
				if (null == wechatHelper) {
					wechatHelper = new WechatHelper(activity);
				}
			}
		}
		return wechatHelper;
	}

//	/**
//	 * 绑定
//	 */
//	public void bundlingWechat(Activity activity) {
//		if (!canWechat()) {
//			DialogUtils.getInstance().showTips(activity,
//					activity.getString(R.string.tip_wechat));
//			return;
//		}
//
//		SendAuth.Req req = new SendAuth.Req();
//		req.scope = Constant.WX_SCOPE;
//		req.state = String.valueOf(System.currentTimeMillis());
//		wxApi.sendReq(req);
//	}
//
//	public String getOpenid(Activity activity, String code) {
//		String openid = null;
//		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
//				+ Constant.WX_APP_ID + "&secret=" + Constant.WX_APP_SECRET
//				+ "&code=" + code + "&grant_type=authorization_code";
//		HttpGet httpGet = new HttpGet(url);
//		byte[] data = HttpProvider.getInstance().querryByHttpGet(activity,
//				httpGet);
//		try {
//			if (data != null && data.length > 0) {
//				String json = new String(data);
//				JSONObject jsonObject = new JSONObject(json);
//				openid = jsonObject.getString("openid");
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return openid;
//	}

//	public void shareToWechat(Activity activity, int type, ShareInfo info,
//			Bitmap shareBitmap) {
//		if (!canWechat()) {
//			DialogUtils.getInstance().showTips(activity,
//					activity.getString(R.string.tip_wechat));
//			return;
//		}
//
//		WXWebpageObject pageObject = new WXWebpageObject();
//		pageObject.webpageUrl = info.getJump_url()
//				+ Configs.getSuffix(activity, info.getType());
//		WXMediaMessage msg = new WXMediaMessage();
//		msg.mediaObject = pageObject;
//		msg.title = info.getTitle();
//		msg.description = info.getContent();
//		Bitmap thumbBmp = Bitmap.createScaledBitmap(shareBitmap, THUMB_SIZE,
//				THUMB_SIZE, true);
//		msg.thumbData = Utils.Bitmap2Bytes(thumbBmp, true);
//
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = String.valueOf(System.currentTimeMillis());
//		req.message = msg;
//		req.scene = type;
//		wxApi.sendReq(req);
//	}

	public void sendWechatPayReq(Activity activity, String json) {
		try {
			if (!canPay()) {
//				DialogUtils.getInstance().showTips(activity,
//						"您的微信版本不支持微信支付, 请升级微信");
				DialogSelector.showAlertDialog(activity, "您的微信版本不支持微信支付, 请升级微信", null);
				return;
			}
			JSONObject jsonObject = new JSONObject(json);
			PayReq req = new PayReq();
			req.appId = Constant.WX_APP_ID;
			req.partnerId = Constant.WX_PARTNER_ID;
			req.prepayId = jsonObject.getString("prepayid");
			req.nonceStr = jsonObject.getString("noncestr");
			req.timeStamp = jsonObject.getString("timestamp");
			req.packageValue = jsonObject.getString("package");
			req.sign = jsonObject.getString("sign");
			req.extData = "app data";
			wxApi.sendReq(req);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private WechatHelper(Activity activity) {
		wxApi = WXAPIFactory.createWXAPI(activity, Constant.WX_APP_ID, true);
		wxApi.registerApp(Constant.WX_APP_ID);
	}

	private boolean canWechat() {
		int wxVersion = wxApi.getWXAppSupportAPI();
		return wxVersion >= TIMELINE_SUPPORTED_VERSION ? true : false;
	}

	private boolean canPay() {
		int wxVersion = wxApi.getWXAppSupportAPI();
		return wxVersion >= Build.PAY_SUPPORTED_SDK_INT ? true : false;
	}
}
