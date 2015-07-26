package cn.crane.application.greenlife.adapter.my;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.model.item.AddressItem;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 17, 2015 11:53:12 PM
 * 
 */
public class ListMyAddressAdapter extends CommonAdapter<AddressItem> {

	public ListMyAddressAdapter(Context context, List<AddressItem> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}
	
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return 4;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_my_address, null);
		}else
		{
			view = convertView;
		}
		TextView tvNameTel = (TextView) view.findViewById(R.id.tv_name_tel);
		TextView tvAddress = (TextView) view.findViewById(R.id.tv_address);
		AddressItem item = (AddressItem) getItem(position);
		if(item != null)
		{
			tvNameTel.setText(String.format("%s %s", item.getContactName(),item.getMobile()));
			tvAddress.setText(context.getString(R.string.txt_address) + item.getAddress());
		}
		
		return view;
	}

}
