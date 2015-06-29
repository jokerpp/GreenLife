package cn.crane.application.greenlife.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.ListOrderAdapter;
import cn.crane.application.greenlife.ui.order.OrderDetailActivity;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 26, 2015 12:20:21 AM
 * 
 */
public class FragmentOrderList extends BaseFragment implements OnClickListener, OnItemClickListener{
	
	private ListView lv;
	private ListOrderAdapter adapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_list;
	}

	@Override
	protected void findViews() {
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
	}

	@Override
	protected void init() {
		adapter = new ListOrderAdapter(getActivity(), null);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			getActivity().onBackPressed();
			break;
		case R.id.btn_right:
			
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		OrderDetailActivity.show(getActivity());
	}

}
