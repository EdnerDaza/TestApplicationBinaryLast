package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private long mSplashDelay =  3000; //3 segundos

    //region LIFE CICLE METHODS
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);


            //TAREA PARA IR AL HOME
            TimerTask taskHome = new TimerTask() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent().setClass(SplashActivity.this, LoginActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                    finish();
                }
            };

            Timer timer = new Timer();
            timer.schedule(taskHome, mSplashDelay);


        }
    //endregion
}
