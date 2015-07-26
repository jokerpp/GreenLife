package cn.crane.application.greenlife.view;

import cn.crane.application.greenlife.R;
import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 25, 2015 9:21:18 PM
 * 
 */
public class CheckableTextView extends TextView {

	public static final int ICON_DOWM = R.drawable.icon_arraw_down;
	public static final int ICON_UP = R.drawable.icon_arraw_up;
	
	private boolean isChecked;
	
	private int icon_down = ICON_DOWM;
	private int icon_up = ICON_UP;

	public CheckableTextView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public CheckableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CheckableTextView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		setEllipsize(TruncateAt.END);
		setSingleLine(true);
		setChecked(false);
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
		setCompoundDrawablesWithIntrinsicBounds(0, 0, isChecked ? icon_up
				: icon_down, 0);

	}

	public boolean SwitchCheck() {
		setChecked(!isChecked);
		return isChecked;
	}
	
	public void setIcon_down(int icon_down) {
		this.icon_down = icon_down;
	}
	
	public void setIcon_up(int icon_up) {
		this.icon_up = icon_up;
	}
	
	public void setIcons(int icon_default,int icon_select) {
		this.icon_down = icon_default;
		this.icon_up = icon_select;
	}
	
	public void seticonGray() {
		setIcon_down(R.drawable.icon_arrow_down_gray);
		setIcon_up(R.drawable.icon_arrow_up_gray);
		setCompoundDrawablesWithIntrinsicBounds(0, 0, isChecked ? icon_up
				: icon_down, 0);

	}
}
