package cn.crane.framework.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.utils.stat.BaiduStatUtil;
import cn.crane.framework.view.swiptback.SwipeBackActivityBase;
import cn.crane.framework.view.swiptback.SwipeBackActivityHelper;
import cn.crane.framework.view.swiptback.SwipeBackLayout;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

/**
 * 
 * @author Ruifeng.yu Email:xyyh0116@aliyun.com
 * 
 * @date 2014-10-29
 */
@SuppressLint("NewApi")
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener, SwipeBackActivityBase {
	private SwipeBackActivityHelper mHelper;
	private ProgressDialog progressDlg;
	public App app;
	public String TAG;

	// page 当前页数
	// pageCount
	protected static final int PAGE_START = 0; 
	protected int page = PAGE_START;
	protected int pageCount = 10;
	private SwipeBackLayout mSwipeBackLayout;
	private LinearLayout llRootView;
	private View loadingView;

	public boolean isLoading = false;
	// public Class<BaseActivity>[] activitiesDotCheckToken = {
	// LoginActivity.class, RegisterActivity.class };

	protected void resetPage() {
		page = PAGE_START;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (App) getApplicationContext();
		TAG = getClass().getSimpleName();
		ActivityMannager.getInstance().addActivity(this);
//		setContentView(getLayoutId());
		setContentView(R.layout.ui_base);
		llRootView = (LinearLayout) findViewById(R.id.ll_rootView);
		if (getLayoutId() != 0) {
			LayoutInflater.from(this).inflate(getLayoutId(), llRootView);
		}
		loadingView = findViewById(R.id.view_loading);
		loadingView.setVisibility(View.GONE);

		
		// addMainBtn();
		addSwiptBack();

		try {
			if (getActionBar() != null) {
				getActionBar().hide();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} catch (Error e) {
			// TODO: handle exception
		}
		
		findViews();
		bindViews();
		init();
	}

	private void addSwiptBack() {
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivityCreate();
		mSwipeBackLayout = getSwipeBackLayout();
		mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
		boolean isSupportSwiptBack = isSupportSwiptBack();
		setSwipeBackEnable(isSupportSwiptBack);
		if (isSupportSwiptBack) {
			// requestWindowFeature(Window.FEATURE_NO_TITLE);
			// Resources res = getResources();
			// Drawable drawable =
			// res.getDrawable(R.drawable.nocolor);////注意该nocolor图片是透明的
			// // Drawable drawable =
			// res.getDrawable(R.drawable.bg_white);////注意该nocolor图片是透明的
			// this.getWindow().setBackgroundDrawable(drawable);
		}
	}

	/**
	 * 右滑关闭页面
	 * 
	 * @return
	 */
	protected boolean isSupportSwiptBack() {
		View view = findViewById(R.id.btn_back);
		return (view != null) && (view.getVisibility() == View.VISIBLE);
	}

	protected abstract int getLayoutId();

	protected abstract void findViews();

	protected abstract void bindViews();

	protected abstract void init();

	// private void addMainBtn() {
	// if (isShowMainBtn()) {
	// View view = LayoutInflater.from(this).inflate(
	// R.layout.view_btn_main, null);
	// addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
	// LayoutParams.MATCH_PARENT));
	// view.findViewById(R.id.btn_main).setOnClickListener(
	// new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// MainActivity.show(getInstance());
	// }
	// });
	// }
	// }

	protected boolean isShowMainBtn() {
		return false;
	}

	/**
	 * 判断当前应用程序处于前台还是后台
	 */
	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Show loading dialog
	 * 
	 * @param sMsg
	 *            the message to display
	 */
	public void displayLoadingDlg(String sMsg) {

//		if (progressDlg != null && progressDlg.isShowing()) {
//			progressDlg.setMessage(sMsg);
//		} else {
//			progressDlg = new ProgressDialog(this);
//			progressDlg.setMessage(sMsg);
//			progressDlg.setIndeterminate(true);
//			progressDlg.setCancelable(true);
//			progressDlg.show();
//		}
		if(loadingView != null)
		{
			loadingView.setVisibility(View.VISIBLE);
			isLoading = true;
		}
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
	 * 
	 * @param listener
	 */
	public void setOnDismissListener(OnCancelListener listener) {
//		if (progressDlg != null)
//			progressDlg.setOnCancelListener(listener);
	}

	/**
	 * Dismiss the loading dialog
	 */
	public void dismissLoadingDlg() {
//		if (progressDlg != null && progressDlg.isShowing())
//			progressDlg.cancel();
		// if(loadingView != null)
		// {
		// loadingView.dismissLoading();
		// }
		if(loadingView != null)
		{
			loadingView.setVisibility(View.GONE);
			isLoading = false;
		}
	}

	/**
	 * Dismiss soft keyboard
	 */
	public void disInputMethod() {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) {
				imm.hideSoftInputFromWindow(getCurrentFocus()
						.getApplicationWindowToken(), 0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Show soft keyboard
	 */
	public void showInputMethod() {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(getCurrentFocus(), InputMethodManager.SHOW_FORCED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	/**
	 * get current time 04000131
	 * 
	 * @return yyyy-mm-dd
	 */
	public String getCurrentTimeMills() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sTime = format.format(new Date());
		System.out.println("CurrentTime:" + sTime);
		sTime = new Date().getTime() / 1000 + "";
		return sTime;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// if (event.getAction() == MotionEvent.ACTION_DOWN) {
		// disInputMethod();
		// }
		return super.onTouchEvent(event);
	}

	@Override
	protected void onPause() {
		super.onPause();
		BaiduStatUtil.onActivityPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		BaiduStatUtil.onActivityResume(this);
	}

	@Override
	protected void onDestroy() {
		ActivityMannager.getInstance().removeActivity(this);
		super.onDestroy();
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v == null && mHelper != null)
			return mHelper.findViewById(id);
		return v;
	}

	@Override
	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	@Override
	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	@Override
	public void scrollToFinishActivity() {
		getSwipeBackLayout().scrollToFinishActivity();
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent != null)
			{
				
			}
		}
	};
	
	public void setVisibleForView(View view, boolean isVisible) {
		if (view != null) {
			view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
		}
	}
	
	
	public static Intent createIntent(Context context,Class<? extends BaseActivity> cls) {
		Intent intent = new Intent(context,cls);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}

}
