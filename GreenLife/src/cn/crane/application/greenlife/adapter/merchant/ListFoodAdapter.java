package cn.crane.application.greenlife.adapter.merchant;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.view.ViewAddMinus;
import cn.crane.framework.view.smartimage.SmartImageView;


/**
 * @author ruifeng.yu
 */
public class ListFoodAdapter extends BaseExpandableListAdapter implements OnClickListener
{
    private Context context;
    private LayoutInflater inflater;
    private List<FoodGroup> arrTeamGroups = new ArrayList<FoodGroup>();
	private int iCount;
	private int totalPrice;
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();;

	private boolean isOpenTime = true;
	
	public void setOpenTime(boolean isOpenTime) {
		this.isOpenTime = isOpenTime;
		notifyDataSetChanged();
	}

    public ListFoodAdapter(Context context, List<FoodGroup> arrTeamGroups)
    {
        this.context = context;
        this.arrTeamGroups = arrTeamGroups;
        this.inflater = LayoutInflater.from(context);
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getGroupCount()
     */
    @Override
    public int getGroupCount()
    {
        if (arrTeamGroups != null)
        {
            return arrTeamGroups.size();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
     */
    @Override
    public int getChildrenCount(int groupPosition)
    {
        if (getGroup(groupPosition) != null && getGroup(groupPosition) instanceof FoodGroup)
        {
        	FoodGroup teamGroup = (FoodGroup) getGroup(groupPosition);
            if (teamGroup != null && teamGroup.getArrFoodItems() != null)
            {
                return teamGroup.getArrFoodItems().size();
            }
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getGroup(int)
     */
    @Override
    public Object getGroup(int groupPosition)
    {
        if (arrTeamGroups != null && groupPosition < arrTeamGroups.size())
        {
            return arrTeamGroups.get(groupPosition);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getChild(int, int)
     */ 
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        if (getGroup(groupPosition) != null && getGroup(groupPosition) instanceof FoodGroup)
        {
        	FoodGroup foodGroup = (FoodGroup) getGroup(groupPosition);
            if (foodGroup != null && foodGroup.getArrFoodItems() != null && childPosition < foodGroup.getArrFoodItems().size())
            {
                return foodGroup.getArrFoodItems().get(childPosition);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getGroupId(int)
     */
    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getChildId(int, int)
     */
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
     * android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
        View view = null;
        if (convertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_food_title,null);
        }
        else
        {
            view = convertView;
        }
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        FoodGroup group = (FoodGroup) getGroup(groupPosition);
        tv.setText(group.getFoodType());
        view.setTag(R.id.tag_data, group);
        return view;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
     * android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        View view = null;
        if (convertView == null)
        {
            view = inflater.inflate(R.layout.item_food_detail, null);
        }
        else
        {
            view = convertView;
        }
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvDetail = (TextView) view.findViewById(R.id.tv_detail);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        SmartImageView iv = (SmartImageView) view.findViewById(R.id.iv_image);
        TextView tvKexuan = (TextView) view.findViewById(R.id.tv_kexuan);
        
        ViewAddMinus addMinus = (ViewAddMinus) view.findViewById(R.id.view_add_minus);
        TextView tvSleep = (TextView) view.findViewById(R.id.tv_merchant_sleep);
        FoodItem item = (FoodItem) getChild(groupPosition,childPosition);
        
        addMinus.setOnNumberChangedListener(onNumberChangedListener);
        if (item != null) {
			tvTitle.setText(item.getDishesName());
			tvDetail.setText(context.getString(R.string.format_food_detail,item.getSaleAmoun(),item.getUnit(),item.getRecommendAmount()));
			tvPrice.setText(context.getString(R.string.txt_format_price, item.getPriceFormat()) + (TextUtils.isEmpty(item.getUnit()) ? "" :"/" + item.getUnit()));
			tvKexuan.setVisibility(View.GONE);
			tvSleep.setVisibility(View.GONE);
			addMinus.setVisibility(View.VISIBLE);
			
			
			iv.setImageUrl(item.getDishesPicture());
			
			if(item.isOption())
			{
				tvKexuan.setVisibility(View.VISIBLE);
				tvSleep.setVisibility(View.GONE);
				addMinus.setVisibility(View.GONE);
			}
			
			if(!isOpenTime)
			{
				tvKexuan.setVisibility(View.GONE);
				tvSleep.setVisibility(View.VISIBLE);
				addMinus.setVisibility(View.GONE);
			}
			addMinus.setItem(item);
			addMinus.setiNumber(item.getiCountChoose());
			view.setTag(R.id.tag_data, item);
			view.setOnClickListener(this);
		}
		return view;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    public List<FoodItem> getChooseFoodList() {
    	return arrFoodItems;
	}

	private void refreshCountChoose() {
		arrFoodItems.clear();
    	iCount = 0;
    	totalPrice = 0;
    	if(arrTeamGroups != null)
    	{
    		for(FoodGroup foodGroup:arrTeamGroups)
    		{
    			if(foodGroup != null && foodGroup.getArrFoodItems() != null)
    			{
    				for(FoodItem item : foodGroup.getArrFoodItems())
    				{
    					if(item != null && item.getiCountChoose() > 0)
    					{
    						iCount += item.getiCountChoose();
    						totalPrice += item.getTotalPrice();
    						arrFoodItems.add(item);
    					}
    				}
    			}
    		}
    	}
	}
    
    @Override
    public void notifyDataSetChanged() {
    	super.notifyDataSetChanged();
    	refreshCountChoose();
		
		if(onCountChangedListener != null)
		{
			onCountChangedListener.onCountChanged(arrFoodItems,iCount,totalPrice);
		}
    }

    public int getiCount() {
		return iCount;
	}
    
    public int getTotalPrice() {
		return totalPrice;
	}
    
    
    private ViewAddMinus.OnNumberChangedListener onNumberChangedListener = new ViewAddMinus.OnNumberChangedListener() {
		
		@Override
		public void onNumberChanged(ViewAddMinus view, int iNumber) {
			notifyDataSetChanged();
		}
	};
    
    private OnCountChangedListener onCountChangedListener;
    public void setOnCountChangedListener(
			OnCountChangedListener onCountChangedListener) {
		this.onCountChangedListener = onCountChangedListener;
	}
    public interface OnCountChangedListener
    {
    	void onCountChanged(List<FoodItem> arrFoodItems,int iCount,int totalPrice);
    	
    	void onChildCLicked(FoodItem foodItem);
    }
	@Override
	public void onClick(View v) {
		if(v.getTag(R.id.tag_data) instanceof FoodItem)
		{
			if(onCountChangedListener != null)
			{
				onCountChangedListener.onChildCLicked((FoodItem) v.getTag(R.id.tag_data));
			}
		}
	}

}
