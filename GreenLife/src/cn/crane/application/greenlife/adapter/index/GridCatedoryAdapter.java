package cn.crane.application.greenlife.adapter.index;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.index.GridCategoryItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:57:28 PM
 * 
 */
public class GridCatedoryAdapter extends CommonAdapter<GridCategoryItem> {

	public GridCatedoryAdapter(Context context, List<GridCategoryItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_grid_category, null);
		}else
		{
			view = convertView;
		}
		TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
		GridCategoryItem categoryItem = (GridCategoryItem) getItem(position);
		if(categoryItem != null)
		{
			tvTitle.setText(categoryItem.getTxt());
			tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, categoryItem.getIconResId(), 0, 0);
		}
		return view;
	}

}
