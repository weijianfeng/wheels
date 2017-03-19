package com.wjf.wrouter;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weijianfeng on 2017/3/19.
 */

public class RouteCache {

    static Map<String, Class<? extends Activity>> localRouteMap = new HashMap<>();
}
