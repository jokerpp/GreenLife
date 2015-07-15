package cn.crane.application.greenlife.model.result;

import java.util.List;

import cn.crane.application.greenlife.model.Result;

/**
 * 商家列表
 * 
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 23, 2015 10:36:14 PM
 * 
 */
public class RE_getNewsDetails extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791990879464478795L;

	// 新闻加密ID newsToken String
	// 新闻标题 newsTitle String
	// 新闻图片 newsPicture String
	// 内容 newsContent String
	// 新闻时间 pubDateTimeStr String

	private String newsPicture;
	private String newsTitle;
	private String newsToken;
	private String newsContent;
	private String pubDateTimeStr;

	public RE_getNewsDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getNewsPicture() {
		return newsPicture;
	}

	public void setNewsPicture(String newsPicture) {
		this.newsPicture = newsPicture;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsToken() {
		return newsToken;
	}

	public void setNewsToken(String newsToken) {
		this.newsToken = newsToken;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getPubDateTimeStr() {
		return pubDateTimeStr;
	}

	public void setPubDateTimeStr(String pubDateTimeStr) {
		this.pubDateTimeStr = pubDateTimeStr;
	}

}
