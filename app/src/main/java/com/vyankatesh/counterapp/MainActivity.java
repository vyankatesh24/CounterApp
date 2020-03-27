package com.vyankatesh.counterapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int COUNT = 0;
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
        Log.d(LOG_TAG, "Reset to " + COUNT);
        count_tv.setText(COUNT + "");
    }

    public void reduceCount(View view){
        if (COUNT > 0)
            COUNT--;
        count_tv.setText(COUNT+"");
        Log.d(LOG_TAG, "Decremented to " + COUNT);

    }

    public void increaseCount(View view){
        COUNT++;

        count_tv.setText(String.valueOf(COUNT));
        Log.d(LOG_TAG, "Incremented to " + COUNT);


    }
}

