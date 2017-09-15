package com.wjf.alipaydemo.ui;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wjf.alipaydemo.Constants;
import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.util.SharePreferenceUtil;
import com.wjf.alipaydemo.widget.NestedListView;
import com.wjf.alipaydemo.widget.WrapHeightGridLayoutManager;
import com.wjf.alipaydemo.widget.maingrid.MainGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private RelativeLayout mToolBarLayout;
    private NestedScrollView mNestedScrollView;

    private RecyclerView mRecyclerView;
    private NestedListView mListView;
    private MainGridAdapter mMainGridAdapter;

    private SharePreferenceUtil sharePreferenceUtil;
    private CollapsingToolbarLayoutState state;


    private static final String[] strs = new String[] {
        "1", "2", "3", "4", "5","6", "7", "8", "9", "10","11", "12", "13", "14", "15","16", "17", "18", "19", "20"
    };

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolBarLayout = (RelativeLayout) findViewById(R.id.rl_toorbar_content);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.scroll_content);

//        sharePreferenceUtil = new SharePreferenceUtil(this.getApplicationContext(), "appInfo");
//
//        List<AppInfo> appList = sharePreferenceUtil.getAppList();
//        if (appList == null) {
//            // 模拟获取数据
//            appList = new ArrayList<>();
//            appList.add(new AppInfo("Luckey Money", R.mipmap.lucky_money, Constants.AppStatus.APP_ADDED));
//            appList.add(new AppInfo("Transfer", R.mipmap.transfer, Constants.AppStatus.APP_ADDED));
////            appList.add(new AppInfo("Card Repay", R.mipmap.card_repay, Constants.AppStatus.APP_ADDED));
////            appList.add(new AppInfo("Zhima Credit", R.mipmap.zhima_credit, Constants.AppStatus.APP_ADDED));
//            sharePreferenceUtil.setAppList(appList);
//        }
//
//        if (appList.size() > 7) {
//            appList = appList.subList(0, 7);
//        }
//
//        appList.add(new AppInfo("All", R.mipmap.all_apps, Constants.AppStatus.APP_ADDED));


//        mRecyclerView = (RecyclerView) findViewById(R.id.main_app);
//        mMainGridAdapter = new MainGridAdapter(this, appList);
//        mRecyclerView.setAdapter(mMainGridAdapter);
//
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 4);
//        mGridLayoutManager.setSmoothScrollbarEnabled(true);
//        mGridLayoutManager.setAutoMeasureEnabled(true);
//
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
//        mRecyclerView.setNestedScrollingEnabled(false);

        mListView = (NestedListView) findViewById(R.id.lv_app);
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mToolBarLayout.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠

                        mNestedScrollView.setNestedScrollingEnabled(false);
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                            mToolBarLayout.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (! mNestedScrollView.isNestedScrollingEnabled()) {
            mNestedScrollView.setNestedScrollingEnabled(true);
            mAppBarLayout.setExpanded(true);
            mNestedScrollView.smoothScrollTo(0,0);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        List<AppInfo> appList = sharePreferenceUtil.getAppList();
//
//        if (appList == null) {
//            appList = new ArrayList<>();
//        }
//
//        if (appList.size() > 7) {
//            appList = appList.subList(0, 7);
//        }
//
//        appList.add(new AppInfo("All", R.mipmap.all_apps, Constants.AppStatus.APP_ADDED));
//        mMainGridAdapter.refreshData(appList);
    }
}
