package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jul 26, 2015 3:11:15 PM
 * 
 */
public class PayItem extends BaseModel{

	public static final String TAG = "PayItem";
	/**
	 * 
	 */
	private static final long serialVersionUID = -1177652416841839026L;

	private String payType ;
	private String orderToken ;
	private float totalMoney ;
	private float totalDiscountMoney ;
	
	public PayItem() {
		// TODO Auto-generated constructor stub
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public float getTotalDiscountMoney() {
		return totalDiscountMoney;
	}

	public void setTotalDiscountMoney(float totalDiscountMoney) {
		this.totalDiscountMoney = totalDiscountMoney;
	}
	
	
}
