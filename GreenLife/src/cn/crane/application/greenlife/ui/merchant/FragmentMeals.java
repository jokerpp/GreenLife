package cn.crane.application.greenlife.ui.merchant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.nineoldandroids.view.ViewHelper;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.merchant.ListFoodAdapter;
import cn.crane.application.greenlife.adapter.merchant.ListFoodCategoryAdapter;
import cn.crane.application.greenlife.adapter.merchant.ListFoodSelectAdapter;
import cn.crane.application.greenlife.api.API;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.api.Task_Post;
import cn.crane.application.greenlife.bean.merchant.FoodGroup;
import cn.crane.application.greenlife.bean.merchant.FoodItem;
import cn.crane.application.greenlife.model.item.ChildFoodItem;
import cn.crane.application.greenlife.model.result.RE_getMerchantDishesList;
import cn.crane.application.greenlife.model.result.RE_getMerchantsDetailsInfo;
import cn.crane.application.greenlife.ui.order.OrderConfirmActivity;
import cn.crane.application.greenlife.view.ViewAddMinus;
import cn.crane.application.greenlife.view.sticky.ViewStickyExpandableList;
import cn.crane.application.greenlife.view.sticky.ViewStickyExpandableList.CallBack;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.fragment.BaseFragment;

public class FragmentMeals extends BaseFragment implements OnItemClickListener, OnClickListener,SetMerchantsDetailsInfo {
	public static final int requestCode = 1000;
	private TextView tv_tips;
	private ListView lv_category;
	private ViewStickyExpandableList view_stickyList;

	private List<FoodItem> arrFoodItemsSelect = new ArrayList<FoodItem>();
	
	private ListFoodAdapter adapter;
	private ListFoodCategoryAdapter categoryAdapter;
	
	private TextView tvBottomLeft;
	private TextView tvBottomRight;
	private LinearLayout llShopCar;
	private TextView tvCount;
	
	private LinearLayout llPopSelectFoods;
	private TextView tvClearShopCar;
	private ListView lvFoodSelect;
	private ListFoodSelectAdapter foodSelectAdapter;
	
	private LinearLayout llGrey;
	
	private int iCount;
	private int totalPrice;
	
	private Animation animBottomIn;
	private Animation animBottomOut;
	private Animation animAlphaIn;
	private Animation animAlphaOut;
	
	private RE_getMerchantsDetailsInfo getMerchantsDetailsInfo = new RE_getMerchantsDetailsInfo();
	
	private Task_Post task_Post_getMerchantDishesList;
	private List<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
	private List<String> arrGroupTitles = new ArrayList<String>();
	private HashMap<String, List<FoodItem>> map = new HashMap<String, List<FoodItem>>();
	
	private List<FoodGroup> arrFoodGroups = new ArrayList<FoodGroup>();

