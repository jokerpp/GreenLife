package cn.crane.application.greenlife.model.result;

import java.util.List;

import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jul 4, 2015 5:48:55 PM
 * 
 */
public class RE_getMyOrderDetailsForUser extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4310557885067764135L;
	// "address": "beijing",
	// "contactName": "crane",
	// "couponValue": "",
	// "deliveryPrice": "null",
	// "deliveryType": "餐到付款",
	// "foodBoxPrice": "null",
	// "gender": "先生",
	// "insertDateTimeStr": "2015-06-28 23:43:52",
	// "merchantName": "川府凉粉",
	// "merchantNote": "",
	// "merchantToken": "13",
	// "mobile": "18877777777",
	// "newOrderPrice": "4444.0",
	// "oldOrderPrice": "4444.0",
	// "orderToken": "11",
	// "payType": "",
	// "requestDeliveryDateTimeStr": "2015-07-01 12:45:00",
	// "resultList": [{
	// "amount": "4.0",
	// "dishesName": "",
	// "dishesToken": "10",
	// "preferentialPrice": "null",
	// "primePrice": "null"
	// }],
	// "resultMessage": "操作成功",
	// "resultNumber": 0,
	// "telephone": "010-58019588",
	// "userNote": "nothing"
	private String address;
	private String contactName;
	private String couponValue;
	private String deliveryPrice;
	private String foodBoxPrice;
	private String gender;
	private String insertDateTimeStr;
	private String merchantName;
	private String merchantNote;
	private String merchantToken;
	private String mobile;
	private String newOrderPrice;
	private String oldOrderPrice;
	private String orderToken;
	private String payType;
	private String requestDeliveryDateTimeStr;
	private String telephone;
	private String userNote;
	
	private List<FoodItem> resultList;

	public RE_getMyOrderDetailsForUser() {
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInsertDateTimeStr() {
		return insertDateTimeStr;
	}

	public void setInsertDateTimeStr(String insertDateTimeStr) {
		this.insertDateTimeStr = insertDateTimeStr;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantNote() {
		return merchantNote;
	}

	public void setMerchantNote(String merchantNote) {
		this.merchantNote = merchantNote;
	}

	public String getMerchantToken() {
		return merchantToken;
	}

	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNewOrderPrice() {
		return newOrderPrice;
	}

	public void setNewOrderPrice(String newOrderPrice) {
		this.newOrderPrice = newOrderPrice;
	}

	public String getOldOrderPrice() {
		return oldOrderPrice;
	}

	public void setOldOrderPrice(String oldOrderPrice) {
		this.oldOrderPrice = oldOrderPrice;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRequestDeliveryDateTimeStr() {
		return requestDeliveryDateTimeStr;
	}

	public void setRequestDeliveryDateTimeStr(String requestDeliveryDateTimeStr) {
		this.requestDeliveryDateTimeStr = requestDeliveryDateTimeStr;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public List<FoodItem> getResultList() {
		return resultList;
	}

	public void setResultList(List<FoodItem> resultList) {
		this.resultList = resultList;
	}

}
