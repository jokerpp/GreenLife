package cn.crane.application.greenlife.bean.merchant;

import java.io.Serializable;

import android.text.TextUtils;

import cn.crane.application.greenlife.api.API_Contant;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 10:45:10 PM
 * 
 */
public class FoodItem implements FoodType,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TAG = FoodItem.class.getSimpleName();
//	请求页码	pageIndex		Int
//	每页记录	pageSize		Int
//	数据总量	total		Int
//	返回数据量	size		Int
//	结果列表	resultList		Json
	
//	菜品加密ID	dishesToken		String
//	菜品图片	dishesPicture		String
//	菜品名称	dishesName		String
//	已售份数	saleAmoun		String
//	推荐数量	recommendAmount		String
//	原价格	primePrice		String
//	优惠价	preferentialPrice		String
//	库存份数	stock		String
//	最低起订份数要求	isMinAmount		String
//	起订份数	minAmount		String
//	菜品单位	unit		String
//	是否有可选规格	isOption		String
	
//	"dishesGroupToken": "",
//	"dishesName": "米饭测试3",
//	"dishesPicture": "http://42.96.167.31:8080/zj/uploadfile/background/dishes/20150624223807109.jpg",
//	"dishesToken": "13",
//	"isMinAmount": 0,
//	"isOption": 0,
//	"minAmount": 0,
//	"preferentialPrice": 3,
//	"primePrice": 3,
//	"recommendAmount": 0,
//	"saleAmoun": 0,
//	"stock": 0,
//	"unit": ""
	
	private String dishesToken;
	private String dishesPicture;
	private String dishesName;
	private String saleAmoun;
	private String recommendAmount;
	private String primePrice;
	private String preferentialPrice;
	private String stock;
	private String isMinAmount;
	private String minAmount;
	private String isOption;
	private String dishesGroupName;
	private String dishesGroupToken;
	private String unit;
	
	private float iprimePrice = 0;
	private float iPrice = 0;
	private int iCountChoose = 0;
	
	
	private String amount;
	
	public FoodItem() {
		// TODO Auto-generated constructor stub
	}

	public String getDishesToken() {
		return dishesToken;
	}

	public void setDishesToken(String dishesToken) {
		this.dishesToken = dishesToken;
	}

	public String getDishesPicture() {
		return dishesPicture;
	}

	public void setDishesPicture(String dishesPicture) {
		this.dishesPicture = dishesPicture;
	}

	public String getDishesName() {
		return dishesName;
	}

	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}

	public String getSaleAmoun() {
		if(TextUtils.isEmpty(saleAmoun) || "null".equalsIgnoreCase(saleAmoun))
		{
			return "0";
		}
		return saleAmoun;
	}

	public void setSaleAmoun(String saleAmoun) {
		this.saleAmoun = saleAmoun;
	}

	public String getRecommendAmount() {
		return recommendAmount;
	}

	public void setRecommendAmount(String recommendAmount) {
		this.recommendAmount = recommendAmount;
	}

	public String getPrimePrice() {
		return primePrice;
	}

	public void setPrimePrice(String primePrice) {
		this.primePrice = primePrice;
		try {
			iprimePrice = Float.parseFloat(primePrice.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(String preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
		try {
			iPrice = Float.parseFloat(preferentialPrice.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
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

	public String getIsOption() {
		return isOption;
	}

	public void setIsOption(String isOption) {
		this.isOption = isOption;
	}

	public String getDishesGroupName() {
		return dishesGroupName;
	}

	public void setDishesGroupName(String dishesGroupName) {
		this.dishesGroupName = dishesGroupName;
	}

	@Override
	public String getFoodType() {
		return dishesGroupName;
	}

	public int getiCountChoose() {
		return iCountChoose;
	}

	public void setiCountChoose(int iCountChoose) {
		this.iCountChoose = iCountChoose;
	}


	public float getiPrice() {
		return iPrice;
	}

	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	


	@Override
	public String getId() {
		return hashCode() + "";
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
		try {
			iCountChoose = (int) Float.parseFloat(amount.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isMinAmount() {
		return API_Contant.TRUE.equalsIgnoreCase(isMinAmount);
	}
	
	public boolean isOption() {
		return API_Contant.TRUE.equalsIgnoreCase(isOption);
	}
	
	
	
	public float getTotalPrice() {
		return iPrice * iCountChoose;
	}
	public float getDiscountedPrice() {
		return (iprimePrice - iPrice )* iCountChoose;
	}

	public String getDishesGroupToken() {
		return dishesGroupToken;
	}

	public void setDishesGroupToken(String dishesGroupToken) {
		this.dishesGroupToken = dishesGroupToken;
	}

	
	
}
