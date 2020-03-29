package com.vyankatesh.counterapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        count_tv = findViewById(R.id.count);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        inputTarget = findViewById(R.id.input_target);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_reduce:
                if (COUNT > 0)
                    COUNT--;
                count_tv.setText(COUNT + "");
                Log.d(LOG_TAG, "Decremented to " + COUNT);
                return true;

            case R.id.menu_reset:
                COUNT = 0;
                Log.d(LOG_TAG, "Reset to " + COUNT);
                count_tv.setText(COUNT + "");

                return true;

            case R.id.menu_set_target:
                setTarget();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void reset(View view) {
        COUNT = 0;
        Log.d(LOG_TAG, "Reset to " + COUNT);
        count_tv.setText(COUNT + "");
    }

    public void increaseCount(View view) {

        if (Target == 0)
            setTarget();

        if (Target != 0 && COUNT < Target) {
            COUNT++;
            count_tv.setText(String.valueOf(COUNT));
            Log.d(LOG_TAG, "Incremented to " + COUNT);
        }
        if (Target != 0 && COUNT == Target) {
            Toast.makeText(getApplicationContext(), "Target Achieved", Toast.LENGTH_LONG).show();
        }
    }

    public void setTarget() {

        final View set_target_view = LayoutInflater.from(this).inflate(R.layout.set_target_layout, null);
        final AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle("Set Target");
        myAlertBuilder.setMessage("Enter your target count number");
        myAlertBuilder.setView(set_target_view);
        myAlertBuilder.setPositiveButton("Set Target", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText target = set_target_view.findViewById(R.id.set_target);
                        Target = Integer.parseInt(target.getText().toString());
                        Toast.makeText(getApplicationContext(), "Target has been set to" + Target, Toast.LENGTH_LONG).show();
                    }
                });
        myAlertBuilder.show();


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

