package com.example.zzl.LaoBan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzl.LaoBan.R;
import com.example.zzl.LaoBan.Utils.HttpUtils;
import com.example.zzl.LaoBan.Utils.MyBitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.PendingIntent.getActivity;

/**
 * 问题详情及回答页
 */

public class ShowQuestionActivity extends AppCompatActivity {
    private TextView title, content, time;
    private EditText answerContent;
    private Button answer;
    private ImageView back;

    private List<String> answerList = new ArrayList<String>();
    private Integer questionId;
    private MyBitmapUtils myBitmapUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        initView();
        initData();

        initAnswer();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ShowQuestionActivity.this, android.R.layout.simple_list_item_1, answerList
        );
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);

//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        AnswerAdapter adapter = new AnswerAdapter(answerList);
//        recyclerView.setAdapter(adapter);

        //回答
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowQuestionActivity add = new ShowQuestionActivity();
                String answerContent1 = answerContent.getText().toString();
                /**
                 * http://47.98.50.35:8080/laoban_back/answer/save?query=&questionId=1&answerContent=%22%E6%88%91%E6%98%AF%E7%AD%94%E6%A1%88%22%22
                 */
                String url = "http://47.98.50.35:8080/laoban_back/answer/save?query=&questionId="
                        + questionId + "&answerContent="
                        + answerContent1;

                add.okHttpGet(url);
                Toast.makeText(ShowQuestionActivity.this, "回答成功", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        Integer questionId1 = intent.getIntExtra("id", 54);
        String title1 = intent.getStringExtra("title");
        String content1 = intent.getStringExtra("content");
        String time1 = intent.getStringExtra("data");

        questionId = questionId1;
        title.setText(title1);
        content.setText(content1);
        time.setText(time1);
    }

    private void initView() {
        title = findViewById(R.id.question_title);
        content = findViewById(R.id.question_content);
        time = findViewById(R.id.question_time);
        answerContent = findViewById(R.id.answer_content);
        answer = findViewById(R.id.answer);
        back = findViewById(R.id.back);
    }

    private void initAnswer() {
        new Thread(new Runnable() {
            final String url = "http://47.98.50.35:8080/laoban_back/answer/index?QuestionId=" + questionId;

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

//                Integer id = json_question.getInt("answerId");
//                Integer qId = json_question.getInt("questionId");
                String content = json_question.getString("answerContent");
//                String updateTime = json_question.getString("updateTime");
                answerList.add(content);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void okHttpGet(String url) {
        // 首先需要创建一个OkHttpClient对象用于Http请求, 可以改成全局型
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        // 创建一个request对象
        Request request = new Request.Builder().url(url).build();
        // 执行和回调
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {

            }

            public void onResponse(Call call, Response response)
                    throws IOException {
                String str = response.body().string();
                System.out.println("OkHttp的get()请求方式" + str);
            }
        });

    }


}
