package cn.crane.application.greenlife.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListOrderFoodsAdapter;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 10, 2015 11:19:50 PM
 * 
 */
public class OrderConfirmActivity extends BaseActivity {
	
	private TextView tvOrderConfirm;
	
	private ListView lvFoods;
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
	private ListOrderFoodsAdapter adapter;

	private Button btnBack;

	@Override
	protected int getLayoutId() {
		return R.layout.ac_order_confirm;
	}

	@Override
	protected void findViews() {
		lvFoods = (ListView) findViewById(R.id.lv);
		tvOrderConfirm = (TextView) findViewById(R.id.tv_confirm);
		btnBack = (Button) findViewById(R.id.btn_back);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
		adapter = new ListOrderFoodsAdapter(this, arrFoodItems);
		lvFoods.setAdapter(adapter);
		
		tvOrderConfirm.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_confirm:
			PayActivity.show(this);
			break;

		default:
			break;
		}
	}
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, OrderConfirmActivity.class));
	}

}
