package com.wjf.wrouter;

import android.app.Activity;
import android.util.Log;

import com.wjf.wrouter.template.IRouter;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Created by weijianfeng on 2017/3/19.
 */

public class WRouter {

    private WRouter() {

    }

    public static void init() {

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
}
