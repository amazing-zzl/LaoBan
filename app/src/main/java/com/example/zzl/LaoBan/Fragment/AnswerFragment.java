package com.example.zzl.LaoBan.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.zzl.LaoBan.Activity.ShowNewsActivity;
import com.example.zzl.LaoBan.Activity.ShowQuestionActivity;
import com.example.zzl.LaoBan.Adapter.NewsAdapter;
import com.example.zzl.LaoBan.Adapter.QuestionAdapter;
import com.example.zzl.LaoBan.Bean.News;
import com.example.zzl.LaoBan.Bean.Question;
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
 * 问答页实现的方法
 */

public class AnswerFragment extends Fragment implements LoadListView.ILoadListener,
        LoadListView.RLoadListener, NewsAdapter.CallBack, QuestionAdapter.CallBack {
    final String url = "http://47.98.50.35:8080/laoban_back/question/index";

    private View view;
    private LoadListView mListView;
    private List<Question> questionList;

    private QuestionAdapter adapter;

    private MyBitmapUtils myBitmapUtils;

    public AnswerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news, container, false);
        myBitmapUtils = new MyBitmapUtils(getContext());
        setupViews();
        if (!HttpUtils.isNetworkAvalible(getContext())) {
            //HttpUtils.checkNetwork(getActivity());
            Toast.makeText(getContext(), "当前没有可以使用的网络，请检查网络设置！", Toast.LENGTH_SHORT).show();

        } else {
            initQuestion();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ShowQuestionActivity.class);
                intent.putExtra("id", questionList.get(i - mListView.getHeaderViewsCount()).getQuestionId());
                intent.putExtra("title", questionList.get(i - mListView.getHeaderViewsCount()).getQuestionTitle());
                intent.putExtra("content", questionList.get(i - mListView.getHeaderViewsCount()).getQuestionContent());
                intent.putExtra("picture", questionList.get(i - mListView.getHeaderViewsCount()).getQuestionPicture());
                intent.putExtra("date", questionList.get(i - mListView.getHeaderViewsCount()).getUpdateTime());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonData = HttpUtils.requestHttp(url);
                parseJSONWithGSON(jsonData);
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_question = jsonArray.getJSONObject(i);
                String imgUrl = json_question.getString("questionPicture");
                /**
                 * 采取三级缓存策略加载图片
                 */

                Bitmap bitmap = myBitmapUtils.getBitmap(imgUrl);
                /**
                 * 不采取缓存策略
                 */
                //Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
                Integer id = json_question.getInt("questionId");
                String title = json_question.getString("questionTitle");
                String content = json_question.getString("questionContent");
                String updateTime = json_question.getString("updateTime");
//                String author_name = json_question.getString("description");

                Question question = new Question(id, title, content, bitmap, updateTime);
                questionList.add(question);


            }
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

    private void parseJSONWithGSON_Refresh(String jsonData) {

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            JSONObject json_question = jsonArray.getJSONObject(new Random().nextInt(30) + 1);
            String imgUrl = json_question.getString("questionPicture");
            Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
            Integer id = json_question.getInt("questionId");
            String title = json_question.getString("questionTitle");
            String content = json_question.getString("questionContent");
            String updateTime = json_question.getString("updateTime");
//            String author_name = json_question.getString("description");
//            String url = json_question.getString("url");

            Question question = new Question(id, title, content, bitmap, updateTime);
            questionList.add(0, question);
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
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            JSONObject json_question = jsonArray.getJSONObject(new Random().nextInt(30) + 1);
            String imgUrl = json_question.getString("questionPicture");
            Bitmap bitmap = HttpUtils.decodeUriAsBitmapFromNet(imgUrl);
            Integer id = json_question.getInt("questionId");
            String title = json_question.getString("questionTitle");
            String content = json_question.getString("questionContent");
            String updateTime = json_question.getString("updateTime");
//            String author_name = json_question.getString("description");
//            String url = json_question.getString("url");

            Question question = new Question(id, title, content, bitmap, updateTime);
            questionList.add(0, question);
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
        questionList = new ArrayList<Question>();
        adapter = new QuestionAdapter(getContext(), R.layout.news_item, questionList, this);
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
                initQuestion();//得到新数据
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
                initQuestion();//得到新数据
                mListView.loadCompleted();
            }
        }, 2000);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        onCreate(null);

    }


    @Override
    public void click(View view) {
        Toast.makeText(getContext(), "该新闻已删除！", Toast.LENGTH_SHORT).show();
        questionList.remove(Integer.parseInt(view.getTag().toString()));
        adapter.notifyDataSetChanged();
    }

}
