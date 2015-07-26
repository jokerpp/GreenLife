package cn.crane.application.greenlife.ui.myaccount.collect;

import java.util.ArrayList;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.view.BottomBar;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.adapter.CommonAdapter;
import cn.crane.framework.adapter.FragmentViewpaperAdapter;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：Jun 3, 2015 10:04:11 PM
 * 
 */
public class MyCollectAtivity extends BaseActivity {



	private FragmentViewpaperAdapter mAdapter;
	private ViewPager mPager;

	private FragmentCollectMerchant index = new FragmentCollectMerchant();
	private FragmentCollectDish index2 = new FragmentCollectDish();
	private ArrayList<BaseFragment> pagerItemList = new ArrayList<BaseFragment>();
	
	
	private Button btnBack;
	private Button btnRight;
	private TextView tvTitle;
	private TextView tvCollectMerchant;
	private TextView tvCollectFood;
	private View view_select;
	private TextView[] tvMenus;
	private int iCurrentPos;
	

	@Override
	protected int getLayoutId() {
		return R.layout.ac_my_collect;
	}

	@Override
	protected void findViews() {
		btnBack = (Button) findViewById(R.id.btn_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		
		mPager = (ViewPager) findViewById(R.id.viewpager);
		btnRight = (Button) findViewById(R.id.btn_right);
		tvCollectMerchant = (TextView) findViewById(R.id.tv_collect_merchant);
		tvCollectFood = (TextView) findViewById(R.id.tv_collect_food);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		view_select = findViewById(R.id.view_select);
		
		tvMenus = new TextView[]{tvCollectMerchant,tvCollectFood};
		
		mPager.setOffscreenPageLimit(5);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		tvCollectMerchant.setOnClickListener(this);
		tvCollectFood.setOnClickListener(this);
	}

	@Override
	protected void init() {
		pagerItemList.clear();
		pagerItemList.add(index);
		pagerItemList.add(index2);
		mAdapter = new FragmentViewpaperAdapter(getSupportFragmentManager(),
				pagerItemList);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case BottomBar.TAG_1:
					break;

				default:
					break;
				}
				setiCurrentPos(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				int x= 0,y =0;
				int [] pos = new int[]{x,y};
				tvMenus[arg0].getLocationOnScreen(pos);
				ViewHelper.setTranslationX(view_select, pos[0] + tvMenus[arg0].getWidth() * arg1);
			}

			@Override
			public void onPageScrollStateChanged(int position) {
			}
		});
		
		setiCurrentPos(0);
	}
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus)
		{
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(tvCollectMerchant.getWidth(), (int) (2 * App.fDensity));
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			view_select.setLayoutParams(layoutParams);
		}
	}
	
	public void setiCurrentPos(int iCurrentPos) {
		this.iCurrentPos = iCurrentPos;
		for(int i = 0;i< tvMenus.length;i++)
		{
			if(i == iCurrentPos)
			{
				tvMenus[i].setTextColor(getResources().getColor(R.color.main_color));
			}else
			{
				tvMenus[i].setTextColor(getResources().getColor(R.color.txt_gray));
			}
		}
		if(iCurrentPos >= 0 && tvMenus != null && iCurrentPos < tvMenus.length)
		{
			int x= 0,y =0;
			int [] pos = new int[]{x,y};
			tvMenus[iCurrentPos].getLocationOnScreen(pos);
			ViewHelper.setTranslationX(view_select, pos[0]);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_collect_merchant:
			mPager.setCurrentItem(0);
			break;
		case R.id.tv_collect_food:
			mPager.setCurrentItem(1);
			break;
		case R.id.btn_right:
			break;
		default:
			break;
		}
	}



	public static void show(Context context) {
		context.startActivity(createIntent(context, MyCollectAtivity.class));
	}


}
