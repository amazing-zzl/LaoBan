package com.example.zzl.LaoBan.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.zzl.LaoBan.Activity.ShowNewsActivity;
import com.example.zzl.LaoBan.Adapter.SuperAdapter;
import com.example.zzl.LaoBan.Bean.Supermarket;
import com.example.zzl.LaoBan.LoadListView;
import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.HttpUtils;
import com.example.zzl.LaoBan.Utils.MyBitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 超市活动
 * Created by zzl on 18-8-21.
 */

public class SuperFragment extends Fragment implements LoadListView.ILoadListener,
        LoadListView.RLoadListener, SuperAdapter.CallBack {
    private static final String TAG = "SuperFragment";
    //获取json的目标地址
    final String url = "http://47.98.50.35/hongfu.json";
    private View view;
    private LoadListView mListView;
    private List<Supermarket> superList;

    private SuperAdapter adapter;

    private MyBitmapUtils myBitmapUtils;


    public SuperFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news, container, false);
        Log.d(TAG, "onCreateView");
        myBitmapUtils = new MyBitmapUtils(getContext());
        setupViews();
        if (!HttpUtils.isNetworkAvalible(getContext())) {
            //HttpUtils.checkNetwork(getActivity());
            Toast.makeText(getContext(), "当前没有可以使用的网络，请检查网络设置！", Toast.LENGTH_SHORT).show();
        } else {
            initNews();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ShowNewsActivity.class);
                intent.putExtra("title", superList.get(i - mListView.getHeaderViewsCount()).getTitle());
                intent.putExtra("url", superList.get(i - mListView.getHeaderViewsCount()).getUrl());
                intent.putExtra("date", superList.get(i - mListView.getHeaderViewsCount()).getDate());
                intent.putExtra("author", superList.get(i - mListView.getHeaderViewsCount()).getSource());
                intent.putExtra("pic_url", superList.get(i - mListView.getHeaderViewsCount()).getImage());
                startActivity(intent);
                //添加Activity过场动画
                getActivity().overridePendingTransition(R.anim.anim_scale, R.anim.anim_alpha);
            }
        });

        return view;


    }

    private void initNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = HttpUtils.requestHttp(url);
                parseJSONWithGSON(jsonData);
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {   //解析json数据

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("app_msg_list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_super = jsonArray.getJSONObject(i);
                String imgUrl = json_super.getString("cover");
                /**
                 * 采取三级缓存策略加载图片
                 */

                Bitmap bitmap = myBitmapUtils.getBitmap(imgUrl);
                /**
                 * 不采取缓存策略
                 */
                //Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
                String title = json_super.getString("title");
                String date = json_super.getString("create_time");
                String author = json_super.getString("digest");
                String url = json_super.getString("link");
                Log.d(TAG, "url:*-*-*-*-*-*-*" + imgUrl);
                Supermarket supermarket = new Supermarket(bitmap, title, url, date, author);
                superList.add(supermarket);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseJSONWithGSON_Refresh(String jsonData) {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("app_msg_list");

            JSONObject json_super = jsonArray.getJSONObject(new Random().nextInt(16) + 1);
            String imgUrl = json_super.getString("cover");
            Log.d(TAG, "url:*-*-*-*-*-*-*" + imgUrl);
            Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
            String title = json_super.getString("title");
            String date = json_super.getString("create_time");
            String author = json_super.getString("digest");
            String url = json_super.getString("link");

            Supermarket supermarket = new Supermarket(bitmap, title, url, date, author);
            superList.add(0, supermarket);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON_Load(String jsonData) {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("app_msg_list");

            JSONObject json_super = jsonArray.getJSONObject(new Random().nextInt(30) + 1);
            String imgUrl = json_super.getString("cover");
            Log.d(TAG, "url:*-*-*-*-*-*-*" + imgUrl);
            Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
            String title = json_super.getString("title");
            String date = json_super.getString("create_time");
            String author = json_super.getString("digest");
            String url = json_super.getString("link");

            Supermarket supermarket = new Supermarket(bitmap, title, url, date, author);
            superList.add(supermarket);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initSuperDatas() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = HttpUtils.requestHttp(url);
                parseJSONWithGSON_Load(jsonData);

            }
        }).start();


    }

    private void initRefreshDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = HttpUtils.requestHttp(url);
                parseJSONWithGSON_Refresh(jsonData);
            }
        }).start();

    }

    private void setupViews() {

        mListView = view.findViewById(R.id.lv_main);
        //上拉加载接口
        mListView.setInterface(this);
        mListView.setReflashInterface(this);
        superList = new ArrayList<Supermarket>();
        adapter = new SuperAdapter(getContext(), R.layout.news_item, superList, this);
        mListView.setAdapter(adapter);


    }

    //实现onLoad()方法。
    @Override
    public void onLoad() {
        //添加延时效果模拟数据加载
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initSuperDatas();//得到新数据
                mListView.loadCompleted();
            }
        }, 2000);
    }

    //  实现的刷新方法
    @Override
    public void onRefresh() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initRefreshDatas();//得到新数据
                mListView.reflashComplete();
            }
        }, 2000);

    }

    @Override
    public void click(View view) {
        Toast.makeText(getContext(), "该新闻已删除！", Toast.LENGTH_SHORT).show();
        superList.remove(Integer.parseInt(view.getTag().toString()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}
