package cn.crane.application.greenlife.model.result;

import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 24, 2015 10:29:48 PM
 * 
 */
public class RE_getInfoForUser extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3458044677262007043L;
	// 用户token userToken String
	// 手机 mobile String
	// 用户名 nickname String
	// 头像 avatarPicture String

	private String userToken;
	private String mobile;
	private String nickname;
	private String avatarPicture;

	public RE_getInfoForUser() {
		// TODO Auto-generated constructor stub
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarPicture() {
		return avatarPicture;
	}

	public void setAvatarPicture(String avatarPicture) {
		this.avatarPicture = avatarPicture;
	}

}
