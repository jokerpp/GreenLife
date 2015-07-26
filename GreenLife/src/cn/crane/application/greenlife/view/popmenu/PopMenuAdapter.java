package cn.crane.application.greenlife.view.popmenu;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.R;

import android.content.ClipData.Item;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

/**
 * 弹出菜单
 * 
 * @author Administrator
 * 
 */
public class PopMenuAdapter extends BaseAdapter {
	private Context context;
	private List<MenuItem> arrMenuItems = new ArrayList<MenuItem>();
	private LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	public PopMenuAdapter(Context context, List<MenuItem> arrMenuItems) {
		super();
		this.context = context;
		this.arrMenuItems = arrMenuItems;
	}

	@Override
	public int getCount() {
		return arrMenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		return arrMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
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
		TextView tvTitle = (TextView) layout.findViewById(R.id.tv_title);

		MenuItem menuItem = arrMenuItems.get(position);
		if (menuItem != null) {
			tvTitle.setText(menuItem.getTitle());
			tvTitle.setCompoundDrawablesWithIntrinsicBounds(menuItem.getIconRes(), 0, 0, 0);
			// tvNew.setVisibility(menuItem.isBnew() ? View.VISIBLE :
			// View.GONE);
		}
		return layout;
	}
}
