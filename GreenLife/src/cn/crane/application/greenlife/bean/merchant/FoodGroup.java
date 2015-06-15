package cn.crane.application.greenlife.bean.merchant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 10:44:56 PM
 * 
 */
public class FoodGroup implements FoodType{
	private String dishesGroupToken;
	private String dishesGroupName;
	
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
	
	public FoodGroup() {
		// TODO Auto-generated constructor stub
	}

	public String getDishesGroupToken() {
		return dishesGroupToken;
	}

	public void setDishesGroupToken(String dishesGroupToken) {
		this.dishesGroupToken = dishesGroupToken;
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

	public List<FoodItem> getArrFoodItems() {
		return arrFoodItems;
	}

	public void setArrFoodItems(List<FoodItem> arrFoodItems) {
		this.arrFoodItems = arrFoodItems;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
