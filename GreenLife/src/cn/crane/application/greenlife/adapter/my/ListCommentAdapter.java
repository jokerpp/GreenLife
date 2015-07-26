package cn.crane.application.greenlife.adapter.my;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.model.item.CommentItem;
import cn.crane.application.greenlife.model.item.RecommandDishesDataList;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 20, 2015 10:27:43 PM
 * @param <T>
 * 
 */
public class ListCommentAdapter<T> extends CommonAdapter<T> {

	public ListCommentAdapter(Context context, List<T> list) {
		super(context, list);
	}
	
//	@Override
//	public int getCount() {
//		return 10;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
			
		}else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		CommentItem item = (CommentItem) getItem(position);
		holder.setData(item);
		return convertView;
	}

	
	 class ViewHolder {
	        public cn.crane.framework.view.smartimage.SmartImageView iv_image;
	        public TextView tv_user;
	        public TextView tv_time;
	        public RatingBar ratingbar;
	        public TextView tv_speed;
	        public TextView tv_detail;
	        public TextView tv_tuijian;
	        public LinearLayout rootView;

	        ViewHolder(View view) {
	            initViews(view);
	        }

	        private void initViews(View root) {
	            iv_image = (cn.crane.framework.view.smartimage.SmartImageView) root.findViewById(R.id.iv_image);
	            tv_user = (TextView) root.findViewById(R.id.tv_user);
	            tv_time = (TextView) root.findViewById(R.id.tv_time);
	            ratingbar = (RatingBar) root.findViewById(R.id.ratingbar);
	            tv_speed = (TextView) root.findViewById(R.id.tv_speed);
	            tv_detail = (TextView) root.findViewById(R.id.tv_detail);
	            tv_tuijian = (TextView) root.findViewById(R.id.tv_tuijian);
	            rootView = (LinearLayout) root.findViewById(R.id.rootView);
	        }
	        
	        
	        public void setData(CommentItem item) {
	        	if(item != null)
	        	{
//	        		iv_image.setImageUrl(item.get)
	        		tv_user.setText(item.getUserName());
	        		tv_time.setText(item.getInsertDateStr());
	        		ratingbar.setProgress((int) item.getScore());
	        		ratingbar.setMax(5);
	        		
	        		tv_speed.setText(context.getResources().getString(R.string.txt_comment_format_speed,"0"));
	        		
	        		tv_detail.setText(item.getContent());
	        		tv_tuijian.setVisibility(View.GONE);
	        		if(item.getRecommandList() != null)
	        		{
	        			StringBuffer buffer = new StringBuffer();
		        		for(RecommandDishesDataList recommandDishesDataList:item.getRecommandList())
		        		{
		        			if(recommandDishesDataList != null && !TextUtils.isEmpty(recommandDishesDataList.getDishesName()))
		        			{
		        				buffer.append(recommandDishesDataList.getDishesName());
		        				buffer.append("  ");
		        			}
		        		}
		        		if(!TextUtils.isEmpty(buffer.toString()))
		        		{
		        			tv_tuijian.setText(buffer.toString());
		        			
		        			tv_tuijian.setVisibility(View.VISIBLE);
		        		}
	        		}
	        		
	        		
	        	}
			}
	    }

}
