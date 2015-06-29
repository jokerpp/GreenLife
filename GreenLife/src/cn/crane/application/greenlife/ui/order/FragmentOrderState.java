package cn.crane.application.greenlife.ui.order;

import android.widget.ListView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.order.ListOrderStateAdapter;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 14, 2015 11:04:52 PM
 * 
 */
public class FragmentOrderState extends BaseFragment {

	private ListView lv;
	private ListOrderStateAdapter adapter;
	@Override
	protected int getLayoutId() {
		return R.layout.fragment_order_state;
	}

	@Override
	protected void findViews() {
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
		adapter = new ListOrderStateAdapter(getActivity(), null);
		lv.setAdapter(adapter);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
