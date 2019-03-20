package com.example.sojournerapplication;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.sojournerapplication.com.edu.cqjtu.bean.Tab;
import com.example.sojournerapplication.com.edu.cqjtu.fragment.FriendsFragment;
import com.example.sojournerapplication.com.edu.cqjtu.fragment.HomeFragment;
import com.example.sojournerapplication.com.edu.cqjtu.fragment.NewsFragment;
import com.example.sojournerapplication.com.edu.cqjtu.fragment.RecordFragment;
import com.example.sojournerapplication.com.edu.cqjtu.fragment.UserFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private ArrayList<Tab> mTabs= new ArrayList<Tab>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initTab();
    }
    private void initTab() {
        //实例化5个Tab类的对象
        Tab Tab_home = new Tab(R.drawable.selector_home, R.string.home, HomeFragment.class);
        Tab Tab_news = new Tab(R.drawable.selector_news, R.string.news, NewsFragment.class);
        Tab Tab_friends = new Tab(R.drawable.selector_friends, R.string.friends, FriendsFragment.class);
        Tab Tab_record = new Tab(R.drawable.selector_record, R.string.record, RecordFragment.class);
        Tab Tab_user = new Tab(R.drawable.selector_user, R.string.user, UserFragment.class);

        //将这5个对象加到一个List中
        mTabs.add(Tab_home);
        mTabs.add(Tab_news);
        mTabs.add(Tab_friends);
        mTabs.add(Tab_record);
        mTabs.add(Tab_user);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);
        mInflater = LayoutInflater.from(this);

        //通过循环实例化一个个TabSpec
        //并调用其中setIndicator方法
        //然后将TabSpec加到TabHost中
        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(tab.getText()));
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }
        //通过这行代码可以去除掉底部菜单5个图表之间的分割线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

        //设置Indicator中的View

    private View buildView(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView Tab_img = (ImageView) view.findViewById(R.id.tab_indicator_icon);
        TextView Tab_txt = (TextView) view.findViewById(R.id.tab_indicator_hint);

        Tab_img.setBackgroundResource(tab.getImage());
        Tab_txt.setText(tab.getText());
        return view;
    }
}
