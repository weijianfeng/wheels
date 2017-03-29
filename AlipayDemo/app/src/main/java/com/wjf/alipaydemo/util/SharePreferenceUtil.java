package com.wjf.alipaydemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.wjf.alipaydemo.entity.AppInfo;

import java.util.List;

/**
 * Created by weijianfeng on 2017/3/29.
 */

public class SharePreferenceUtil {

    private static final String APP_LIST = "appList";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setAppList(List<AppInfo> list) {
        String json = new Gson().toJson(list);
        editor.putString(APP_LIST, json);
        editor.commit();
    }

    public void addAppInfo(AppInfo appInfo) {
        List<AppInfo> list = getAppList();
        if (list == null) {
            return;
        }
        list.add(appInfo);
        clearAppList();
        setAppList(list);
    }

    public List<AppInfo> getAppList() {
        String str = sp.getString(APP_LIST, "");
        if (str == null) {
            return null;
        }

        List<AppInfo> list = null;
        try {
            list = new Gson().fromJson(str, new TypeToken<List<AppInfo>>(){}.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    public void clearAppList() {
        editor.clear();
        editor.commit();
    }
}
