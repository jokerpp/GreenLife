package cn.crane.application.greenlife.ui;

import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.adapter.index.GridCatedoryAdapter;
import cn.crane.application.greenlife.adapter.index.ListArticleCatedoryAdapter;
import cn.crane.application.greenlife.adapter.index.PictureInfo;
import cn.crane.application.greenlife.api.API_Contant;
import cn.crane.application.greenlife.bean.index.ArticleCategoryItem;
import cn.crane.application.greenlife.bean.index.GridCategoryItem;
import cn.crane.application.greenlife.ui.article.ArticleListActivity;
import cn.crane.application.greenlife.ui.merchant.FoodListActivity;
import cn.crane.application.greenlife.ui.merchant.MerchantListAtivity;
import cn.crane.framework.fragment.BaseFragment;
import cn.crane.framework.view.carouselview.CarouselItemInfo;
import cn.crane.framework.view.carouselview.CarouselView;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 8, 2015 10:45:12 PM
 * 
 */
public class FragmentIndex extends BaseFragment implements OnClickListener, OnItemClickListener {

	private CarouselView carouselView;
	private GridView gvCategory;
	private ListView lvArticle;
	
	private TextView tvShilingzhong;
	private TextView tvYouhui;
	private TextView tvYuding;
	private TextView tvMeishi;
	
	private EditText et_search;
	
	private GridCatedoryAdapter gridCatedoryAdapter;
	private ListArticleCatedoryAdapter articleCatedoryAdapter;
	
	private List<GridCategoryItem> arrGridCategoryItems = new ArrayList<GridCategoryItem>();
	private List<ArticleCategoryItem> arrArticleCategoryItems = new ArrayList<ArticleCategoryItem>();
	
	private List<CarouselItemInfo> arrPictureInfos = new ArrayList<CarouselItemInfo>();
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_index;
	}

	@Override
	protected void findViews() {
		carouselView = (CarouselView) findViewById(R.id.carouseView);
		gvCategory = (GridView) findViewById(R.id.grid_category);
		lvArticle = (ListView) findViewById(R.id.lv);
		tvShilingzhong = (TextView) findViewById(R.id.tv_shilingzhong);
		tvYouhui= (TextView) findViewById(R.id.tv_youhui);
		tvYuding= (TextView) findViewById(R.id.tv_yuding);
		tvMeishi = (TextView) findViewById(R.id.tv_meishi);
		et_search = (EditText) findViewById(R.id.et_search);
	}

	@Override
	protected void bindViews() {
		gvCategory.setOnItemClickListener(this);
		lvArticle.setOnItemClickListener(this);
		
		tvShilingzhong.setOnClickListener(this);
		tvYouhui.setOnClickListener(this);
		tvYuding.setOnClickListener(this);
		tvMeishi.setOnClickListener(this);
		
		carouselView.setOnItemListener(itemListener);
		
		et_search.addTextChangedListener(textWatcher);
		et_search.setOnKeyListener(onKeyListener);
	}
	
	private TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};

	private OnKeyListener onKeyListener = new OnKeyListener() {
		
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_ENTER)
			{
				String key = et_search.getText().toString().trim();
				MerchantListAtivity.show(getActivity(), key);
			}
			return false;
		}
	};
	
	@Override
	protected void init() {
		gridCatedoryAdapter = new GridCatedoryAdapter(getActivity(), arrGridCategoryItems);
		gvCategory.setAdapter(gridCatedoryAdapter);
		
		articleCatedoryAdapter = new ListArticleCatedoryAdapter(getActivity(), arrArticleCategoryItems);
		lvArticle.setAdapter(articleCatedoryAdapter);
		
		loadData();
	}
	
	private void loadData() {
		String [] arrGrids = getResources().getStringArray(R.array.arr_grid_category);
		int [] arrIcons = new int[arrGrids.length];
		arrIcons = new int []{R.drawable.icon_shucai,R.drawable.icon_shuiguo,R.drawable.icon_liangyou
				,R.drawable.icon_seafood,R.drawable.icon_shushi,R.drawable.icon_drink
				,R.drawable.icon_eggs,R.drawable.icon_yewei};
		
		
		arrGridCategoryItems.clear();
		for(int i = 0;i< arrGrids.length;i++)
		{
			GridCategoryItem item = new GridCategoryItem();
			item.setIconResId(arrIcons[i]);
			item.setTxt(arrGrids[i]);
			item.setType(API_Contant.arrMerchantTYpes[i]);
			arrGridCategoryItems.add(item);
		}
		gridCatedoryAdapter.notifyDataSetChanged();
		
		String [] arrArticles = getResources().getStringArray(R.array.arr_article_category);
		int [] colors = new int []{R.color.main_color,R.color.txt_purple,R.color.txt_orange,R.color.txt_blue};
		arrArticleCategoryItems.clear();
		for(int i = 0;i< arrArticles.length;i++)
		{
			ArticleCategoryItem item = new ArticleCategoryItem();
			item.setTxt(arrArticles[i]);
			item.setBgColor(colors[i]);
			item.setType(API_Contant.arrArticleTypes[i]);
			arrArticleCategoryItems.add(item);
		}
		articleCatedoryAdapter.notifyDataSetChanged();
		
		arrPictureInfos.clear();
		for(int i = 0;i<3;i++)
		{
			PictureInfo info = new PictureInfo();
			info.setDefaultImageRes(R.drawable.test_index_image);
			info.setImageUrl("");
			arrPictureInfos.add(info);
		}
		carouselView.setArrPictureInfos(arrPictureInfos);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_shilingzhong:
			MerchantListAtivity.show(getActivity(),API_Contant.MERCHANT_TYPE_SHILINGZHONG);
			break;
		case R.id.tv_youhui:
			MerchantListAtivity.show(getActivity(),API_Contant.MERCHANT_TYPE_YOUHUI);
			break;
		case R.id.tv_yuding:
			MerchantListAtivity.show(getActivity(),API_Contant.MERCHANT_TYPE_YUDING);
			break;
		case R.id.tv_meishi:
			MerchantListAtivity.show(getActivity(),API_Contant.MERCHANT_TYPE_MEISHI);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Object object = parent.getItemAtPosition(position);
		switch (parent.getId()) {
		case R.id.grid_category:
			if(object instanceof GridCategoryItem)
			{
				MerchantListAtivity.show(getActivity(),((GridCategoryItem)object).getType());
			}
			break;
		case R.id.lv:
			if(object instanceof ArticleCategoryItem)
			{
				ArticleListActivity.show(getActivity(),((ArticleCategoryItem)object).getType());
			}
			break;

		default:
			break;
		}
	}

	private CarouselView.OnItemListener itemListener = new CarouselView.OnItemListener() {
		
		@Override
		public void onItemClick(CarouselItemInfo pictureInfo) {
			FoodListActivity.show(getActivity(),"");
		}
		
		@Override
		public void onItemChanged(int position) {
			// TODO Auto-generated method stub
			
		}
	};
}
