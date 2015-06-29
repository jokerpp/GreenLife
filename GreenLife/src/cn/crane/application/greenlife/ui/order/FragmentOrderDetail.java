package cn.crane.application.greenlife.ui.order;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.order.ListOrderDetailAdapter;
import cn.crane.application.greenlife.adapter.order.ListOrderFoodsAdapter;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 14, 2015 11:04:52 PM
 * 
 */
public class FragmentOrderDetail extends BaseFragment implements OnClickListener {

	private TextView tv_merchant_name;
	private ListView lv_foods;
	private TextView tv_total_price;
	private ListView lv_detail;
	private LinearLayout ll_content;
	private TextView tv_order_again;
	
	private ListOrderFoodsAdapter foodsAdapter;
	private ListOrderDetailAdapter detailAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_detail;
	}

	@Override
	protected void findViews() {
		tv_merchant_name = (TextView) findViewById(R.id.tv_merchant_name);
		lv_foods = (ListView) findViewById(R.id.lv_foods);
		tv_total_price = (TextView) findViewById(R.id.tv_total_price);
		lv_detail = (ListView) findViewById(R.id.lv_detail);
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		tv_order_again = (TextView) findViewById(R.id.tv_order_again);
	}

	@Override
	protected void bindViews() {
		foodsAdapter = new ListOrderFoodsAdapter(getActivity(), null);
		lv_foods.setAdapter(foodsAdapter);
		
		detailAdapter = new ListOrderDetailAdapter(getActivity(), null);
		lv_detail.setAdapter(detailAdapter);
		
		tv_order_again.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_order_again:
			OrderConfirmActivity.show(getActivity());
			break;

		default:
			break;
		}
	}

}
