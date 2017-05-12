package com.wjf.multifragment.subfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wjf.multifragment.BaseFragment;
import com.wjf.multifragment.R;
import com.wjf.multifragment.SecondActivity;
import com.wjf.multifragment.fragment.Fragment1;

/**
 * Created by weijianfeng on 2017/5/11.
 */

public class SubFragment1 extends BaseFragment {

    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfragment1, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mButton = (Button) view.findViewById(R.id.bt_sub1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("msg", "sub1 msg");
                Fragment fragment = ((Fragment1) getParentFragment()).mFragmentPagerAdapter.getItem(0);
                fragment.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Toast.makeText(getActivity(), "SubFragment1 receive", Toast.LENGTH_SHORT).show();
        }
    }
}
