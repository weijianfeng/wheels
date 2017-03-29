package com.wjf.alipaydemo.widget.appgrid.listener;

import com.wjf.alipaydemo.entity.AppInfo;
import com.wjf.alipaydemo.recyclerhelper.OnStartDragListener;

/**
 * Created by weijianfeng on 2017/3/29.
 */

public interface OnAppInfoChangeListener extends OnStartDragListener{

    void insert(AppInfo appInfo);

    void delete(AppInfo appInfo);

    void isEditStatus(boolean status);
}
