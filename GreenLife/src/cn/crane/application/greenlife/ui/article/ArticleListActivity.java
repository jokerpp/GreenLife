package cn.crane.application.greenlife.ui.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.article.ListArticleAdapter;
import cn.crane.application.greenlife.adapter.merchant.ListMerchantAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.model.item.NewsGroupItem;
import cn.crane.application.greenlife.model.item.NewsItem;
import cn.crane.application.greenlife.model.result.RE_getNewsList;
import cn.crane.application.greenlife.ui.merchant.MerchantListAtivity;
import cn.crane.framework.activity.BaseActivity;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 29, 2015 8:48:11 PM
 * 
 */
public class ArticleListActivity extends BaseActivity implements OnItemClickListener {

	private Button btnBack;
	private TextView tvTitle;

	private ListView lv;
	private ListArticleAdapter adapter;
	private List<NewsItem> arrNewsItems = new ArrayList<NewsItem>();
	
	private Task_Post task_Post_getTopNews;
	
	private String type;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_article_list;
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

		adapter = new ListArticleAdapter(this, arrNewsItems);
//		adapter.setOnItemClickListener(onItemClickListener);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	@Override
	protected void init() {
		type = getIntent().getStringExtra(API_Contant.TYPE);
		getNewsList();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.tv_title:
			break;

		default:
			break;
		}
	}
	
	private void getNewsList() {
//		新闻类型	newsType	必填	String
//		请求页码	pageIndex		Int
//		每页记录	pageSize		Int
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("newsType", type);
		map.put("pageIndex", "0");
		map.put("pageSize", "10");
		Task_Post.clearTask(task_Post_getTopNews);
		task_Post_getTopNews = new Task_Post(map, API.API_getNewsList,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				RE_getNewsList result = new RE_getNewsList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getNewsList.class);
					if(result.isSuccess())
					{
						refreshTopNewsUI(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		task_Post_getTopNews.execute();
	}
	
	protected void refreshTopNewsUI(RE_getNewsList result) {
		if(result != null)
		{
			arrNewsItems.clear();
			arrNewsItems.addAll(result.getResultList());
			adapter.notifyDataSetChanged();
		}
	}

	private ListMerchantAdapter.OnItemClickListener onItemClickListener = new ListMerchantAdapter.OnItemClickListener() {

		@Override
		public void onItemClick(Object object) {
		}
	};
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		if(object instanceof NewsItem)
		{
			ArticleDetailActivity.show(this,((NewsItem)object).getNewsToken());
		}
	
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_getTopNews);
	}

	
	public static void show(Context context ,String type) {
		Intent intent = createIntent(context, ArticleListActivity.class);
		intent.putExtra(API_Contant.TYPE, type);
		context.startActivity(intent);
	}
	public static void show(Context context ,NewsGroupItem groupItem) {
		Intent intent = createIntent(context, ArticleListActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(NewsGroupItem.TAG, groupItem);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
