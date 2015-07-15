package cn.crane.application.greenlife.bean.index;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:55:28 PM
 * 
 */
public class GridCategoryItem {

	private int strResId;
	private int iconResId;
	
	private String txt;
	
	private String type;

	public GridCategoryItem() {
		// TODO Auto-generated constructor stub
	}

	public int getStrResId() {
		return strResId;
	}

	public void setStrResId(int strResId) {
		this.strResId = strResId;
	}

	public int getIconResId() {
		return iconResId;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
