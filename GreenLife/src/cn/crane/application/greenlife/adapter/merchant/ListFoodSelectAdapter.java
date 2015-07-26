package cn.crane.application.greenlife.adapter.merchant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.view.ViewAddMinus;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * Created by ruifeng.yu on 2015/5/28.
 */
public class ListFoodSelectAdapter extends CommonAdapter<FoodItem>
{
    public ListFoodSelectAdapter(Context context, List<FoodItem> list) {
		super(context, list);
	}

    private int iSelect = -1;

    private ViewAddMinus.OnNumberChangedListener onNumberChangedListener;
    
    public void setOnNumberChangedListener(
			ViewAddMinus.OnNumberChangedListener onNumberChangedListener) {
		this.onNumberChangedListener = onNumberChangedListener;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = null;
        if (convertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_food_select_list,null);
        }
        else
        {
            view = convertView;
        }
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        ViewAddMinus view_add_minus = (ViewAddMinus) view.findViewById(R.id.view_add_minus);
        
        FoodItem item = (FoodItem) getItem(position);
        if(item != null)
        {
        	tvTitle.setText(item.getDishesName());
        	tvPrice.setText(item.getTotalPrice() +"");
        	view_add_minus.setOnNumberChangedListener(onNumberChangedListener);
        	view_add_minus.setItem(item);
        	view_add_minus.setiNumber(item.getiCountChoose());
        }
        view.setTag(R.id.tag_data, item);
        return view;
    }

    public void setiSelect(int iSelect)
    {
        this.iSelect = iSelect;
        notifyDataSetChanged();
    }
    
}
