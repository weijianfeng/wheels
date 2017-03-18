package com.wjf.wrouter.template;

import android.app.Activity;

import java.util.Map;

/**
 * Created by weijianfeng on 2017/3/18.
 */

public interface IRouter {

    void loadInto(Map<String, Class<? extends Activity>> routes);
}
