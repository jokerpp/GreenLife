package cn.crane.application.greenlife.ui.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSONArray;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.TestConfig;
import cn.crane.application.greenlife.adapter.order.ListOrderStateAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.item.OrderStateItem;
import cn.crane.application.greenlife.model.item.OrderStateType;
import cn.crane.application.greenlife.model.result.RE_getMyOrderStatusList;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 14, 2015 11:04:52 PM
 * 
 */
public class FragmentOrderState extends BaseFragment implements OnClickListener {

	private TextView tvOptionCancel;
	private TextView tvOptionPay;
	private TextView tvOptionReminder;
	private TextView tvOptionComment;
	
	private ListView lv;
	private ListOrderStateAdapter adapter;
	private List<OrderStateItem> arrOrderStateItems = new ArrayList<OrderStateItem>();
	private Task_Post task_Post_getMyOrderStatusList;
	private Task_Post task_Post_cancelOrder;
	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_state;
	}

	@Override
	protected void findViews() {
		lv = (ListView) findViewById(R.id.lv);
		tvOptionCancel = (TextView) findViewById(R.id.tv_option_cancel);
		tvOptionPay = (TextView) findViewById(R.id.tv_option_pay);
		tvOptionReminder = (TextView) findViewById(R.id.tv_option_reminder);
		tvOptionComment = (TextView) findViewById(R.id.tv_option_comment);
	}

	@Override
	protected void bindViews() {
		adapter = new ListOrderStateAdapter(getActivity(), arrOrderStateItems);
		lv.setAdapter(adapter);
		
		tvOptionCancel.setOnClickListener(this);
		tvOptionPay.setOnClickListener(this);
		tvOptionReminder.setOnClickListener(this);
		tvOptionComment.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		getMyOrderStatusList();

		refreshOrderState(OrderStateType.STATE_NULL);
	}
	
	private void refreshOrderState(OrderStateType stateType) {
		switch (stateType) {
		case STATE_0:
		case STATE_NULL:
			tvOptionCancel.setVisibility(View.GONE);
			tvOptionPay.setVisibility(View.GONE);
			tvOptionReminder.setVisibility(View.GONE);
			tvOptionComment.setVisibility(View.GONE);
			break;
		case STATE_1:
			tvOptionCancel.setVisibility(View.VISIBLE);
			 if(TestConfig.IS_SURPORT_PAY_ONLINE)
			 {
				 tvOptionPay.setVisibility(View.VISIBLE);
			 }
			
			tvOptionReminder.setVisibility(View.GONE);
			tvOptionComment.setVisibility(View.GONE);
			break;
		case STATE_2:
			tvOptionCancel.setVisibility(View.VISIBLE);
			 if(TestConfig.IS_SURPORT_PAY_ONLINE)
			 {
				 tvOptionPay.setVisibility(View.VISIBLE);
			 }
			tvOptionReminder.setVisibility(View.GONE);
			tvOptionComment.setVisibility(View.GONE);
			break;
		case STATE_3:
		case STATE_4:
		case STATE_5:
		case STATE_6:
			tvOptionCancel.setVisibility(View.VISIBLE);
			tvOptionPay.setVisibility(View.GONE);
			tvOptionReminder.setVisibility(View.VISIBLE);
			tvOptionComment.setVisibility(View.GONE);
			break;
		case STATE_7:
		case STATE_8:
			tvOptionCancel.setVisibility(View.GONE);
			tvOptionPay.setVisibility(View.GONE);
			tvOptionReminder.setVisibility(View.GONE);
			tvOptionComment.setVisibility(View.VISIBLE);
			break;
		case STATE_9:
		case STATE_10:
		case STATE_11:
		case STATE_21:
		case STATE_22:
			tvOptionCancel.setVisibility(View.GONE);
			tvOptionPay.setVisibility(View.GONE);
			tvOptionReminder.setVisibility(View.GONE);
			tvOptionComment.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_option_cancel:
			cancelOrder();
			break;
		case R.id.tv_option_pay:
		case R.id.tv_option_reminder:
		case R.id.tv_option_comment:
			
			break;

		default:
			break;
		}
	}
	
	private void getMyOrderStatusList() {
//		用户加密ID	userToken	必填	String	
//		订单加密ID	orderToken	必填	String	
//		时间戳	timeStamp		Long	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("timeStamp", API_Contant.getTimeStamp());
		map.put("orderToken", getOrderToken());
		
		
		Task_Post.clearTask(task_Post_getMyOrderStatusList);
		task_Post_getMyOrderStatusList = new Task_Post(map, API.API_getMyOrderStatusList,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMyOrderStatusList result = new RE_getMyOrderStatusList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMyOrderStatusList.class);
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
		task_Post_getMyOrderStatusList.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	private void cancelOrder() {
//		用户加密ID	userToken	必填	String
//		订单加密ID	orderToken	必填	String
//		描述	note		String	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("note", "");
		map.put("orderToken", getOrderToken());
		
		
		Task_Post.clearTask(task_Post_cancelOrder);
		task_Post_cancelOrder = new Task_Post(map, API.API_cancelOrder,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if (result.isSuccess()) {
						App.showToast(R.string.order_cancel_success);
						getMyOrderStatusList();
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_cancelOrder.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	protected void refreshUI(RE_getMyOrderStatusList result) {
		if(result != null)
		{
			if(result.getResultList() != null)
			{
				this.arrOrderStateItems.clear();
				this.arrOrderStateItems.addAll(result.getResultList());
				adapter.notifyDataSetChanged();
				if(arrOrderStateItems.size() > 0)
				{
					OrderStateItem item = arrOrderStateItems.get(arrOrderStateItems.size() - 1);
					if(item != null)
					{
						refreshOrderState(item.getOrderStateType());
					}
				}
			}
		}
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
		Task_Post.clearTask(task_Post_getMyOrderStatusList);
		Task_Post.clearTask(task_Post_cancelOrder);
	}

	

}
