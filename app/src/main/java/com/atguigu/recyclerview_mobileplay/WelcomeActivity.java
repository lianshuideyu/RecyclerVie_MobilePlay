package com.atguigu.recyclerview_mobileplay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        handler.removeCallbacksAndMessages(null);
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);

        }
        super.onDestroy();
    }
}
