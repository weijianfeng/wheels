package com.wjf.multifragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjf.multifragment.BaseFragment;
import com.wjf.multifragment.R;
import com.wjf.multifragment.subfragment.SubFragment1;
import com.wjf.multifragment.subfragment.SubFragment2;
import com.wjf.multifragment.widget.PagerSlidingTabStrip;

/**
 * Created by weijianfeng on 2017/5/11.
 */

public class Fragment1 extends BaseFragment {

    private static final int PAGE_COUNT = 2;
    private static final int PAGE_INDEX_SUB1 = 0;
    private static final int PAGE_INDEX_SUB2 = 1;

    private static final String[] PAGE_TITLE = new String[]{"SUB1", "SUB2"};

    ViewPager mViewPager;
    PagerSlidingTabStrip mPagerSliding;

    public FragmentPagerAdapter mFragmentPagerAdapter;
    SubFragment1 subFragment1;
    SubFragment2 subFragment2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mPagerSliding = (PagerSlidingTabStrip) view.findViewById(R.id.pager_sliding);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getChildFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mPagerSliding.setViewPager(mViewPager);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == PAGE_INDEX_SUB1) {
                if (subFragment1 == null) {
                    subFragment1 = new SubFragment1();
                }
                fragment = subFragment1;
            } else if (position == PAGE_INDEX_SUB2) {
                if (subFragment2 == null) {
                    subFragment2 = new SubFragment2();
                }
                fragment = subFragment2;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLE[position];
        }
    }
}
