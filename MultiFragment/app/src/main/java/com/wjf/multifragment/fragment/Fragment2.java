package com.wjf.multifragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wjf.multifragment.BaseFragment;
import com.wjf.multifragment.MainActivity;
import com.wjf.multifragment.R;
import com.wjf.multifragment.SecondActivity;

/**
 * Created by weijianfeng on 2017/5/11.
 */

public class Fragment2 extends BaseFragment {

    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mButton = (Button) view.findViewById(R.id.bt_fragment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("msg", "fragment2 msg");
                Fragment fragment = ((MainActivity)getActivity()).mCurrentFragment;
                fragment.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(getActivity(), "Fragment2 receive", Toast.LENGTH_SHORT).show();
        }
    }
}
