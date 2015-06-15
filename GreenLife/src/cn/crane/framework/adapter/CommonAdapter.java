package cn.crane.framework.adapter;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 20, 2015 9:22:35 PM
 * @param <T>
 * 
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected List<T> list;
	protected Context context;
	

	public CommonAdapter(Context context, List<T> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		if(list != null)
			return list.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(list != null)
			return list.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
