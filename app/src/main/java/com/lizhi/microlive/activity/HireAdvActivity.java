package com.lizhi.microlive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.lizhi.microlive.R;
import com.lizhi.microlive.adpter.HireAdvAdapter;
import com.lizhi.microlive.com.lizhi.microlive.utils.ConstantValues;
import com.lizhi.microlive.com.lizhi.microlive.utils.MyToast;
import com.lizhi.microlive.com.lizhi.microlive.utils.ParseJson2Bean;
import com.lizhi.microlive.model.HireAdv;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HireAdvActivity extends AppCompatActivity {

    //title bar
    private ImageView leftView;
    private TextView titleView;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private ArrayList<HireAdv> hireAdvItems;
    private HireAdvAdapter hireAdvAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.hire_adv_layout);

        findViews();
        setSupportActionBar(toolbar);
        init();

        getDataFromHost();
    }

    private void init() {

        titleView.setText(R.string.advTitle);
        setTitle("");
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*初始化功能列表*/
        hireAdvItems = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        hireAdvAdapter = new HireAdvAdapter(this, hireAdvItems);
        recyclerView.setAdapter(hireAdvAdapter);
        // 设置点击 item 的点击事件
        hireAdvAdapter.setOnHireAdvItemClickListener(new HireAdvAdapter.OnHireAdvItemClickListener(){

            @Override
            public void onItemClick(HireAdv data) {
                Intent intent = new Intent(HireAdvActivity.this, DetailHireAdvActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消同一个tag的
        OkHttpUtils.getInstance().cancelTag(this);//取消以Activity.this作为tag的请求
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-14 12:35:56 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

        leftView = (ImageView)findViewById(R.id.leftView );
        titleView = (TextView)findViewById(R.id.titleView );
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView );
    }

    private void getDataFromHost() {

        OkHttpUtils.get()
                .url(ConstantValues.HIRE_ADV_URL)
                .tag(this)
                .id(ConstantValues.HIREADV_ID)
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (ConstantValues.HIREADV_ID==id) {
                            MyToast.showToast(HireAdvActivity.this, getString(R.string.app_name), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (null!=response && ConstantValues.HIREADV_ID==id) {

                            ParseJson2Bean<HireAdv> parse = new ParseJson2Bean<HireAdv>();
                            List<HireAdv> items = parse.parseJson2List(response);

                            hireAdvItems.addAll(items);
                            hireAdvAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
