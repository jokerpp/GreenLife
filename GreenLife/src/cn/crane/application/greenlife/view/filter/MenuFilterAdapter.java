package cn.crane.application.greenlife.view.filter;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.view.popmenu.MenuItem;
import cn.crane.framework.adapter.CommonAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date Jun 21, 2015
 */
public class MenuFilterAdapter extends CommonAdapter<MenuItem> {
	


	private int iSelect = -1;
	public void setiSelect(int iSelect) {
		this.iSelect = iSelect;
		notifyDataSetChanged();
	}


	public MenuFilterAdapter(Context context, List<MenuItem> list) {
		super(context, list);
	}
	public MenuFilterAdapter(Context context, List<MenuItem> list,int iSelect) {
		super(context, list);
		this.iSelect = iSelect;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View layout = null;
		if (convertView == null) {
			layout = LayoutInflater.from(context).inflate(
					R.layout.item_pop_menu, null);
		} else {
			layout = convertView;
		}
		LinearLayout rootView = (LinearLayout) layout.findViewById(R.id.rootView);
		TextView tvTitle = (TextView) layout.findViewById(R.id.tv_title);
		rootView.setBackgroundColor(context.getResources().getColor(R.color.white));
		MenuItem menuItem =(MenuItem) getItem(position);
		if (menuItem != null) {
			tvTitle.setText(menuItem.getStringTitle());
			if(iSelect == position)
			{
				tvTitle.setTextColor(context.getResources().getColor(R.color.main_color));
				if(menuItem.getIconSelect() > 0)
				tvTitle.setCompoundDrawablesWithIntrinsicBounds(menuItem.getIconSelect(), 0, 0, 0);
			}else
			{
				tvTitle.setTextColor(context.getResources().getColor(R.color.txt_black));
				if(menuItem.getIconDefault() > 0)
				tvTitle.setCompoundDrawablesWithIntrinsicBounds(menuItem.getIconDefault(), 0, 0, 0);
			}
		}
		return layout;
	}
}
