package com.wjf.alipaydemo.entity;

/**
 * Created by weijianfeng on 2017/3/29.
 */

public class AppInfo {

    public String appName;
    public int appIcon;   // 从本地资源文件读取的
    public int status;    // 在 app 应用中心中的状态，已添加，未添加。

    public AppInfo(String appName, int appIcon, int status) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.status = status;
    }
}
