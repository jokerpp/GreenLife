package cn.crane.application.greenlife.adapter.index;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.index.ArticleCategoryItem;
import cn.crane.application.greenlife.model.item.NewsGroupItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:57:28 PM
 * 
 */
public class ListArticleCatedoryAdapter extends CommonAdapter<NewsGroupItem> {

	private int [] colors = new int []{R.color.main_color,R.color.txt_purple,R.color.txt_orange,R.color.txt_blue};
	public ListArticleCatedoryAdapter(Context context, List<NewsGroupItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_article_category, null);
		}else
		{
			view = convertView;
		}
		TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
		NewsGroupItem item = (NewsGroupItem) getItem(position);
		if(item != null)
		{
			tvTitle.setText(item.getNewsGroupName());
			view.setBackgroundColor(context.getResources().getColor(colors[position % colors.length]));
		}
		return view;
	}

}
