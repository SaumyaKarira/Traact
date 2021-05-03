package com.example.traact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Locale;

public class StartActivity extends AppCompatActivity {


    private ConstraintLayout startPage = null;

    private View tapAny;
    Animation bump;

    private View title ;
    Animation fade;

    private View page;

    MediaPlayer mySong ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        fade = AnimationUtils.loadAnimation(this, R.anim.fade_in) ;
        bump = AnimationUtils.loadAnimation(this, R.anim.bounce) ;

        page = (ConstraintLayout) findViewById(R.id.startPage);
        page.startAnimation(fade);

        mySong = MediaPlayer.create(StartActivity.this,R.raw.tap2);
        mySong.start();

        startPage = (ConstraintLayout) findViewById(R.id.startPage);
        startPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openMainPage();
                return true;
            }
        });
    }


    public void openMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}