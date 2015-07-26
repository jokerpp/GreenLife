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
import cn.crane.application.greenlife.ui.myaccount.LoginActivity;
import cn.crane.application.greenlife.ui.myaccount.RegisterActivity;
import cn.crane.application.greenlife.ui.myaccount.ResetPasswordActivity;
import cn.crane.application.greenlife.ui.myaccount.address.MyAddressActivity;
import cn.crane.application.greenlife.ui.myaccount.collect.MyCollectAtivity;
import cn.crane.application.greenlife.ui.myaccount.comment.MyCommentsAtivity;
import cn.crane.framework.fragment.BaseFragment;
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
				Intent intent = new Intent();
				intent.setClass(getActivity(), RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

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
		scrollView.setHeaderView(headView);
		scrollView.setZoomView(zoomView);
		scrollView.setScrollContentView(conentView);

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

		default:
			break;
		}
	}

}
