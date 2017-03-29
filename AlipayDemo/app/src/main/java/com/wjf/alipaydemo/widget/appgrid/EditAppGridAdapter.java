package com.wjf.alipaydemo.widget.appgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjf.alipaydemo.Constants;
import com.wjf.alipaydemo.R;
import com.wjf.alipaydemo.util.SharePreferenceUtil;
import com.wjf.alipaydemo.widget.appgrid.listener.OnAppInfoChangeListener;
import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.recyclerhelper.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/29.
 */

public class EditAppGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperAdapter {

    private Context context;
    private List<AppInfo> appList = new ArrayList<>();
    private OnAppInfoChangeListener mOnAppInfoChangeListener;

    private boolean isStatusStatus = false;
    private SharePreferenceUtil sharePreferenceUtil;

    public EditAppGridAdapter(Context context, OnAppInfoChangeListener mOnAppInfoChangeListener, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
        this.mOnAppInfoChangeListener = mOnAppInfoChangeListener;

        sharePreferenceUtil = new SharePreferenceUtil(context, "appInfo");
    }

    public void setEditStatus(boolean status) {
        isStatusStatus = status;
        notifyDataSetChanged();
    }

    public void handleDeleteApp(AppInfo appInfo) {
        for (AppInfo info : appList) {
            if (info.appName.equals(appInfo.appName)) {
                info.status = Constants.AppStatus.APP_NOT_ADDED;
                break;
            }
        }

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

        if (isStatusStatus) {
            ((ItemViewHolder)holder).badgeView.setVisibility(View.VISIBLE);
            if ((appList.get(position)).status == Constants.AppStatus.APP_ADDED) {
                ((ItemViewHolder)holder).badgeView.setBackgroundResource(R.mipmap.isadd_icon);
            } else {
                ((ItemViewHolder)holder).badgeView.setBackgroundResource(R.mipmap.add_icon);
            }
            ((ItemViewHolder)holder).linearLayout.setBackgroundResource(R.drawable.border_appitem_select);
        } else {
            ((ItemViewHolder)holder).badgeView.setVisibility(View.INVISIBLE);
        }

        ((ItemViewHolder)holder).badgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((appList.get(position)).status == Constants.AppStatus.APP_NOT_ADDED) {
                    mOnAppInfoChangeListener.insert(appList.get(position));
                    //onItemDismiss(position);
                    appList.get(position).status = Constants.AppStatus.APP_ADDED;
                    notifyDataSetChanged();
                }
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
                if (!isStatusStatus) {
                    notifyDataSetChanged();
                }
                isStatusStatus = true;
                mOnAppInfoChangeListener.isEditStatus(true);
                notifyDataSetChanged();
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
        public ImageView badgeView;
        public ImageView imageView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.app_layout);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
            imageView = (ImageView) itemView.findViewById(R.id.imv_item);
            badgeView = (ImageView) itemView.findViewById(R.id.imv_delete_icon);
        }
    }

    private boolean isAppExisted(AppInfo appInfo) {
        List<AppInfo> list = sharePreferenceUtil.getAppList();
        if (list == null) {
            return false;
        } else {
            for(AppInfo info : list) {
                if (appInfo.appName.equals(info.appName)) {
                    return true;
                }
            }
            return false;
        }
    }
}
