package cn.crane.application.greenlife.view.popmenu;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * 弹出菜单
 */
public class PopMenu extends PopupWindow implements OnItemClickListener {
	private Context context;
	private ListView lvMenus;
	private PopMenuAdapter adapter;
	private List<MenuItem> arrMenuItems = new ArrayList<MenuItem>();
	private OnMenuClickListener onMenuClickListener;
	
	public static final int  width1 = (int) (135 * App.fDensity);
	public static final int  width2 = (int) (170 * App.fDensity);

	public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
		this.onMenuClickListener = onMenuClickListener;
	}

	public PopMenu(Context context, List<MenuItem> arrMenuItems,
			OnMenuClickListener onMenuClickListener,int width) {
		super(context);
		this.context = context;
		this.arrMenuItems = arrMenuItems;
		this.onMenuClickListener = onMenuClickListener;
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.pop_menu, null);
		this.setContentView(layout);
//		this.setWidth((int) (165 * App.fDensity));
		this.setWidth(width);
		this.setHeight(LayoutParams.WRAP_CONTENT);

		this.setFocusable(true);
		this.setOutsideTouchable(true);
		lvMenus = (ListView) layout.findViewById(R.id.lv);
		adapter = new PopMenuAdapter(context, arrMenuItems);
		lvMenus.setAdapter(adapter);
		lvMenus.setOnItemClickListener(this);

		this.setBackgroundDrawable(context.getResources().getDrawable(
				R.color.transparent));
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MenuItem menuItem = (MenuItem) arg0.getItemAtPosition(arg2);
		if (onMenuClickListener != null) {
			onMenuClickListener.onMenuClick(null, arg2,menuItem);
		}
	}

	
	public void refreshUI() {
		if(adapter != null)
		{
			adapter.notifyDataSetChanged();
		}
	}
	
}
