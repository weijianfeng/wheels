package com.wjf.multifragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by weijianfeng on 2017/5/12.
 */

public class SecondActivity extends AppCompatActivity {

    private String mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("msg")) {
                mMessage = intent.getStringExtra("msg");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", mMessage);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
