package cn.crane.application.greenlife.ui.order;

import java.util.ArrayList;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.adapter.FragmentViewpaperAdapter;
import cn.crane.framework.fragment.BaseFragment;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 29, 2015 11:18:18 PM
 * 
 */
public class OrderDetailActivity extends BaseActivity {

	private FragmentViewpaperAdapter mAdapter;
	private ViewPager mPager;

	private FragmentOrderState index = new FragmentOrderState();
	private FragmentOrderDetail index2 = new FragmentOrderDetail();
	private ArrayList<BaseFragment> pagerItemList = new ArrayList<BaseFragment>();
	
	
	private Button btnBack;
	private Button btnRight;
	private TextView tvTitle;
	private TextView tvOrderState;
	private TextView tvOrderDetail;
	private View view_select;
	private LinearLayout ll_tvmenus;
	
	private int iCurrentPos = 0;
	private TextView [] tvMenus;
//	private ArrayList<MenuItem> arrMenuItems = new ArrayList<MenuItem>();
//	private PopMenu popMenu;

	@Override
	protected int getLayoutId() {
		return R.layout.ac_order_detail;
	}

	@Override
	protected void findViews() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		btnBack = (Button) findViewById(R.id.btn_back);
		btnRight = (Button) findViewById(R.id.btn_right);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvOrderState = (TextView) findViewById(R.id.tv_order_state);
		tvOrderDetail = (TextView) findViewById(R.id.tv_order_detail);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		view_select = findViewById(R.id.view_select);
		ll_tvmenus = (LinearLayout) findViewById(R.id.ll_tvmenus);
		
		tvMenus = new TextView[]{tvOrderState,tvOrderDetail};
		
		mPager.setOffscreenPageLimit(5);
	}

	@Override
	protected void bindViews() {
		btnBack.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		tvOrderState.setOnClickListener(this);
		tvOrderDetail.setOnClickListener(this);
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
//		initMenu();
	}
	
//	private void initMenu() {
//		arrMenuItems.clear();
//		MenuItem item = null;
//		item = new MenuItem();
//		item.setTag(MenuItem.TAG_MENU_MERCHANT_LIANXI);
//		item.setTitle(getString(R.string.order_contact_merchant));
//		item.setIconRes(R.drawable.icon_order_detail_tel);
//		arrMenuItems .add(item);
//		item = new MenuItem();
//		item.setTag(MenuItem.TAG_MENU_MERCHANT_TOUSU);
//		item.setTitle(getString(R.string.order_tousu_merchant));
//		item.setIconRes(R.drawable.icon_order_detail_msg);
//		item.setBnew(false);
//		arrMenuItems.add(item);
//		popMenu = new PopMenu(this, arrMenuItems, onMenuClickListener,
//				PopMenu.width1);
//	}
//	
//	/**
//	 * 菜单点击回调
//	 */
//	private OnMenuClickListener onMenuClickListener = new OnMenuClickListener() {
//
//		@Override
//		public void onMenuClick(MenuItem item) {
//			if (item != null) {
//				switch (item.getTag()) {
//				case MenuItem.TAG_MENU_MERCHANT_LIANXI:
//					MerchantFeedbackActivity.show(OrderDetailActivity.this);
//					break;
//				case MenuItem.TAG_MENU_MERCHANT_TOUSU:
//					MerchantFeedbackActivity.show(OrderDetailActivity.this);
//					break;
//
//				default:
//					break;
//				}
//				if (popMenu != null && popMenu.isShowing())
//					popMenu.dismiss();
//			}
//		}
//	};

	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus)
		{
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(tvOrderState.getWidth(), (int) (2 * App.fDensity));
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
		case R.id.tv_order_state:
			mPager.setCurrentItem(0);
			break;
		case R.id.tv_order_detail:
			mPager.setCurrentItem(1);
			break;
		case R.id.btn_right:
//			popMenu.showAsDropDown(btnRight);
			break;
		default:
			break;
		}
	}
	
	
	
	@Override
	protected boolean isSupportSwiptBack() {
		return false;
	}
	
	public static void show(Context context) {
		context.startActivity(createIntent(context, OrderDetailActivity.class));
	}
}
