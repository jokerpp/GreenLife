package cn.crane.application.greenlife.model.item;

import cn.crane.framework.view.carouselview.CarouselItemInfo;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 11:30:11 PM
 * 
 */
public class NewsItem implements CarouselItemInfo{
	
	private int defaultImageRes;
	
//	"newsPicture": "",
//	"newsTitle": "最新资讯20141008",
//	"newsToken": "6"
	
//	新闻加密ID	newsToken		String	
//	新闻标题	newsTitle		String	
//	新闻图片	newsPicture		String	
//	内容	newsContent		String	
//	新闻时间	pubDateTimeStr		String	
	
	private String newsPicture;
	private String newsTitle;
	private String newsToken;
	private String newsContent;
	private String pubDateTimeStr;
	
	public NewsItem() {
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



	public int getDefaultImageRes() {
		return defaultImageRes;
	}



	public void setDefaultImageRes(int defaultImageRes) {
		this.defaultImageRes = defaultImageRes;
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



	@Override
	public int getImageResourse() {
		// TODO Auto-generated method stub
		return defaultImageRes;
	}

	@Override
	public int getDefaultResourse() {
		// TODO Auto-generated method stub
		return defaultImageRes;
	}



	@Override
	public String getImageUrl() {
		return newsPicture;
	}




}
