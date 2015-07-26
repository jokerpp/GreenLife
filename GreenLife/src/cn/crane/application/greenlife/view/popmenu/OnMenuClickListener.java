package cn.crane.application.greenlife.view.popmenu;

import cn.crane.application.greenlife.view.filter.ViewFilter;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 21, 2015 12:13:52 AM
 * 
 */
public interface OnMenuClickListener {
	public void onMenuClick(ViewFilter.Type type,int position,MenuItem item);
}