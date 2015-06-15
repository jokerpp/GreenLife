package cn.crane.application.greenlife.ui;

import java.util.ArrayList;

import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.view.BottomBar;
import cn.crane.framework.activity.BaseActivity;
import cn.crane.framework.adapter.FragmentViewpaperAdapter;
import cn.crane.framework.fragment.BaseFragment;
import android.support.v4.view.ViewPager;
import android.content.Context;

/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date Jun 8, 2015
 */
public class MainActivity extends BaseActivity {
	private FragmentViewpaperAdapter mAdapter;
	private ViewPager mPager;

	private FragmentIndex index = new FragmentIndex();
	private FragmentOrderList index2 = new FragmentOrderList();
	private FragmentMy index3 = new FragmentMy();
	private ArrayList<BaseFragment> pagerItemList = new ArrayList<BaseFragment>();

	private BottomBar bottomBar;


	@Override
	protected int getLayoutId() {
		return R.layout.ac_main;
	}


	@Override
	protected void findViews() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		bottomBar = (BottomBar) findViewById(R.id.bottombar);
	}


	@Override
	protected void bindViews() {
		bottomBar.setOnItemSelectedListener(onItemSelectedListener);		
	}


	@Override
	protected void init() {
		pagerItemList.clear();
		pagerItemList.add(index);
		pagerItemList.add(index2);
		pagerItemList.add(index3);
		mAdapter = new FragmentViewpaperAdapter(getSupportFragmentManager(),
				pagerItemList);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				bottomBar.switchItem(position);
				switch (position) {
				case BottomBar.TAG_1:
					break;

				default:
					break;
				}
				// if (myPageChangeListener != null)
				// myPageChangeListener.onPageSelected(position);
				// switch (position) {
				// case 0:
				// if (radioClassCircle != null
				// && !radioClassCircle.isChecked()) {
				// radioClassCircle.setChecked(true);
				// }
				// break;
				// case 1:
				// if (radioClassTrack != null && !radioClassTrack.isChecked())
				// {
				// radioClassTrack.setChecked(true);
				// }
				// break;
				//
				// default:
				// break;
				// }
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		});		
	}
	
	private BottomBar.OnItemSelectedListener onItemSelectedListener = new BottomBar.OnItemSelectedListener() {

		@Override
		public void onItemSelected(int tag) {
			mPager.setCurrentItem(tag);
		}
	};

	public static void show(Context context) {
		context.startActivity(createIntent(context, MainActivity.class));
	}

}
