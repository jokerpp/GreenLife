package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;




public class RecommandDishesDataList extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1753316100357970652L;
	private String dishesToken;
	private String dishesName;
	private String merchantToken;
	
	public String getDishesToken() {
		return dishesToken;
	}
	public void setDishesToken(String dishesToken) {
		this.dishesToken = dishesToken;
	}
	public String getDishesName() {
		return dishesName;
	}
	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}
	public String getMerchantToken() {
		return merchantToken;
	}
	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

}