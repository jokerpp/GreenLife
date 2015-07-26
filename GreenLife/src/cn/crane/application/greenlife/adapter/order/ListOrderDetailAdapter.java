package cn.crane.application.greenlife.adapter.order;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 17, 2015 12:20:08 AM
 * 
 */
public class ListOrderDetailAdapter extends CommonAdapter<String> {

	public ListOrderDetailAdapter(Context context, List<String> list) {
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
			view = LayoutInflater.from(context).inflate(R.layout.item_order_detail, null);
		}else
		{
			view = convertView;
		}
		TextView tv = (TextView) view.findViewById(R.id.tv_title);
		Object obj = getItem(position);
		if(obj != null)
		{
			tv.setText((String) obj);
		}
		
		return view;
	}

}
