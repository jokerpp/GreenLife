package cn.crane.application.greenlife.ui.myaccount.address;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.my.ListMyAddressAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.item.AddressItem;
import cn.crane.application.greenlife.model.result.RE_getMyDeliveryAddressList;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 17, 2015 11:54:43 PM
 * 
 */
public class MyAddressActivity extends BaseActivity implements OnItemClickListener {

	public static final String TYPE = "type";
	public static final int TYPE_VIEW = 1000;
	public static final int TYPE_SELECT = 1001;
	private Button btnBack;
	private ListView lvAddress;
	private ListMyAddressAdapter adapter;
	
	private View footerView;
	private TextView tvAddNew;
	private Task_Post task_Post_getMyDeliveryAddressList;
	private List<AddressItem> arrAddressItems = new ArrayList<AddressItem>();
	
	private int type = TYPE_VIEW;
	@Override
	protected int getLayoutId() {
		return R.layout.ac_my_address;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
		lvAddress = (ListView) findViewById(R.id.lv);
		
		footerView = LayoutInflater.from(this).inflate(R.layout.item_my_address_footer, null);
		tvAddNew = (TextView) footerView.findViewById(R.id.tv_add_new_address);
	}

	@Override
	protected void bindViews() {
		lvAddress.addFooterView(footerView);
		adapter = new ListMyAddressAdapter(this, arrAddressItems);
		lvAddress.setAdapter(adapter);
		
		btnBack.setOnClickListener(this);
		tvAddNew.setOnClickListener(this);
		lvAddress.setOnItemClickListener(this);
	}

	
	@Override
	protected void init() {
		type = getIntent().getIntExtra(TYPE, TYPE_VIEW);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_add_new_address:
			AddAddressActivity.show(this,AddAddressActivity.TYPE_NEW,"");
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Object object = parent.getItemAtPosition(position);
		if(object instanceof AddressItem)
		{
			if(type == TYPE_VIEW)
			{
				AddAddressActivity.show(this,AddAddressActivity.TYPE_EDIT,((AddressItem)object).getDeliveryToken());
			}else
			{
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable(AddressItem.TAG, (AddressItem)object);
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
			}
			
		}
//		if(object instanceof)
	}
	
	private void getMyDeliveryAddressList() {
//		用户ID加密	userToken	必填	String	
//		时间戳	timeStamp		Long	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("timeStamp", new Date().getTime() +"");

		Task_Post.clearTask(task_Post_getMyDeliveryAddressList);
		task_Post_getMyDeliveryAddressList = new Task_Post(map,
				API.API_getMyDeliveryAddressList, new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						dismissLoadingDlg();
						RE_getMyDeliveryAddressList result = new RE_getMyDeliveryAddressList();
						try {
							result = JSONArray.parseObject(sResult,
									RE_getMyDeliveryAddressList.class);
							if (result.isSuccess()) {
								 refreshUI(result);
							} else {
								App.showToast(result.getResultMessage());
							}
						} catch (Exception e) {
							e.printStackTrace();
							// App.showToast(R.string.api_error_code_6);
						}
					}
				});
		task_Post_getMyDeliveryAddressList.execute();
		displayLoadingDlg(R.string.loading);

	}

	protected void refreshUI(RE_getMyDeliveryAddressList result) {
		if(result != null && result.getResultList() != null)
		{
			this.arrAddressItems.clear();
			this.arrAddressItems.addAll(result.getResultList());
			adapter.notifyDataSetChanged();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getMyDeliveryAddressList();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_getMyDeliveryAddressList);
	}
	
	public static void show(Activity context,int requestCode,int type) {
		Intent intent = createIntent(context, MyAddressActivity.class);
		intent.putExtra(TYPE, type);
		context.startActivityForResult(intent, requestCode);
	}

	
}
