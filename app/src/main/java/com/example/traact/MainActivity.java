package com.example.traact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView data, object;
    ImageView boxImage;
    DatabaseReference databaseReference;
    String status;
    MediaPlayer obj;
    Vibrator vibrator;
    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.ir_data);
        object = findViewById(R.id.presence);
        boxImage = findViewById(R.id.imageView);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                status = snapshot.child("data").getValue().toString();
                data.setText(status);
                if(status.equals("0")){
                    object.setText("Object Present");
                    shakeBox();
                    vibratePhone();
                    voiceMessage();

                }
                if(status.equals("1")){
                    object.setText("Object Not Present");
                    obj.stop();
                    stopVibrate();
                    stopShake();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void stopShake() {
        shake.setRepeatCount(0);
        shake.cancel();
    }

    private void vibratePhone() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long pattern[] = {60,120,180,240,300,360,420,480};
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            //v.vibrate(pattern , 5);
        } else {
            //deprecated in API 26
            vibrator.vibrate(pattern, 1);
        }

    }

    public void stopVibrate() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.cancel();
    }


    private void shakeBox() {
        shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        boxImage.setAnimation(shake);
    }



    private void voiceMessage() {
        obj = MediaPlayer.create(MainActivity.this,R.raw.object);
        obj.setLooping(true) ;
        obj.start();
    }
}