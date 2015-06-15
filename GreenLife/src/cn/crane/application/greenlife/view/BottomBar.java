package cn.crane.application.greenlife.view;

import cn.crane.application.greenlife.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Mar 13, 2015 10:17:22 PM
 * 
 */
public class BottomBar extends RelativeLayout {
	public static final int TAG_1 = 0;
	public static final int TAG_2 = 1;
	public static final int TAG_3 = 2;
	public static final int TAG_4 = 3;
	
	private View rootView;
	private LinearLayout ll1;
	private LinearLayout ll2;
	private LinearLayout ll3;
	private LinearLayout ll4;

	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private Context context;

	private int[] resIds = new int[] { R.string.txt_index, R.string.txt_order,
			R.string.txt_my,R.string.txt_my};
	
	private int[] icon_defaut = new int[] { R.drawable.icon_bottom_index1, R.drawable.icon_bottom_dingdan1,
			R.drawable.icon_bottom_my1,R.drawable.icon_bottom_my1};
	private int[] icon_select = new int[] { R.drawable.icon_bottom_index, R.drawable.icon_bottom_dingdan,
			R.drawable.icon_bottom_my,R.drawable.icon_bottom_my };

	private TextView [] tvs;
	
	
	private int selectItem = TAG_1;

	private OnItemSelectedListener onItemSelectedListener;

	public BottomBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public BottomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public BottomBar(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.view_bottombar, this);
		rootView = findViewById(R.id.rootView);
		ll1 = (LinearLayout) findViewById(R.id.ll_item1);
		ll2 = (LinearLayout) findViewById(R.id.ll_item2);
		ll3 = (LinearLayout) findViewById(R.id.ll_item3);
		ll4 = (LinearLayout) findViewById(R.id.ll_item4);

		ll1.setTag(TAG_1);
		ll2.setTag(TAG_2);
		ll3.setTag(TAG_3);
		ll4.setTag(TAG_4);

		tv1 = (TextView) findViewById(R.id.tv_item1);
		tv2 = (TextView) findViewById(R.id.tv_item2);
		tv3 = (TextView) findViewById(R.id.tv_item3);
		tv4 = (TextView) findViewById(R.id.tv_item4);
		
		tvs = new TextView[] { tv1, tv2, tv3, tv4 };
		
		tv1.setText(resIds[0]);
		tv2.setText(resIds[1]);
		tv3.setText(resIds[2]);
		tv4.setText(resIds[3]);

		ll1.setOnClickListener(onClickListener);
		ll2.setOnClickListener(onClickListener);
		ll3.setOnClickListener(onClickListener);
		ll4.setOnClickListener(onClickListener);

		switchItem(TAG_1);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (onItemSelectedListener != null) {
				if (v.getTag() instanceof Integer) {
					onItemSelectedListener.onItemSelected((Integer) v.getTag());
				}
			}
		}
	};

	public void switchItem(int tag) {
		selectItem = tag;
		for (int i = 0; i < tvs.length; i++) {
			if(i == selectItem)
			{
				tvs[i].setTextColor(context.getResources().getColor(R.color.main_color));
				tvs[i].setCompoundDrawablesWithIntrinsicBounds(0, icon_select[i], 0, 0);
			}else
			{
				tvs[i].setTextColor(context.getResources().getColor(R.color.txt_black));
				tvs[i].setCompoundDrawablesWithIntrinsicBounds(0, icon_defaut[i], 0, 0);
			}
			tvs[i].setText(resIds[i]);
		}
	}
	
	public void setBgColor(int color) {
		if (rootView != null) {
			rootView.setBackgroundColor(getResources().getColor(color));
		}
	}

	public void setOnItemSelectedListener(
			OnItemSelectedListener onItemSelectedListener) {
		this.onItemSelectedListener = onItemSelectedListener;
	}

	@Override
	protected void onDetachedFromWindow() {
		context = null;
		super.onDetachedFromWindow();
	}

	public interface OnItemSelectedListener {
		void onItemSelected(int tag);
	}

}
