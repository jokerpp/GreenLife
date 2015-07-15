package cn.crane.application.greenlife.ui.myaccount;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;

public class LoginActivity extends BaseActivity{

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_myaccount_login;
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
		// TODO Auto-generated method stub
		
	}

	public static void show(Context context) {
		Intent intent = createIntent(context, LoginActivity.class);
		context.startActivity(intent);
	}

}
