package com.example.zzl.LaoBan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zzl.LaoBan.Activity.ConvenActivity;
import com.example.zzl.LaoBan.MyApplication;
import com.example.zzl.LaoBan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 和MainActivity配套的，我懒得改名字了
 */

public class HomeFragment extends Fragment {
    private View view;
    SimpleAdapter adapter;
    private GridView gridView;
    List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();

    private int[] icons = {R.mipmap.wenti, R.mipmap.bianmin, R.mipmap.gongyi
            , R.mipmap.zhishi, R.mipmap.tuiguang, R.mipmap.fenxiang
            , R.mipmap.activity, R.mipmap.shop, R.mipmap.wenti};
    private String[] text = {"问题回答", "便民信息", "有偿公益", "知识学堂", "信息推广", "分享故事", "老年活动", "老人用品", "定位服务"};

    /*加载布局文件*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        initView();
        data_list = getData();
        //加载适配器
        String[] form = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        adapter = new SimpleAdapter(MyApplication.getContext(), data_list, R.layout.item, form, to);
        gridView.setAdapter(adapter);

        //监听item每一项
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0: {
                        Toast.makeText(MyApplication.getContext(), "你点击了问题回答", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(getContext(), ConvenActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.anim_scale, R.anim.anim_alpha);
                        break;
                    }
                    case 2: {
                        Toast.makeText(MyApplication.getContext(), "你点击了有偿公益", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(MyApplication.getContext(), "你点击了知识学堂", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 4: {
                        Toast.makeText(MyApplication.getContext(), "你点击了信息推广", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 5: {
                        Toast.makeText(MyApplication.getContext(), "你点击了分享故事", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 6: {
                        Toast.makeText(MyApplication.getContext(), "你点击了老年活动", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 7: {
                        Toast.makeText(MyApplication.getContext(), "你点击了老人用品", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 8: {
                        Toast.makeText(MyApplication.getContext(), "你点击了定位服务", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
            }
        });

        return view;
    }


    private void initView() {
        gridView = view.findViewById(R.id.gridView);
    }

    //准备数据源
    public List<Map<String, Object>> getData() {

        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            map.put("text", text[i]);
            data_list.add(map);
        }
        return data_list;
    }
}