package com.mary.fragmenttabhostdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * File Name:
 * Author:      Mary
 * Write Dates: 16/8/2
 * Description:
 * Change log:
 * 16/8/2-18-56---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mLists;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mLists = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }
}
