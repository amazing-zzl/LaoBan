package com.example.zzl.LaoBan.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzl.LaoBan.Adapter.FragmentAdapter;
import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.HttpUtils;
import com.example.zzl.LaoBan.Utils.MD5Encoder;
import com.example.zzl.LaoBan.Utils.TimeCount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl on 18-8-20.
 */

public class ConvenFragment extends Fragment {

    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    private FragmentAdapter fragmentAdapter;

    private SuperFragment super_fragment;
    private EnteFragment ente_fragment;
    private SportFragment sport_fragment;
    private MiliFragment mili_fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        fragmentChange();
        TimeCount.getInstance().setTime(System.currentTimeMillis());
        return view;
    }

    private void initView() {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

    }

    private void fragmentChange() {
        fragmentList = new ArrayList<>();

        super_fragment = new SuperFragment();
        ente_fragment = new EnteFragment();
        sport_fragment = new SportFragment();
        mili_fragment = new MiliFragment();


        fragmentList.add(super_fragment);
        fragmentList.add(ente_fragment);
        fragmentList.add(sport_fragment);
        fragmentList.add(mili_fragment);

        titleList = new ArrayList<>();
        titleList.add("超市");
        titleList.add("药店");
        titleList.add("助老");
        titleList.add("通知");

        fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(fragmentAdapter);

        //将tabLayout与viewPager连起来
        tabLayout.setupWithViewPager(viewPager);
    }


}
