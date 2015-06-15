package cn.crane.application.greenlife.view;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 4, 2015 10:40:13 PM
 * 
 */
public class ViewAddMinus extends LinearLayout {

	private TextView tvMinus;
	private TextView tvNumber;
	private TextView tvAdd;

	private int iNumber = 0;
	
	private FoodItem item = new FoodItem();
	private OnNumberChangedListener onNumberChangedListener;

	public ViewAddMinus(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public ViewAddMinus(Context context) {
		super(context);
		initViews(context);
	}

	private void initViews(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_add_minus, this);
		tvMinus = (TextView) findViewById(R.id.tv_minus);
		tvNumber = (TextView) findViewById(R.id.tv_number);
		tvAdd = (TextView) findViewById(R.id.tv_add);

		// iNumber = 0;
		// tvNumber.setText(iNumber + "");
		// tvMinus.setVisibility(View.GONE);
		// tvNumber.setVisibility(View.GONE);
		// tvAdd.setVisibility(View.VISIBLE);
		setiNumber(0);

		tvMinus.setOnClickListener(onClickListener);
		tvAdd.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_minus:
				// if(iNumber > 1)
				// {
				// iNumber--;
				// tvNumber.setText(iNumber + "");
				//
				// }else
				// {
				// iNumber = 0;
				// tvMinus.setVisibility(View.GONE);
				// tvNumber.setVisibility(View.GONE);
				// tvAdd.setVisibility(View.VISIBLE);
				// }
				setiNumber(--iNumber);
				break;
			case R.id.tv_add:
				// iNumber ++;
				// tvNumber.setText(iNumber + "");
				// tvMinus.setVisibility(View.VISIBLE);
				// tvNumber.setVisibility(View.VISIBLE);
				// tvAdd.setVisibility(View.VISIBLE);
				setiNumber(++iNumber);
				break;

			default:
				break;
			}
		}
	};

	public int getiNumber() {
		return iNumber;
	}

	public void setiNumber(int iNumber) {
//		if (iNumber != 0 && this.iNumber == iNumber)
//			return;
		this.iNumber = iNumber;
		if (iNumber <= 0) {
			this.iNumber = 0;
			tvNumber.setText(iNumber + "");
			tvMinus.setVisibility(View.GONE);
			tvNumber.setVisibility(View.GONE);
			tvAdd.setVisibility(View.VISIBLE);
		} else {
			tvNumber.setText(iNumber + "");
			tvMinus.setVisibility(View.VISIBLE);
			tvNumber.setVisibility(View.VISIBLE);
			tvAdd.setVisibility(View.VISIBLE);
		}
		
		if(item != null)
		{
			item.setiCountChoose(iNumber);
		}
		if(onNumberChangedListener != null)
		{
			onNumberChangedListener.onNumberChanged(this, iNumber);
		}
	}
	
	public void setItem(FoodItem item) {
		this.item = item;
	}
	
	public FoodItem getItem() {
		return item;
	}
	
	public void setOnNumberChangedListener(
			OnNumberChangedListener onNumberChangedListener) {
		this.onNumberChangedListener = onNumberChangedListener;
	}

	public interface OnNumberChangedListener {
		void onNumberChanged(ViewAddMinus view, int iNumber);
	}
}
