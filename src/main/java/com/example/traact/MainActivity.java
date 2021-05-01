package com.example.traact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout startPage = null;

    private View tapAny;
    Animation bump;

    private View title ;
    Animation fade;

    private View page;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fade = AnimationUtils.loadAnimation(this, R.anim.fade_in) ;
        bump = AnimationUtils.loadAnimation(this, R.anim.bounce) ;

        page = (ConstraintLayout) findViewById(R.id.startPage);
        page.startAnimation(fade);



        startPage = (ConstraintLayout) findViewById(R.id.startPage);

        startPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openHomePage();
                return true;
            }
        });
    }

    public void openHomePage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}