package com.lizhi.microlive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lizhi.microlive.R;


/**
 * Created by Administrator on 2017/5/17.
 */


public class MyScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView TitleView;
    private Toolbar toolbar;


    private TextView myScore;
    private LinearLayout SocreExchange;
    private LinearLayout SocreSend;
    private LinearLayout SocreDetail;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-17 16:47:08 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_score_layout);


        findViews();
        setSupportActionBar(toolbar);

        init();


    }

    private void init() {
        TitleView.setText("我的积分");
        setTitle("");
        SocreExchange.setOnClickListener(this);
        SocreSend.setOnClickListener(this);
        SocreDetail.setOnClickListener(this);


    }
    private void findViews() {
        TitleView = (TextView) findViewById(R.id.titleView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        myScore = (TextView)findViewById( R.id.myScore );
        SocreExchange = (LinearLayout)findViewById( R.id.Socre_exchange );
        SocreSend = (LinearLayout)findViewById( R.id.Socre_send );
        SocreDetail = (LinearLayout)findViewById( R.id.Socre_detail );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Socre_exchange:
                Intent ExchangeIntent = new Intent(MyScoreActivity.this,ScoreExchangeActivity.class);
                startActivity(ExchangeIntent);
             break;
            case R.id.Socre_send:
                Intent SendIntent = new Intent(MyScoreActivity.this,ScoreSendActivity.class);
                startActivity(SendIntent);
                break;
            case  R.id.Socre_detail:
                Intent DetailIntent = new Intent(MyScoreActivity.this,ScoreExchangeActivity.class);
                startActivity(DetailIntent);
                break;
            default:
                break;
        }

    }
}
