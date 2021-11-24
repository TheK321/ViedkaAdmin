package com.example.viedkaadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {

    private ImageView logo;
    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        logo = findViewById(R.id.loginLogo);
        lottie = findViewById(R.id.loginLottie);
        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(login.this, ActivityLoginViedka.class));
                finish();
            }
        }, 3000 );





    }
}