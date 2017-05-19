package com.lizhi.microlive.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lizhi.microlive.R;

public class ScoreSendActivity extends AppCompatActivity {
    private  Toolbar toolbar;
    private ImageView leftView;
    private TextView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_send_layout);
        findViews();
        setSupportActionBar(toolbar);
        init();


    }

    private void init() {

        setTitle("");
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleView.setText("积分赠送");
    }

    private void findViews() {
        toolbar  = (Toolbar)findViewById(R.id.toolbar);
        leftView = (ImageView)findViewById(R.id.leftView);
        titleView = (TextView)findViewById(R.id.titleView);
    }

}
