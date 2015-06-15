package cn.crane.framework.adapter;

import java.util.List;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-30
 */
public class PageItemAdapter extends PagerAdapter {
	private List<View> mViewArray;

	public PageItemAdapter(List<View> mViewArray) {
		super();
		this.mViewArray = mViewArray;
	}

	@Override
	public int getCount() {
		return mViewArray.size();
	}

	@Override
	public Object instantiateItem(View pager, int position) {
		View v = mViewArray.get(position);
		String xName = v.getClass().getSimpleName();
		Log.e("debug", "xName:" + xName);
		if (v.getParent() != null) {
			Log.e("debug", "v.getParent():"
					+ v.getParent().getClass().getSimpleName());
		} else {
			((ViewPager) pager).addView(v, 0);
		}
		return v;
	}

	@Override
	public void destroyItem(View pager, int position, Object view) {
		((ViewPager) pager).removeView(mViewArray.get(position));
	}

	@Override
	public boolean isViewFromObject(View view, Object xview) {
		return view == xview;
	}

	@Override
	public void finishUpdate(View view) {
	}

	@Override
	public void restoreState(Parcelable p, ClassLoader c) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View view) {

	}
}