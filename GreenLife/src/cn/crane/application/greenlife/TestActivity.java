package cn.crane.application.greenlife;


import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.utils.SharedPref;
import cn.crane.framework.activity.BaseActivity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Test Config
 * 
 * @author Administrator
 * 
 */
public class TestActivity extends BaseActivity {
	public static final String HOST = "host";
	private EditText etHost;
	private Button btnSet;
	private Button btnSetDefault;
	
	@Override
	protected int getLayoutId() {
		return R.layout.ac_test_config;
	}

	@Override
	protected void findViews() {
		etHost = (EditText) findViewById(R.id.et_host);
		btnSet = (Button) findViewById(R.id.btn_ok);
		btnSetDefault = (Button) findViewById(R.id.btn_default);
		findViewById(R.id.btn_back).setOnClickListener(this);
	}

	@Override
	protected void bindViews() {
		btnSet.setOnClickListener(this);
		btnSetDefault.setOnClickListener(this);
	}

	@Override
	protected void init() {
		String host = SharedPref.getInstance().getSharedPreference(HOST, API.HOST_DEFAULT);
		etHost.setText(host);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_ok:
			String host = etHost.getText().toString().trim();
			if(TextUtils.isEmpty(host))
			{
				App.showToast("Please input host!");
			}else
			{
				API.HOST = host;
				SharedPref.getInstance().setSharedPreference(HOST, host);
				finish();
			}
			break;
		case R.id.btn_default:
			etHost.setText(API.HOST_DEFAULT);
			
			break;

		default:
			break;
		}
	}

	public static void show(Context context) {
		Intent intent = new Intent(context, TestActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}



}
