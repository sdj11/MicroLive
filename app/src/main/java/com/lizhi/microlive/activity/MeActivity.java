package com.lizhi.microlive.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lizhi.microlive.MyApplication;
import com.lizhi.microlive.R;
import com.lizhi.microlive.model.User;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.lizhi.microlive.R.id.imgView;
import static com.lizhi.microlive.R.id.leftView;
import static com.lizhi.microlive.R.id.rightView;

/**
 * Created by Administrator on 2017/5/14.
 */

public class MeActivity extends AppCompatActivity {
//title bar
    private ImageView leftView;
    private TextView titleView;
    private Toolbar toolbar;
//    private ImageView rightView;

    private ImageView faceView;
    private TextView myAmountView;
    private TextView usernameTitleView;
    private TextView nameView;
    private TextView mobileNoView;
    private TextView idView;
    private TextView bankNoView;
    private TextView emergencyNoView;
    private TextView roomNoView;

    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;
    private ImageView imgView5;
    private ImageView imgView6;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.me_layout);

        findViews();
        setSupportActionBar(toolbar);

        init();

    }

    private void init() {

        leftView.setImageResource(R.mipmap.ic_launcher);
        leftView.setVisibility(View.VISIBLE);
        titleView.setText(R.string.meTitle);
        setTitle("");
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        User user = ((MyApplication)getApplication()).getUser();

//        myAmountView.setText(user.getScore());
        Glide.with(this)
//                .load(user.getFaceUrl())
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.temp)
                .bitmapTransform(new RoundedCornersTransformation(this, 500, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(faceView);


        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.temp)
                .bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView1);
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView2);
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView3);
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView4);
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView5);
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(imgView6);

    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-14 12:12:45 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        leftView = (ImageView)findViewById( R.id.leftView );
        titleView = (TextView)findViewById( R.id.titleView );
        toolbar = (Toolbar)findViewById(R.id.toolbar) ;
//        rightView = (ImageView)findViewById( R.id.rightView );

        faceView = (ImageView)findViewById( R.id.faceView );
        myAmountView = (TextView)findViewById( R.id.myAmountView );
        usernameTitleView = (TextView)findViewById( R.id.usernameTitleView );
        nameView = (TextView)findViewById( R.id.nameView );
        mobileNoView = (TextView)findViewById( R.id.mobileNoView );
        idView = (TextView)findViewById( R.id.idView );
        bankNoView = (TextView)findViewById( R.id.bankNoView );
        emergencyNoView = (TextView)findViewById( R.id.emergencyNoView );
        roomNoView = (TextView)findViewById( R.id.roomNoView );

        imgView1 = (ImageView)findViewById( R.id.imgView1 );
        imgView2 = (ImageView)findViewById( R.id.imgView2 );
        imgView3 = (ImageView)findViewById( R.id.imgView3 );
        imgView4 = (ImageView)findViewById( R.id.imgView4 );
        imgView5 = (ImageView)findViewById( R.id.imgView5 );
        imgView6 = (ImageView)findViewById( R.id.imgView6 );

    }
}
