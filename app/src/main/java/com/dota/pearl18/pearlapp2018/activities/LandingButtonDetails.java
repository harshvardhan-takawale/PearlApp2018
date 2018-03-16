package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.dota.pearl18.pearlapp2018.R;

/**
 * Created by harshvardhan on 3/11/2018.
 */

public class LandingButtonDetails {
  private String name;
  private Intent targetActivity;
  private Context context;
  private int imageRef;

  public LandingButtonDetails(String name, Intent targetActivity, Context context){
      this.name = name;
      this.targetActivity = targetActivity;
      this.context = context;
      this.imageRef = R.drawable.pearl;
  }

  public LandingButtonDetails(String name, Intent targetActivity, Context context, int imageRef) {
    this.name = name;
    this.targetActivity = targetActivity;
    this.context = context;
    this.imageRef = imageRef;
  }

  public void setButtonName(String name) {
    this.name = name;
  }

  public void setTargetActivity(Intent targetActivity) {
    this.targetActivity = targetActivity;
  }

  public void setButtonContext(Context context) {
    this.context = context;
  }

  public void setButtonImageRef(int imageRef){this.imageRef = imageRef;}

  public String getButtonName() {
    return name;
  }

  public Intent getTargetActivity() {
    return targetActivity;
  }

  public Context getButtonContext() {
    return context;
  }

  public int getImageRef(){  return imageRef; }
}
