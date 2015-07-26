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
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 19, 2015 11:06:41 PM
 * 
 */
public class ResetPasswordActivity extends BaseActivity {
	private Button btn_back;
	private TextView tv_title;
	private Button btn_right;
	private RelativeLayout ll_titleBar;
	private EditText et_password;
	private EditText et_password_again;
	private TextView tv_ok;
	private Task_Post task_Post_updatePassForUser;

	@Override
	protected int getLayoutId() {
		return R.layout.ac_account_reset_psw;
	}

	@Override
	protected void findViews() {
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_right = (Button) findViewById(R.id.btn_right);
		ll_titleBar = (RelativeLayout) findViewById(R.id.ll_titleBar);
		et_password = (EditText) findViewById(R.id.et_password);
		et_password_again = (EditText) findViewById(R.id.et_password_again);
		tv_ok = (TextView) findViewById(R.id.tv_ok);
	}

	@Override
	protected void bindViews() {
		btn_back.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		tv_ok.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

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
			break;
		case R.id.tv_ok:
			if(checkInput())
			{
				updatePassForUser();
			}
			break;

		default:
			break;
		}
	}
	
	private boolean checkInput() {
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
	
	public void updatePassForUser() {
//		用户ID加密	userToken	必填	String
//		旧密码	oldPassord	必填	String
//		新密码	newPassord	必填	String
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("oldPassord", et_password.getText().toString().trim());
		map.put("newPassord", et_password_again.getText().toString().trim());
		Task_Post.clearTask(task_Post_updatePassForUser);
		task_Post_updatePassForUser = new Task_Post(map, API.API_updatePassForUser,
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
		task_Post_updatePassForUser.execute();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_updatePassForUser);
	}
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, ResetPasswordActivity.class));
	}
}
