package cn.crane.application.greenlife.model.result;

import java.util.List;

import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.item.NewsItem;


/**
 * 商家列表
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 23, 2015 10:36:14 PM
 * 
 */
public class RE_getTopNews extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791990879464478795L;

	private List<NewsItem> resultList;

	public List<NewsItem> getResultList() {
		return resultList;
	}

	public void setResultList(List<NewsItem> resultList) {
		this.resultList = resultList;
	}
}