package cn.crane.application.greenlife.model.result;

import java.util.List;

import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.item.CommentItem;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 27, 2015 10:08:30 PM
 * 
 */
public class RE_getMerchantCommentsList extends Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = -424028847830786925L;

	private List<CommentItem> resultList;

	public List<CommentItem> getResultList() {
		return resultList;
	}

	public void setResultList(List<CommentItem> resultList) {
		this.resultList = resultList;
	}
}
