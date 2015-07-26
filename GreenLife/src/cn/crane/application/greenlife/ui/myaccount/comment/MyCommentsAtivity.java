package cn.crane.application.greenlife.ui.myaccount.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.my.ListCommentAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.data.DataManager;
import cn.crane.application.greenlife.model.item.CommentItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantCommentsList;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 3, 2015 10:04:11 PM
 * 
 */
public class MyCommentsAtivity extends BaseActivity {

	private Button btnBack;
	private TextView tvTitle;

	private ListView lv;
	private ListCommentAdapter<CommentItem> adapter;
	private List<CommentItem> arrCommentItems = new ArrayList<CommentItem>();
	private Task_Post task_Post_getMyCommentsList;
	


	@Override
	protected int getLayoutId() {
		return R.layout.ac_my_comments;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		lv = (ListView) findViewById(R.id.lv);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);

		adapter = new ListCommentAdapter<CommentItem>(this, arrCommentItems);
		lv.setAdapter(adapter);

	}

	@Override
	protected void init() {
		getMyCommentsList();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
////		case R.id.tv_city:
//			break;

		default:
			break;
		}
	}
	
	private void getMyCommentsList() {
//		用户ID加密	userToken	必填	String	
//		时间戳	timeStamp		Long	
//		请求页码	pageIndex		Int	
//		每页记录	pageSize		Int	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userToken", DataManager.userToken);
		map.put("timeStamp", API_Contant.getTimeStamp());
		map.put("pageIndex", page +"");
		map.put("pageSize", pageCount +"");
		
		Task_Post.clearTask(task_Post_getMyCommentsList);
		task_Post_getMyCommentsList = new Task_Post(map,
				API.API_getMyCommentsList, new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMerchantCommentsList result = new RE_getMerchantCommentsList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMerchantCommentsList.class);
					if (result.isSuccess()) {
						refreshUI(result);
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					// App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getMyCommentsList.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	
	
	

	protected void refreshUI(RE_getMerchantCommentsList result) {
		if(result != null)
		{
			if(result.getResultList() != null)
			{
				this.arrCommentItems.clear();
				this.arrCommentItems.addAll(result.getResultList());
				adapter.notifyDataSetChanged();
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_getMyCommentsList);
	}


	public static void show(Context context) {
		context.startActivity(createIntent(context, MyCommentsAtivity.class));
	}


}
