package cn.crane.application.greenlife.view.popmenu;

import cn.crane.application.greenlife.view.filter.MenuFilter;



public class MenuItem implements MenuFilter{
	public static final String TAG = "MenuItem";
	public static final int TAG_MENU_MERCHANT_LIANXI = 1000;
	public static final int TAG_MENU_MERCHANT_TOUSU = 1001;
	
	
	private int tag;
	private String title;
	private int iconRes;
	private int iconResSelect;
	private boolean bnew;
	
	private String id;

	public MenuItem() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isBnew() {
		return bnew;
	}

	public void setBnew(boolean bnew) {
		this.bnew = bnew;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getIconRes() {
		return iconRes;
	}

	public void setIconRes(int iconRes) {
		this.iconRes = iconRes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIconResSelect() {
		return iconResSelect;
	}

	public void setIconResSelect(int iconResSelect) {
		this.iconResSelect = iconResSelect;
	}

	@Override
	public String getStringTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public int getIconDefault() {
		// TODO Auto-generated method stub
		return iconRes;
	}

	@Override
	public int getIconSelect() {
		if(iconResSelect <= 0)
		{
			return getIconDefault();
		}
		return iconResSelect;
	}




}
