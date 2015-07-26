package cn.crane.application.greenlife.adapter.order;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.model.item.OrderItem;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_order, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
			
		}else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		OrderItem item = (OrderItem) getItem(position);
		holder.setData(context,item);
		return convertView;
	}
	
	static class ViewHolder {
		private cn.crane.framework.view.smartimage.SmartImageView iv_image;
		private TextView tv_title;
		private TextView tv_price;
		private TextView tv_time;
		private TextView tv_state;

		ViewHolder(View view) {
			initViews(view);
		}

		private void initViews(View root) {
			iv_image = (cn.crane.framework.view.smartimage.SmartImageView) root
					.findViewById(R.id.iv_image);
			tv_title = (TextView) root.findViewById(R.id.tv_title);
			tv_price = (TextView) root.findViewById(R.id.tv_price);
			tv_time = (TextView) root.findViewById(R.id.tv_time);
			tv_state = (TextView) root.findViewById(R.id.tv_state);
		}
		
		public void setData(Context context,OrderItem item) {
			if(item != null)
			{
				iv_image.setImageUrl(item.getMerchantPictrue());
				tv_title.setText(item.getMerchantName());
				tv_price.setText(context.getString(R.string.format_order_total_price, item.getPreferentialPrice()));
				tv_time.setText(item.getInsertDateTimeStr());
				tv_state.setText(item.getOrderState().nameRes);
			}
		}
	}

}
