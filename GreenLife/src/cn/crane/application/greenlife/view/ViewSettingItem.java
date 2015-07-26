package cn.crane.application.greenlife.view;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.view.smartimage.SmartImageView;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Setting->List Item
 * 
 * @author yuruifeng
 * 
 */
public class ViewSettingItem extends LinearLayout {
	private SmartImageView tvIcon;
	private TextView tvTitle;
	private TextView tvRight;
	private TextView tvArrow;
	private View line;
	private Context context;

	public ViewSettingItem(Context context) {
		super(context);
		initViews(context);
	}

	public ViewSettingItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
		if (attrs != null) {
			TypedArray params = context.obtainStyledAttributes(attrs,
					R.styleable.viewItem);
			// Get title
			int titleRes = params.getResourceId(
					R.styleable.viewItem_main_title, 0);
			if (titleRes != 0) {
				setTitle(titleRes);
			}
			// Get Detail
			int rightres = params.getResourceId(R.styleable.viewItem_right_str,
					0);
			if (rightres != 0)
				setRightStr(rightres);
			// Get Icon
			int rightIconRes = params.getResourceId(
					R.styleable.viewItem_right_icon, 0);
			if (rightIconRes != 0)
				setIcon(rightIconRes);
			else {
				setIconVisible(false);
			}
			boolean visible = params.getBoolean(
					R.styleable.viewItem_bottom_line_visible, true);
			boolean visibleArrow = params.getBoolean(
					R.styleable.viewItem_arrow_visible, true);
			setLineVisible(visible);
			setArrowVisible(visibleArrow);
		}

	}

	private void initViews(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.view_item_setting, this);
		tvIcon = (SmartImageView) findViewById(R.id.tv_icon);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvRight = (TextView) findViewById(R.id.tv_right);
		tvArrow = (TextView) findViewById(R.id.tv_icon_enter);
		line = (View) findViewById(R.id.line);
		tvTitle.setText("");
		tvRight.setText("");
	}

	/**
	 * Set title
	 * 
	 * @param str
	 */
	public void setTitle(String str) {
		if (tvTitle != null)
			tvTitle.setText(str);
	}

	public void setTitle(int resId) {
		setTitle(context.getString(resId));
	}

	/**
	 * Set Detail
	 * 
	 * @param str
	 */
	public void setRightStr(String str) {
		if (tvRight != null)
			tvRight.setText(str);
	}

	public void setRightStr(int resId) {
		setRightStr(context.getString(resId));
	}

	public String getRightStr() {
		if (tvRight != null) {
			return tvRight.getText().toString().trim();
		}
		return "";
	}

	public TextView getTvTitle() {
		return tvTitle;
	}

	public TextView getTvRight() {
		return tvRight;
	}

	/**
	 * Set icon in the left
	 * 
	 * @param resid
	 */
	public void setIcon(int resid) {
		if (tvIcon != null)
			tvIcon.setBackgroundResource(resid);
	}

	/**
	 * Set icon in the left
	 * 
	 * @param resid
	 */
	public void setIconUrl(String sUrl) {
		// if (tvIcon != null)
		// tvIcon.setImageUrl(sUrl, R.drawable.head_default);
	}

	public void setIconVisible(boolean b) {
		if (tvIcon != null)
			tvIcon.setVisibility(b ? View.VISIBLE : View.GONE);
	}

	public void setLineVisible(boolean b) {
		if (line != null)
			line.setVisibility(b ? View.VISIBLE : View.GONE);
	}

	/**
	 * 右箭头是否显示
	 * 
	 * @param visible
	 */
	public void setArrowVisible(boolean visible) {
		if (tvArrow != null) {

			tvArrow.setVisibility(visible ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 
	 */
	public void toBankStyle() {
		line.setVisibility(View.GONE);
		tvRight.setText("");
		setPadding(0, (int) (3 * App.fDensity), (int) (10 * App.fDensity),
				(int) (3 * App.fDensity));
	}

	public void setRightTxtColor(int color) {
		if (tvRight != null) {
			tvRight.setTextColor(context.getResources().getColor(color));
		}
	}

}
