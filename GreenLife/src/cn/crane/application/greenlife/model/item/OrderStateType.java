package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.R;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 27, 2015 10:30:11 PM
 * 
 */
public enum OrderStateType{
	// "0、已取消
	// 1、提交成功
	// 2、等待支付
	// 3、已支付
	// 4、商家确认
	// 5、配送员赶往商家取餐(取餐中)
	// 6、配送员已取餐（送餐中）
	// 7、已送达
	// 8、待评价
	// 9、订单完成
	// 21、商户投诉用户（用户恶意下单）
	// 22、用户投诉商户（商家不诚信）"
	STATE_NULL(-1, R.string.order_state_null),
	STATE_0(0, R.string.order_state_0),
	STATE_1(1, R.string.order_state_1),
	STATE_2(2, R.string.order_state_2),
	STATE_3(3, R.string.order_state_3),
	STATE_4(4, R.string.order_state_4),
	STATE_5(5, R.string.order_state_5),
	STATE_6(6, R.string.order_state_6),
	STATE_7(7, R.string.order_state_7),
	STATE_8(8, R.string.order_state_8),
	STATE_9(9, R.string.order_state_9),
	STATE_10(10, R.string.order_state_10),
	STATE_11(11, R.string.order_state_11),
	STATE_21(21, R.string.order_state_21),
	STATE_22(22, R.string.order_state_22);
	public int key;
	public int nameRes;

	private OrderStateType() {
	}

	private OrderStateType(int key, int nameRes) {
		this.key = key;
		this.nameRes = nameRes;
	}
	
	public static OrderStateType getOrderState(int key) {
		for(OrderStateType orderState:values())
		{
			if(orderState.key == key)
			{
				return orderState;
			}
		}
		return STATE_0;
	}
}