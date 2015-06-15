package cn.crane.application.greenlife.view.sticky;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ExpandableListView;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;

import com.nineoldandroids.view.ViewHelper;

public class CustomExpandableListView extends ExpandableListView

{
    public static final String TAG = "CustomExpandableListView";

    public CustomExpandableListView(Context context)
    {
        super(context);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollChangedListener != null)
            onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
    }


    private OnScrollChangedListener onScrollChangedListener;

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener)
    {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    public interface OnScrollChangedListener
    {
        public void onScrollChanged(int x, int y, int oldx, int oldy);
    }

    private int posMonthX;
    public int getItemPosition()
    {
        {
            try
            {
                int itemTitleH = getChildAt(0).getHeight();
                for (int i = 0; i < getChildCount(); i++)
                {
                    Object obj = getChildAt(i).getTag(R.id.tag_data);
                    if (!(obj instanceof FoodGroup))
                    {
                        continue;
                    }
                    itemTitleH = getChildAt(i).getHeight();
                    int lenBeforeMonth = (int) (ViewHelper.getY(getChildAt(i)) - itemTitleH);
                    int posMonthX = getScrollY() - lenBeforeMonth;
                    if (posMonthX > 0 && posMonthX < itemTitleH)
                    {
                        this.posMonthX = posMonthX;
                        return posMonthX;
                    }
                    else
                    {
                        this.posMonthX = 0;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return posMonthX;
    }
}
