package cn.crane.application.greenlife.adapter.merchant;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 10, 2015 11:29:33 PM
 * 
 */
public class ListOrderFoodsAdapter extends CommonAdapter<FoodItem> {

	public ListOrderFoodsAdapter(Context context, List<FoodItem> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_order_food_list, null);
		}else
		{
			view = convertView;
		}
		TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
		TextView tvNum = (TextView) view.findViewById(R.id.tv_num);
		TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
		FoodItem item = (FoodItem) getItem(position);
		if(item != null)
		{
			tvTitle.setText(item.getDishesName());
			tvNum.setText(String.format("X%s", item.getiCountChoose() + ""));
			tvPrice.setText(context.getString(R.string.txt_format_price, item.getTotalPrice() + ""));
		}
		return view;
	}

}
