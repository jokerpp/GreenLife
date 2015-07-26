package cn.crane.application.greenlife.utils.pay.wechat;

import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import cn.crane.application.greenlife.Constant;

public class WechatProduct {
	public static String nonceStr, packageValue;
	public static long timeStamp;

	public static String genProductArgs(String productName, float orderPrice) {
		JSONObject json = new JSONObject();
		try {
			json.put("appid", Constant.WX_APP_ID);
			String traceId = getTraceId();
			json.put("traceid", traceId);
			nonceStr = genNonceStr();
			json.put("noncestr", nonceStr);

			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("bank_type", "WX"));
			packageParams.add(new BasicNameValuePair("body", productName));
			packageParams.add(new BasicNameValuePair("fee_type", "1"));
			packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
			packageParams.add(new BasicNameValuePair("notify_url",
					"http://121.14.73.81:8080/agent/wxpay/payNotifyUrl.jsp"));
			packageParams.add(new BasicNameValuePair("out_trade_no",
					genOutTradNo()));
			packageParams.add(new BasicNameValuePair("partner",
					Constant.WX_PARTNER_ID));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",
					"196.168.1.1"));
			packageParams.add(new BasicNameValuePair("total_fee", String
					.valueOf((int) (orderPrice * 100))));
			packageValue = genPackage(packageParams);

			json.put("package", packageValue);
			timeStamp = genTimeStamp();
			json.put("timestamp", timeStamp);

			List<NameValuePair> signParams = new LinkedList<NameValuePair>();
			signParams.add(new BasicNameValuePair("appid", Constant.WX_APP_ID));
			signParams
					.add(new BasicNameValuePair("appkey", Constant.WX_PAY_KEY));
			signParams.add(new BasicNameValuePair("noncestr", nonceStr));
			signParams.add(new BasicNameValuePair("package", packageValue));
			signParams.add(new BasicNameValuePair("timestamp", String
					.valueOf(timeStamp)));
			signParams.add(new BasicNameValuePair("traceid", traceId));
			json.put("app_signature", genSign(signParams));

			json.put("sign_method", "sha1");
		} catch (Exception e) {
			return null;
		}
		return json.toString();
	}

	public static String genSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (; i < params.size() - 1; i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append(params.get(i).getName());
		sb.append('=');
		sb.append(params.get(i).getValue());
		String sha1 = sha1(sb.toString());
		return sha1;
	}

	/**
	 * traceId 由开发者自定义，可用于订单的查询与跟踪，建议根据支付用户信息生成此id
	 */
	private static String getTraceId() {
		return "crestxu_" + genTimeStamp();
	}

	private static String genNonceStr() {
		Random random = new Random();
		return getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());
	}

	/**
	 * 注意：商户系统内部的订单号,32个字符内、可包含字母,确保在商户系统唯一
	 */
	private static String genOutTradNo() {
		Random random = new Random();
		return getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());
	}

	private static String genPackage(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constant.WX_PARTNER_KEY);
		// 进行md5摘要前，params内容为原始内容，未经过url encode处理
		String packageSign = getMessageDigest(sb.toString().getBytes())
				.toUpperCase(Locale.ENGLISH);
		return URLEncodedUtils.format(params, "utf-8") + "&sign=" + packageSign;
	}

	private static long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	private static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	private static String sha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}
