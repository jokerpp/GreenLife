package cn.crane.application.greenlife.view.sticky;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListFoodAdapter;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.view.sticky.CustomExpandableListView.OnScrollChangedListener;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruifeng.yu on 2015/5/26.
 */
public class ViewStickyExpandableList extends RelativeLayout {
	private CustomExpandableListView expandableListView;
	private TextView tvHeader;

	private ListFoodAdapter adapter;
	private List<FoodGroup> arrGroups = new ArrayList<FoodGroup>();

	public ViewStickyExpandableList(Context context) {
		super(context);
		initViews(context);
	}

	public ViewStickyExpandableList(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public ViewStickyExpandableList(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews(context);
	}

	private void initViews(Context context) {
		LayoutInflater.from(context).inflate(
				R.layout.view_sticky_expandablelist, this);
		expandableListView = (CustomExpandableListView) findViewById(R.id.lv);
		expandableListView.setOnScrollChangedListener(onScrollChangedListener);
		tvHeader = (TextView) findViewById(R.id.tv_header);

		adapter = new ListFoodAdapter(getContext(), arrGroups);
		expandableListView.setAdapter(adapter);
		expandAllGroup();

		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		

		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Object object = adapter.getChild(groupPosition, childPosition);
				if (object instanceof FoodItem) {
					if (onGroupChangedListener != null) {
						onGroupChangedListener.onChildClicked((FoodItem) object);
					}
				}				return false;
			}
		});
		expandableListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
					}
				});
	}

	private OnScrollChangedListener onScrollChangedListener = new OnScrollChangedListener() {
		private String foodType = "";

		@Override
		public void onScrollChanged(int x, int y, int oldx, int oldy) {
			ViewHelper.setTranslationY(tvHeader,
					-expandableListView.getItemPosition());
			int pos = expandableListView.getFirstVisiblePosition();
			Object obj = expandableListView.getItemAtPosition(pos);
			if (obj instanceof FoodGroup) {
				foodType = ((FoodGroup) obj).getFoodType();

			} else if (obj instanceof FoodItem) {
				foodType = ((FoodItem) obj).getFoodType();

			}
			if (onGroupChangedListener != null) {
				onGroupChangedListener.onGroupChanged(foodType);
			}
			tvHeader.setText(foodType);
		}
	};

	private void expandAllGroup() {
		if (expandableListView != null) {
			for (int i = 0; i < arrGroups.size(); i++) {
				expandableListView.expandGroup(i);
			}
		}
	}

	public void setArrGroups(List<FoodGroup> arrGroups) {
		if (arrGroups != null) {
			this.arrGroups.clear();
			this.arrGroups.addAll(arrGroups);
			if (adapter != null) {
				adapter.notifyDataSetChanged();
				expandAllGroup();
			}
		}
	}

	public void scrollToGroup(int groupPosition) {
		if (expandableListView != null) {
			expandableListView.setSelectedGroup(groupPosition);
			// expandableListView.scrollBy(0, 1);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					int pos = expandableListView.getFirstVisiblePosition();
					Object obj = expandableListView.getItemAtPosition(pos);
					if (obj instanceof FoodGroup) {
						tvHeader.setText(((FoodGroup) obj).getFoodType());
					} else if (obj instanceof FoodItem) {
						tvHeader.setText(((FoodItem) obj).getFoodType());
					}
				}
			}, 100);
		}
	}

	public ListFoodAdapter getAdapter() {
		return adapter;
	}

	private CallBack onGroupChangedListener;

	public void setOnGroupChangedListener(CallBack onGroupChangedListener) {
		this.onGroupChangedListener = onGroupChangedListener;
	}

	public interface CallBack {
		void onGroupChanged(String groupId);

		void onChildClicked(FoodItem foodItem);
	}
}
