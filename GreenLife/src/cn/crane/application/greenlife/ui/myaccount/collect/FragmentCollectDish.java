package cn.crane.application.greenlife.ui.myaccount.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListMerchantAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.item.MerchantItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantsList;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 19, 2015 9:03:15 PM
 * 
 */
public class FragmentCollectDish extends BaseFragment implements OnClickListener, OnItemClickListener {

	private ListView lv;
	private ListMerchantAdapter<MerchantItem> adapter;
	private List<MerchantItem> arrMerchantItems = new ArrayList<MerchantItem>();
	private Task_Post task_Post_getMyCollectionDeshesList;
	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_comments;
	}

	@Override
	protected void findViews() {
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
		adapter = new ListMerchantAdapter<MerchantItem>(getActivity(), arrMerchantItems);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
	}

	@Override
	protected void init() {
		getMyCollectionDeshesList();
	}

	@Override
	public void onClick(View v) {
		
	}
	
	private void getMyCollectionDeshesList() {
//		用户ID加密	userToken	必填	String	
//		时间戳	timeStamp		Long	
//		请求页码	pageIndex		Int	
//		每页记录	pageSize		Int	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("timeStamp", API_Contant.getTimeStamp());
		map.put("pageIndex", page +"");
		map.put("pageSize", pageCount +"");
		Task_Post.clearTask(task_Post_getMyCollectionDeshesList);
		task_Post_getMyCollectionDeshesList = new Task_Post(map, API.API_getMyCollectionDeshesList,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMerchantsList result = new RE_getMerchantsList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMerchantsList.class);
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
		task_Post_getMyCollectionDeshesList.execute();
		displayLoadingDlg(R.string.loading);
		
	}

	protected void refreshUI(RE_getMerchantsList result) {
		if(result != null)
		{
			arrMerchantItems.clear();
			arrMerchantItems.addAll(result.getResultList());
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Task_Post.clearTask(task_Post_getMyCollectionDeshesList);
	}

}
