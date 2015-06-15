package cn.crane.framework.view;

import android.graphics.Canvas;
import android.widget.GridView;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-29
 */
public class MyGridView extends GridView {
	public MyGridView(android.content.Context context,
			android.util.AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不滚动
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
	}
}