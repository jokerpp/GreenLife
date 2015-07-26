package cn.crane.application.greenlife.model.item;

import java.util.List;

import cn.crane.application.greenlife.model.BaseModel;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 27, 2015 10:02:31 PM
 * 
 */
public class CommentItem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5215583817178118379L;
	
	private String commentToken;
	private String content;
	private String insertDateStr;
	private float score;
	private String userToken;
	private String userName;
	private String orderToken;
	private String merchantName;
	private String merchantToken;
	private String answer;
	private String answerDateStr;
	private List<RecommandDishesDataList> recommandList;
	
	public CommentItem() {
		// TODO Auto-generated constructor stub
	}

	public String getCommentToken() {
		return commentToken;
	}

	public void setCommentToken(String commentToken) {
		this.commentToken = commentToken;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInsertDateStr() {
		return insertDateStr;
	}

	public void setInsertDateStr(String insertDateStr) {
		this.insertDateStr = insertDateStr;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderToken() {
		return orderToken;
	}

	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantToken() {
		return merchantToken;
	}

	public void setMerchantToken(String merchantToken) {
		this.merchantToken = merchantToken;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerDateStr() {
		return answerDateStr;
	}

	public void setAnswerDateStr(String answerDateStr) {
		this.answerDateStr = answerDateStr;
	}

	public List<RecommandDishesDataList> getRecommandList() {
		return recommandList;
	}

	public void setRecommandList(List<RecommandDishesDataList> recommandList) {
		this.recommandList = recommandList;
	}
	
	

}
