package cn.crane.application.greenlife.model.result;

import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 24, 2015 9:33:40 PM
 * 
 */
public class RE_Login extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062434928064674478L;
	// 用户ID加密 userToken 可以为空 String
	// 商户ID嘉宾 merchantToken String 商户专有
	private String userToken;
	private String merchantToken;

	public RE_Login() {
		// TODO Auto-generated constructor stub
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getMerchantToken() {
		return merchantToken;
	}

	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

}
