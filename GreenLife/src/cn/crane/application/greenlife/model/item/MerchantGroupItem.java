package cn.crane.application.greenlife.model.item;

import cn.crane.application.greenlife.model.BaseModel;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Aug 6, 2015 10:24:27 PM
 * 
 */
public class MerchantGroupItem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 775908728260874785L;
	// 分类加密ID merchantGroupToken
	// 名称 merchantGroupName
	// 图标 merchantGroupPhoto
	private String merchantGroupToken;
	private String merchantGroupName;
	private String merchantGroupPhoto;

	public MerchantGroupItem() {
		// TODO Auto-generated constructor stub
	}

	public String getMerchantGroupToken() {
		return merchantGroupToken;
	}

	public void setMerchantGroupToken(String merchantGroupToken) {
		this.merchantGroupToken = merchantGroupToken;
	}

	public String getMerchantGroupName() {
		return merchantGroupName;
	}

	public void setMerchantGroupName(String merchantGroupName) {
		this.merchantGroupName = merchantGroupName;
	}

	public String getMerchantGroupPhoto() {
		return merchantGroupPhoto;
	}

	public void setMerchantGroupPhoto(String merchantGroupPhoto) {
		this.merchantGroupPhoto = merchantGroupPhoto;
	}

}
