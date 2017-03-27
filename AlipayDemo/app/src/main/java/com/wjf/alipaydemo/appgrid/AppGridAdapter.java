package com.wjf.alipaydemo.appgrid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjf.alipaydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijianfeng on 2017/3/27.
 */

public class AppGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //0代表头，1代表表格内容
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    private List<String> appList = new ArrayList<>();

    public AppGridAdapter(List<String> appList) {
        this.appList = appList;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_app_gridview_header, parent, false));
        } else if (viewType == ITEM_VIEW_TYPE_ITEM) {
            return new ItemViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_app_gridview, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)) {
            ((HeaderViewHolder)holder).getTextView().setText("Header");
        } else {
            ((ItemViewHolder)holder).getTextView().setText(appList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return appList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_header);
        }
        public TextView getTextView() {
            return textView;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
        public TextView getTextView() {
            return textView;
        }
    }
}
