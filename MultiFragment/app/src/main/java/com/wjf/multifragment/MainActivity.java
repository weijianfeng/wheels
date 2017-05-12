package com.wjf.multifragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wjf.multifragment.fragment.Fragment1;
import com.wjf.multifragment.fragment.Fragment2;

public class MainActivity extends AppCompatActivity {

    private static final int TAB_COUNT = 2;
    private static final int INDEX_FRAGMENT1 = 0;
    private static final int INDEX_FRAGMENT2 = 1;

    private RelativeLayout mTab1;
    private RelativeLayout mTab2;

    public Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        switchFragment(INDEX_FRAGMENT1);
    }

    private void initView() {
        mTab1 = (RelativeLayout) findViewById(R.id.rl_tab1);
        mTab2 = (RelativeLayout) findViewById(R.id.rl_tab2);
    }

    private void initEvent() {
        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(INDEX_FRAGMENT1);
            }
        });
        mTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(INDEX_FRAGMENT2);
            }
        });
    }

    private Fragment createFragment(int key) {
        Fragment fragment;
        switch (key) {
            case INDEX_FRAGMENT1:
                fragment = Fragment.instantiate(getApplicationContext(), Fragment1.class.getName(), null);
                break;
            case INDEX_FRAGMENT2:
                fragment = Fragment.instantiate(getApplicationContext(), Fragment2.class.getName(), null);
                break;
            default:
                fragment = new Fragment1();
                break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.view_container, fragment, String.valueOf(key));
        ft.commit();
        return fragment;
    }

    protected Fragment getFragment(int key, boolean selected) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(key));
        if (fragment != null) {
            if (fragment instanceof BaseFragment && selected) {
                ((BaseFragment) fragment).onFragmentSelected();
            }
            return fragment;
        } else {
            return fragment;
        }
    }

    protected void switchFragment(int tabIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < TAB_COUNT; i++) {
            Fragment fragment = getFragment(i, i == tabIndex);
            if (fragment == null && i == tabIndex) {
                fragment = createFragment(i);
            }

            if (i == tabIndex) {
                ft.show(fragment);
                mCurrentFragment = fragment;
            } else {
                if (fragment != null) {
                    ft.hide(fragment);
                }
            }
        }
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "MainActivity receive", Toast.LENGTH_SHORT).show();
    }
}
