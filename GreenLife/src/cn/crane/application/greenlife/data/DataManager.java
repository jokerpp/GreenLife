package cn.crane.application.greenlife.data;

import java.util.HashMap;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.model.Result;
import cn.crane.application.greenlife.model.result.RE_Login;
import cn.crane.application.greenlife.model.result.RE_getInfoForUser;
import cn.crane.application.greenlife.ui.myaccount.LoginActivity;

import com.alibaba.fastjson.JSONArray;


/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 22, 2015 11:13:52 AM
 * 
 */
public class DataManager {

	public static String longitude = "";
	public static String latitude = "";

//	private List<CityInfo> arrCityInfos = new ArrayList<CityInfo>();

	public static String userToken = "";

	private Task_Post task_Post_getActiveCitiesList;
	private Task_Post task_Post_getInfoForUser;
	private Task_Post task_Post_getCaptcha;
	private Task_Post task_Post_addItemToMyCollection;
	private Task_Post task_Post_deleteItemFromMyCollection;
	private Task_Post task_Post_submitSuggestion;

	private static DataManager instance;

	public static DataManager getInstance() {
		if (instance == null)
			instance = new DataManager();
		return instance;
	}


//	public void getInfoForUser(final Callback callback) {
//		// 用户ID加密 userToken 必填 String
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("userToken", userToken);
//		Task_Post.clearTask(task_Post_getInfoForUser);
//		task_Post_getInfoForUser = new Task_Post(map, API.API_getInfoForUser,
//				new Task_Post.OnPostEndListener() {
//
//					@Override
//					public void onPostEnd(String sResult) {
//						RE_getInfoForUser result = new RE_getInfoForUser();
//						try {
//							result = JSONArray.parseObject(sResult,
//									RE_getInfoForUser.class);
//							if (callback != null) {
//								callback.onPost(result);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//							if (callback != null) {
//								callback.onError();
//							}
//						}
//					}
//				});
//		task_Post_getInfoForUser.execute();
//		if (callback != null) {
//			callback.onPre();
//		}
//	}
	
	/**
	 * Get Captcha
	 */
//	public void getCaptcha(EditText et_mobile,final Callback callback) {
////		手机号	moblie	必填	string
//		String tel = et_mobile.getText().toString().trim();
//		if(TextUtils.isEmpty(tel))
//		{
//			App.showToast(et_mobile.getHint().toString());
//			return;
//		}
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("mobile", tel);
//		Task_Post.clearTask(task_Post_getCaptcha);
//		task_Post_getCaptcha = new Task_Post(map, API.API_sendValidateCode,
//				new Task_Post.OnPostEndListener() {
//
//					@Override
//					public void onPostEnd(String sResult) {
//						RE_Login result = new RE_Login();
//						try {
//							result = JSONArray.parseObject(sResult,
//									RE_Login.class);
//							if (result.isSuccess()) {
//								App.showToast(R.string.getcaptcha_success);
//							} else {
//								App.showToast(result.getResultMessage());
//							}
//							if(callback != null)
//							{
//								callback.onPost(result);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//							// App.showToast(R.string.api_error_code_6);
//							if(callback != null)
//							{
//								callback.onError();
//							}
//						}
//					}
//				});
//		task_Post_getCaptcha.execute();
//		if(callback != null)
//		{
//			callback.onPre();
//		}
//	}
	
