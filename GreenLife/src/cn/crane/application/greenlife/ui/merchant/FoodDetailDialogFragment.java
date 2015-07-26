package cn.crane.application.greenlife.ui.merchant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.fragment.BaseDialogFragment;
import cn.crane.framework.view.smartimage.SmartImageView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 22, 2015 3:34:31 PM
 * 
 */
public class FoodDetailDialogFragment extends BaseDialogFragment {
	public static final String TAG = FoodDetailDialogFragment.class.getSimpleName();
	private View rootView;
	
	private SmartImageView iv;
	private TextView tvName;
	private TextView tvPrice;
	private TextView tvDetail;
	
	private FoodItem foodItem;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.dialog_merchant_detail, null);
		iv = (SmartImageView) rootView.findViewById(R.id.iv_image);
		tvName = (TextView) rootView.findViewById(R.id.tv_name);
		tvPrice = (TextView) rootView.findViewById(R.id.tv_price);
		tvDetail = (TextView) rootView.findViewById(R.id.tv_detail);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Bundle bundle = getArguments();
		if(bundle != null)
		{
			foodItem = (FoodItem) bundle.getSerializable(FoodItem.TAG);
			if(foodItem != null)
			{
				iv.setImageUrl(foodItem.getDishesPicture());
				tvName.setText(foodItem.getDishesName());
				tvPrice.setText(getString(R.string.txt_format_price,foodItem.getPreferentialPrice()));
				tvDetail.setText(foodItem.getStock());
			}
		}
	}
	
	public static void show(BaseActivity baseActivity,FoodItem foodItem)
	{
		FoodDetailDialogFragment detailDialogFragment = new FoodDetailDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(FoodItem.TAG, foodItem);
		detailDialogFragment.setArguments(bundle);
		detailDialogFragment.show(baseActivity.getSupportFragmentManager(), TAG);
	}
}
