package cn.crane.application.greenlife.utils.pay;


import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.utils.pay.alipay.AlipayHelper;
import cn.crane.application.greenlife.utils.pay.wechat.WechatHelper;
import android.app.Activity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jul 26, 2015 3:00:39 PM
 * 
 */
public class PayUtils {

	/**
	 * Wechat
	 * @param activity
	 * @param json
	 */
	public static void sendWechatPayReq(Activity activity, String json) {
		WechatHelper.getInstance(activity).sendWechatPayReq(activity, json);
	}

	/**
	 * Alipay
	 */
	public static Result sendAliPay(Activity activity, String payInfo) {
		return AlipayHelper.sendAliPay(activity, payInfo);
	}
}
