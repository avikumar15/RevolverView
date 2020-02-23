package com.example.revolverview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayoutMainActivity;
    CircularRevolver circularRevolver;
    float screenWidth;
    float screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        frameLayoutMainActivity = findViewById(R.id.fl_main);
        circularRevolver = new CircularRevolver(this,screenWidth/2,screenHeight/2,screenWidth/4);

        frameLayoutMainActivity.addView(circularRevolver);
    }
}
