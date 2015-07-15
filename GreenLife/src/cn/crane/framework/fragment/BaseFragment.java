package cn.crane.framework.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-29
 */
public abstract class BaseFragment extends Fragment {
	public  String TAG = "";
	private View rootView;
	private ProgressDialog progressDlg;
	public App app;
	// page 当前页数
	// pageCount
	protected int page = 0;
	protected int pageCount = 10;
	private LinearLayout rootContent;
	private View loadingView;
	
	public boolean isLoading = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TAG = getClass().getSimpleName();
		
		rootView = inflater.inflate(R.layout.ui_base, null);
		rootContent = (LinearLayout) rootView.findViewById(R.id.ll_rootView);
		inflater.inflate(getLayoutId(), rootContent);
		loadingView = rootView.findViewById(R.id.view_loading);
		dismissLoadingDlg();
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		app = (App) getActivity().getApplicationContext();
		findViews();
		bindViews();
		init();
	}

	protected abstract int getLayoutId();

	protected abstract void findViews();

	protected abstract void bindViews();

	protected abstract void init();

	public View findViewById(int resId) {
		if (rootView != null) {
			return rootView.findViewById(resId);
		}
		return null;
	}

	/**
	 * Dismiss soft keyboard
	 */
	public void disInputMethod() {
		InputMethodManager imm = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
					.getApplicationWindowToken(), 0);
		}
	}

	/**
	 * Show loading dialog
	 * 
	 * @param sMsg
	 *            the message to display
	 */
	public void displayLoadingDlg(String sMsg) {
		boolean loading = false;
		if(getActivity() instanceof BaseActivity)
		{
			loading = ((BaseActivity)getActivity()).isLoading;
		}
			if(!loading && loadingView != null)
			{
				loadingView.setVisibility(View.VISIBLE);
				isLoading = true;
			}
//		if (progressDlg != null && progressDlg.isShowing()) {
//			progressDlg.setMessage(sMsg);
//		} else {
//			progressDlg = new ProgressDialog(getActivity());
//			progressDlg.setMessage(sMsg);
//			progressDlg.setIndeterminate(true);
//			progressDlg.setCancelable(true);
//			progressDlg.show();
//		}
		
	}

	/**
	 * Show loading dialog
	 * 
	 * @param resId
	 *            message resId in string.xml to display
	 */
	public void displayLoadingDlg(int resId) {
		displayLoadingDlg(getString(resId));
	}

	/**
	 * Dismiss the loading dialog
	 */
	public void dismissLoadingDlg() {
		if(getActivity() instanceof BaseActivity)
		{
			if(!isLoading)
			{
				((BaseActivity)getActivity()).dismissLoadingDlg();
			}
		}
//		if (progressDlg != null && progressDlg.isShowing())
//			progressDlg.cancel();
		
		if(loadingView != null)
		{
			loadingView.setVisibility(View.GONE);
		}

	}

	/**
	 * get current time
	 * 
	 * @return yyyy-mm-dd
	 */
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sTime = format.format(new Date());
		System.out.println("CurrentTime:" + sTime);
		return sTime;

	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void setVisibleForView(View view, boolean isVisible) {
		if (view != null) {
			view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
		}
	}
}
