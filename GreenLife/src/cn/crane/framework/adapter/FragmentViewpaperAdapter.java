package cn.crane.framework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import cn.crane.framework.fragment.BaseFragment;

public class FragmentViewpaperAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> pagerItemList = new ArrayList<BaseFragment>();

    public FragmentViewpaperAdapter(FragmentManager fm, ArrayList<BaseFragment> pagerItemList) {
        super(fm);
        this.pagerItemList = pagerItemList;
    }

    @Override
    public int getCount() {
        return pagerItemList.size();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position < pagerItemList.size())
            fragment = pagerItemList.get(position);
        else
            fragment = pagerItemList.get(0);
        return fragment;

    }

}
