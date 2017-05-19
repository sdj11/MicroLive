package com.lizhi.microlive.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhi.microlive.MyApplication;
import com.lizhi.microlive.R;
import com.lizhi.microlive.adpter.FunctionAdapter;
import com.lizhi.microlive.adpter.GoodsAdapter;
import com.lizhi.microlive.com.lizhi.microlive.utils.ConstantValues;
import com.lizhi.microlive.com.lizhi.microlive.utils.DividerGridItemDecoration;
import com.lizhi.microlive.com.lizhi.microlive.utils.DividerListItemDecoration;
import com.lizhi.microlive.com.lizhi.microlive.utils.GlideImageLoader;
import com.lizhi.microlive.com.lizhi.microlive.utils.MyToast;
import com.lizhi.microlive.com.lizhi.microlive.utils.ParseJson2Bean;
import com.lizhi.microlive.model.BannerAdv;
import com.lizhi.microlive.model.FunctionItem;
import com.lizhi.microlive.model.Goods;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.R.attr.data;
import static java.lang.System.load;

public class HomeActivity extends AppCompatActivity {

    //title bar
    private ImageView leftView;
    private TextView titleView;
    private ImageView rightView;
    private Toolbar toolbar;

    private Banner advBanner;
    private LinearLayout ruleView;
    private TextView scoreView;
    private RecyclerView functionView;
    private RecyclerView goodsView;

    //图片集合
    private List<String> images = new ArrayList<>();
    //标题集合（当banner样式有显示title时）
    private List<String> titles = new ArrayList<>();

    private boolean isPlaying = false;

    private FunctionAdapter functionAdapter;
    private GoodsAdapter goodsAdapter;
    private List<FunctionItem> functionItems;
    private List<Goods> goodsItems;
    private List<BannerAdv> advs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        findViews();
        setSupportActionBar(toolbar);
        init();

        setListener();
    }

    private void setListener() {

        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //兑换规则
        ruleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isPlaying) {
            //开始轮播
            advBanner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isPlaying) {
            //结束轮播
            advBanner.stopAutoPlay();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消同一个tag的
        OkHttpUtils.getInstance().cancelTag(this);//取消以Activity.this作为tag的请求
    }

    private void init() {
        setTitle("");
        leftView.setImageResource(R.mipmap.ic_launcher);
        rightView.setImageResource(R.mipmap.ic_launcher);
        leftView.setVisibility(View.VISIBLE);
        rightView.setVisibility(View.VISIBLE);
        titleView.setText(R.string.homeTitle);

      /* scoreView.setText(((MyApplication)getApplication()).getUser().getScore());*/

        /*初始化广告轮播 */
        //设置总高度的四分之一
        advBanner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyApplication.screenHeight/5));
        //设置banner样式
        advBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        advBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        advBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        advBanner.isAutoPlay(true);
        //设置轮播时间
        advBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        advBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置点击 item 的点击事件
        advBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerAdv adv = advs.get(position);
                Intent intent = new Intent(HomeActivity.this, DetailHireAdvActivity.class);
                intent.putExtra("advId", adv.getId());
                startActivity(intent);
            }
        });

        /*初始化功能列表*/
        functionItems = new ArrayList<>();
        /**
         * 模拟数据 ---------------------------------------------------------------------------------
         */
        FunctionItem item = new FunctionItem();
        item.setType(3);
        item.setImgUrl("android.resource://com.lizhi.microlive/drawable/"+R.drawable.meinv1);
        item.setTitle("社区生活");
        item.setSubtitle("多姿多彩");
        functionItems.add(item);

        item = new FunctionItem();
        item.setType(2);
        item.setImgUrl("android.resource://com.lizhi.microlive/drawable/"+R.drawable.meinv1);
        item.setTitle("开门功能");
        item.setSubtitle("蓝牙进入");
        functionItems.add(item);

        item = new FunctionItem();
        item.setType(2);
        item.setImgUrl("android.resource://com.lizhi.microlive/drawable/"+R.drawable.meinv1);
        item.setTitle("顺客隆超市");
        item.setSubtitle("购出精彩");
        functionItems.add(item);

        item = new FunctionItem();
        item.setType(2);
        item.setImgUrl("android.resource://com.lizhi.microlive/drawable/"+R.drawable.meinv1);
        item.setTitle("广告招聘");
        item.setSubtitle("有你所需");
        functionItems.add(item);
        //---------------------------------------------------------------------------


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        functionView.setLayoutManager(gridLayoutManager);
        //设置分割线 - 分割线需要自定义 & 还可以自定义分割线的样式
