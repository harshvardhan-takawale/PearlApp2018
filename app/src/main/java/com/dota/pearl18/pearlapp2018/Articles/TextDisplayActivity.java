package com.dota.pearl18.pearlapp2018.Articles;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


import com.dota.pearl18.pearlapp2018.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class TextDisplayActivity extends AppCompatActivity {
    private static final String TAG = TextDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        setContentView(R.layout.activity_text_display);

        JustifiedTextView display = findViewById(R.id.tv_display);

        String s = getIntent().getStringExtra("text");
        if (s.equalsIgnoreCase("about")) {
            setTitle("About us");
            // TODO change this about text
            display.setText(R.string.about_us);
        } else if (s.equalsIgnoreCase("dir")) {
            setTitle("Directions");
            display.setText(R.string.directions);
        } else {
            //Log.i(TAG, "onCreate: Something went wrong");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
