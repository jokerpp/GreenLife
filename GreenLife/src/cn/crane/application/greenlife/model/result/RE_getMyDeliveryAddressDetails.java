package cn.crane.application.greenlife.model.result;

import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 26, 2015 8:07:06 PM
 * 
 */
public class RE_getMyDeliveryAddressDetails extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5903242947484199723L;

//	加密ID 	deliveryToken		String	
//	联系人	contactName		String	
//	性别	gender		int	1男，2女
//	手机号码	mobile		String	
//	送餐地址	address		String	
//	门牌号	doorCode		String	
//	经度	longitude		Double	
//	纬度	latitude		Double	
//	默认地址	isDefault		int	0,非默认；1默认
	
	private String deliveryToken;
	private String contactName;
	private String gender;
	private String mobile;
	private String address;
	private String doorCode;
	private String longitude;
	private String latitude;
	private String isDefault;
	
	public RE_getMyDeliveryAddressDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDeliveryToken() {
		return deliveryToken;
	}

	public void setDeliveryToken(String deliveryToken) {
		this.deliveryToken = deliveryToken;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoorCode() {
		return doorCode;
	}

	public void setDoorCode(String doorCode) {
		this.doorCode = doorCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public boolean isDefault() {
		return API_Contant.TRUE.equalsIgnoreCase(isDefault);
	}
	public boolean isMan() {
		return API_Contant.TRUE.equalsIgnoreCase(gender);
	}
}
