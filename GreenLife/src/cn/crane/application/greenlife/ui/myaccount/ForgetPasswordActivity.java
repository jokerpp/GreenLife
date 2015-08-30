package cn.crane.application.greenlife.ui.myaccount;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.utils.VerifyUtils;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 19, 2015 11:06:41 PM
 * 
 */
public class ForgetPasswordActivity extends BaseActivity {
	private Button btn_back;
	private TextView tv_title;
	private Button btn_right;
	private RelativeLayout ll_titleBar;
	private EditText et_mobile;
	private EditText et_captcha;
	private EditText et_password;
	private EditText et_password_again;
	private TextView tv_next;
	private TextView tv_get_captcha;
	
	private View ll_captcha;
	private Task_Post task_Post_findForgetPassForUser;

	@Override
	protected int getLayoutId() {
		return R.layout.ac_account_forget_psw;
	}

	@Override
	protected void findViews() {
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_right = (Button) findViewById(R.id.btn_right);
		ll_titleBar = (RelativeLayout) findViewById(R.id.ll_titleBar);
		et_mobile = (EditText) findViewById(R.id.et_mobile);
		et_captcha = (EditText) findViewById(R.id.et_captcha);
		et_password = (EditText) findViewById(R.id.et_password);
		et_password_again = (EditText) findViewById(R.id.et_password_again);
		tv_next = (TextView) findViewById(R.id.tv_next);
		tv_get_captcha = (TextView) findViewById(R.id.tv_get_captcha);
		ll_captcha = findViewById(R.id.ll_captcha);
	}

	@Override
	protected void bindViews() {
		btn_back.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		tv_get_captcha.setOnClickListener(this);
		tv_next.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		ll_captcha.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_right:
			LoginActivity.show(this);
			finish();
			break;
		case R.id.tv_get_captcha:
			getCaptcha();
			break;
		case R.id.tv_next:
			if(checkInput())
			{
				findForgetPassForUser();
			}
			break;

		default:
			break;
		}
	}
	
	private void getCaptcha() {
		DataManager.getInstance().getCaptcha(et_mobile, new DataManager.Callback() {
			
			@Override
			public void onPre() {
				displayLoadingDlg(R.string.loading);
			}
			
			@Override
			public void onPost(Result result) {
				dismissLoadingDlg();
			}
			
			@Override
			public void onError() {
				dismissLoadingDlg();				
			}
		});
	}
	
	
	private boolean checkInput() {
		if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
			App.showToast(et_mobile.getHint().toString());
			return false;
		}
		if (!VerifyUtils.checkMobile(et_mobile.getText().toString().trim())) {
			App.showToast(R.string.please_input_right_phone);
			return false;
		}
//		if (TextUtils.isEmpty(et_captcha.getText().toString().trim())) {
//			App.showToast(et_captcha.getHint().toString());
//			return false;
//		}
		if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
			App.showToast(et_password.getHint().toString());
			return false;
		}
		if (TextUtils.isEmpty(et_password_again.getText().toString().trim())) {
			App.showToast(et_password_again.getHint().toString());
			return false;
		}
		return true;
	}
	
	public void findForgetPassForUser() {
//		手机号码	mobile	必填	String	
//		验证码	validateCode	必填	String	
//		密码	password	必填	String	
//		确认密码	confirmPassword	必填	String	
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("userToken", DataManager.userToken);
		map.put("mobile", et_mobile.getText().toString().trim());
		map.put("validateCode", et_captcha.getText().toString().trim());
		map.put("oldPassord", et_password.getText().toString().trim());
		map.put("newPassord", et_password_again.getText().toString().trim());
		Task_Post.clearTask(task_Post_findForgetPassForUser);
		task_Post_findForgetPassForUser = new Task_Post(map, API.API_findForgetPassForGreenLife,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if(result.isSuccess())
					{
						App.showToast(R.string.change_psw_success);
						finish();
					}else
					{
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		task_Post_findForgetPassForUser.execute();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_findForgetPassForUser);
	}

	public static void show(Context context) {
		context.startActivity(createIntent(context, ForgetPasswordActivity.class));
	}
}
