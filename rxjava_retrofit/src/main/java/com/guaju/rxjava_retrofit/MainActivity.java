package com.guaju.rxjava_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.guaju.rxjava_retrofit.adpter.GoodsListAdapter;
import com.guaju.rxjava_retrofit.http.RetrofitHelper;




public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private GoodsListAdapter goodsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerview);
        String controller= "goodsapp";
        String action="index_nowsell";
        RetrofitHelper.getInstance().goodspage(controller, action, new RetrofitHelper.OnComplete<String>() {



            @Override
            public void onCompleted(String goodsList) {
//                ArrayList<Goods> data = (ArrayList<Goods>) goodsList.getData();
//                goodsListAdapter = new GoodsListAdapter(MainActivity.this, data);
//                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                LinearDividerItemDecoration linearDividerItemDecoration = new LinearDividerItemDecoration(MainActivity.this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
//                rv.addItemDecoration(linearDividerItemDecoration);
//                rv.setAdapter(goodsListAdapter);
            }
        });
    }
}
