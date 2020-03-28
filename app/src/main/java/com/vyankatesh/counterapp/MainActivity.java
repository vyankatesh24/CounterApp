package com.vyankatesh.counterapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int COUNT = 0;
    public String KEY_COUNT = "count";
    TextView count_tv;
    int Target = 0;
    SharedPreferences mPreferences;
    EditText inputTarget;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count_tv =  findViewById(R.id.count);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        inputTarget = findViewById(R.id.input_target);

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

        if (Target != 0 && COUNT == Target - 1) {
            COUNT++;
            count_tv.setText(String.valueOf(COUNT));
            Log.d(LOG_TAG, "Incremented to " + COUNT);
            Toast.makeText(getApplicationContext(), "Target Achieved", Toast.LENGTH_LONG).show();
        } else {
            COUNT++;
            count_tv.setText(String.valueOf(COUNT));
            Log.d(LOG_TAG, "Incremented to " + COUNT);
        }



    }

    public void setTarget(View view) {
        Target = Integer.parseInt(inputTarget.getText().toString());
        Toast.makeText(getApplicationContext(), "Target has been set.", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(KEY_COUNT, COUNT);
        preferencesEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        COUNT = mPreferences.getInt(KEY_COUNT, 0);
        count_tv.setText(COUNT + "");
    }
}

