package com.mary.fragmenttabhostdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mary.fragmenttabhostdemo.R;

/**
 * File Name:
 * Author:      Mary
 * Write Dates: 16/8/2
 * Description:
 * Change log:
 * 16/8/2-18-50---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class Fragment02 extends Fragment {

    private View mRootView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_item02, container, false);
        return mRootView;
    }

}
