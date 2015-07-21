package cn.crane.application.greenlife.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.ListOrderAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.bean.OrderItem;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.result.RE_getMyOrderList;
import cn.crane.application.greenlife.ui.myaccount.LoginActivity;
import cn.crane.application.greenlife.ui.order.OrderDetailActivity;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 26, 2015 12:20:21 AM
 * 
 */
public class FragmentOrderList extends BaseFragment implements OnClickListener, OnItemClickListener{
	
	private ListView lv;
	private ListOrderAdapter adapter;
	private List<OrderItem> arrOrderItems = new ArrayList<OrderItem>();
	private Task_Post task_Post_getMyOrderList;
	
	private View ll_login;
	private Button btnLogin;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_list;
	}

	@Override
	protected void findViews() {
		lv = (ListView) findViewById(R.id.lv);
		ll_login = findViewById(R.id.ll_need_login);
		btnLogin = (Button) findViewById(R.id.btn_login);
	}

	@Override
	protected void bindViews() {
		btnLogin.setOnClickListener(this);
	}

	@Override
	protected void init() {
		adapter = new ListOrderAdapter(getActivity(), arrOrderItems);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}
	
	private void onLoginStateChanged(boolean isLogin) {
		if(isLogin)
		{
			lv.setVisibility(View.VISIBLE);
			ll_login.setVisibility(View.GONE);
			getMyOrderList();
		}else
		{
			lv.setVisibility(View.GONE);
			ll_login.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			getActivity().onBackPressed();
			break;
		case R.id.btn_right:
			break;
		case R.id.btn_login:
			LoginActivity.show(getActivity());
			break;

		default:
			break;
		}
	}
	
	
	private void getMyOrderList() {
//		用户加密ID	userToken	必填	String	
//		时间戳	timeStamp		Long	
//		请求页码	pageIndex		Int	
//		每页记录	pageSize		Int	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("timeStamp", API_Contant.getTimeStamp());
		map.put("pageIndex", "0");
		map.put("pageSize", "10");
		
		
		Task_Post.clearTask(task_Post_getMyOrderList);
		task_Post_getMyOrderList = new Task_Post(map, API.API_getMyOrderList,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMyOrderList result = new RE_getMyOrderList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMyOrderList.class);
					if (result.isSuccess()) {
						refreshUI(result);
					} else {
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getMyOrderList.execute();
		displayLoadingDlg(R.string.loading);
		
	}

	protected void refreshUI(RE_getMyOrderList result) {
		if(result != null && result.getResultList() != null)
		{
			this.arrOrderItems.clear();
			this.arrOrderItems.addAll(result.getResultList());
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		if(object != null && object instanceof OrderItem)
		{
//			OrderDetailActivity.show(getActivity(),(OrderItem) object);
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		onLoginStateChanged(DataManager.isLogin());
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Task_Post.clearTask(task_Post_getMyOrderList);
	}

}
