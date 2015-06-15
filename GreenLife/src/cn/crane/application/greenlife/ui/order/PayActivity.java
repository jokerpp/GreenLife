package cn.crane.application.greenlife.ui.order;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 10, 2015 11:19:50 PM
 * 
 */
public class PayActivity extends BaseActivity {
	private Button btnBack;

	@Override
	protected int getLayoutId() {
		return R.layout.ac_order_pay;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
	}
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, PayActivity.class));
	}

}
