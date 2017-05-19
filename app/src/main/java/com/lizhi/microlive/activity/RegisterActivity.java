package com.lizhi.microlive.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lizhi.microlive.R;
import com.lizhi.microlive.com.lizhi.microlive.utils.ConstantValues;
import com.lizhi.microlive.com.lizhi.microlive.utils.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Call;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_IMAGE = 1;

    private ImageView faceView;
    private EditText usernameView;
    private EditText mobileNoView;
    private EditText userIdView;
    private EditText bankNoView;
    private EditText emergencyNoView;
    private EditText roomNoView;
    private Button registerButton;

    private String facePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        findViews();

        init();

    }

    private void init() {
        Glide.with(this)
                .load("android.resource://com.lizhi.microlive/drawable/" + R.drawable.meinv1)
                .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(faceView);
        registerButton.setOnClickListener(this);
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
     * Auto-created on 2017-05-14 10:46:20 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

        faceView = (ImageView)findViewById( R.id.faceView );
        usernameView = (EditText)findViewById( R.id.usernameView );
        mobileNoView = (EditText)findViewById( R.id.mobileNoView );
        userIdView = (EditText)findViewById( R.id.userIdView );
        bankNoView = (EditText)findViewById( R.id.bankNoView );
        emergencyNoView = (EditText)findViewById( R.id.emergencyNoView );
        roomNoView = (EditText)findViewById( R.id.roomNoView );
        registerButton = (Button)findViewById( R.id.registerButton );

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-05-14 10:46:20 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.registerButton:

                OkHttpUtils.post()
                        .url(ConstantValues.REGISTER_URL)
                        .addParams("facePath", facePath)
                        .addParams("facePath", usernameView.getText().toString())
                        .addParams("facePath", mobileNoView.getText().toString())
                        .addParams("facePath", userIdView.getText().toString())
                        .addParams("facePath", bankNoView.getText().toString())
                        .addParams("facePath", emergencyNoView.getText().toString())
                        .addParams("facePath", roomNoView.getText().toString())
                        .tag(this)
                        .id(ConstantValues.REGISTER_ID)
                        .build()
                        .execute(new StringCallback() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if (ConstantValues.REGISTER_ID==id) {
                                    MyToast.showToast(RegisterActivity.this, getString(R.string.register_fail), Toast.LENGTH_LONG);
                                }
                            }

                            public void onResponse(String response, int id) {
                                if (null!=response && ConstantValues.REGISTER_ID==id){
                                    MyToast.showToast(RegisterActivity.this, getString(R.string.register_success), Toast.LENGTH_LONG);
                                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                break;

            case R.id.imageView:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
                break;
        }
    }

//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        super.onActivityReenter(resultCode, data);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                facePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Glide.with(this)
                        .load(facePath)
                        .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
                        .into(faceView);

                cursor.close();
            }
        }

    }
}