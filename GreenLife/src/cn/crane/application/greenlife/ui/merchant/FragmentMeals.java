package cn.crane.application.greenlife.ui.merchant;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListFoodAdapter;
import cn.crane.application.greenlife.adapter.merchant.ListFoodCategoryAdapter;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.ui.order.OrderConfirmActivity;
import cn.crane.application.greenlife.view.ViewAddMinus;
import cn.crane.application.greenlife.view.sticky.ViewStickyExpandableList;
import cn.crane.application.greenlife.view.sticky.ViewStickyExpandableList.CallBack;
import cn.crane.framework.fragment.BaseFragment;

public class FragmentMeals extends BaseFragment implements OnItemClickListener, OnClickListener {
	public static final int requestCode = 1000;
	
	private TextView tvTitle;
	
	private ListView lv_category;
	private ViewStickyExpandableList view_stickyList;
	private List<FoodGroup> arrGroups = new ArrayList<FoodGroup>();

	private List<FoodItem> arrFoodItemsSelect = new ArrayList<FoodItem>();
	
	private ListFoodAdapter adapter;
	private ListFoodCategoryAdapter categoryAdapter;
	
	private TextView tvBottomLeft;
	private TextView tvBottomRight;
	private LinearLayout llShopCar;
	private TextView tvCount;
	
	
	
	private int iCount;
	private int totalPrice;
	

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_food_list;
	}

	@Override
	protected void findViews() {
		initViews(getView());
		
		tvBottomLeft = (TextView) findViewById(R.id.tv_total_price);
		tvBottomRight = (TextView) findViewById(R.id.tv_choose_ok);
		tvCount = (TextView) findViewById(R.id.tv_total_count);
		llShopCar = (LinearLayout) findViewById(R.id.ll_shop_car);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		
	}

	@Override
	protected void bindViews() {
		tvBottomLeft.setOnClickListener(this);
		tvBottomRight.setOnClickListener(this);
		llShopCar.setOnClickListener(this);
	}

	@Override
	protected void init() {
		view_stickyList.getAdapter().setOnCountChangedListener(onCountChangedListener);
		adapter = view_stickyList.getAdapter();
		
//		llPopSelectFoods.setVisibility(View.GONE);
		dismissSelectList();
		
		
//		animBottomIn.setAnimationListener(animationListener);
//		animBottomOut.setAnimationListener(animationListener);
		
		tvTitle.setText("XXX水果店");
		
	}

	private void initViews(View root) {
		lv_category = (ListView) root.findViewById(R.id.lv_category);
		view_stickyList = (ViewStickyExpandableList) root
				.findViewById(R.id.view_stickyList);

		createList();
		
		categoryAdapter = new ListFoodCategoryAdapter(getActivity(), arrGroups);
		lv_category.setAdapter(categoryAdapter);
		lv_category.setOnItemClickListener(this);
		
		view_stickyList.setOnGroupChangedListener(onGroupChangedListener);
		view_stickyList.setArrGroups(arrGroups);
		
	}

	public void createList() {
		arrGroups.clear();
		for (int i = 0; i < 10; i++) {
			FoodGroup group = new FoodGroup();
			group.setDishesGroupName("Group " + i);
			List<FoodItem> arrItems = new ArrayList<FoodItem>();
			for (int j = 0; j < 5; j++) {
				FoodItem item = new FoodItem();
				item.setDishesGroupName("Group " + i);
				item.setDishesName("Item " + j);
				arrItems.add(item);
			}
			group.setArrFoodItems(arrItems);
			arrGroups.add(group);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		categoryAdapter.setiSelect(position);
		view_stickyList.scrollToGroup(position);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_total_price:
		case R.id.ll_shop_car:
//			if(llGrey.getVisibility() == View.VISIBLE)
//			{
//				animDismissSelectList();
//			}else
//			{
//				if(arrFoodItemsSelect != null && arrFoodItemsSelect.size() > 0)
//				{
//					animShowSelectList();
//				}
//			}
			
			break;
		case R.id.tv_choose_ok:
			OrderConfirmActivity.show(getActivity());
			break;

		default:
			break;
		}
	}
	
	private ListFoodAdapter.OnCountChangedListener onCountChangedListener = new ListFoodAdapter.OnCountChangedListener() {

	

		@Override
		public void onCountChanged(List<FoodItem> arrFoodItems, int iCount,
				int totalPrice) {
			FragmentMeals.this.iCount = iCount;
			FragmentMeals.this.totalPrice = totalPrice;
			tvCount.setText(iCount + "");
			tvBottomLeft.setText(FragmentMeals.this.totalPrice +"");
			if(arrFoodItems != null && arrFoodItems.size() > 0)
			{
				arrFoodItemsSelect.clear();
				arrFoodItemsSelect.addAll(arrFoodItems);
			}else
			{
				dismissSelectList();
			}
		}

		@Override
		public void onChildCLicked(FoodItem foodItem) {
			// TODO Auto-generated method stub
//			FoodDetailActivity.show(getActivity(), foodItem, requestCode);

		}
		
	};
	
	private ViewAddMinus.OnNumberChangedListener onNumberChangedListener = new ViewAddMinus.OnNumberChangedListener() {
		
		@Override
		public void onNumberChanged(ViewAddMinus view, int iNumber) {
			FoodItem item = view.getItem();
			if(item != null && arrGroups != null)
			{
				for(FoodGroup group : arrGroups)
				{
					if(group != null && group.getArrFoodItems() != null)
					{
						for(FoodItem foodItem : group.getArrFoodItems())
						{
							if(foodItem != null && foodItem.getId() != null)
							{
								if(foodItem.getId().equalsIgnoreCase(item.getId()))
								{
									foodItem.setiCountChoose(iNumber);
									adapter.notifyDataSetChanged();
									break;
								}
							}
						}
					}
				}
			}
		}
	};

	
	
	private CallBack onGroupChangedListener = new CallBack() {
		
		@Override
		public void onGroupChanged(String groupId) {
			if(!TextUtils.isEmpty(groupId))
			{
				for(int i = 0;i<arrGroups.size();i++)
				{
					if(groupId.equalsIgnoreCase(arrGroups.get(i).getFoodType()))
					{
						categoryAdapter.setiSelect(i);
						break;
					}
				}
			}
			
		}

		@Override
		public void onChildClicked(FoodItem foodItem) {
//			FoodDetailActivity.show(getActivity(), foodItem, requestCode);
		}
	};
	
	
	
	
	private void animShowSelectList() {
//		llGrey.clearAnimation();
//		llGrey.startAnimation(animAlphaIn);
//		llPopSelectFoods.startAnimation(animBottomIn);
		showSelectList();
	}
	private void animDismissSelectList() {
//		llGrey.clearAnimation();
//		llGrey.startAnimation(animAlphaOut);
//		llPopSelectFoods.startAnimation(animBottomOut);
		dismissSelectList();
	}
	
	private void showSelectList() {
//		llGrey.setVisibility(View.VISIBLE);
//		ViewHelper.setAlpha(llGrey, 0.5f);
//		
//		llPopSelectFoods.setVisibility(View.VISIBLE);
//		
	}
	private void dismissSelectList() {
//		llGrey.setVisibility(View.GONE);
//		ViewHelper.setAlpha(llGrey, 0.5f);
//		
//		llPopSelectFoods.setVisibility(View.GONE);
		
	}

}
