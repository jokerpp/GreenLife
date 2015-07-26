package cn.crane.application.greenlife.model.result;

import java.util.List;

import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.model.Result;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 24, 2015 11:23:30 PM
 * 
 */
public class RE_getMerchantDishesList extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923630764210855103L;
	
	private List<FoodGroup> dishesGroupList;
	private List<FoodItem> resultList;

	public List<FoodItem> getResultList() {
		return resultList;
	}

	public void setResultList(List<FoodItem> resultList) {
		this.resultList = resultList;
	}

	public List<FoodGroup> getDishesGroupList() {
		return dishesGroupList;
	}

	public void setDishesGroupList(List<FoodGroup> dishesGroupList) {
		this.dishesGroupList = dishesGroupList;
	}

}
