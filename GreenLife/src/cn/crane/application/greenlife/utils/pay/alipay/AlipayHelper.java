package cn.crane.application.greenlife.utils.pay.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


import android.app.Activity;

import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.model.Result;

import com.alipay.sdk.app.PayTask;

public class AlipayHelper {
	/**
	 * 调用SDK支付
	 * 
	 */
	public static Result sendAliPay(Activity activity, String payInfo) {
		PayTask alipay = new PayTask(activity);
		String result = alipay.pay(payInfo);
		PayResult payResult = new PayResult(result);
		String resultStatus = payResult.getResultStatus();
		Result baseInfo = new Result();
		if ("9000".equals(resultStatus)) {
			baseInfo.setResultNumber(Constant.RETURN_CODE_SUCCESS);
			baseInfo.setResultMessage("支付成功");
		} else {
			if ("8000".equals(resultStatus)) {
				baseInfo.setResultNumber(Constant.RETURN_CODE_SUCCESS);
				baseInfo.setResultMessage("支付结果确认中");
			} else {
				baseInfo.setResultNumber(Constant.RETURN_CODE_ERROR);
				baseInfo.setResultMessage("支付失败");
			}
		}
		return baseInfo;
	}

	/**
	 * 查询终端设备是否存在支付宝认证账户
	 */
	public static boolean hasAliAccount(Activity activity) {
		PayTask payTask = new PayTask(activity);
		boolean isExist = payTask.checkAccountIfExist();
		return isExist;
	}

	/**
	 * 获取SDK版本号
	 */
	public static String getAliSDKVersion(Activity activity) {
		PayTask payTask = new PayTask(activity);
		String version = payTask.getVersion();
		return version;
	}

	public static String getPayInfo(String title, String body, float price) {
		// 订单
		String orderInfo = getOrderInfo(title, body, price);
		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 完整的符合支付宝参数规范的订单信息
		String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
		return payInfo;
	}

	/**
	 * 创建订单信息
	 */
	private static String getOrderInfo(String title, String body, float price) {
		// 签约合作者身份ID
		StringBuffer orderBuffer = new StringBuffer("partner=" + "\""
				+ Constant.ALIPAY_PARTNER + "\"");
		// 签约卖家支付宝账号
		orderBuffer.append("&seller_id=" + "\"" + Constant.ALIPAY_SELLER + "\"");
		// 商户网站唯一订单号
		orderBuffer.append("&out_trade_no=" + "\"" + getOutTradeNo() + "\"");
		// 商品名称
		orderBuffer.append("&subject=" + "\"" + title + "\"");
		// 商品详情
		orderBuffer.append("&body=" + "\"" + body + "\"");
		// 商品金额
		orderBuffer.append("&total_fee=" + "\"" + price + "\"");
		// 服务器异步通知页面路径
		orderBuffer.append("&notify_url=" + "\""
				+ "http://notify.msp.hk/notify.htm" + "\"");
		// 服务接口名称， 固定值
		orderBuffer.append("&service=\"mobile.securitypay.pay\"");
		// 支付类型， 固定值
		orderBuffer.append("&payment_type=\"1\"");
		// 参数编码， 固定值
		orderBuffer.append("&_input_charset=\"utf-8\"");
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderBuffer.append("&it_b_pay=\"30m\"");
		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderBuffer.append("&extern_token=" + "\"" + extern_token + "\"");
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderBuffer.append("&return_url=\"m.alipay.com\"");
		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderBuffer.append("&paymethod=\"expressGateway\"");
		return orderBuffer.toString();
	}

	/**
	 * 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * 对订单信息进行签名
	 */
	private static String sign(String content) {
		return SignUtils.sign(content, Constant.ALIPAY_RSA_PRIVATE);
	}

	/**
	 * 获取签名方式
	 */
	private static String getSignType() {
		return "sign_type=\"RSA\"";
	}
}
