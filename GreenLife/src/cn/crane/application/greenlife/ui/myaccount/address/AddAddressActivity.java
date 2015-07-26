package cn.crane.application.greenlife.ui.myaccount.address;

import java.net.URLEncoder;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.result.RE_getMyDeliveryAddressDetails;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 18, 2015 11:55:22 PM
 * 
 */
public class AddAddressActivity extends BaseActivity {

	public static final String TYPE = "type";
	public static final String ADDRESSID = "addressId";
	public static final int TYPE_NEW = 1000;
	public static final int TYPE_EDIT = 1001;
	
	private Button btn_back;
	private TextView tv_title;
	private CheckBox btn_right;
	private RelativeLayout ll_titleBar;
	private TextView tv_contact;
	private EditText et_contact;
	private RadioButton radio_man;
	private RadioButton radio_woman;
	private RadioGroup radioGroup;
	private TextView tv_address;
	private EditText et_address;
	private TextView tv_door_num;
	private EditText et_door_num;
	private TextView tv_mobile;
	private EditText et_mobile;
	private LinearLayout ll_content;
	private TextView tv_address_delete;
	private Task_Post task_Post_addDeliveryAddress;
	private Task_Post task_Post_updateDeliveryAddress;
	private Task_Post task_Post_getMyDeliveryAddressDetails;
	private Task_Post task_Post_deleteMyDeliveryAddress;
	
