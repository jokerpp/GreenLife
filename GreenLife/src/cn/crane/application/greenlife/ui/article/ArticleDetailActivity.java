package cn.crane.application.greenlife.ui.article;

import java.net.URLEncoder;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.model.result.RE_getNewsDetails;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.view.smartimage.SmartImageView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 29, 2015 9:02:02 PM
 * 
 */
public class ArticleDetailActivity extends BaseActivity {

	public static final String NEWS_TOKEN = "newsToken";
	private Button btnBack;
	private TextView tvTitle;
	
	private SmartImageView iv;
	private TextView tvArticleTitle;
	private TextView tvArticleDetail;
	private WebView webView;
	
	private Task_Post task_Post_getNewsDetails;
	private String newsToken;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_article_detail;
	}

	@Override
	protected void findViews() {
		
		btnBack = (Button) findViewById(R.id.btn_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		iv = (SmartImageView) findViewById(R.id.iv_image);
		tvArticleTitle = (TextView) findViewById(R.id.tv_name);
		tvArticleDetail = (TextView) findViewById(R.id.tv_detail);
		webView = (WebView) findViewById(R.id.web);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.setWebViewClient(client);
	}

	@Override
	protected void init() {
		iv.setVisibility(View.GONE);
		newsToken = getIntent().getStringExtra(NEWS_TOKEN);
		getNewsDetails();
		
		webView.setVisibility(View.VISIBLE);
	}
	
	WebViewClient client = new WebViewClient() {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			displayLoadingDlg("");
			super.onPageStarted(view, url, favicon);

		}

		public void onPageFinished(WebView webview, String url) {
			dismissLoadingDlg();
		}
	};
	
	private void getNewsDetails() {
//		新闻加密ID	newsToken	必填	String
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("newsToken", newsToken);
//		map.put("longitude", DataManager.longitude);
//		map.put("latitude", DataManager.latitude);
		Task_Post.clearTask(task_Post_getNewsDetails);
		task_Post_getNewsDetails = new Task_Post(map, API.API_getNewsDetails,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getNewsDetails result = new RE_getNewsDetails();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getNewsDetails.class);
					if (result.isSuccess()) {
						refreshUI(result);
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getNewsDetails.execute();
		displayLoadingDlg(R.string.loading);
		
	}
	
	protected void refreshUI(RE_getNewsDetails result) {
		if(result != null)
		{
			if(!TextUtils.isEmpty(result.getNewsPicture()))
			{
				iv.setImageUrl(result.getNewsPicture());
				iv.setVisibility(View.VISIBLE);
			}else
			{
				iv.setVisibility(View.GONE);
			}
			if (!TextUtils.isEmpty(result.getNewsTitle())) {
				tvTitle.setText(result.getNewsTitle());
				tvArticleTitle.setText(result.getNewsTitle());
			}
			tvArticleDetail.setText(Html.fromHtml(result.getNewsContent()));
			
			StringBuilder builder = new StringBuilder();
			builder.append("<html>");
			builder.append("<body>");
			
			builder.append(result.getNewsContent());
			
			builder.append("</body>");
			builder.append("</html>");
			
			
			webView.loadDataWithBaseURL(null, builder.toString(), "text/html","UTF-8", null);
		}
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Task_Post.clearTask(task_Post_getNewsDetails);
	}
	
	
	public static void show(Context context,String nesToken) {
		Intent intent = createIntent(context, ArticleDetailActivity.class);
		intent.putExtra(NEWS_TOKEN, nesToken);
		context.startActivity(intent);
	}

}
