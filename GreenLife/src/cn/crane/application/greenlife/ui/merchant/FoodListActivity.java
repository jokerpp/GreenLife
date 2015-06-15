package cn.crane.application.greenlife.ui.merchant;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 13, 2015 10:29:55 PM
 * 
 */
public class FoodListActivity extends BaseActivity {

	private FragmentManager fragmentManager;
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
		fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.rootView, new FragmentMeals()).commit();
	}
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, FoodListActivity.class));
	}

}
