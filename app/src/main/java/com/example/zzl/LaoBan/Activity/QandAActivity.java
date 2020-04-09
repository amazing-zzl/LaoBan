package com.example.zzl.LaoBan.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.zzl.LaoBan.Fragment.ConvenQandAFragment;
import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

public class QandAActivity extends AppCompatActivity {

    private ConvenQandAFragment convenQandAFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conven);
        initFragment();
        ApplicationUtil.getInstance().addActivity(this);
    }

    private void initFragment() {
        convenQandAFragment = new ConvenQandAFragment();
        addFragment(convenQandAFragment);
        showFragment(convenQandAFragment);
    }

    /*添加fragment*/
    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(R.id.main_content, fragment).commit();
            fragmentList.add(fragment);
        }
    }

    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        for (Fragment frag : fragmentList) {
            if (frag != fragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(frag).commit();
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.show(fragment).commit();
    }
}
