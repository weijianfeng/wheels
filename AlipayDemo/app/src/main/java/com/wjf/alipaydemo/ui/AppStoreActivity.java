package com.wjf.alipaydemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.wjf.alipaydemo.Constants;
import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.widget.appgrid.AppGridAdapter;
import com.wjf.alipaydemo.widget.appgrid.EditAppGridAdapter;
import com.wjf.alipaydemo.widget.appgrid.listener.OnAppInfoChangeListener;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.recyclerhelper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/27.
 */

public class AppStoreActivity extends Activity
        implements OnAppInfoChangeListener{

    private RecyclerView mAppRecyclerView;
    private RecyclerView mRecommandAppRecyclerView;
    private RecyclerView mThirdPartyAppRecyclerView;

    private AppGridAdapter mAppGridAdapter;
    private EditAppGridAdapter mRecommandEditAppGridAdapter;
    private EditAppGridAdapter mThirdPartyEditAppGridAdapter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstore);

        mAppRecyclerView = (RecyclerView)findViewById(R.id.my_app);
        mRecommandAppRecyclerView = (RecyclerView)findViewById(R.id.recommend_app);
        mThirdPartyAppRecyclerView = (RecyclerView)findViewById(R.id.thirdparty_app);

        List<AppInfo> appList1 = new ArrayList<>();
        appList1.add(new AppInfo("Luckey Money",R.mipmap.lucky_money, Constants.AppStatus.APP_ADDED));
        appList1.add(new AppInfo("Transfer", R.mipmap.transfer, Constants.AppStatus.APP_ADDED));
        appList1.add(new AppInfo("Card Repay",R.mipmap.card_repay, Constants.AppStatus.APP_ADDED));
        appList1.add(new AppInfo("Zhima Credit", R.mipmap.zhima_credit, Constants.AppStatus.APP_ADDED));

        List<AppInfo> appList2 = new ArrayList<>();
        appList2.add(new AppInfo("Luckey Money",R.mipmap.lucky_money, Constants.AppStatus.APP_ADDED));
        appList2.add(new AppInfo("Zhima Credit", R.mipmap.zhima_credit, Constants.AppStatus.APP_ADDED));
        appList2.add(new AppInfo("Ant Fonture",R.mipmap.ant_forture, Constants.AppStatus.APP_NOT_ADDED));
        appList2.add(new AppInfo("City Service",R.mipmap.city_service, Constants.AppStatus.APP_NOT_ADDED));

        List<AppInfo> appList3 = new ArrayList<>();
        appList3.add(new AppInfo("Transfer", R.mipmap.transfer, Constants.AppStatus.APP_ADDED));
        appList3.add(new AppInfo("Card Repay",R.mipmap.card_repay, Constants.AppStatus.APP_ADDED));
        appList3.add(new AppInfo("Top Up", R.mipmap.top_up, Constants.AppStatus.APP_NOT_ADDED));
        appList3.add(new AppInfo("Credit Pay", R.mipmap.credit_pay, Constants.AppStatus.APP_NOT_ADDED));

        final GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 4);

        final GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 4);

        final GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this, 4);

        mAppGridAdapter = new AppGridAdapter(this, appList1);
        mAppRecyclerView.setAdapter(mAppGridAdapter);
        mAppRecyclerView.setLayoutManager(gridLayoutManager1);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAppGridAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mAppRecyclerView);

        mRecommandEditAppGridAdapter = new EditAppGridAdapter(this,this,appList2);
        mRecommandAppRecyclerView.setAdapter(mRecommandEditAppGridAdapter);
        mRecommandAppRecyclerView.setLayoutManager(gridLayoutManager2);


        mThirdPartyEditAppGridAdapter = new EditAppGridAdapter(this,this,appList3);
        mThirdPartyAppRecyclerView.setAdapter(mThirdPartyEditAppGridAdapter);
        mThirdPartyAppRecyclerView.setLayoutManager(gridLayoutManager3);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void insert(AppInfo appInfo) {
        mAppGridAdapter.addAppInfo(appInfo);
    }

    @Override
    public void delete(AppInfo appInfo) {
        mRecommandEditAppGridAdapter.handleDeleteApp(appInfo);
        mThirdPartyEditAppGridAdapter.handleDeleteApp(appInfo);
    }

    @Override
    public void isEditStatus(boolean status) {
        mAppGridAdapter.setEditStatus(true);
        mRecommandEditAppGridAdapter.setEditStatus(true);
        mThirdPartyEditAppGridAdapter.setEditStatus(true);
    }
}
