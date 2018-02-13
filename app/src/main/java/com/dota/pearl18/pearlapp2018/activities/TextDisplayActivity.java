package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.pearl18.pearlapp2018.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class TextDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        JustifiedTextView textView = findViewById(R.id.tv_display);

        String s = getIntent().getStringExtra("text");

        if(s.equalsIgnoreCase("about")){
            setTitle("About Us");
            textView.setText(R.string.about_us);
        }
        else if(s.equalsIgnoreCase("dir")){
            setTitle("Directions");
            textView.setText(R.string.directions);
        }
        else {
            // TODO: ADD ERROR MESSAGE
        }
    }
}
