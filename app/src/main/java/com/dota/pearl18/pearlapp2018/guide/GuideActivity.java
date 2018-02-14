package com.dota.pearl18.pearlapp2018.guide;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.Articles.TextDisplayActivity;
import com.dota.pearl18.pearlapp2018.R;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class GuideActivity extends AppCompatActivity {

    private static int permCheck;
    public static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Button mMapBtn = findViewById(R.id.guide_map_btn);
        mMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permCheck = ContextCompat.checkSelfPermission(GuideActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permCheck == PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(GuideActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    startActivity(new Intent(GuideActivity.this,MapsActivity.class));
                }
            }
        });

        Button mAboutBtn = findViewById(R.id.guide_about_btn);
        mAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(GuideActivity.this, TextDisplayActivity.class);
                aboutIntent.putExtra("text","about");
                startActivity(aboutIntent);
            }
        });

        Button mReachBtn = findViewById(R.id.guide_reach_btn);
        mReachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reachUsIntent = new Intent(GuideActivity.this,TextDisplayActivity.class);
                reachUsIntent.putExtra("text","dir");
                startActivity(reachUsIntent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_LOCATION:
                if(grantResults[0] == PERMISSION_GRANTED)
                    startActivity(new Intent(this,MapsActivity.class));
                else
                    Toast.makeText(this,"Please activate Location Services", Toast.LENGTH_LONG).show();
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
