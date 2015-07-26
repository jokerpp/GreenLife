package cn.crane.application.greenlife.adapter.merchant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * Created by ruifeng.yu on 2015/5/28.
 */
public class ListFoodCategoryAdapter extends CommonAdapter<FoodGroup>
{
    public ListFoodCategoryAdapter(Context context, List<FoodGroup> list) {
		super(context, list);
	}

    private int iSelect = -1;



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = null;
        if (convertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_food_category,null);
        }
        else
        {
            view = convertView;
        }
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        View viewLeft =  view.findViewById(R.id.view_left_red);
        View bg =  view.findViewById(R.id.ll_bg);
        FoodGroup group = (FoodGroup) getItem(position);
        tv.setText(group.getDishesGroupName());
        view.setTag(R.id.tag_data, group);
        if(iSelect == position)
        {
            tv.setTextColor(context.getResources().getColor(R.color.main_color));
            bg.setBackgroundColor(context.getResources().getColor(R.color.white));
            viewLeft.setVisibility(View.VISIBLE);
        }else
        {
            tv.setTextColor(context.getResources().getColor(R.color.txt_black));
            bg.setBackgroundColor(context.getResources().getColor(R.color.bg_index));
            viewLeft.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    public void setiSelect(int iSelect)
    {
        this.iSelect = iSelect;
        notifyDataSetChanged();
    }
    
}
