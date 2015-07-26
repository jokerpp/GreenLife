package cn.crane.application.greenlife.utils.pay.wechat;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;

import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.model.Result;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WechatPayHelper {
	private static WechatPayHelper wechatPayHelper;
	private IWXAPI wxApi;

	public Result getPrepayId(Activity activity, String productName,
			float orderPrice) {
		Result Result = canPay();
		if (!Result.isSuccess()) {
			return Result;
		}
		Result = getAccessToken(activity);
		if (!Result.isSuccess()) {
			return Result;
		}
		Result = getPrepayId(activity, Result.getData(), productName,
				orderPrice);
		return Result;
	}

	public void sendPayReq(String prepayId) {
		PayReq req = new PayReq();
		req.appId = Constant.WX_APP_ID;
		req.partnerId = Constant.WX_PARTNER_ID;
		req.prepayId = prepayId;
		req.nonceStr = WechatProduct.nonceStr;
		req.timeStamp = String.valueOf(WechatProduct.timeStamp);
		req.packageValue = "Sign=" + WechatProduct.packageValue;
		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", req.appId));
		signParams.add(new BasicNameValuePair("appkey", Constant.WX_PAY_KEY));
		signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
		signParams.add(new BasicNameValuePair("package", req.packageValue));
		signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
		signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
		signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
		req.sign = WechatProduct.genSign(signParams);
		wxApi.sendReq(req);
	}

	public static WechatPayHelper getInstance(Activity activity) {
		if (null == wechatPayHelper) {
			synchronized (WechatHelper.class) {
				if (null == wechatPayHelper) {
					wechatPayHelper = new WechatPayHelper(activity);
				}
			}
		}
		return wechatPayHelper;
	}

	private WechatPayHelper(Activity activity) {
		wxApi = WXAPIFactory.createWXAPI(activity, Constant.WX_APP_ID, true);
		wxApi.registerApp(Constant.WX_APP_ID);
	}

	private Result canPay() {
		Result info = new Result();
		int wxVersion = wxApi.getWXAppSupportAPI();
		if (wxVersion >= Build.PAY_SUPPORTED_SDK_INT) {
			info.setResultNumber(Constant.RETURN_CODE_SUCCESS);
		} else {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("您的微信版本不支持微信支付, 请升级");
		}
		return info;
	}

	private Result getAccessToken(Activity activity) {
		Result info = new Result();
		String url = String
				.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
						Constant.WX_APP_ID, Constant.WX_APP_SECRET);
		byte[] data = WechatUtils.httpGet(url);
		if (null == data || 0 == data.length) {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 网络异常!");
			return info;
		}
		String content = new String(data);
		if (null == content || "".equals(content)) {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 未知错误!");
			return info;
		}
		try {
			JSONObject json = new JSONObject(content);
			if (!json.has("access_token")) {
				info.setResultNumber(Constant.RETURN_CODE_FAILED);
				info.setResultMessage(json.getString("retmsg"));
				return info;
			}
			info.setResultNumber(Constant.RETURN_CODE_SUCCESS);
			info.setData(json.getString("access_token"));
			return info;
			// int expiresIn = json.getInt("expires_in");
		} catch (Exception e) {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 未知错误!");
			return info;
		}
	}

	private Result getPrepayId(Activity activity, String accessToken,
			String productName, float orderPrice) {
		Result info = new Result();
		String url = String.format(
				"https://api.weixin.qq.com/pay/genprepay?access_token=%s",
				accessToken);
		String entity = WechatProduct.genProductArgs(productName, orderPrice);
		byte[] data = WechatUtils.httpPost(url, entity);
		if (null == data || 0 == data.length) {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 网络异常!");
			return info;
		}
		String content = new String(data);
		if (null == content || "".equals(content)) {
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 未知错误!");
			return info;
		}
		try {
			JSONObject json = new JSONObject(content);
			if (!json.has("prepayid")) {
				info.setResultNumber(Constant.RETURN_CODE_FAILED);
				info.setResultMessage(json.getString("errmsg"));
				return info;
			}
			info.setResultNumber(Constant.RETURN_CODE_SUCCESS);
			info.setData(json.getString("prepayid"));
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			info.setResultNumber(Constant.RETURN_CODE_ERROR);
			info.setResultMessage("对不起! 未知错误!");
			return info;
		}
	}
}
