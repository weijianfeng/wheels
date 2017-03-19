package com.router.test;

import android.app.Activity;
import android.os.Bundle;
import com.wjf.wrouter.Route;

/**
 * Created by weijianfeng on 2017/3/19.
 */
@Route(path = "/test")
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
