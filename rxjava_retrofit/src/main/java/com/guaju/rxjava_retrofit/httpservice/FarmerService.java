package com.guaju.rxjava_retrofit.httpservice;

import com.guaju.rxjava_retrofit.bean.GoodsList;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by guaju on 2018/7/20.
 */

public interface FarmerService {
//    String GOODS_LIST = BASE_URL + "index.php?controller=goodsapp&action=index_nowsell";
    //获取现货列表
    @GET("index.php")
    Observable<GoodsList> goodsList(@Query("controller") String contr, @Query("action") String action);

    //map中的key 必须是     controller 和    action 否则就会访问失败
    @GET("index.php")
    Observable<GoodsList> goodsList(@QueryMap HashMap<String,String> map);


//    https://www.ynw56.com/index.php?controller=site&action=products&id=911

    @GET("index.php")
    Observable<String> goodspage(@Query("controller") String contr, @Query("action") String action,@Query("id") String id);
}
