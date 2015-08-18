package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.model.BaseModel;
import cn.crane.framework.view.carouselview.CarouselItemInfo;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 23, 2015 10:33:08 PM
 * 
 */
public class MerchantItem extends BaseModel implements CarouselItemInfo {

	public static final String TAG = MerchantItem.class.getSimpleName();
	/**
	 * 
	 */
	private static final long serialVersionUID = 3306606548613871685L;
	
	// "deliveryPrice": 0,
	// "isSuportDiscount": 0,
	// "isSuportFree": 0,
	// "isSuportGive": 0,
	// "isSuportMainDelivery": 0,
	// "isSuportNew": 0,
	// "isSuportOnLinePay": 0,
	// "juli": "",
	// "latitude": 0,
	// "longitude": 0,
	// "merchantName": "大洋烧烤",
	// "merchantPictrue":
	// "http://42.96.167.31:8080/zj/uploadfile/background/barber/20150409215422193.jpg",
	// "merchantToken": "11",
	// "orderMinPrice": 0,
	// "score": 0,
	// "status": 0
	
//	菜品加密ID	dishesToken		String
//	菜品名称	dishesName		String

	private String dishesToken;
	private String dishesName;
	
	private String deliveryPrice;
	private String isSuportDiscount;
	private String isSuportFree;
	private String isSuportGive;
	private String isSuportMainDelivery;
	private String isSuportNew;
	private String isSuportOnLinePay;
	private String juli;
	private String latitude;
	private String longitude;
	private String merchantName;
	private String merchantPictrue;
	private String merchantToken;
	private String orderMinPrice;
	private String score;
	private String status;
	
	private String isSuportNewStr;
	private String isSuportFreeStr;
	private String isSuportDiscountStr;
	private String isSuportGiveStr;
	
	private int iScore;
	
	private boolean canExtand = true;

	public MerchantItem() {
		// TODO Auto-generated constructor stub
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



	public String getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
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

	public String getIsSuportMainDelivery() {
		return isSuportMainDelivery;
	}

	public void setIsSuportMainDelivery(String isSuportMainDelivery) {
		this.isSuportMainDelivery = isSuportMainDelivery;
	}

	public String getIsSuportNew() {
		return isSuportNew;
	}

	public void setIsSuportNew(String isSuportNew) {
		this.isSuportNew = isSuportNew;
	}

	public String getIsSuportOnLinePay() {
		return isSuportOnLinePay;
	}

	public void setIsSuportOnLinePay(String isSuportOnLinePay) {
		this.isSuportOnLinePay = isSuportOnLinePay;
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
		try {
			iScore = (int) Float.parseFloat(score.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	// private String isSuportDiscount;
	// private String isSuportFree;
	// private String isSuportGive;
	// private String isSuportMainDelivery;
	// private String isSuportNew;
	// private String isSuportOnLinePay;
	public boolean isSuportDiscount() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportDiscount);
	}

	public boolean isSuportFree() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportFree);
	}

	public boolean isSuportGive() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportGive);
	}

	public boolean isSuportMainDelivery() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportMainDelivery);
	}

	public boolean isSuportNew() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportNew);
	}

	public boolean isSuportOnLinePay() {
		return API_Contant.TRUE.equalsIgnoreCase(isSuportOnLinePay);
	}
	
	public int getScoreInt() {
		try {
			return (int) Float.parseFloat(score.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public int getiScore() {
		return iScore;
	}

	public void setiScore(int iScore) {
		this.iScore = iScore;
	}
	
	public boolean canExtand() {
		int icons = 0;
		if(isSuportNew())
		{
			icons ++;
		}
		if(isSuportDiscount())
		{
			icons ++;
		}
		if(isSuportFree())
		{
			icons ++;
		}
		if(isSuportGive())
		{
			icons ++;
		}
		return icons > 1;
	}



	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return merchantPictrue;
	}



	@Override
	public int getImageResourse() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getDefaultResourse() {
		// TODO Auto-generated method stub
		return 0;
	}
}
