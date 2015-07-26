package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 12:37:12 AM
 * 
 */
public class OrderItem extends BaseModel {
	public static final String TAG = "OrderItem";

	/**
	 * 
	 */
	private static final long serialVersionUID = -9211459735084894267L;

	// 订单加密ID orderToken String
	// 商户加密ID merchantToken String
	// 商户图片 merchantPictrue String
	// 商户名称 merchantName String
	// 订单价格 preferentialPrice Float
	// 订单状态 status Int
	// 订单下单时间 insertDateTimeStr String

	private String orderToken;
	private String merchantToken;
	private String merchantPictrue;
	private String merchantName;
	private String preferentialPrice;
	private Integer status;
	private String insertDateTimeStr;

	private OrderStateType orderState;

	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public String getMerchantToken() {
		return merchantToken;
	}

	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

	public String getMerchantPictrue() {
		return merchantPictrue;
	}

	public void setMerchantPictrue(String merchantPictrue) {
		this.merchantPictrue = merchantPictrue;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(String preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		try {
			orderState = OrderStateType.getOrderState(status);
		} catch (Exception e) {
		}
	
	}

	public String getInsertDateTimeStr() {
		return insertDateTimeStr;
	}

	public void setInsertDateTimeStr(String insertDateTimeStr) {
		this.insertDateTimeStr = insertDateTimeStr;
	}

	public OrderStateType getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateType orderState) {
		this.orderState = orderState;
	}

}
