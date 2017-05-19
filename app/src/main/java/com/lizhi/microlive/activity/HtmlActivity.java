package com.lizhi.microlive.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lizhi.microlive.R;

/**
 * Created by Administrator on 2017/5/14.
 */

public class HtmlActivity extends AppCompatActivity {

    //title bar
    private ImageView leftView;
    private TextView titleView;
    private ImageView rightView;
    private Toolbar toolbar;

    private WebView webView;

    private String resUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.html_layout);

        findViews();
        setSupportActionBar(toolbar);

        init();
    }

   private void init() {
        resUrl = getIntent().getStringExtra("url");
        initWebView();
       setTitle("");

    }

    private void initWebView() {
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        //支持双击-前提是页面要支持才显示
        webSettings.setUseWideViewPort(true);

        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);

        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());

        //加载网络资源
        webView.loadUrl(resUrl);
//        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");

        //显示页面
        setContentView(webView);
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-14 13:38:58 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        leftView = (ImageView)findViewById( R.id.leftView );
        titleView = (TextView)findViewById( R.id.titleView );
        rightView = (ImageView)findViewById( R.id.rightView );
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        webView = (WebView)findViewById( R.id.webView );
    }


}
