package cn.crane.application.greenlife.ui.merchant;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 13, 2015 10:29:55 PM
 * 
 */
public class FoodListActivity extends BaseActivity {
	public static final String MERCHANT_TOKEN = "merchantToken";
	private FragmentManager fragmentManager;
	
	private String merchantToken = "";
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_common;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		
		merchantToken = getIntent().getStringExtra(MERCHANT_TOKEN);
		fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.rootView, FragmentMeals.newInstance(merchantToken)).commit();
	}
	
	public static void show(Context context,String merchantToken) {
		Intent intent = createIntent(context, FoodListActivity.class);
		intent.putExtra(MERCHANT_TOKEN, merchantToken);
		context.startActivity(intent);
	}

}
