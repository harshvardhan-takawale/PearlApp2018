package com.dota.pearl18.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.pearl18.R;

public class TextDisplayActivity extends AppCompatActivity {
    private static final String TAG = TextDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        TextView display = findViewById(R.id.tv_display);
        TextView title = findViewById(R.id.tv_title);

        String s = getIntent().getStringExtra("text");
        if (s.equalsIgnoreCase("about")) {
            title.setText("About us");
            display.setText(R.string.about_us);
        } else if (s.equalsIgnoreCase("dir")) {
            title.setText("Directions");
            display.setText(R.string.directions);
        } else {
            //Log.i(TAG, "onCreate: Something went wrong");
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
