package cn.crane.framework.view;

import java.util.ArrayList;
import java.util.List;

import cn.crane.framework.adapter.PageItemAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-29
 */
public class Splash {

	private FrameLayout baseView;
	private ViewPager mViewPager;
	private LinearLayout llDots;
	
	private PageItemAdapter pageItemAdapter;
	private List<View> arrViews;
	private int iCurrentPage;

	private Context context;
	private int [] resIds;
	private int dotResId1;
	private int dotResId2;
	private float fDensity;
	
	private OnSpalshDismissListener onSpalshDismissListener;
	public void setOnSpalshDismissListener(
			OnSpalshDismissListener onSpalshDismissListener) {
		this.onSpalshDismissListener = onSpalshDismissListener;
	}

	/**
	 * Constructor 
	 * @param context
	 * @param resIds
	 * @param resDotselector  
	 */
	public Splash(Context context) {
		super();
		this.context = context;
		this.fDensity = context.getResources().getDisplayMetrics().density;
		
	}
	
	
	
	public void setResIds(int[] drawableIds, int dotResId1, int dotResId2) {
		this.resIds = drawableIds;
		this.dotResId1 = dotResId1;
		this.dotResId2 = dotResId2;
		initViews();
	}
	
	private void initViews() {
		arrViews = new ArrayList<View>();
		baseView = new FrameLayout(context);
		mViewPager = new ViewPager(context);
		llDots = new LinearLayout(context);
		llDots.setOrientation(RadioGroup.HORIZONTAL);
		
		llDots.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout.LayoutParams paramsGroup = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) (20*fDensity));
		paramsGroup.gravity = Gravity.CENTER_HORIZONTAL;
		llDots.setLayoutParams(paramsGroup);
		if(resIds == null)
			return;
		for(int i = 0;i<resIds.length;i++)
		{
			ImageView iv = new ImageView(context);
			iv.setId(i);
			iv.setTag(i);
			iv.setOnClickListener(mClick);
			iv.setOnTouchListener(onTouch);
			iv.setBackgroundResource(resIds[i]);
			arrViews.add(iv);
			RelativeLayout rl = new RelativeLayout(context);
			android.widget.RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
			layoutParam.addRule(RelativeLayout.CENTER_VERTICAL);
			ImageView dot = new ImageView(context);
			dot.setId(i);
			
			if (i==0) {
				dot.setImageResource(dotResId1);
			}else
			{
				dot.setImageResource(dotResId2);
			}
			rl.addView(dot, layoutParam);
			rl.setPadding(0, 0, (int) (13*fDensity), 0);
			llDots.addView(rl);
		}
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		baseView.addView(mViewPager, params);
		
		pageItemAdapter = new PageItemAdapter(arrViews);
		mViewPager.setAdapter(pageItemAdapter);
		mViewPager.setOnPageChangeListener(mPageChange);
		
		LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
				(int) (20 * fDensity));
		params2.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
		params2.bottomMargin = (int) (10*fDensity);
		baseView.addView(llDots,params2);
		initPage();
		addContentView();
	}
	
	private FrameLayout getBaseView() {
		return baseView;
	}
	
	private void addContentView() {
		((Activity)context).addContentView(getBaseView(), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
	}

	public void show() {
		if(baseView != null )
		{
			baseView.setVisibility(View.VISIBLE);
		}
	}
	
	public void dismiss() {
		if(baseView != null && baseView.getVisibility() == View.VISIBLE)
		{
//			baseView.setVisibility(View.INVISIBLE);
			alphaDismissView(baseView);
		}
	}
	
	private void alphaDismissView(final View view) {
		Animation animation = new AlphaAnimation(1, 0);
		animation.setDuration(500);
		view.startAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				view.setVisibility(View.GONE);
				baseView.setVisibility(View.INVISIBLE);
				if(onSpalshDismissListener != null)
				{
					onSpalshDismissListener.onSplashDismissed();
				}
			}
		});
	}
	
	private void initPage() {
		if (resIds != null) {
			for (int i = 0; i < resIds.length; i++) {
				if (iCurrentPage == i)
					((ImageView) llDots.findViewById(i))
							.setImageResource(dotResId1);
				else
					((ImageView) llDots.findViewById(i))
							.setImageResource(dotResId2);
			}
		}
	}
	
	private OnPageChangeListener mPageChange = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			iCurrentPage = arg0;
			initPage();
			if(iCurrentPage == resIds.length-1)
			{
				dismiss();
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	OnClickListener mClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case 0:
				
				break;

			default:
				break;
			}
		}
	};
	
	
	OnTouchListener onTouch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_UP)
			{
				int tag = (Integer) v.getTag();
				if(tag == resIds.length - 1)
				{
					
					dismiss();
					return true;
				}
			}
			return false;
		}
	};
	
	public interface OnSpalshDismissListener
	{
		public void onSplashDismissed();
	}

}
