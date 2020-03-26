package com.vyankatesh.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static int COUNT = 0;
    TextView count_tv;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count_tv =  findViewById(R.id.count);
    }

    public void reset(View view){
        COUNT = 0;
        Log.d(LOG_TAG,COUNT+"");
        count_tv.setText(COUNT+"");
    }

    public void reduceCount(View view){
        COUNT--;
        count_tv.setText(COUNT+"");
        Log.d(LOG_TAG,COUNT+"");

    }
    public void increaseCount(View view){
        COUNT++;

        count_tv.setText(String.valueOf(COUNT));
        Log.d(LOG_TAG,COUNT+"");


    }
}

