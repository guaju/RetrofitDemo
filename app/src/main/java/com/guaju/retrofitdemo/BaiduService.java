package com.guaju.retrofitdemo;

import com.guaju.retrofitdemo.bean.Fishing;
import com.guaju.retrofitdemo.bean.TestBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by guaju on 2018/7/19.
 */


//使用retrofit第一步
//现在访问https://www.baidu.com/地址 这个地址将来会返回一个网页

    //http://devapi.niudiao.cn/home/all
public interface BaiduService {
    @GET("/")
    Call<String> getBaidu();

    @POST("home/{all}")
    Call<Fishing>  getFishing(@Path("all") String param);

    @Multipart
    @POST("index.php")
    Call<TestBean>  getSomething(@Query("controller") String controller, @Field("action") String action);
}
