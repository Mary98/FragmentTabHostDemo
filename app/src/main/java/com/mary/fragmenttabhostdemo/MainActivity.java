package com.mary.fragmenttabhostdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.mary.fragmenttabhostdemo.adapter.FragmentAdapter;
import com.mary.fragmenttabhostdemo.fragment.Fragment01;
import com.mary.fragmenttabhostdemo.fragment.Fragment02;
import com.mary.fragmenttabhostdemo.fragment.Fragment03;
import com.mary.fragmenttabhostdemo.fragment.Fragment04;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private Class[] fragmentArray = {Fragment01.class, Fragment02.class, Fragment03.class, Fragment04.class};
    private int[] imageArray = {R.drawable.tab_home_btn, R.drawable.tab_view_btn, R.drawable.tab_home_btn, R.drawable.tab_view_btn};
    private String[] textArray = {"首页", "通讯录", "发现", "我的"};
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initViews();
        initPages();
    }

    private void initViews() {
        mViewPager = (ViewPager) this.findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(this);
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) this.findViewById(R.id.tabhost);
        //绑定viewpager
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        mTabHost.setOnTabChangedListener(this);

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < textArray.length; i++) {
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(textArray[i]);
            tabSpec.setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);//设置Tab被选中的时候颜色改变
        }

    }

    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        View view = mInflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageArray[i]);
        mTextView.setText(textArray[i]);
        return view;
    }

    private void initPages() {
        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();
        Fragment04 fragment04 = new Fragment04();
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
        list.add(fragment04);

        //绑定Fragment适配器
        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        mTabHost.getTabWidget().setDividerDrawable(null);
    }

    /** 防止EventBus报错 */
    @Subscribe
    public void notDispatchEventBus(Object obj) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * position是表示你当前选中的页面位置Postion，这事件是在你页面跳转完毕的时候调用的。
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点
        mTabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Tab改变的时候调用
     * @param tabId
     */
    @Override
    public void onTabChanged(String tabId) {
        //把选中的Tab的位置赋给适配器，让它控制页面切换
        int position = mTabHost.getCurrentTab();
        mViewPager.setCurrentItem(position);
    }
}
