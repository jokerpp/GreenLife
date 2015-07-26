package cn.crane.application.greenlife.model.result;

import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 23, 2015 11:35:45 PM
 * 
 */
public class RE_getMerchantsDetailsInfo extends Result {
	public static final String TAG = "RE_getMerchantsDetailsInfo";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1713297986860856144L;
	// "address": "清水路102号底商",
	// "bussinessTimeStr": "",
	// "deliveryPrice": 0,
	// "foodBoxPrice": 0,
	// "isCollectStatus": 0,
	// "juli": "",
	// "latitude": 0,
	// "longitude": 0,
	// "merchantBgPic": "",
	// "merchantName": "",
	// "merchantPictrue":
	// "http://42.96.167.31:8080/zj/uploadfile/background/barber/20150409215422193.jpg",
	// "merchantToken": "11",
	// "note": "啊啊啊啊",
	// "orderMinPrice": 0,
	// "resultMessage": "操作成功",
	// "resultNumber": 0,
	// "score": 0,
	// "status": 0,
	// "telephone": "010-58019598",
	// "tips": "请提前30分钟下单"
	private String address;
	private String bussinessTimeStr;
	private String deliveryPrice;
	private String foodBoxPrice;
	private String isCollectStatus;
	private String juli;
	private String latitude;
	private String longitude;
	private String merchantBgPic;
	private String merchantName;
	private String merchantPictrue;
	private String merchantToken;
	private String note;
	private String orderMinPrice;
	private String score;
	private String status;
	private String telephone;
	private String tips;
	
	private String isSuportDiscount;
	private String isSuportFree;
	private String isSuportGive;
	private String isSuportNew;

	private String isSuportNewStr;
	private String isSuportFreeStr;
	private String isSuportDiscountStr;
	private String isSuportGiveStr;

	public RE_getMerchantsDetailsInfo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getIsSuportDiscount() {
		return isSuportDiscount;
	}



	public void setIsSuportDiscount(String isSuportDiscount) {
		this.isSuportDiscount = isSuportDiscount;
	}



	public String getIsSuportFree() {
		return isSuportFree;
	}



	public void setIsSuportFree(String isSuportFree) {
		this.isSuportFree = isSuportFree;
	}



	public String getIsSuportGive() {
		return isSuportGive;
	}



	public void setIsSuportGive(String isSuportGive) {
		this.isSuportGive = isSuportGive;
	}



	public String getIsSuportNew() {
		return isSuportNew;
	}



	public void setIsSuportNew(String isSuportNew) {
		this.isSuportNew = isSuportNew;
	}



	public String getIsSuportNewStr() {
		return isSuportNewStr;
	}



	public void setIsSuportNewStr(String isSuportNewStr) {
		this.isSuportNewStr = isSuportNewStr;
	}



	public String getIsSuportFreeStr() {
		return isSuportFreeStr;
	}



	public void setIsSuportFreeStr(String isSuportFreeStr) {
		this.isSuportFreeStr = isSuportFreeStr;
	}



	public String getIsSuportDiscountStr() {
		return isSuportDiscountStr;
	}



	public void setIsSuportDiscountStr(String isSuportDiscountStr) {
		this.isSuportDiscountStr = isSuportDiscountStr;
	}



	public String getIsSuportGiveStr() {
		return isSuportGiveStr;
	}



	public void setIsSuportGiveStr(String isSuportGiveStr) {
		this.isSuportGiveStr = isSuportGiveStr;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBussinessTimeStr() {
		return bussinessTimeStr;
	}

	public void setBussinessTimeStr(String bussinessTimeStr) {
		this.bussinessTimeStr = bussinessTimeStr;
	}

	public String getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public String getFoodBoxPrice() {
		return foodBoxPrice;
	}

	public void setFoodBoxPrice(String foodBoxPrice) {
		this.foodBoxPrice = foodBoxPrice;
	}

	public String getIsCollectStatus() {
		return isCollectStatus;
	}

	public void setIsCollectStatus(String isCollectStatus) {
		this.isCollectStatus = isCollectStatus;
	}

	public String getJuli() {
		return juli;
	}

	public void setJuli(String juli) {
		this.juli = juli;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMerchantBgPic() {
		return merchantBgPic;
	}

	public void setMerchantBgPic(String merchantBgPic) {
		this.merchantBgPic = merchantBgPic;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantPictrue() {
		return merchantPictrue;
	}

	public void setMerchantPictrue(String merchantPictrue) {
		this.merchantPictrue = merchantPictrue;
	}

	public String getMerchantToken() {
		return merchantToken;
	}

	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderMinPrice() {
		return orderMinPrice;
	}

	public void setOrderMinPrice(String orderMinPrice) {
		this.orderMinPrice = orderMinPrice;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
	public boolean isCollected() {
		return "1".equalsIgnoreCase(isCollectStatus);
	}
	
	public boolean isOpenning() {
		return API_Contant.MERCHANT_STATE_OPENING.equalsIgnoreCase(status);
	}
	
	
	public boolean isSuportDiscount() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportDiscount);
	}

	public boolean isSuportFree() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportFree);
	}

	public boolean isSuportGive() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportGive);
	}


	public boolean isSuportNew() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportNew);
	}

	
}
