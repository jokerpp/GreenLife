package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Aug 3, 2015 11:36:42 PM
 * 
 */
public class NewsGroupItem extends BaseModel{
	public static final String TAG = "NewsGroupItem";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3139076048222310334L;
	
//	"newsGroupName": "aaaa1",
//	"newsGroupToken": "6"

	private String newsGroupName;
	private String newsGroupToken;
	
	public NewsGroupItem() {
		// TODO Auto-generated constructor stub
	}

	public String getNewsGroupName() {
		return newsGroupName;
	}

	public void setNewsGroupName(String newsGroupName) {
		this.newsGroupName = newsGroupName;
	}

	public String getNewsGroupToken() {
		return newsGroupToken;
	}

	public void setNewsGroupToken(String newsGroupToken) {
		this.newsGroupToken = newsGroupToken;
	}
	
	
}
