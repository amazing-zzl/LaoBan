package com.example.zzl.LaoBan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zzl.LaoBan.Activity.AddQuestionActivity;
import com.example.zzl.LaoBan.Adapter.FragmentAdapter;
import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.TimeCount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl on 18-3-20.
 * 问答页主页
 */

public class ConvenQandAFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView create_question;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;

    private QuestionFragment questionFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qanda, container, false);
        initView();
        fragmentChange();
        TimeCount.getInstance().setTime(System.currentTimeMillis());

        create_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddQuestionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView() {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        create_question = view.findViewById(R.id.create_question);
    }

    private void fragmentChange() {
        fragmentList = new ArrayList<>();

        questionFragment = new QuestionFragment();

        fragmentList.add(questionFragment);

        titleList = new ArrayList<>();

        titleList.add(" ");

        fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(fragmentAdapter);

        //将tabLayout与viewPager连起来
        tabLayout.setupWithViewPager(viewPager);
    }

}
