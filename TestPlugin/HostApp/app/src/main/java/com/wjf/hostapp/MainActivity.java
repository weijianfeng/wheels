package com.wjf.hostapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.morgoo.droidplugin.pm.PluginManager;

public class MainActivity extends AppCompatActivity {

    private Button bt_plugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_plugin = (Button)findViewById(R.id.button);

        final String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pluginapp.apk";
        Log.i("WJFPlugin", "filepath " + filepath);
        PluginManager.getInstance().init(getApplication());
        PluginManager.getInstance().addServiceConnection(new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                int result = 0;
                try {
                    result = PluginManager.getInstance().installPackage(filepath, 0);
                } catch (RemoteException e) {
                    Log.i("WJFPlugin", "result " + e.getMessage());
                    e.printStackTrace();
                }
                Log.i("WJFPlugin", "result " + result);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        });

        bt_plugin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PluginManager.getInstance().isConnected()) {
                    Intent i = new Intent("com.wjf.plugin.action.main");
                    startActivity(i);
                }
            }
        });
    }
}
