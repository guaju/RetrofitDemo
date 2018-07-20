package com.guaju.retrofitdemo;

import com.guaju.retrofitdemo.bean.Fishing;
import com.guaju.retrofitdemo.bean.TestBean;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by guaju on 2018/7/19.
 */

public class RetrofitHelper {
    //饿汉式
    //TODO 步骤一：私有化成员变量
    private static RetrofitHelper retrofitHelper = new RetrofitHelper();

    private final Retrofit baiduRetrofit;
    private final Retrofit fishingRetrofit;
    private final Retrofit testRetrofit;
    private final BaiduService baiduService;
    private final BaiduService fishingService;
    private final BaiduService testService;
    //TODO 步骤2：私有化构造方法
    private RetrofitHelper() {
        OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = okbuilder.build();
        //实例化唯一的Retrofit
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BaiduApi.BASE);//最后边一定要有"/"否则就会报错
        builder.addConverterFactory(ScalarsConverterFactory.create());//添加一个格式转化工厂(把输出结果转成类的方法)
        builder.client(okHttpClient);
        baiduRetrofit = builder.build();
        //实例化 第一步定义的接口
        baiduService = baiduRetrofit.create(BaiduService.class);

        Retrofit.Builder builder2 = new Retrofit.Builder();
        builder2.baseUrl(BaiduApi.FISHING_BASE);//最后边一定要有"/"否则就会报错
        builder2.addConverterFactory(GsonConverterFactory.create());//添加一个格式转化工厂(把输出结果转成类的方法)
        builder2.client(okHttpClient);
        fishingRetrofit = builder2.build();
        //实例化 第一步定义的接口
        fishingService = fishingRetrofit.create(BaiduService.class); //这一部分是属于封装了一下   动态代理模式




        Retrofit.Builder builder3 = new Retrofit.Builder();
        builder3.baseUrl(BaiduApi.TEST_BASE);//最后边一定要有"/"否则就会报错
        builder3.addConverterFactory(GsonConverterFactory.create());//添加一个格式转化工厂(把输出结果转成类的方法)
        builder3.client(okHttpClient);
        testRetrofit = builder3.build();
        //实例化 第一步定义的接口
        testService = testRetrofit.create(BaiduService.class);

    }
    //TODO 步骤三：提供一个公共方法用于返回成员变量
    public static RetrofitHelper getInstance() {
        return retrofitHelper;
    }

    //提供访问各种接口的方法
    public void getBaiduPage(Callback callback){
        Call<String> baidu = baiduService.getBaidu();
        baidu.enqueue(callback);
    }
    //提供外界获取fishing数据的方法
    public  void  getFishing(Callback<Fishing> callback){
        Call<Fishing> fishing = fishingService.getFishing("list");
        fishing.enqueue(callback);
    }

    public void getTest(Callback<TestBean> callback){
        Call<TestBean> something = testService.getSomething("goodsapp", "recommendation");
        something.enqueue(callback);
    }
}
