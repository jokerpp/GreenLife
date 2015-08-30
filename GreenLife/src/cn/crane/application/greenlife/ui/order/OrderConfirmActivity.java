package cn.crane.application.greenlife.ui.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.TestConfig;
import cn.crane.application.greenlife.adapter.merchant.ListOrderFoodsAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.item.AddressItem;
import cn.crane.application.greenlife.model.item.PayItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantDishesList;
import cn.crane.application.greenlife.ui.myaccount.address.MyAddressActivity;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 10, 2015 11:19:50 PM
 * 
 */
public class OrderConfirmActivity extends BaseActivity {
	public static final String MERCHANT_TOKEN = "merchantToken";
	public static final int requestCode = 1000;
	private TextView tvOrderConfirm;
	
	private Button btn_back;
    private TextView tv_title;
    private CheckBox btn_right;
    private RelativeLayout ll_titleBar;
    private TextView tv_name_tel;
    private TextView tv_address;
    private LinearLayout ll_address;
    private LinearLayout ll_coupon;
    private RadioButton radio_pay_default;
    private RadioButton radio_pay_alipay;
    private RadioButton radio_pay_wechat;
    private RadioGroup radioGroup;
    private TextView tv_send_time;
    private EditText et_mark;
    private TextView tv_coupon;
    private TextView tv_coupon_tips;
    private LinearLayout ll_content;
    private TextView tv_total_price;
	
	private ListView lvFoods;
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
	private ListOrderFoodsAdapter adapter;

	private float totalMoney;

	private float totalDiscountMoney;
	
	private String payType = API_Contant.PAYTYPE_DEFAULT;
	
	private String merchantToken = "";

	private Task_Post task_Post_generateOrder;
	private AddressItem addressItem = null;
	
	@Override
	protected int getLayoutId() {
		return R.layout.ac_order_confirm;
	}

