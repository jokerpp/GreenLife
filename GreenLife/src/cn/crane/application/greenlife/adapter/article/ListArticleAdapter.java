package cn.crane.application.greenlife.adapter.article;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.OrderItem;
import cn.crane.application.greenlife.model.item.NewsItem;
import cn.crane.framework.adapter.CommonAdapter;
import cn.crane.framework.view.smartimage.SmartImageView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 12:35:53 AM
 * 
 */
public class ListArticleAdapter extends CommonAdapter<NewsItem> {

	public ListArticleAdapter(Context context, List<NewsItem> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}
	
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return 10;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_article_list,null);
		}else
		{
			view = convertView;
		}
		SmartImageView iv_image = (SmartImageView) view.findViewById(R.id.iv_image);
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
		TextView tv_detail = (TextView) view.findViewById(R.id.tv_detail);
		NewsItem item = (NewsItem) getItem(position);
		if(item != null)
		{
			iv_image.setImageUrl(item.getImageUrl());
			tv_title.setText(item.getNewsTitle());
			tv_detail.setText(item.getNewsContent());
		}
		return view;
	}

}
