package com.wjf.alipaydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjf.alipaydemo.appgrid.AppGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/27.
 */

public class AppStoreActivity extends Activity {

    private RecyclerView mRecyclerView;
    private AppGridAdapter mAppGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstore);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_app);

        List<String> appList = new ArrayList<>();
        appList.add("第1个");
        appList.add("第2个");
        appList.add("第3个");
        appList.add("第4个");
        appList.add("第5个");
        appList.add("第6个");
        appList.add("第7个");
        appList.add("第8个");
        appList.add("第9个");

        mAppGridAdapter = new AppGridAdapter(appList);
        mRecyclerView.setAdapter(mAppGridAdapter);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAppGridAdapter.isHeader(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });

        mRecyclerView.setLayoutManager(gridLayoutManager);

    }
}
