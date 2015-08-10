package cn.crane.application.greenlife.ui.merchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListMerchantAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.item.MerchantItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantsList;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 3, 2015 10:04:11 PM
 * 
 */
public class MerchantListAtivity extends BaseActivity implements OnItemClickListener {

	private Button btnBack;
	private TextView tvTitle;

	private ListView lv;
	private ListMerchantAdapter<MerchantItem> adapter;
	private List<MerchantItem> arrMerchantItems = new ArrayList<MerchantItem>();
	
	private Task_Post task_Post_getMerchantsList;

	private String type;
	private String token;
	
	private PullToRefreshListView mPullRefreshListView;
	


	@Override
	protected int getLayoutId() {
		return R.layout.ac_merchant_list;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);


		 mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

	        // Set a listener to be invoked when the list should be refreshed.
	     mPullRefreshListView.setOnRefreshListener(onRefreshListener2);

	     mPullRefreshListView.setMode(Mode.BOTH);
	        

	     lv = mPullRefreshListView.getRefreshableView();
		
		adapter = new ListMerchantAdapter<MerchantItem>(this, arrMerchantItems);
		adapter.setOnItemClickListener(onItemClickListener);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

	}
	
	private OnRefreshListener2<ListView> onRefreshListener2 = new OnRefreshListener2<ListView>()
		    {

		        @Override
		        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
		        {
		        	  String label = DateUtils.formatDateTime(MerchantListAtivity.this, System.currentTimeMillis(),
		                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

		                // Update the LastUpdatedLabel
		                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

		                // Do work to refresh the list here.
		                resetPage();
		                
		                getMerchantsList();
		            
		        }

		        @Override
		        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
		        {
		            // TODO Auto-generated method stub
		        	  getMerchantsList();
		        }
		    };

	@Override
	protected void init() {
		type = getIntent().getStringExtra(API_Contant.TYPE);
		getMerchantsList();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.tv_title:
			break;

		default:
			break;
		}
	}
	
	
	private void getMerchantsList() {
//		商户类型	merchantType	必填	String	1-外卖订餐(默认)；2-生活；3-鲜花
//		排序类型	orderType		int	"0-默认销量最高、1-距离最近、
//		2-评分最高、3-起送价最低"
//		"商户分类
//		加密ID"	merchantGroupToken		String	
//		福利条件	conditionType		Int 	"0-默认、1-新、2-免、3-折、
//		4-赠、5-在线支付、6-宅家配送"
//		经度	longitude		String	
//		纬度	latitude		String	
//		请求页码	pageIndex		Int	
//		每页记录	pageSize		Int	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("merchantType", getMerchantType());
		
		map.put("orderType", "0");
		map.put("merchantGroupToken", getMerchantType());
		map.put("conditionType", "0");
		
		map.put("longitude", DataManager.longitude);
		map.put("latitude", DataManager.latitude);
		
		map.put("pageIndex", "0");
		map.put("pageSize", "100");
		
		
		Task_Post.clearTask(task_Post_getMerchantsList);
		task_Post_getMerchantsList = new Task_Post(map, API.API_getMerchantsList,
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
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getMerchantsList.execute();
		if(page == PAGE_START)
		{
			displayLoadingDlg(R.string.loading);
		}
		
	}
	
	private void refreshUI(RE_getMerchantsList result) {
		if(result != null && result.getResultList() != null)
		{
//			this.arrMerchantItems.clear();
//			this.arrMerchantItems.addAll(result.getResultList());
//			adapter.notifyDataSetChanged();
			updateProgramList(result.getResultList(), result.getPageIndex(), result.getTotal());
		}
	}
	
	 private void updateProgramList(List<MerchantItem> arrMerchantItems, int curPage, int totalCount)
	    {
	        {
	            if (curPage == 1)
	            {
	            	
	                this.arrMerchantItems.clear();
	                this.arrMerchantItems.addAll(arrMerchantItems);
	                adapter.notifyDataSetChanged();
	            }
	            else
	            {
	            	  this.arrMerchantItems.addAll(arrMerchantItems);
		               adapter.notifyDataSetChanged();
	            }
	        }

	        mPullRefreshListView.onRefreshComplete();
	        if (pageCount > 0)
	        {
	            if ((curPage * pageCount) < totalCount)
	            {
	            	 mPullRefreshListView.setMode(Mode.BOTH);
	            	 page ++;
	            	
	            }
	               
	            else
	                mPullRefreshListView.setMode(Mode.PULL_FROM_START);
	        }
	        else
	        {
	            mPullRefreshListView.setMode(Mode.PULL_FROM_START);
	        }

//	        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm z");
//	        String lastUpdated = format.format(new Date(System
//	                .currentTimeMillis()));
//	        mPullRefreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(
//	                "Last Updated:" + lastUpdated);
	    }

	private String getMerchantType() {
		return type;
	}

	private ListMerchantAdapter.OnItemClickListener onItemClickListener = new ListMerchantAdapter.OnItemClickListener() {

		@Override
		public void onItemClick(Object object) {
			if(object instanceof MerchantItem)
			{
				FoodListActivity.show(MerchantListAtivity.this,((MerchantItem)object).getMerchantToken());

			}
		}
	};


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		
	}
	

	public static void show(Context context ,String type) {
		Intent intent = createIntent(context, MerchantListAtivity.class);
		intent.putExtra(API_Contant.TYPE, type);
		context.startActivity(intent);
	}



}
