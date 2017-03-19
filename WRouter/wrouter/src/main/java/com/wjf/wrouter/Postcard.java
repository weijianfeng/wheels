package com.wjf.wrouter;

import android.os.Bundle;

/**
 * Created by weijianfeng on 2017/3/19.
 */

public class Postcard {

    private String path;
    private Class<?> destination;
    private Bundle mBundle;
    private int flags = -1;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public void setDestination(Class<?> destination) {
        this.destination = destination;
    }

    public Bundle getmBundle() {
        return mBundle;
    }

    public void setmBundle(Bundle mBundle) {
        this.mBundle = mBundle;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public Postcard(String path) {
        this.path = path;
        this.destination = RouteCache.localRouteMap.get(path);
    }

    public void navigation() {
        WRouter.getInstance().navigation(this);
    }
}
