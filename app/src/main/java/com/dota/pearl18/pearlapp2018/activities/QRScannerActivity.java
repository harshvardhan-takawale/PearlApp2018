package com.dota.pearl18.pearlapp2018.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.util.LinkifyCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.google.zxing.Result;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.ViewConfigurator;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = QRScannerActivity.class.getSimpleName();
    private static final int REQUEST_CAMERA = 111;

    private ZXingScannerView mScannerView;
    private boolean setupComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        checkGrantedPermissions();
    }

    @Override
    public void handleResult(Result result) {
//        Log.d(TAG, result.getText());
        new LovelyInfoDialog(this)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setMessage(result.getText())
                .configureMessageView(new ViewConfigurator<TextView>() {
                    @Override
                    public void configureView(TextView message) {
                        Log.d(TAG, "configureView: called");
                        LinkifyCompat.addLinks(message, Linkify.WEB_URLS);
                        message.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                })
                .show()
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mScannerView.resumeCameraPreview(QRScannerActivity.this);
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (setupComplete) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    private void checkGrantedPermissions() {
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permCheck == PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            // PERMISSION_GRANTED, so proceed normally
            setupComplete = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    // Camera has been granted; proceed with app
                    setupComplete = true;
                    mScannerView.setResultHandler(this);
                    mScannerView.startCamera();
                } else {
                    // Not granted; close the activity
                    finish();
                }
        }
    }
}
