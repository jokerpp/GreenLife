package cn.crane.application.greenlife.adapter.order;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
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
		return view;
	}

}
