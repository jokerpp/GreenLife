package cn.crane.application.greenlife.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListMerchantAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.model.item.MerchantItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantsList;
import cn.crane.application.greenlife.ui.merchant.FoodListActivity;
import cn.crane.application.greenlife.ui.merchant.MerchantListAtivity;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 3, 2015 10:04:11 PM
 * 
 */
public class SearchResultAtivity extends BaseActivity implements OnItemClickListener {

	public static final String SEARCH_TYPE = "search_type";
	public static final String SEARCH_KEY = "search_key";
	private Button btnBack;

	private EditText etSearch;
	private ListView lv;

	private ListMerchantAdapter<MerchantItem> adapter;
	private List<MerchantItem> arrMerchantItems = new ArrayList<MerchantItem>();

	
	private String sSearchKey;

	private PullToRefreshListView mPullRefreshListView;
	
	
	@Override
	protected int getLayoutId() {
		return R.layout.ac_search_result;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
		etSearch = (EditText) findViewById(R.id.et_search);
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
		etSearch.setOnClickListener(this);
		etSearch.addTextChangedListener(textWatcher);
		
		lv.setOnItemClickListener(this);

		
	}

	@Override
	protected void init() {
		sSearchKey = getIntent().getStringExtra(SEARCH_KEY);
		etSearch.setText(sSearchKey);
		
		etSearch.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_ENTER)
				{
					searchMerchantList();
				}
				return false;
			}
		});
		
			adapter = new ListMerchantAdapter<MerchantItem>(this, arrMerchantItems);
			adapter.setOnItemClickListener(onItemClickListener);
			lv.setAdapter(adapter);
			searchMerchantList();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.et_search:
			break;

		default:
			break;
		}
	}

	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	private Task_Post task_Post_searchMerchantList;

	
	private void searchMerchantList() {
//		搜索关键词	keyWord	必填	String	
//		请求页码	pageIndex		Int	
//		每页记录	pageSize		Int	
		String sSearchKey = etSearch.getText().toString();
		if(TextUtils.isEmpty(sSearchKey))return;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyWord", sSearchKey);
		map.put("pageIndex", "0");
		map.put("pageSize", "100");
		
		
		Task_Post.clearTask(task_Post_searchMerchantList);
		task_Post_searchMerchantList = new Task_Post(map, API.API_searchMerchantList,
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
		task_Post_searchMerchantList.execute();
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
		Object object = parent.getItemAtPosition(position);
		if(object instanceof MerchantItem)
		{
			FoodListActivity.show(this, ((MerchantItem) object).getMerchantToken());
		}
	}
	
	private ListMerchantAdapter.OnItemClickListener onItemClickListener = new ListMerchantAdapter.OnItemClickListener() {

		@Override
		public void onItemClick(Object object) {
			if(object instanceof MerchantItem)
			{
				FoodListActivity.show(SearchResultAtivity.this,((MerchantItem)object).getMerchantToken());

			}
		}
	};
	
	@Override
	protected void onDestroy() {
		Task_Post.clearTask(task_Post_searchMerchantList);
		super.onDestroy();
	}

	public static void show(Context context) {
		context.startActivity(createIntent(context, SearchResultAtivity.class));
	}

	public static void show(Context context, int type, String key) {
		Intent intent = createIntent(context, SearchResultAtivity.class);
		intent.putExtra(SEARCH_KEY, key);
		intent.putExtra(SEARCH_TYPE, type);
		context.startActivity(intent);
	}

	

}
