package cn.crane.application.greenlife.ui.merchant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.fragment.BaseDialogFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 22, 2015 3:34:31 PM
 * 
 */
public class FoodDetailDialogFragment extends BaseDialogFragment {
	public static final String TAG = FoodDetailDialogFragment.class.getSimpleName();
	private View rootView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.dialog_merchant_detail, null);
		return rootView;
	}
	
	public static void show(BaseActivity baseActivity)
	{
		FoodDetailDialogFragment detailDialogFragment = new FoodDetailDialogFragment();
		detailDialogFragment.show(baseActivity.getSupportFragmentManager(), TAG);
	}
}
