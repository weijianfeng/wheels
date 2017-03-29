package com.wjf.alipaydemo.widget.maingrid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.ui.AppStoreActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/29.
 */

public class MainGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<AppInfo> appList = new ArrayList<>();

    public MainGridAdapter(Context context, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    public void refreshData(List<AppInfo> appList) {
        this.appList = appList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_app_gridview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder)holder).textView.setText(appList.get(position).appName);
        ((ItemViewHolder)holder).imageView.setBackgroundResource(appList.get(position).appIcon);

        ((ItemViewHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == getItemCount() - 1) {
                    Intent intent = new Intent(context, AppStoreActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ImageView bagdeView;
        public ImageView imageView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.app_layout);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
            imageView = (ImageView) itemView.findViewById(R.id.imv_item);
            bagdeView = (ImageView) itemView.findViewById(R.id.imv_delete_icon);
        }
    }
}