	@Override
	protected void findViews() {
		lvFoods = (ListView) findViewById(R.id.lv);
		tvOrderConfirm = (TextView) findViewById(R.id.tv_confirm);
		
		btn_back = (Button) findViewById(R.id.btn_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_right = (CheckBox) findViewById(R.id.btn_right);
        ll_titleBar = (RelativeLayout) findViewById(R.id.ll_titleBar);
        tv_name_tel = (TextView) findViewById(R.id.tv_name_tel);
        tv_address = (TextView) findViewById(R.id.tv_address);
        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        ll_coupon = (LinearLayout) findViewById(R.id.ll_coupon);
        radio_pay_default = (RadioButton) findViewById(R.id.radio_pay_default);
        radio_pay_alipay = (RadioButton) findViewById(R.id.radio_pay_alipay);
        radio_pay_wechat = (RadioButton) findViewById(R.id.radio_pay_wechat);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tv_send_time = (TextView) findViewById(R.id.tv_send_time);
        et_mark = (EditText) findViewById(R.id.et_mark);
        tv_coupon = (TextView) findViewById(R.id.tv_coupon);
        tv_coupon_tips = (TextView) findViewById(R.id.tv_coupon_tips);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        
        radio_pay_default.setChecked(true);
        if(!TestConfig.IS_SURPORT_PAY_ONLINE)
        {
        	
        	  radio_pay_wechat.setVisibility(View.GONE);
              radio_pay_alipay.setVisibility(View.GONE);
              findViewById(R.id.view_line1).setVisibility(View.GONE);
              findViewById(R.id.view_line2).setVisibility(View.GONE);
        }
        
      
	}

	@Override
	protected void bindViews() {
		radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		adapter = new ListOrderFoodsAdapter(this, arrFoodItems);
		lvFoods.setAdapter(adapter);
		
		btn_back.setOnClickListener(this);
		tvOrderConfirm.setOnClickListener(this);
		ll_address.setOnClickListener(this);
		ll_coupon.setOnClickListener(this);
		tv_send_time.setOnClickListener(this);
		
		
	}

	@Override
	protected void init() {
		
		try {
			List<FoodItem> arrFoodItems = (List<FoodItem>) getIntent().getBundleExtra(FoodItem.TAG).getSerializable(FoodItem.TAG);
			if(arrFoodItems != null)
			{
				this.arrFoodItems.clear();
				this.arrFoodItems.addAll(arrFoodItems);
				adapter.notifyDataSetChanged();
				
			}
			merchantToken = getIntent().getBundleExtra(FoodItem.TAG).getString(MERCHANT_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		refreshAddress();
		getTotalMoney();
	}
	
	
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ll_address:
			if(!DataManager.checkLogin(this))
			{
				return;
			}
			MyAddressActivity.show(this,requestCode,MyAddressActivity.TYPE_SELECT);
			break;
		case R.id.tv_send_time:
			break;
		case R.id.ll_coupon:
//			MyCouponsAtivity.show(this);
			break;
		case R.id.tv_confirm:
			if(!DataManager.checkLogin(this))
			{
				return;
			}
			generateOrder();
			break;

		default:
			break;
		}
	}
	
	private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_pay_default:
				payType = API_Contant.PAYTYPE_DEFAULT;
				break;
			case R.id.radio_pay_alipay:
				payType = API_Contant.PAYTYPE_ALIPAY;
				break;
			case R.id.radio_pay_wechat:
				payType = API_Contant.PAYTYPE_WECHAT;
				break;

			default:
				break;
			}
		}
	};


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
//		tv_total_price.setText(getString(R.string.order_total,totalDiscountMoney + "",totalMoney +""));
		tv_total_price.setText(getString(R.string.order_total_only,totalMoney +""));
		return 0;
	}
	
	@Override
	protected void onActivityResult(int arg0, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			if(data != null)
			{
				Bundle bundle = data.getExtras();
				if(bundle != null && bundle.containsKey(AddressItem.TAG));
				addressItem  = (AddressItem) bundle.getSerializable(AddressItem.TAG);
				
				refreshAddress();
			}
		}
	}
	
	@Override
	public void onActivityReenter(int resultCode, Intent data) {
		super.onActivityReenter(resultCode, data);
		
	}

	private void refreshAddress() {
		if(addressItem != null)
		{
			tv_name_tel.setText(String.format("%s %s", addressItem.getContactName(),addressItem.getMobile()));
			tv_address.setText(addressItem.getAddress());
			tv_address.setVisibility(View.VISIBLE);
		}else
		{
			tv_name_tel.setText(R.string.toast_choose_address);
			tv_address.setVisibility(View.GONE);
		}
	}
	
	
	private void generateOrder() {
//		用户加密ID	userToken	必填	String	
//		商户加密ID	merchantToken	必填	String	
//		代金券加密ID	couponToken		String	
//		送餐地址加密ID	deliveryToken	必填	String	
//		支付方式	payType	必填	Int	1餐到付款、2在线支付
//		配送商家	deliveryType		Int	1商家配送、2宅家配送 
//		优惠方式	discountType		Int	"0不优惠、
//		1新、2免、3折、4赠"
//		期望送达时间	requestDeliveryDateTimeStr		String	格式：2015-06-01 12:45:00
//		备注信息	userNote		String	
//		菜品加密ID	dishesToken		String[]	数组
//		数量	amount		float[]	数组
		if(addressItem == null)
		{
			App.showToast(R.string.toast_choose_address);
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("merchantToken", merchantToken);
		map.put("userToken", DataManager.userToken);
		map.put("couponToken", "");
		map.put("deliveryToken", addressItem == null ? "" : addressItem.getDeliveryToken());
		map.put("payType", payType);
		map.put("deliveryType", "1");
		map.put("discountType", "0");
		map.put("requestDeliveryDateTimeStr", "");
		map.put("userNote", et_mark.getText().toString().trim());
		
		HashMap<String, String[]> mapArr = new HashMap<String, String[]>();
		if(arrFoodItems != null && arrFoodItems.size() > 0)
		{
			String [] dishesTokens = new String[arrFoodItems.size()];
			String [] amounts = new String[arrFoodItems.size()];
			for(int i = 0;i<arrFoodItems.size();i++)
			{
				dishesTokens[i] = arrFoodItems.get(i).getDishesToken();
				amounts[i] = arrFoodItems.get(i).getiCountChoose() +"";
			}
			mapArr.put("dishesToken", dishesTokens);
			mapArr.put("amount", amounts);
		}
		
		Task_Post.clearTask(task_Post_generateOrder);
		task_Post_generateOrder = new Task_Post(map,mapArr,null, API.API_generateOrder,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMerchantDishesList result = new RE_getMerchantDishesList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMerchantDishesList.class);
					if (result.isSuccess()) {
						if(payType == API_Contant.PAYTYPE_DEFAULT)
						{
							App.showToast(R.string.order_commit_success);
							finish();
							return;
						}
//						refreshUI(result);
						PayItem payItem = new PayItem();
						payItem.setOrderToken(result.getData());
						payItem.setPayType(payType);
						payItem.setTotalMoney(totalMoney);
						payItem.setTotalDiscountMoney(totalDiscountMoney);
						
						PayActivity.show(OrderConfirmActivity.this,payItem);
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_generateOrder.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	public static void show(Context context,ArrayList<FoodItem> arrFoodItemsList,String merchantToken) {
		Intent intent = createIntent(context, OrderConfirmActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(FoodItem.TAG, arrFoodItemsList);
		bundle.putString(MERCHANT_TOKEN, merchantToken);
		
		intent.putExtra(FoodItem.TAG, bundle);
		context.startActivity(intent);
	}

}
