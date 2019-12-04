package com.nataliee_edeno.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GameView myGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//        Display display = getWindowManager().getDefaultDisplay();
//
//        // Load the resolution into a Point object
//        Point size = new Point();
//        display.getSize(size);
//        Log.d("debug size x", String.valueOf(size.x));
//        Log.d("debug size y", String.valueOf(size.y));
       // Toast.makeText(this, "x is: " + String.valueOf(size.x) + " y is : " + String.valueOf(size.y), Toast.LENGTH_LONG).show();
        myGame = findViewById(R.id.myViewID);
        //myGame.moveBall();
      //  if(!myGame.isGameOn())


    }
}
