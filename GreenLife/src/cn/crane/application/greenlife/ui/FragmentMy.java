package cn.crane.application.greenlife.ui;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.result.RE_getInfoForUser;
import cn.crane.application.greenlife.ui.myaccount.LoginActivity;
import cn.crane.application.greenlife.ui.myaccount.RegisterActivity;
import cn.crane.application.greenlife.ui.myaccount.ResetPasswordActivity;
import cn.crane.application.greenlife.ui.myaccount.address.MyAddressActivity;
import cn.crane.application.greenlife.ui.myaccount.collect.MyCollectAtivity;
import cn.crane.application.greenlife.ui.myaccount.comment.MyCommentsAtivity;
import cn.crane.framework.fragment.BaseFragment;
import cn.crane.framework.utils.MakePhoneCall;
import cn.crane.framework.utils.myaccount.MyaccountRoundImageView;
import cn.crane.framework.utils.myaccount.PullToZoomScrollViewEx;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 11:38:46 PM
 * 
 */
public class FragmentMy extends BaseFragment implements OnClickListener {
	private PullToZoomScrollViewEx scrollView;
	private TextView login;
	private TextView register;
	
	
	private LinearLayout myaccount_address;
	private LinearLayout myaccount_collect;
	private LinearLayout myaccount_comments;
	private LinearLayout myaccount_editpasswd;
	private TextView tv_phone;
	private TextView tv_user_name;
	private MyaccountRoundImageView iv_user_head;
	private View ll_action_button;
	private LinearLayout myaccount_phone;

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_my;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		this.loadViewForCode();
		scrollView = (PullToZoomScrollViewEx) this
				.findViewById(R.id.scroll_view);
		
		myaccount_address = (LinearLayout) findViewById(R.id.myaccount_address);
		myaccount_collect = (LinearLayout) findViewById(R.id.myaccount_collect);
		myaccount_comments = (LinearLayout) findViewById(R.id.myaccount_comments);
		myaccount_editpasswd = (LinearLayout) findViewById(R.id.myaccount_editpasswd);
		myaccount_phone = (LinearLayout) findViewById(R.id.myaccount_phone);
		tv_phone = (TextView) findViewById(R.id.myaccount_item_text_phone);
	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);
		int mScreenHeight = localDisplayMetrics.heightPixels;
		int mScreenWidth = localDisplayMetrics.widthPixels;

		LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(
				mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));

		scrollView.setHeaderLayoutParams(localObject);
		
		myaccount_address.setOnClickListener(this);
		myaccount_collect.setOnClickListener(this);
		myaccount_comments.setOnClickListener(this);
		myaccount_editpasswd.setOnClickListener(this);
		myaccount_phone.setOnClickListener(this);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.setClass(getActivity(), RegisterActivity_old.class);
//				startActivity(intent);
				
				RegisterActivity.show(getActivity());
			}
		});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		tv_user_name.setText("");
		
		myaccount_phone.setVisibility(View.VISIBLE);
		
		tv_phone.setText(getString(R.string.txt_kefu_format,getString(R.string.txt_kefu_tel)));
		
		myaccount_phone.setTag(getString(R.string.txt_kefu_tel));
	}

	private void loadViewForCode() {
		PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
		View headView = LayoutInflater.from(getActivity()).inflate(
				R.layout.profile_head_view, null, false);
		View zoomView = LayoutInflater.from(getActivity()).inflate(
				R.layout.profile_zoom_view, null, false);
		View conentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.profile_content_view, null, false);
		login = (TextView) headView.findViewById(R.id.tv_login);
		register = (TextView) headView.findViewById(R.id.tv_register);
		tv_user_name = (TextView) headView.findViewById(R.id.tv_user_name);
		iv_user_head = (MyaccountRoundImageView) headView.findViewById(R.id.iv_user_head);
		ll_action_button =  headView.findViewById(R.id.ll_action_button);
		scrollView.setHeaderView(headView);
		scrollView.setZoomView(zoomView);
		scrollView.setScrollContentView(conentView);

	}
	
	private void onLoginStateChanged(boolean isLogin) {
		if(isLogin)
		{
			ll_action_button.setVisibility(View.GONE);
			tv_user_name.setVisibility(View.VISIBLE);
			getMyLoginInfo();
		}else
		{
			ll_action_button.setVisibility(View.VISIBLE);
			tv_user_name.setVisibility(View.GONE);
		}
	}
	
	private void getMyLoginInfo() {
		if(DataManager.isLogin())
		{
			DataManager.getInstance().getInfoForUser(new DataManager.Callback() {
				
				@Override
				public void onPre() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPost(Result result) {
					// TODO Auto-generated method stub
					if(result != null)
					{
						RE_getInfoForUser getInfoForUser = (RE_getInfoForUser) result;
//						iv_user_head.setImageUrl(getInfoForUser.getAvatarPicture(),R.drawable.icon_head_logo);
						tv_user_name.setText(getInfoForUser.getNickname());
					}
				}
				
				@Override
				public void onError() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.myaccount_address:
			if(!DataManager.checkLogin(getActivity()))
			{
				return;
			}
			MyAddressActivity.show(getActivity(),0,MyAddressActivity.TYPE_VIEW);
			break;
		case R.id.myaccount_collect:
			if(!DataManager.checkLogin(getActivity()))
			{
				return;
			}
			MyCollectAtivity.show(getActivity());
			break;
		case R.id.myaccount_comments:
			if(!DataManager.checkLogin(getActivity()))
			{
				return;
			}
			MyCommentsAtivity.show(getActivity());
			break;
		case R.id.myaccount_editpasswd:
			if(!DataManager.checkLogin(getActivity()))
			{
				return;
			}
			ResetPasswordActivity.show(getActivity());
			break;
		case R.id.myaccount_phone:
			if(v.getTag() instanceof String)
			{
				String tel = (String) v.getTag();
				MakePhoneCall.call(getActivity(), tel);
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		onLoginStateChanged(DataManager.isLogin());
	}

}