	private String merchantToken;
	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_food_list;
	}

	@Override
	protected void findViews() {
		Bundle bundle = getArguments();
		if(bundle != null)
		{
			merchantToken = bundle.getString(FoodListActivity.MERCHANT_TOKEN);
		}
		initViews(getView());
		
		tvBottomLeft = (TextView) findViewById(R.id.tv_total_price);
		tvBottomRight = (TextView) findViewById(R.id.tv_choose_ok);
		tvCount = (TextView) findViewById(R.id.tv_total_count);
		llShopCar = (LinearLayout) findViewById(R.id.ll_shop_car);
		
		llPopSelectFoods = (LinearLayout) findViewById(R.id.ll_pop_select_list);
		tvClearShopCar = (TextView) findViewById(R.id.tv_pop_clear);
		lvFoodSelect = (ListView) findViewById(R.id.lv_food_select);
		
		llGrey = (LinearLayout) findViewById(R.id.ll_grey);
	}

	@Override
	protected void bindViews() {
		tvBottomLeft.setOnClickListener(this);
		tvBottomRight.setOnClickListener(this);
		llShopCar.setOnClickListener(this);
		tvClearShopCar.setOnClickListener(this);
		llGrey.setOnClickListener(this);
	}

	@Override
	protected void init() {
		
		
		view_stickyList.getAdapter().setOnCountChangedListener(onCountChangedListener);
		adapter = view_stickyList.getAdapter();
		
//		llPopSelectFoods.setVisibility(View.GONE);
		dismissSelectList();
		foodSelectAdapter = new ListFoodSelectAdapter(getActivity(), arrFoodItemsSelect);
		foodSelectAdapter.setOnNumberChangedListener(onNumberChangedListener);
		lvFoodSelect.setAdapter(foodSelectAdapter);
		
		
		animBottomIn = AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottomt_in);
		animBottomOut = AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottomt_out);
		animAlphaIn = AnimationUtils.loadAnimation(getActivity(), R.anim.push_alpha_in);
		animAlphaOut = AnimationUtils.loadAnimation(getActivity(), R.anim.push_alpha_out);
		
		animBottomIn.setAnimationListener(animationListener);
		animBottomOut.setAnimationListener(animationListener);
		
		tv_tips.setVisibility(View.GONE);
		
	}

	private void initViews(View root) {
		tv_tips = (TextView) root.findViewById(R.id.tv_tips);
		lv_category = (ListView) root.findViewById(R.id.lv_category);
		view_stickyList = (ViewStickyExpandableList) root
				.findViewById(R.id.view_stickyList);

//		createList();
		
		categoryAdapter = new ListFoodCategoryAdapter(getActivity(), arrFoodGroups);
		lv_category.setAdapter(categoryAdapter);
		lv_category.setOnItemClickListener(this);
		
		view_stickyList.setOnGroupChangedListener(onGroupChangedListener);
		view_stickyList.setArrGroups(arrFoodGroups);
		
		tv_tips.setOnClickListener(this);
		
		
		getMerchantDishesList();
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
		case R.id.ll_grey:
		case R.id.ll_shop_car:
			if(llGrey.getVisibility() == View.VISIBLE)
			{
				animDismissSelectList();
			}else
			{
				if(arrFoodItemsSelect != null && arrFoodItemsSelect.size() > 0)
				{
					animShowSelectList();
				}
			}
			
			break;
		case R.id.tv_pop_clear:
			arrFoodItemsSelect.clear();
			foodSelectAdapter.notifyDataSetChanged();
			animDismissSelectList();
			break;
		case R.id.tv_tips:
//			DialogFragmentTips.show((BaseActivity) getActivity(), getMerchantsDetailsInfo);
//			TipsActivity.show(getActivity());
			break;
		case R.id.tv_choose_ok:
			if(arrFoodItemsSelect != null && arrFoodItemsSelect.size() >0)
			{
				ArrayList<FoodItem> arrFoodItems = new ArrayList<FoodItem>();
//				FoodItem item = new FoodItem();
//				
//				item.setPreferentialPrice(getMerchantsDetailsInfo.getFoodBoxPrice());
//				item.setPrimePrice(getMerchantsDetailsInfo.getFoodBoxPrice());
//				item.setDishesName(getString(R.string.txt_food_box_price));
//				item.setiCountChoose(1);
//				arrFoodItems.add(0, item);
//				
//				item = new FoodItem();
//				item.setPreferentialPrice(getMerchantsDetailsInfo.getDeliveryPrice());
//				item.setPrimePrice(getMerchantsDetailsInfo.getDeliveryPrice());
//				item.setDishesName(getString(R.string.txt_food_send_price));
//				item.setiCountChoose(1);
//				arrFoodItems.add(0, item);
				
				arrFoodItems.addAll(arrFoodItemsSelect);
				
				OrderConfirmActivity.show(getActivity(),arrFoodItems,getMerchantToken());
			}
			break;

		default:
			break;
		}
	}
	
	
	
	private ListFoodAdapter.OnCountChangedListener onCountChangedListener = new ListFoodAdapter.OnCountChangedListener() {

	

		@Override
		public void onCountChanged(List<FoodItem> arrFoodItems, int iCount,
				int totalPrice) {
			if(isOpenning)
			{
				FragmentMeals.this.iCount = iCount;
				FragmentMeals.this.totalPrice = totalPrice;
				tvCount.setText(iCount + "");
				tvBottomLeft.setText(getString(R.string.format_total_money, FragmentMeals.this.totalPrice +""));
				if(arrFoodItems != null && arrFoodItems.size() > 0)
				{
					arrFoodItemsSelect.clear();
					arrFoodItemsSelect.addAll(arrFoodItems);
					foodSelectAdapter.notifyDataSetChanged();
				}else
				{
					dismissSelectList();
				}
			}
		}

		@Override
		public void onChildCLicked(FoodItem foodItem) {
			// TODO Auto-generated method stub
//			FoodDetailActivity.show(getActivity(), foodItem,getMerchantToken(), requestCode);
			FoodDetailDialogFragment.show((BaseActivity) getActivity(),foodItem);
		}
		
	};
	
	private ViewAddMinus.OnNumberChangedListener onNumberChangedListener = new ViewAddMinus.OnNumberChangedListener() {
		
		@Override
		public void onNumberChanged(ViewAddMinus view, int iNumber) {
			FoodItem item = view.getItem();
			if(item != null && arrFoodGroups != null)
			{
				for(FoodGroup group : arrFoodGroups)
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
									llPopSelectFoods.invalidate();
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
				for(int i = 0;i<arrFoodGroups.size();i++)
				{
					if(groupId.equalsIgnoreCase(arrFoodGroups.get(i).getFoodType()))
					{
						categoryAdapter.setiSelect(i);
						lvFoodSelect.smoothScrollToPosition(i);
						break;
					}
				}
			}
			
		}

		@Override
		public void onChildClicked(FoodItem foodItem) {
//			FoodDetailActivity.show(getActivity(), foodItem, getMerchantToken(),requestCode);
			FoodDetailDialogFragment.show((BaseActivity) getActivity(),foodItem);
		}
	};
	
	private 
	
	AnimationListener animationListener = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			if(animBottomIn == animation)
			{
				showSelectList();
			}else
			{
				dismissSelectList();
			}
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
		llGrey.setVisibility(View.VISIBLE);
		ViewHelper.setAlpha(llGrey, 0.5f);
		
		llPopSelectFoods.setVisibility(View.VISIBLE);
		
	}
	private void dismissSelectList() {
		llGrey.setVisibility(View.GONE);
		ViewHelper.setAlpha(llGrey, 0.5f);
		
		llPopSelectFoods.setVisibility(View.GONE);
		
	}
	
	private void getMerchantDishesList() {
//		商户加密ID	merchantToken	必填	String	
//		时间戳	timeStamp		Long	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("merchantToken", getMerchantToken());
		map.put("timeStamp", API_Contant.getTimeStamp());
		Task_Post.clearTask(task_Post_getMerchantDishesList);
		task_Post_getMerchantDishesList = new Task_Post(map, API.API_getMerchantDishesList,
				new Task_Post.OnPostEndListener() {
			
			@Override
			public void onPostEnd(String sResult) {
				dismissLoadingDlg();
				RE_getMerchantDishesList result = new RE_getMerchantDishesList();
				try {
					result = JSONArray.parseObject(sResult,
							RE_getMerchantDishesList.class);
					refreshUI(result);
					if (result.isSuccess()) {
						
					} else {
						App.showToast(result.getResultMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
//							App.showToast(R.string.api_error_code_6);
				}
			}
		});
		task_Post_getMerchantDishesList.execute();
		displayLoadingDlg(R.string.loading);
		
	}

	private String getMerchantToken() {
		return merchantToken;
	}

	protected void refreshUI(RE_getMerchantDishesList result) {
		if(result != null && result.isSuccess())
		{
			if(result.getResultList() != null)
			{
				sortCollectionList(result.getResultList());
			}
		}else
		{
			tvBottomLeft.setText(getString(R.string.format_total_money, FragmentMeals.this.totalPrice +""));
			tvCount.setText("0");
		}
	}
	
	/**
	 * 排序收藏列表
	 * 
	 * @param arrFoodItems
	 */
	private void sortCollectionList(List<FoodItem> arrFoodItems) {
		this.arrFoodItems = arrFoodItems;
		arrGroupTitles.clear();
		map.clear();
		for (int i = 0; i < arrFoodItems.size(); i++) {
			if (i == 0) {
				List<FoodItem> list = new ArrayList<FoodItem>();
				list.add(arrFoodItems.get(i));
				map.put(arrFoodItems.get(i).getFoodType(), list);
				arrGroupTitles.add(arrFoodItems.get(i).getFoodType());

			} else {
				List<FoodItem> list = null;
				list = map.get(arrFoodItems.get(i).getFoodType());
				if (list != null) {
					list.add(arrFoodItems.get(i));
				} else {
					list = new ArrayList<FoodItem>();
					list.add(arrFoodItems.get(i));
					map.put(arrFoodItems.get(i).getFoodType(), list);
					arrGroupTitles.add(arrFoodItems.get(i).getFoodType());
				}
			}
		}
		String alphas[] = new String[arrGroupTitles.size()];
		arrGroupTitles.toArray(alphas);
//		Arrays.sort(arrTimes.toArray(alphas), String.CASE_INSENSITIVE_ORDER);
		arrGroupTitles.clear();
		arrGroupTitles.addAll(Arrays.asList(alphas));

		arrFoodGroups.clear();
		for (int i = 0; i < alphas.length; i++) {
			FoodGroup group = new FoodGroup();
			group.setDishesGroupName(arrGroupTitles.get(i));
			group.setArrFoodItems(map.get(arrGroupTitles.get(i)));
			arrFoodGroups.add(group);
		}
		adapter.notifyDataSetChanged();
//		expandListView();
		
		view_stickyList.setArrGroups(arrFoodGroups);
		adapter.notifyDataSetChanged();
		categoryAdapter.notifyDataSetChanged();
		

	}
	
//	public void createList() {
//		arrFoodGroups.clear();
//		for (int i = 0; i < 10; i++) {
//			FoodGroup group = new FoodGroup();
//			group.setDishesGroupName("Group " + i);
//			List<FoodItem> arrItems = new ArrayList<FoodItem>();
//			for (int j = 0; j < 5; j++) {
//				FoodItem item = new FoodItem();
//				item.setDishesGroupName("Group " + i);
//				item.setDishesName("Item " + j);
//				arrItems.add(item);
//			}
//			group.setArrFoodItems(arrItems);
//			arrFoodGroups.add(group);
//		}
//	}

	private boolean isOpenning = true;
	@Override
	public void setMerchantsDetailsInfo(RE_getMerchantsDetailsInfo result) {
		if(result != null)
		{
			this.getMerchantsDetailsInfo = result;
			tv_tips.setText(result.getTips());
			isOpenning = result.isOpenning();
			adapter.setOpenTime(isOpenning);
			if(!isOpenning)
			{
				tvBottomRight.setVisibility(View.GONE);
				llShopCar.setVisibility(View.GONE);
				
				tvBottomLeft.setText(R.string.txt_merchant_sleep);
			}else
			{
				tvBottomRight.setVisibility(View.VISIBLE);
				llShopCar.setVisibility(View.VISIBLE);
				
				tvBottomLeft.setText(getString(R.string.format_total_money, FragmentMeals.this.totalPrice +""));
			}
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK)
		{
			if(data != null && data.getExtras() != null)
			{
				ChildFoodItem childFoodItem = (ChildFoodItem) data.getExtras().getSerializable(ChildFoodItem.TAG);
			}
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Task_Post.clearTask(task_Post_getMerchantDishesList);
	}

	
	public static FragmentMeals newInstance(String merchantToken) {
		FragmentMeals fragmentMeals = new FragmentMeals();
		Bundle bundle = new Bundle();
		bundle.putString(FoodListActivity.MERCHANT_TOKEN, merchantToken);
		fragmentMeals.setArguments(bundle);
		return fragmentMeals;
	}

}
