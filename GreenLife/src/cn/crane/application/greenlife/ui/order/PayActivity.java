package cn.crane.application.greenlife.ui.order;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.model.item.PayItem;
import cn.crane.application.greenlife.utils.pay.PayUtils;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 10, 2015 11:19:50 PM
 * 
 */
public class PayActivity extends BaseActivity {
	private Button btn_back;
    private TextView tv_title;
    private CheckBox btn_right;
    private RelativeLayout ll_titleBar;
    private TextView tv_merchant_name;
    private TextView tv_old_price;
    private TextView tv_pay_price;
    private RadioButton radio_pay_default;
    private RadioButton radio_pay_alipay;
    private RadioGroup radioGroup;
    private LinearLayout ll_content;
    private TextView tv_pay_confirm;
    
    private PayItem payItem = new PayItem();

    private String payType = API_Contant.PAYTYPE_ALIPAY;
    
	@Override
	protected int getLayoutId() {
		return R.layout.ac_order_pay;
	}

	@Override
	protected void findViews() {
		btn_back = (Button) findViewById(R.id.btn_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_right = (CheckBox) findViewById(R.id.btn_right);
        ll_titleBar = (RelativeLayout) findViewById(R.id.ll_titleBar);
        tv_merchant_name = (TextView) findViewById(R.id.tv_merchant_name);
        tv_old_price = (TextView) findViewById(R.id.tv_old_price);
        tv_pay_price = (TextView) findViewById(R.id.tv_pay_price);
        radio_pay_default = (RadioButton) findViewById(R.id.radio_pay_default);
        radio_pay_alipay = (RadioButton) findViewById(R.id.radio_pay_alipay);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        tv_pay_confirm = (TextView) findViewById(R.id.tv_pay_confirm);
	}

	@Override
	protected void bindViews() {
		btn_back.setOnClickListener(this);
		tv_pay_confirm.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		if(bundle != null)
		{
			payItem = (PayItem) bundle.getSerializable(PayItem.TAG);
			if(payItem != null)
			{
				tv_pay_price.setText(getString(R.string.txt_format_price,payItem.getTotalMoney() + ""));
				tv_old_price.setText(getString(R.string.txt_format_price,payItem.getTotalDiscountMoney() + ""));
				payType = payItem.getPayType();
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.tv_pay_confirm:
			String json = "";
			if(API_Contant.PAYTYPE_ALIPAY.equalsIgnoreCase(payType))
			{
				
				PayUtils.sendAliPay(this, json);
			}else if(API_Contant.PAYTYPE_WECHAT.equalsIgnoreCase(payType))
			{
				PayUtils.sendWechatPayReq(this, json);
			}
			break;

		default:
			break;
		}
	}
	
	public static void show(Context context,PayItem payItem) {
		Intent intent = createIntent(context, PayActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(PayItem.TAG, payItem);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
