package cn.crane.application.greenlife.ui;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import cn.crane.application.greenlife.R;
import cn.crane.framework.fragment.BaseFragment;
import cn.crane.framework.utils.myaccount.PullToZoomScrollViewEx;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 11:38:46 PM
 * 
 */
public class FragmentMy extends BaseFragment {
	private PullToZoomScrollViewEx scrollView;
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
	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		int mScreenHeight = localDisplayMetrics.heightPixels;
		int mScreenWidth = localDisplayMetrics.widthPixels;

		LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(
				mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));

		scrollView.setHeaderLayoutParams(localObject);
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
		scrollView.setHeaderView(headView);
		scrollView.setZoomView(zoomView);
		scrollView.setScrollContentView(conentView);

	}

}
