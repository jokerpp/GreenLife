package cn.crane.application.greenlife.view.filter;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.view.popmenu.MenuItem;
import cn.crane.application.greenlife.view.popmenu.OnMenuClickListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 21, 2015 12:11:31 AM
 * 
 */
public class ViewFilter extends LinearLayout implements OnItemClickListener {

	private Context context;
	private ListView lvMenus;
	private View rootView;
	private MenuFilterAdapter adapter;
	private List<MenuItem> arrMenuItemsCategory = new ArrayList<MenuItem>();
	private List<MenuItem> arrMenuItemsSort = new ArrayList<MenuItem>();
	private List<MenuItem> arrMenuItemsWelfare = new ArrayList<MenuItem>();
	private OnMenuClickListener onMenuClickListener;

	private int iPosCategory;
	private int iPosSort;
	private int iPosWelfare;
	
	private Type type;

	public enum Type
	{
		TYPE_CATEGORY,TYPE_SORT,TYPE_WELFARE
	}
	public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
		this.onMenuClickListener = onMenuClickListener;
	}

	public ViewFilter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public ViewFilter(Context context) {
		this(context, null);
	}

	private void initViews(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.pop_menu_filter, this);
		arrMenuItemsCategory = createCategoryList(context);
		arrMenuItemsSort = createSortList(context);
		arrMenuItemsWelfare = createWelfareList(context);
		
		rootView = findViewById(R.id.rootView);
		lvMenus = (ListView) findViewById(R.id.lv);
		adapter = new MenuFilterAdapter(context, arrMenuItemsCategory,iPosCategory);
		
		lvMenus.setAdapter(adapter);
		lvMenus.setOnItemClickListener(this);
		
		setType(Type.TYPE_CATEGORY);
		setVisibility(View.GONE);
		
		
		rootView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setVisibility(View.GONE);
			}
		});
	}

	public void setType(Type type) {
		this.type = type;
		switch (type) {
		case TYPE_CATEGORY:
			adapter = new MenuFilterAdapter(context, arrMenuItemsCategory,iPosCategory);
			lvMenus.setAdapter(adapter);
			break;
		case TYPE_SORT:
			adapter = new MenuFilterAdapter(context, arrMenuItemsSort,iPosSort);
			lvMenus.setAdapter(adapter);
			break;
		case TYPE_WELFARE:
			adapter = new MenuFilterAdapter(context, arrMenuItemsWelfare,iPosWelfare);
			lvMenus.setAdapter(adapter);
			break;

		default:
			break;
		}
	}
	
	public boolean switchType(Type type) {
		if(type == this.type)
		{
			if(getVisibility() == View.VISIBLE)
			{
				setVisibility(View.GONE);
			}else
			{
				setVisibility(View.VISIBLE);
			}
		}else
		{
			setType(type);
			setVisibility(View.VISIBLE);
		}
		return getVisibility() ==View.VISIBLE;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (type) {
		case TYPE_CATEGORY:
			iPosCategory = position;
			break;
		case TYPE_SORT:
			iPosSort = position;
			break;
		case TYPE_WELFARE:
			iPosWelfare = position;
			break;

		default:
			break;
		}
		adapter.setiSelect(position);
		MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
		setVisibility(View.GONE);
		if (onMenuClickListener != null) {
			onMenuClickListener.onMenuClick(type,position,menuItem);
		}
	}

	public void setArrMenuItems(List<MenuItem> arrMenuItems) {
		if (arrMenuItems != null) {
			this.arrMenuItemsCategory.clear();
			this.arrMenuItemsCategory.addAll(arrMenuItems);
			refreshUI();
		}
	}

	public void refreshUI() {
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	public List<MenuItem> createCategoryList(Context context) {
		return createMenuList(context, R.array.arr_merchant_category,
				R.array.arr_merchant_category_icon_default,
				R.array.arr_merchant_category_icon_select);
	}
	public List<MenuItem> createSortList(Context context) {
		return createMenuList(context, R.array.arr_merchant_sort,
				0,
				0);
	}
	public List<MenuItem> createWelfareList(Context context) {
		return createMenuList(context, R.array.arr_merchant_welfare,
				R.array.arr_merchant_welfare_icon_default,
				R.array.arr_merchant_welfare_icon_default);
	}

	public List<MenuItem> createMenuList(Context context, int arr_title,
			int arr_icon_default, int arr_icon_select) {
		List<MenuItem> arrMenuItems = new ArrayList<MenuItem>();
		String[] arrTitle = context.getResources().getStringArray(arr_title);
		int[] arrIconDefault = null;
		int[] arrIconSelect = null ;
		if(arr_icon_default>0)
		{
			arrIconDefault = context.getResources().getIntArray(
					arr_icon_default);
		}
		if(arr_icon_select>0)
		{
			arrIconSelect = context.getResources().getIntArray(
					arr_icon_select);
		}
		if(arrTitle == null || arrTitle.length == 0)
			return arrMenuItems;
		for (int i = 0; i < arrTitle.length; i++) {
			MenuItem item = new MenuItem();
			item.setTitle(arrTitle[i]);
			if(arrIconDefault != null && i < arrIconDefault.length)
			{
				item.setIconRes(arrIconDefault[i]);
			}
			if(arrIconSelect != null && i < arrIconSelect.length)
			{
				item.setIconResSelect(arrIconSelect[i]);
			}
			arrMenuItems.add(item);
		}
		return arrMenuItems;
	}
	
	
}
