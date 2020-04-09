package com.example.zzl.LaoBan.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class QandAFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public QandAFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return 0;
    }
}
