package com.guaju.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guaju.retrofitdemo.bean.TestBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Retrofit   是square公司（jackwharton 大牛以前的公司  被goolge挖走）出品的

//是基于____D____封装的网络加载框架

//OKHttp也是square公司提供的网络加载框架 （并不是一个完整的框架（不怎么简洁易用））
//   httpurlconnection 需要程序员自己写逻辑  只是为开发者提供了一个核心 但是并没有封装
//

//A.gson  B.picasso  C.fresco  D.  Okhttp

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RetrofitHelper.getInstance().getFishing(new Callback<Fishing>() {
//            @Override
//            public void onResponse(Call<Fishing> call, Response<Fishing> response) {
//                System.out.println(response.body().getList().size() + "长度是前面的数");
//            }
//
//            @Override
//            public void onFailure(Call<Fishing> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });

        RetrofitHelper.getInstance().getTest(new Callback<TestBean>() {
            @Override
            public void onResponse(Call<TestBean> call, Response<TestBean> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}
