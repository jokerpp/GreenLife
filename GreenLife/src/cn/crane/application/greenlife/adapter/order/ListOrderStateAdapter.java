package cn.crane.application.greenlife.adapter.order;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.model.item.OrderStateItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 16, 2015 10:00:44 PM
 * 
 */
public class ListOrderStateAdapter extends CommonAdapter<OrderStateItem> {

	public ListOrderStateAdapter(Context context, List<OrderStateItem> list) {
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
		ViewHolder holder = null;
		if(convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_order_state, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
			
		}else
		{
			holder = (ViewHolder) convertView.getTag();
		}
			
		if(position == 0)
		{
			holder.line_top.setVisibility(View.INVISIBLE);
			holder.line_bottom.setVisibility(View.VISIBLE);
		}else if(position == getCount() - 1)
		{
			holder.line_top.setVisibility(View.VISIBLE);
			holder.line_bottom.setVisibility(View.INVISIBLE);
		}else
		{
			holder.line_top.setVisibility(View.VISIBLE);
			holder.line_bottom.setVisibility(View.VISIBLE);
		}
		
		OrderStateItem item = (OrderStateItem) getItem(position);
		if(item != null)
		{
			holder.setData(item);
		}
		return convertView;

	}
	
	static class ViewHolder {
	        private View line_top;
	        private View line_bottom;
	        private ImageView iv_icon;
	        private TextView tv_title;
	        private TextView tv_time;
	        private TextView tv_detail;

	        ViewHolder(View view) {
	            initViews(view);
	        }

	        private void initViews(View root) {
	            line_top = root.findViewById(R.id.line_top);
	            line_bottom = root.findViewById(R.id.line_bottom);
	            iv_icon = (ImageView) root.findViewById(R.id.iv_icon);
	            tv_title = (TextView) root.findViewById(R.id.tv_title);
	            tv_time = (TextView) root.findViewById(R.id.tv_time);
	            tv_detail = (TextView) root.findViewById(R.id.tv_detail);
	        }
	        
	        public void setData(OrderStateItem item) {
	        	if (item != null) {
					tv_title.setText(item.getOrderStateType().nameRes);
					tv_detail.setText(item.getOrderContent());
					tv_time.setText(item.getOrderStatusDateStr());
				}
			}
	    }

}
