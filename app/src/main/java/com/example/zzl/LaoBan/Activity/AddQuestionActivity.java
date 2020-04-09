package com.example.zzl.LaoBan.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zzl.LaoBan.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 添加问题页
 */
public class AddQuestionActivity extends AppCompatActivity {
    private ImageView back;
    private EditText title;
    private EditText content;
    private Button create_question;

    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        initView();

        //提问
        create_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuestionActivity add = new AddQuestionActivity();
                String questionTitle = title.getText().toString();
                String questionContent = content.getText().toString();
                String url = "http://47.98.50.35:8080/laoban_back/question/save?query=&questionTitle="
                        + questionTitle + "&questionContent="
                        + questionContent;
                add.okHttpGet(url);
                Toast.makeText(AddQuestionActivity.this, "创建问题成功", Toast.LENGTH_SHORT).show();

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


    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.question_title);
        content = findViewById(R.id.question_content);
        create_question = findViewById(R.id.create);
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

    /*
     * post请求 get不一样的地方就是传参数不一样，post请求需要把参数封装到RequestBody对象，
     * 调用Request对象的post方法把RequestBody传入进去
     */
    private void okHttpPost(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("name", "shixz");// 请求参数一
        formBuilder.add("pass", "shixz");// 请求参数二
        formBuilder.add("mobiles", "1302101XXXX");// 请求参数三
        formBuilder.add("content", "OkHttp的post()请求方式.测试测试");// 请求参数四
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder().post(requestBody).url(url)
                .post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            System.out.println("OkHttp的post()请求方式" + result);
            response.body().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

