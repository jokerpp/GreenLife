package cn.crane.application.greenlife.model;

import java.io.Serializable;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 21, 2015 10:16:32 PM
 * 
 */
public class BaseModel implements Serializable{
	public String TAG;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseModel() {
		TAG = getClass().getSimpleName();
	}
}
