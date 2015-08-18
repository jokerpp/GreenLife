package cn.crane.application.greenlife.ui.myaccount;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.TestConfig;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.result.RE_Login;
import cn.crane.framework.activity.BaseActivity;

public class LoginActivity extends BaseActivity{

	private EditText userName_edit;
	private EditText password_edit;
	
	private Button btn_right;
	private TextView login_button;
	private TextView login_forgetpassword;
	private Task_Post task_Post_login;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_myaccount_login;
	}

	@Override
	protected void findViews() {
		userName_edit = (EditText) findViewById(R.id.userName_edit);
		password_edit = (EditText) findViewById(R.id.password_edit);
		login_button = (TextView) findViewById(R.id.login_button);
		login_forgetpassword = (TextView) findViewById(R.id.login_forgetpassword);
		btn_right = (Button) findViewById(R.id.btn_right);
	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub
		login_button.setOnClickListener(this);
		login_forgetpassword.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		if(TestConfig.TEST_LOGIN)
		{
			userName_edit.setText(TestConfig.TEST_ACCOUNT);
			password_edit.setText(TestConfig.TEST_PASSWORD);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_right:
			RegisterActivity.show(this);
			finish();
			break;
		case R.id.login_forgetpassword:
			ForgetPasswordActivity.show(this);
			break;
		case R.id.login_button:
			if(checkInput())
			{
				doLoginCustomer();
			}
			break;

		default:
			break;
		}
	}
	
	private boolean checkInput() {
		if (TextUtils.isEmpty(userName_edit.getText().toString().trim())) {
			App.showToast(userName_edit.getHint().toString());
			return false;
		}
		if (TextUtils.isEmpty(password_edit.getText().toString().trim())) {
			App.showToast(password_edit.getHint().toString());
			return false;
		}
		return true;
	}

	private void doLoginCustomer() {
//		手机号	mobile	必填	String		
//		密码	password	必填	String		
//		用户类型	userType	必填	String		"0、普通用户；
//		4、商户人员(普通)、
//		6、配送员"
		final String tel = userName_edit.getText().toString().trim();
		final String psw = password_edit.getText().toString().trim();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mobile", tel);
		map.put("password", psw);
		map.put("userType", API_Contant.USERTYPE_USER);
		Task_Post.clearTask(task_Post_login);
		task_Post_login = new Task_Post(map, API.API_loginForUser,
				new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						dismissLoadingDlg();
						RE_Login re_Login = new RE_Login();
						try {
							re_Login = JSONArray.parseObject(sResult,
									RE_Login.class);
							if (re_Login.isSuccess()) {
								DataManager.setTokenUser(re_Login.getUserToken());
								
//								if(checkBoxRememberPsw.isChecked())
//								{
//									SharedPref.getInstance().setSharedPreferenceAsBoolean(SharedPref.REMENBER_PSW, true);
//									SharedPref.getInstance().setSharedPreference(SharedPref.LOGIN_ACCOUNT, tel);
//									SharedPref.getInstance().setSharedPreference(SharedPref.LOGIN_PSW, psw);
//								}else
//								{
//									SharedPref.getInstance().setSharedPreferenceAsBoolean(SharedPref.REMENBER_PSW, false);
//
//								}
//								if (toMain) {
//								}
								finish();
							} else {
								App.showToast(re_Login.getResultMessage());
							}
						} catch (Exception e) {
							e.printStackTrace();
							// App.showToast(R.string.api_error_code_6);
						}
					}
				});
		task_Post_login.execute();
		displayLoadingDlg(R.string.loading);

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Task_Post.clearTask(task_Post_login);
	}

	public static void show(Context context) {
		Intent intent = createIntent(context, LoginActivity.class);
		context.startActivity(intent);
	}

}