//        functionView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        functionView.addItemDecoration(new DividerGridItemDecoration(this));
        functionAdapter = new FunctionAdapter(this, functionItems);
        functionView.setAdapter(functionAdapter);
        // 设置点击 item 的点击事件
        functionAdapter.setOnFunctionItemClickListener(new FunctionAdapter.OnFuntionItemClickListener() {
            @Override
            public void onItemClick(FunctionItem data) {

                //1、网页资源   2、apk资源   3、activity资源
                switch (data.getType()){

                    case 1:
                        Intent intent = new Intent(HomeActivity.this, HtmlActivity.class);
                        intent.putExtra("url", data.getToIdentity());
                        startActivity(intent);

                    case 2:


                    case 3:

                    default:
                        MyToast.showToast(HomeActivity.this, getString(R.string.app_name), Toast.LENGTH_LONG);
                }
            }
        });

        /*初始化精品列表*/
        goodsItems = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        goodsView.setLayoutManager(staggeredGridLayoutManager);
        //设置分割线 - 分割线需要自定义 & 还可以自定义分割线的样式
        goodsView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        goodsAdapter = new GoodsAdapter(this, goodsItems);
        goodsView.setAdapter(goodsAdapter);
        // 设置点击 item 的点击事件
        goodsAdapter.setOnGoodsItemClickListener(new GoodsAdapter.OnGoodsItemClickListener() {
            @Override
            public void onItemClick(Goods data) {
                Intent intent = new Intent(HomeActivity.this, DetailGreatGoodsActivity.class);
                intent.putExtra("goodsId", data.getId());
                startActivity(intent);
            }
        });

        //从服务器上获取数据
//        getDataFromHost();

    }

    private void getDataFromHost() {

        //广告轮播数据
        OkHttpUtils.post()
                .url(ConstantValues.ADVBANNER_URL)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .tag(this)
                .id(ConstantValues.ADVBANNER_ID)
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (ConstantValues.ADVBANNER_ID==id) {
                            MyToast.showToast(HomeActivity.this, getString(R.string.app_name), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (null!=response && ConstantValues.ADVBANNER_ID==id) {

                            ParseJson2Bean<BannerAdv> parse = new ParseJson2Bean<BannerAdv>();
                            advs = parse.parseJson2List(response);

                            titles = new ArrayList<String>();
                            images = new ArrayList<>();

                            for (BannerAdv item : advs) {
                                titles.add(item.getTitle());
                                images.add(item.getImgUrl());
                            }

                            //设置标题集合（当banner样式有显示title时）
                            advBanner.setBannerTitles(titles);
                            //设置图片集合
                            advBanner.setImages(images);
                            //banner设置方法全部调用完毕时最后调用
                            advBanner.start();

                            //开始轮播
                            isPlaying = true;
                            advBanner.startAutoPlay();
                        }
                    }
                });

        //功能列表数据
        /*
        OkHttpUtils.post()
                .url(ConstantValues.FUNCTION_URL)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .tag(this)
                .id(ConstantValues.FUNCTION_ID)
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (ConstantValues.FUNCTION_ID==id) {
                          MyToast.showToast(HomeActivity.this, getString(R.string.app_name), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                         if (null!=response && ConstantValues.FUNCTION_ID==id) {

                            ParseJson2Bean<FunctionItem> parse = new ParseJson2Bean<FunctionItem>();
                            List<FunctionItem> items = parse.parseJson2List(response);
                            functionItems.addAll(items);
                            functionAdapter.notifyDataSetChanged();
                        }
                    }
                });
    */
        //推荐精品数据
        OkHttpUtils.post()
                .url(ConstantValues.GREAT_GOODS_URL)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .tag(this)
                .id(ConstantValues.GREAT_GOODS_ID)
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (ConstantValues.GREAT_GOODS_ID==id) {
                            MyToast.showToast(HomeActivity.this, getString(R.string.app_name), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (null!=response && ConstantValues.GREAT_GOODS_ID==id) {

                            ParseJson2Bean<Goods> parse = new ParseJson2Bean<Goods>();
                            List<Goods> items = parse.parseJson2List(response);

                            goodsItems.addAll(items);
                            goodsAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-13 15:49:36 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

        leftView = (ImageView)findViewById( R.id.leftView );
        titleView = (TextView)findViewById( R.id.titleView );
        rightView = (ImageView)findViewById( R.id.rightView );
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        advBanner = (Banner) findViewById( R.id.advBanner );
        ruleView = (LinearLayout) findViewById( R.id.ruleView );
        scoreView = (TextView)findViewById( R.id.scoreView );
        functionView = (RecyclerView)findViewById( R.id.functionView );
        goodsView = (RecyclerView)findViewById( R.id.goodsView );
    }

}