	private String sex = API_Contant.GENDER_MAN;
	private int type = TYPE_NEW;
	private String addressId;

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_my_address_new;
	}

	@Override
	protected void findViews() {
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_right = (CheckBox) findViewById(R.id.btn_right);
		ll_titleBar = (RelativeLayout) findViewById(R.id.ll_titleBar);
		tv_contact = (TextView) findViewById(R.id.tv_contact);
		et_contact = (EditText) findViewById(R.id.et_contact);
		radio_man = (RadioButton) findViewById(R.id.radio_man);
		radio_woman = (RadioButton) findViewById(R.id.radio_woman);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		tv_address = (TextView) findViewById(R.id.tv_address);
		et_address = (EditText) findViewById(R.id.et_address);
		tv_door_num = (TextView) findViewById(R.id.tv_door_num);
		et_door_num = (EditText) findViewById(R.id.et_door_num);
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		et_mobile = (EditText) findViewById(R.id.et_mobile);
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		tv_address_delete = (TextView) findViewById(R.id.tv_address_delete);
	}

	@Override
	protected void bindViews() {
		btn_back.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		tv_address_delete.setOnClickListener(this);
		
		radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
	}

	@Override
	protected void init() {
		type = getIntent().getIntExtra(TYPE, TYPE_NEW);
		addressId = getIntent().getStringExtra(ADDRESSID);
		if(type == TYPE_EDIT)
		{
			tv_address_delete.setVisibility(View.VISIBLE);
			getMyDeliveryAddressDetails();
		}else
		{
			tv_address_delete.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_right:
			if(checkInput())
			{
				if(type == TYPE_EDIT)
				{
					updateDeliveryAddress();
				}else
				{
					addDeliveryAddress();
				}
			}
			break;
		case R.id.tv_address_delete:
			deleteMyDeliveryAddress();
			break;

		default:
			break;
		}
	}
	
	private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_man:
				sex = API_Contant.GENDER_MAN;
				break;
			case R.id.radio_woman:
				sex = API_Contant.GENDER_WOMAN;
				break;

			default:
				break;
			}
		}
	};
	
	private boolean checkInput() {
		if (TextUtils.isEmpty(et_contact.getText().toString().trim())) {
			App.showToast(et_contact.getHint().toString());
			return false;
		}
		if (TextUtils.isEmpty(et_address.getText().toString().trim())) {
			App.showToast(et_address.getHint().toString());
			return false;
		}
		if (TextUtils.isEmpty(et_door_num.getText().toString().trim())) {
			App.showToast(et_door_num.getHint().toString());
			return false;
		}
		if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
			App.showToast(et_mobile.getHint().toString());
			return false;
		}
		return true;
	}


	

	private void addDeliveryAddress() {
		// 用户加密ID userToken 必填 String
		// 联系人 contactName 必填 String
		// 性别 gender 必填 Int 1男，2女
		// 手机号码 mobile 必填 String
		// 送餐地址 address 必填 String
		// 门牌号 doorCode 必填 String
		// 经度 longitude String
		// 纬度 latitude String
		// 默认地址 isDefault Int 0,非默认；1默认
		String name = et_contact.getText().toString().trim();
		String mobile = et_mobile.getText().toString().trim();
		String address = et_address.getText().toString().trim();
		String doorNum = et_door_num.getText().toString().trim();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("contactName", URLEncoder.encode(name));
		map.put("gender", sex);
		map.put("mobile", URLEncoder.encode(mobile));
		map.put("address", URLEncoder.encode(address));
		map.put("doorCode", URLEncoder.encode(doorNum));
		map.put("longitude", DataManager.longitude);
		map.put("latitude", DataManager.latitude);
		map.put("isDefault", "1");

		Task_Post.clearTask(task_Post_addDeliveryAddress);
		task_Post_addDeliveryAddress = new Task_Post(map,
				API.API_addDeliveryAddress, new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						dismissLoadingDlg();
						Result result = new Result();
						try {
							result = JSONArray.parseObject(sResult,
									Result.class);
							if (result.isSuccess()) {
								// refreshUI(result);
								finish();
							} else {
								App.showToast(result.getResultMessage());
							}
						} catch (Exception e) {
							e.printStackTrace();
							// App.showToast(R.string.api_error_code_6);
						}
					}
				});
		task_Post_addDeliveryAddress.execute();
		displayLoadingDlg(R.string.loading);

	}
	private void updateDeliveryAddress() {
//		用户加密ID	userToken	必填	String	
//		加密地址ID	deliveryToken	必填	String	
//		联系人	contactName		String	
//		性别	gender		Int	1男，2女
//		手机号码	mobile		String	
//		送餐地址	address		String	
//		门牌号	doorCode		String	
//		经度	longitude		String	
//		纬度	latitude		String	
//		默认地址	isDefault		Int	0,非默认；1默认
		String name = et_contact.getText().toString().trim();
		String mobile = et_mobile.getText().toString().trim();
		String address = et_address.getText().toString().trim();
		String doorNum = et_door_num.getText().toString().trim();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("deliveryToken", addressId);
		map.put("contactName", URLEncoder.encode(name));
		map.put("gender", sex);
		map.put("mobile", URLEncoder.encode(mobile));
		map.put("address", URLEncoder.encode(address));
		map.put("doorCode", URLEncoder.encode(doorNum));
		map.put("longitude", DataManager.longitude);
		map.put("latitude", DataManager.latitude);
		map.put("isDefault", "1");
		
		Task_Post.clearTask(task_Post_updateDeliveryAddress);
		task_Post_updateDeliveryAddress = new Task_Post(map,
				API.API_updateDeliveryAddress, new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if (result.isSuccess()) {
						// refreshUI(result);
						finish();
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_updateDeliveryAddress.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	private void getMyDeliveryAddressDetails() {
//		地址ID加密	deliveryToken	必填	String
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("deliveryToken", addressId);
		
		Task_Post.clearTask(task_Post_getMyDeliveryAddressDetails);
		task_Post_getMyDeliveryAddressDetails = new Task_Post(map,
				API.API_getMyDeliveryAddressDetails, new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMyDeliveryAddressDetails result = new RE_getMyDeliveryAddressDetails();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMyDeliveryAddressDetails.class);
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
		task_Post_getMyDeliveryAddressDetails.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	private void deleteMyDeliveryAddress() {
//		用户加密ID 	userToken	必填	String	
//		地址加密ID	deliveryToken	必填	String	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("deliveryToken", addressId);
		map.put("userToken", DataManager.userToken);
		
		Task_Post.clearTask(task_Post_deleteMyDeliveryAddress);
		task_Post_deleteMyDeliveryAddress = new Task_Post(map,
				API.API_deleteMyDeliveryAddress, new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if (result.isSuccess()) {
						App.showToast(R.string.toast_delete_success);
						finish();
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_deleteMyDeliveryAddress.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	protected void refreshUI(RE_getMyDeliveryAddressDetails result) {
		if(result != null)
		{
			et_contact.setText(result.getContactName());
			radioGroup.check(result.isMan() ? R.id.radio_man : R.id.radio_woman);
			et_address.setText(result.getAddress());
			et_door_num.setText(result.getDoorCode());
			et_mobile.setText(result.getMobile());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_addDeliveryAddress);
		Task_Post.clearTask(task_Post_getMyDeliveryAddressDetails);
		Task_Post.clearTask(task_Post_updateDeliveryAddress);
		Task_Post.clearTask(task_Post_deleteMyDeliveryAddress);

	}
	
	public static void show(Context context,int type,String addressId) {
		Intent intent = createIntent(context, AddAddressActivity.class);
		intent.putExtra(TYPE, type);
		intent.putExtra(ADDRESSID, addressId);
		context.startActivity(intent);
	}
}
