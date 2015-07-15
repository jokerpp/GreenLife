package cn.crane.application.greenlife.ui;

import android.os.Handler;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jul 13, 2015 8:28:36 PM
 * 
 */
public class SplashActivity extends BaseActivity {

	private TextView tvVersion;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_splash;
	}

	@Override
	protected void findViews() {
		tvVersion = (TextView) findViewById(R.id.tv_version);
	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		tvVersion.setText(App.getVersion());
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				MainActivity.show(SplashActivity.this);
				finish();
			}
		}, 2000);
	}

}
