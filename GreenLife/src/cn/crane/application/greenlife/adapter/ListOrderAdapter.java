package cn.crane.application.greenlife.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.OrderItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 12:35:53 AM
 * 
 */
public class ListOrderAdapter extends CommonAdapter<OrderItem> {

	public ListOrderAdapter(Context context, List<OrderItem> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_order,null);
		}else
		{
			view = convertView;
		}
		return view;
	}

}
