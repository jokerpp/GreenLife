package cn.crane.application.greenlife.adapter.index;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.index.GridCategoryItem;
import cn.crane.application.greenlife.model.item.MerchantGroupItem;
import cn.crane.framework.adapter.CommonAdapter;
import cn.crane.framework.view.smartimage.SmartImageView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:57:28 PM
 * 
 */
public class GridCatedoryServerAdapter extends CommonAdapter<MerchantGroupItem> {

	public GridCatedoryServerAdapter(Context context, List<MerchantGroupItem> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_grid_category_server, null);
		}else
		{
			view = convertView;
		}
		SmartImageView iv = (SmartImageView) view.findViewById(R.id.iv_icon);
		TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
		MerchantGroupItem categoryItem = (MerchantGroupItem) getItem(position);
		if(categoryItem != null)
		{
			tvTitle.setText(categoryItem.getMerchantGroupName());
			iv.setImageUrl(categoryItem.getMerchantGroupPhoto());
//			tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, categoryItem.getIconResId(), 0, 0);
		}
		view.setMinimumHeight((int) (80 * App.fDensity));
		return view;
	}

}
