package cn.crane.application.greenlife.ui.article;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.article.ListArticleAdapter;
import cn.crane.application.greenlife.adapter.merchant.ListMerchantAdapter;
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

		adapter = new ListArticleAdapter(this, null);
//		adapter.setOnItemClickListener(onItemClickListener);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
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
	
	private ListMerchantAdapter.OnItemClickListener onItemClickListener = new ListMerchantAdapter.OnItemClickListener() {

		@Override
		public void onItemClick(Object object) {
		}
	};
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		ArticleDetailActivity.show(this);
		
	}

	public static void show(Context context) {
		context.startActivity(createIntent(context, ArticleListActivity.class));
	}

}