	/**
	 * Add Collect
	 * @param id
	 * @param type
	 * @param callback
	 */
	public void addItemToMyCollection(Context context,String id,String type,final Callback callback) {
//		用户加密ID 	userToken	必填	String	
//		添加项目ID	itemToken	必填	String	
//		收藏类型	collectionType	必填	int	1、商家；2、菜品
		if(!checkLogin(context))
		{
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", userToken);
		map.put("itemToken", id);
		map.put("collectionType", type);
		Task_Post.clearTask(task_Post_addItemToMyCollection);
		task_Post_addItemToMyCollection = new Task_Post(map, API.API_addItemToMyCollection,
				new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						Result result = new Result();
						try {
							result = JSONArray.parseObject(sResult,
									Result.class);
							if (callback != null) {
								callback.onPost(result);
							}
						} catch (Exception e) {
							e.printStackTrace();
							if (callback != null) {
								callback.onError();
							}
						}
					}
				});
		task_Post_addItemToMyCollection.execute();
		if (callback != null) {
			callback.onPre();
		}
	}
	/**
	 * Delete Collect
	 * @param id
	 * @param type
	 * @param callback
	 */
	public void deleteItemFromMyCollection(Context context,String id,String type,final Callback callback) {
//		用户加密ID 	userToken	必填	String	
//		添加项目ID	itemToken	必填	String	
//		收藏类型	collectionType	必填	int	1、商家；2、菜品
		if(!checkLogin(context))
		{
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", userToken);
		map.put("itemToken", id);
		map.put("collectionType", type);
		Task_Post.clearTask(task_Post_deleteItemFromMyCollection);
		task_Post_deleteItemFromMyCollection = new Task_Post(map, API.API_deleteItemFromMyCollection,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if (callback != null) {
						callback.onPost(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (callback != null) {
						callback.onError();
					}
				}
			}
		});
		task_Post_deleteItemFromMyCollection.execute();
		if (callback != null) {
			callback.onPre();
		}
	}
	/**
	 * Feedback
	 * @param id
	 * @param type
	 * @param callback
	 */
	public void submitSuggestion(String id,String type,final Callback callback) {
//		用户ID加密	userToken		String	
//		软件功能分	softScore		Float	
//		客服服务分	serviceScore		Float	
//		描述内容	content	必填	String	
//		联系人	contact		String	
//		联系方式	mobile		String	
//		客户端类型	clientType		Int	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", userToken);
		map.put("itemToken", id);
		map.put("collectionType", type);
		Task_Post.clearTask(task_Post_submitSuggestion);
		task_Post_submitSuggestion = new Task_Post(map, API.API_submitSuggestion,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				Result result = new Result();
				try {
					result = JSONArray.parseObject(sResult,
							Result.class);
					if (callback != null) {
						callback.onPost(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (callback != null) {
						callback.onError();
					}
				}
			}
		});
		task_Post_submitSuggestion.execute();
		if (callback != null) {
			callback.onPre();
		}
	}
	
	
	/**
	 * Get Captcha
	 */
	public void getCaptcha(EditText et_mobile,final Callback callback) {
//		手机号	moblie	必填	string
		String tel = et_mobile.getText().toString().trim();
		if(TextUtils.isEmpty(tel))
		{
			App.showToast(et_mobile.getHint().toString());
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mobile", tel);
		Task_Post.clearTask(task_Post_getCaptcha);
		task_Post_getCaptcha = new Task_Post(map, API.API_sendValidateCode,
				new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						RE_Login result = new RE_Login();
						try {
							result = JSONArray.parseObject(sResult,
									RE_Login.class);
							if (result.isSuccess()) {
								App.showToast(R.string.getcaptcha_success);
							} else {
								App.showToast(result.getResultMessage());
							}
							if(callback != null)
							{
								callback.onPost(result);
							}
						} catch (Exception e) {
							e.printStackTrace();
							// App.showToast(R.string.api_error_code_6);
							if(callback != null)
							{
								callback.onError();
							}
						}
					}
				});
		task_Post_getCaptcha.execute();
		if(callback != null)
		{
			callback.onPre();
		}
	}
	
	public void getInfoForUser(final Callback callback) {
		// 用户ID加密 userToken 必填 String
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", userToken);
		Task_Post.clearTask(task_Post_getInfoForUser);
		task_Post_getInfoForUser = new Task_Post(map, API.API_getInfoForUser,
				new Task_Post.OnPostEndListener() {

					@Override
					public void onPostEnd(String sResult) {
						RE_getInfoForUser result = new RE_getInfoForUser();
						try {
							result = JSONArray.parseObject(sResult,
									RE_getInfoForUser.class);
							if (callback != null) {
								callback.onPost(result);
							}
						} catch (Exception e) {
							e.printStackTrace();
							if (callback != null) {
								callback.onError();
							}
						}
					}
				});
		task_Post_getInfoForUser.execute();
		if (callback != null) {
			callback.onPre();
		}
	}
	

	public interface Callback {
		public void onPre();

		public void onPost(Result result);

		public void onError();
	}

	public static void setTokenUser(String userToken) {
		DataManager.userToken = userToken;
	}
	
	public static boolean checkLogin(Context context) {
		if(!isLogin())
		{
			LoginActivity.show(context);
			return false;
		}
		return true;
	}

	public static boolean isLogin() {
		return !TextUtils.isEmpty(userToken);
	}

}
