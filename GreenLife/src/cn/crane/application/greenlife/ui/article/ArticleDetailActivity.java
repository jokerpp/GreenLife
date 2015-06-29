package cn.crane.application.greenlife.ui.article;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.view.smartimage.SmartImageView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 29, 2015 9:02:02 PM
 * 
 */
public class ArticleDetailActivity extends BaseActivity {

	private Button btnBack;
	private TextView tvTitle;
	
	private SmartImageView iv;
	private TextView tvArticleTitle;
	private TextView tvArticleDetail;
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.ac_article_detail;
	}

	@Override
	protected void findViews() {
		
		btnBack = (Button) findViewById(R.id.btn_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
	}

	@Override
	protected void bindViews() {
		// TODO Auto-generated method stub
		btnBack.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

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
	
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, ArticleDetailActivity.class));
	}

}
