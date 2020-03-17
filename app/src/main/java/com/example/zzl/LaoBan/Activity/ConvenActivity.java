package com.example.zzl.LaoBan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zzl.LaoBan.Fragment.ConvenFragment;
import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ConvenActivity extends AppCompatActivity {

    private ConvenFragment convenFragment;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conven);
        initFragment();
        ApplicationUtil.getInstance().addActivity(this);

    }

    private void initFragment() {
        convenFragment = new ConvenFragment();
        addFragment(convenFragment);
        showFragment(convenFragment);

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