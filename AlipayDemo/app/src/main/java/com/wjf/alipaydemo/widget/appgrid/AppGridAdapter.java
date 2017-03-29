package com.wjf.alipaydemo.widget.appgrid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.widget.appgrid.listener.OnAppInfoChangeListener;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.recyclerhelper.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/27.
 */

public class AppGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperAdapter {

    private List<AppInfo> appList = new ArrayList<>();
    private List<AppInfo> originAppList = new ArrayList<>();

    private OnAppInfoChangeListener mOnAppInfoChangeListener;

    private boolean isEditStatus = false;

    public AppGridAdapter(OnAppInfoChangeListener onAppInfoChangeListener, List<AppInfo> appList) {
        mOnAppInfoChangeListener = onAppInfoChangeListener;
        this.appList = appList;

        originAppList.addAll(appList);
    }

    public void addAppInfo(AppInfo appInfo) {
        appList.add(appInfo);
        notifyDataSetChanged();
    }

    public void resetList() {
        appList.clear();
        appList.addAll(originAppList);
        notifyDataSetChanged();
    }

    public List<AppInfo> getAppList() {
        return appList;
    }

    public void setEditStatus(boolean status) {
        isEditStatus = status;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_app_gridview, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder)holder).textView.setText(appList.get(position).appName);
        ((ItemViewHolder)holder).imageView.setBackgroundResource(appList.get(position).appIcon);

        if (isEditStatus) {
            ((ItemViewHolder)holder).bagdeView.setVisibility(View.VISIBLE);
            ((ItemViewHolder)holder).linearLayout.setBackgroundResource(R.drawable.border_appitem_select);
        } else {
            ((ItemViewHolder)holder).bagdeView.setVisibility(View.INVISIBLE);
            ((ItemViewHolder)holder).linearLayout.setBackgroundResource(R.drawable.border_appitem_not_select);
        }

        ((ItemViewHolder)holder).bagdeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAppInfoChangeListener.delete(appList.get(position));
                onItemDismiss(position);
                notifyDataSetChanged();
            }
        });

        // Start a drag whenever the handle view it touched
//        ((ItemViewHolder)holder).textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
//                    mDragStartListener.onStartDrag(holder);
//                }
//                return false;
//            }
//        });

        ((ItemViewHolder)holder).linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEditStatus) {
                    notifyDataSetChanged();
                    mOnAppInfoChangeListener.setEditStatus(true);
                    return false;
                }
                mOnAppInfoChangeListener.onStartDrag(holder);
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        appList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(appList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
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
