package cn.crane.application.greenlife.adapter.index;

import cn.crane.framework.view.carouselview.CarouselItemInfo;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 11:30:11 PM
 * 
 */
public class PictureInfo implements CarouselItemInfo{
	
	private String imageUrl;
	private int defaultImageRes;
	public PictureInfo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getDefaultImageRes() {
		return defaultImageRes;
	}



	public void setDefaultImageRes(int defaultImageRes) {
		this.defaultImageRes = defaultImageRes;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	@Override
	public String getImageUrl() {
		// TODO Auto-generated method stub
		return imageUrl;
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

}
