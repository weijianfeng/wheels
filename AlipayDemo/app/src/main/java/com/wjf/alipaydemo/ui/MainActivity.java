package com.wjf.alipaydemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjf.alipaydemo.Constants;
import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.util.SharePreferenceUtil;
import com.wjf.alipaydemo.widget.maingrid.MainGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainGridAdapter mMainGridAdapter;
    private SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePreferenceUtil = new SharePreferenceUtil(this.getApplicationContext(), "appInfo");

        List<AppInfo> appList = sharePreferenceUtil.getAppList();
        if (appList == null) {
            // 模拟获取数据
            appList = new ArrayList<>();
            appList.add(new AppInfo("Luckey Money",R.mipmap.lucky_money, Constants.AppStatus.APP_ADDED));
            appList.add(new AppInfo("Transfer", R.mipmap.transfer, Constants.AppStatus.APP_ADDED));
            appList.add(new AppInfo("Card Repay",R.mipmap.card_repay, Constants.AppStatus.APP_ADDED));
            appList.add(new AppInfo("Zhima Credit", R.mipmap.zhima_credit, Constants.AppStatus.APP_ADDED));
            sharePreferenceUtil.setAppList(appList);
        }

        if (appList.size() > 7) {
            appList = appList.subList(0, 7);
        }

        appList.add(new AppInfo("All", R.mipmap.all_apps, Constants.AppStatus.APP_ADDED));


        mRecyclerView = (RecyclerView) findViewById(R.id.main_app);
        mMainGridAdapter = new MainGridAdapter(this, appList);
        mRecyclerView.setAdapter(mMainGridAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<AppInfo> appList = sharePreferenceUtil.getAppList();

        if (appList.size() > 7) {
            appList = appList.subList(0, 7);
        }

        appList.add(new AppInfo("All", R.mipmap.all_apps, Constants.AppStatus.APP_ADDED));
        mMainGridAdapter.refreshData(appList);
    }
}
