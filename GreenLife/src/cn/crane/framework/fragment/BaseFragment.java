package cn.crane.framework.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.crane.application.greenlife.App;
import cn.crane.framework.activity.BaseActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(getLayoutId(), null);
		TAG = getClass().getSimpleName();
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
		if(getActivity() instanceof BaseActivity)
		{
			((BaseActivity)getActivity()).displayLoadingDlg(sMsg);
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
			((BaseActivity)getActivity()).dismissLoadingDlg();
		}
//		if (progressDlg != null && progressDlg.isShowing())
//			progressDlg.cancel();

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
}
