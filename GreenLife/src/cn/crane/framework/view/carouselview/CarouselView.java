package cn.crane.framework.view.carouselview;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.adapter.PageItemAdapter;
import cn.crane.framework.view.smartimage.SmartImageView;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date Jun 8, 2015
 */
public class CarouselView extends FrameLayout {
	private static final int MSG_CHANGE_IMAGE = 1000;
	private static final int TIME_SPAN = 6 * 1000;
	private View baseView;
	private ViewPager viewPager;
	private LinearLayout llDots;
	private ArrayList<View> arrViews = new ArrayList<View>();
	private Context context;
	private PageItemAdapter pageItemAdapter;
	private int iCurrentPage;
	private List<CarouselItemInfo> arrCarouselItemInfos;
	private OnItemListener onItemListener;
	

	public void setOnItemListener(OnItemListener onItemListener) {
		this.onItemListener = onItemListener;
	}

	public CarouselView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public CarouselView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public CarouselView(Context context) {
		super(context);
		initView(context);
	}

	public void setArrPictureInfos(List<CarouselItemInfo> arrPictureInfos) {
		// this.arrPictureInfos.clear();
		// this.arrPictureInfos.addAll(arrPictureInfos);
		if (arrPictureInfos == null || arrPictureInfos.size() == 0) {
			setVisibility(View.GONE);
		} else {
			this.arrCarouselItemInfos = arrPictureInfos;
			initViewPager();
			setVisibility(View.VISIBLE);
		}

	}

	private void initView(Context context) {
		this.context = context;
		baseView = LayoutInflater.from(context).inflate(R.layout.view_pictures,
				this);
		viewPager = (ViewPager) baseView.findViewById(R.id.viewPager);
		llDots = (LinearLayout) baseView.findViewById(R.id.ll_dots);

	}

	private void initViewPager() {

		if (arrCarouselItemInfos == null) {
			clearViewPager();
			return;
		}
		setVisibility(View.VISIBLE);
		arrViews.clear();
		RadioGroup.LayoutParams paramsGroup = new RadioGroup.LayoutParams(
				RadioGroup.LayoutParams.WRAP_CONTENT,
				RadioGroup.LayoutParams.WRAP_CONTENT);
		paramsGroup.gravity = Gravity.CENTER_HORIZONTAL;

		llDots.removeAllViews();
		for (int i = 0; i < arrCarouselItemInfos.size(); i++) {
			SmartImageView iv = new SmartImageView(context);
			iv.setScaleType(ScaleType.CENTER_CROP);
			iv.setId(i);
			iv.setTag(i);
			iv.setOnClickListener(mClick);
			// iv.setImageUrl(arrImageUrls[i]);
			iv.setImageUrl(arrCarouselItemInfos.get(i).getImageUrl(), arrCarouselItemInfos
					.get(i).getDefaultResourse());
			arrViews.add(iv);
			RelativeLayout rl = new RelativeLayout(context);
			android.widget.RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
			layoutParam.addRule(RelativeLayout.CENTER_VERTICAL);
			ImageView dot = new ImageView(context);
			dot.setId(i);

			if (i == 0) {
				dot.setImageResource(R.drawable.dot_wihte);
			} else {
				dot.setImageResource(R.drawable.dot_green);
			}
			rl.addView(dot, layoutParam);
			rl.setPadding(0, 0, (int) (13 * App.fDensity), 0);
			llDots.addView(rl);
		}

		pageItemAdapter = new PageItemAdapter(arrViews);
		viewPager.setAdapter(pageItemAdapter);
		viewPager.setOnPageChangeListener(mPageChange);
		initPage();
		mHandler.sendEmptyMessageDelayed(MSG_CHANGE_IMAGE, TIME_SPAN);
	}

	private void initPage() {
		if (arrCarouselItemInfos == null)
			return;
		for (int i = 0; i < arrCarouselItemInfos.size(); i++) {
			if (iCurrentPage == i)
				((ImageView) llDots.findViewById(i))
						.setImageResource(R.drawable.dot_wihte);
			else
				((ImageView) llDots.findViewById(i))
						.setImageResource(R.drawable.dot_green);
		}
	}

	private OnPageChangeListener mPageChange = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			iCurrentPage = arg0;
			initPage();
			if (onItemListener != null) {
				onItemListener.onItemChanged(arg0);
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

			try {
				if (arrCarouselItemInfos != null) {
					int position = v.getId();
					CarouselItemInfo pictureInfo = arrCarouselItemInfos.get(position);
					if (onItemListener != null) {
						onItemListener.onItemClick(pictureInfo);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_CHANGE_IMAGE:
				if (arrCarouselItemInfos != null && arrCarouselItemInfos.size() > 0) {
					iCurrentPage = (iCurrentPage + 1) % arrCarouselItemInfos.size();
					viewPager.setCurrentItem(iCurrentPage);
					mHandler.removeMessages(MSG_CHANGE_IMAGE);
					mHandler.sendEmptyMessageDelayed(MSG_CHANGE_IMAGE,
							TIME_SPAN);
				}
				break;

			default:
				break;
			}
		};
	};

	public void clearViewPager() {
		mHandler.removeMessages(MSG_CHANGE_IMAGE);
		if (arrViews != null) {
			arrViews.clear();
		}
		if (llDots != null) {
			llDots.removeAllViews();
		}
		// pageItemAdapter = new PageItemAdapter(arrViews);
		// viewPager.setAdapter(null);
		viewPager.removeAllViews();
		viewPager.setOnPageChangeListener(null);

	}
	

	public interface OnItemListener {
		public void onItemClick(CarouselItemInfo pictureInfo);

		public void onItemChanged(int position);
	}

}
