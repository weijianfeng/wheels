package com.wjf.wrouter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wjf.wrouter.template.IRouter;

import java.lang.reflect.Constructor;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by weijianfeng on 2017/3/19.
 */

public class WRouter {

    private static Context mContext;

    private volatile static WRouter instance = null;

    private WRouter() {
    }

    public static void init(Application application) {
        mContext = application;
        try {
            Constructor<?> constructor =  Class.forName("com.router.test.RouterMap").getConstructor();
            IRouter iRouter = (IRouter) constructor.newInstance();
            iRouter.loadInto(RouteCache.localRouteMap);

        } catch (Exception e) {
            Log.e("WRouter", "generate localRouteMap error " + e.toString());
        }

        for(Map.Entry<String, Class<? extends Activity>> entry : RouteCache.localRouteMap.entrySet()) {
            Log.i("WRouter", "routet path " + entry.getKey()
                    + "  destination class " + entry.getValue());
        }
    }

    public static WRouter getInstance() {
        if (instance == null) {
            synchronized (WRouter.class) {
                if (instance == null) {
                    instance = new WRouter();
                }
            }
        }
            return instance;
    }

    public Postcard build(String path) {
        return new Postcard(path);
    }

    public void navigation(Postcard postcard) {
        Intent intent = new Intent(mContext, postcard.getDestination());
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}
