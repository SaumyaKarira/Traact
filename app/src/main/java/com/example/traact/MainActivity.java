package com.example.traact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
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
                    voiceMessage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void shakeBox() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        boxImage.setAnimation(shake);
    }

    private void voiceMessage() {
        obj = MediaPlayer.create(MainActivity.this,R.raw.object);
        obj.start();
    }
}