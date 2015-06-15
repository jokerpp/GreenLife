package cn.crane.application.greenlife.adapter.index;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.index.ArticleCategoryItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:57:28 PM
 * 
 */
public class ListArticleCatedoryAdapter extends CommonAdapter<ArticleCategoryItem> {

	public ListArticleCatedoryAdapter(Context context, List<ArticleCategoryItem> list) {
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
		ArticleCategoryItem articleCategoryItem = (ArticleCategoryItem) getItem(position);
		if(articleCategoryItem != null)
		{
			tvTitle.setText(articleCategoryItem.getTxt());
			view.setBackgroundColor(context.getResources().getColor(articleCategoryItem.getBgColor()));
		}
		return view;
	}

}
