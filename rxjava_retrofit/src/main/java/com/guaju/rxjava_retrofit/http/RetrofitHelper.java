package com.guaju.rxjava_retrofit.http;

import com.guaju.rxjava_retrofit.api.FarmerApi;
import com.guaju.rxjava_retrofit.bean.GoodsList;
import com.guaju.rxjava_retrofit.httpservice.FarmerService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guaju on 2018/7/20.
 */

public class RetrofitHelper {
    //回调接口
    public  interface  OnComplete<T>{
        void onCompleted(T t);
    }


//        饿汉式
//    单例模式懒汉式
    //第一步：私有化成员变量
    //第二步：私有化构造方法
    //第三步： 提供公共的调用方法 ，供外界访问本成员变量
    private volatile  static RetrofitHelper  helper;
    private final FarmerService farmerService;
    private final FarmerService farmerService2;

    private RetrofitHelper(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //拦截器
        //有的后台为了保证数据交互的安全 ，需要让客户端去添加固定头信息，并且有固定值
        //才能够明确这个请求是由本app发送过来，而不是别的app发送过来
        Interceptor author = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request author = request.newBuilder()
                        .header("author", "http://www.baidu.com")
                        .build();
                return chain.proceed(author);

            }
        };
        builder.addInterceptor(author);
        OkHttpClient okhttp = builder.build();


        //创建 Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FarmerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okhttp)
                .build();

        //创建 Retrofit
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(FarmerApi.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okhttp)
                .build();

        farmerService = retrofit.create(FarmerService.class);   //动态代理模式构建接口的实例对象
        farmerService2 = retrofit2.create(FarmerService.class);   //动态代理模式构建接口的实例对象
    }
    public static RetrofitHelper getInstance(){
        if (helper==null){
            synchronized (RetrofitHelper.class){
                if (helper==null){
                    helper=new RetrofitHelper();
                }
            }
        }
        return helper;
    }
    /*
    现在的写法能够满足一般的需求  但是有一种特殊的需求 ：就是层级接口，
    android端需要访问两边 甚至两次以上的网络数据才能拿到想要的数据

     */



    //获取在售商品列表
    public void  goodsList(String controller, String action, final OnComplete<GoodsList> onComplete){
        Observable<GoodsList> o = farmerService.goodsList(controller, action);
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GoodsList>() {
                    @Override
                    public void call(GoodsList goodsList) {
                        //这个操作 已经被安排到了主线程
                         onComplete.onCompleted(goodsList);
                    }
                });

    }
     //被操作 包含网络请求
    public void  goodspage(final String controller, final String action, final OnComplete<String> onComplete){






        //map和flatmap的区别
//        map可以把结果转成一个新的类型的数据 但是转化不成新的observable 而 flatmap可以转成新的Observable


//        flatmap一般用于层级接口访问 使用
        Observable<String> observable = farmerService.goodsList(controller, action)
                .map(new Func1<GoodsList, String>() {
                    @Override
                    public String call(GoodsList goodsList) {
                        return goodsList.getMassage();
                    }
                });

//
//                .flatMap(new Func1<GoodsList, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(GoodsList goodsList) {
//                        Goods goods = goodsList.getData().get(0);
//                        String id = goods.getId();
//                        Observable<String> goodspage = farmerService2.goodspage("site", "products", id);
//
//                        return goodspage;
//                    }
//                });


                    observable
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Action1<String>() {
                      @Override
                      public void call(String s) {
                          System.out.println(s);
                      }
                  });
    }






}
