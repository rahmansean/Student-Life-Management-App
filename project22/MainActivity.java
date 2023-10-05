package com.example.project22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Splash screen
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView splash_text1,splash_text2,splash_text3;
    private static int SPLASH_SCREEN =5000; //5 sec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image=findViewById(R.id.splash_pic);
        splash_text1=findViewById(R.id.splash_text1);
        splash_text2=findViewById(R.id.splash_text2);
        splash_text3=findViewById(R.id.splash_text3);

        image.setAnimation(topAnim);
        splash_text1.setAnimation(bottomAnim);
        splash_text2.setAnimation(bottomAnim);
        splash_text3.setAnimation(bottomAnim);

        getSupportActionBar().hide();
        //This will handle our delay process
        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish(); //remove this activity from activity list
            }
        },SPLASH_SCREEN);
    }
}