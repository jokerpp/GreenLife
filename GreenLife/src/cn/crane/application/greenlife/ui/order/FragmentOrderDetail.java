package cn.crane.application.greenlife.ui.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListOrderFoodsAdapter;
import cn.crane.application.greenlife.adapter.order.ListOrderDetailAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.result.RE_getMyOrderDetailsForUser;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 14, 2015 11:04:52 PM
 * 
 */
public class FragmentOrderDetail extends BaseFragment implements OnClickListener {

	private TextView tv_merchant_name;
	private ListView lv_foods;
	private TextView tv_total_price;
	private ListView lv_detail;
	private LinearLayout ll_content;
	private TextView tv_order_again;
	
	private ListOrderFoodsAdapter foodsAdapter;
	private ListOrderDetailAdapter detailAdapter;
	
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
	private List<String> arrTitle = new ArrayList<String>();

	private Task_Post task_Post_getMyOrderDetailsForUser;
	private int totalMoney;
	private int totalDiscountMoney;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_detail;
	}

	@Override
	protected void findViews() {
		tv_merchant_name = (TextView) findViewById(R.id.tv_merchant_name);
		lv_foods = (ListView) findViewById(R.id.lv_foods);
		tv_total_price = (TextView) findViewById(R.id.tv_total_price);
		lv_detail = (ListView) findViewById(R.id.lv_detail);
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		tv_order_again = (TextView) findViewById(R.id.tv_order_again);
	}

	@Override
	protected void bindViews() {
		foodsAdapter = new ListOrderFoodsAdapter(getActivity(), arrFoodItems);
		lv_foods.setAdapter(foodsAdapter);
		
		detailAdapter = new ListOrderDetailAdapter(getActivity(), arrTitle);
		lv_detail.setAdapter(detailAdapter);
		
		tv_order_again.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		getMyOrderDetailsForUser();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_order_again:
//			OrderConfirmActivity.show(getActivity(),null,null);
//			PayActivity.show(getActivity());
			break;

		default:
			break;
		}
	}
	
	private void getMyOrderDetailsForUser() {
//		用户加密ID	userToken	必填	String	
//		订单加密ID	orderToken	必填	String	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("orderToken", getOrderToken());
		
		
		Task_Post.clearTask(task_Post_getMyOrderDetailsForUser);
		task_Post_getMyOrderDetailsForUser = new Task_Post(map, API.API_getMyOrderDetailsForUser,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMyOrderDetailsForUser result = new RE_getMyOrderDetailsForUser();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMyOrderDetailsForUser.class);
					if (result.isSuccess()) {
						refreshUI(result);
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getMyOrderDetailsForUser.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	protected void refreshUI(RE_getMyOrderDetailsForUser result) {
		if(result != null)
		{
			tv_merchant_name.setText(result.getMerchantName());
			if(result.getResultList() != null)
			{
				arrFoodItems.clear();
				
//				FoodItem item = new FoodItem();
//				
//				item.setPreferentialPrice(result.getFoodBoxPrice());
//				item.setPrimePrice(result.getFoodBoxPrice());
//				item.setDishesName(getString(R.string.txt_food_box_price));
//				item.setiCountChoose(1);
//				arrFoodItems.add(0, item);
//				
//				item = new FoodItem();
//				item.setPreferentialPrice(result.getDeliveryPrice());
//				item.setPrimePrice(result.getDeliveryPrice());
//				item.setDishesName(getString(R.string.txt_food_send_price));
//				item.setiCountChoose(1);
//				arrFoodItems.add(0, item);
				
				arrFoodItems.addAll(result.getResultList());
				foodsAdapter.notifyDataSetChanged();
				getTotalMoney();
				
				arrTitle.clear();
				
				arrTitle.add(getString(R.string.format_order_detail, result.getMerchantName()));
				arrTitle.add(getString(R.string.format_order_contact, result.getContactName()) + " " + result.getGender());
				arrTitle.add(getString(R.string.format_order_mobile, result.getMobile()));
				arrTitle.add(getString(R.string.format_order_address, result.getAddress()));
				arrTitle.add(getString(R.string.format_order_paytype, result.getPayType()));
				arrTitle.add(getString(R.string.format_order_sendtime, result.getRequestDeliveryDateTimeStr()));
				arrTitle.add(getString(R.string.format_order_note, result.getMerchantNote()));
				
				detailAdapter.notifyDataSetChanged();
				
				
				if(getActivity() instanceof OrderDetailActivity)
				{
					((OrderDetailActivity)getActivity()).setTel(result.getTelephone());
				}
				
			}
			
			
		}
	}

	private float getTotalMoney() {
		totalMoney = 0;
		totalDiscountMoney = 0;
		if(arrFoodItems != null)
		{
			for(FoodItem item : arrFoodItems)
			{
				if(item != null)
				{
					totalMoney += item.getTotalPrice();
					totalDiscountMoney += item.getDiscountedPrice();
				}
			}
		}
		tv_total_price.setText(getString(R.string.order_total,totalDiscountMoney + "",totalMoney +""));
		return 0;
	}
	
	public String getOrderToken() {
		if(getActivity() instanceof OrderDetailActivity)
		{
			return ((OrderDetailActivity)getActivity()).getOrderToken();
		}
		return "";
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Task_Post.clearTask(task_Post_getMyOrderDetailsForUser);
	}
	

}
