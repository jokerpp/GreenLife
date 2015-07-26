package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 28, 2015 12:33:09 AM
 * 
 */
public class OrderStateItem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -596801083925845479L;
	// 订单状态内容 orderContent String
	// 经度 longitude Double
	// 纬度 latitude Double
	// 订单状态 status Int
	// 订单状态时间 orderStatusDateStr String

	private String orderToken;
	private String orderStatusToken;
	private String orderContent;
	private Double longitude;
	private Double latitude;
	private Integer status;
	private String orderStatusDateStr;

	private OrderStateType orderStateType;
	
	public OrderStateItem() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public String getOrderStatusToken() {
		return orderStatusToken;
	}

	public void setOrderStatusToken(String orderStatusToken) {
		this.orderStatusToken = orderStatusToken;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		try {
			orderStateType = OrderStateType.getOrderState(status);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getOrderStatusDateStr() {
		return orderStatusDateStr;
	}

	public void setOrderStatusDateStr(String orderStatusDateStr) {
		this.orderStatusDateStr = orderStatusDateStr;
	}

	public OrderStateType getOrderStateType() {
		return orderStateType;
	}

	public void setOrderStateType(OrderStateType orderStateType) {
		this.orderStateType = orderStateType;
	}

}
