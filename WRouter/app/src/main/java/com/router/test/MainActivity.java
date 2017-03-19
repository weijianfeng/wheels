package com.router.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.wjf.wrouter.Route;
import com.wjf.wrouter.WRouter;

@Route(path = "/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WRouter.init();
    }
}
