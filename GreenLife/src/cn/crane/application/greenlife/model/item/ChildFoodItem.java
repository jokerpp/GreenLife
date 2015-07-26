package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.model.BaseModel;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jul 4, 2015 3:20:16 PM
 * 
 */
public class ChildFoodItem extends BaseModel{
	public static final String TAG = "ChildFoodItem";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7610558749831174062L;
//	菜品加密ID	dishesToken		String	
//	菜品名称	dishesName		String	
//	原价格	primePrice		Float	
//	优惠价	preferentialPrice		Float	
//	库存份数	stock		Float	
//	最低起订份数要求	isMinAmount		Int	1有、0无
//	起订份数	minAmount		Float	
//	菜品单位	unit		String	
	
	private String dishesToken;
	private String dishesName;
	private String primePrice;
	private String preferentialPrice;
	private String stock;
	private String isMinAmount;
	private String minAmount;
	private String unit;
	
	private int iSelectAccount = 1;
	
	private String parentDishToken;
	
	public ChildFoodItem() {
		// TODO Auto-generated constructor stub
	}

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

	public String getPrimePrice() {
		return primePrice;
	}

	public void setPrimePrice(String primePrice) {
		this.primePrice = primePrice;
	}

	public String getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(String preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getIsMinAmount() {
		return isMinAmount;
	}

	public void setIsMinAmount(String isMinAmount) {
		this.isMinAmount = isMinAmount;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public boolean isMinAmount() {
		return API_Contant.TRUE.equalsIgnoreCase(isMinAmount);
	}

	public int getiSelectAccount() {
		return iSelectAccount;
	}

	public void setiSelectAccount(int iSelectAccount) {
		this.iSelectAccount = iSelectAccount;
	}

	public String getParentDishToken() {
		return parentDishToken;
	}

	public void setParentDishToken(String parentDishToken) {
		this.parentDishToken = parentDishToken;
	}
	
}
