package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.content.Intent;

/**
 * Created by harshvardhan on 3/11/2018.
 */

public class LandingButtonDetails {
  private String name;
  private Intent targetActivity;
  private Context context;

  public LandingButtonDetails(String name, Intent targetActivity, Context context) {
    this.name = name;
    this.targetActivity = targetActivity;
    this.context = context;
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

  public String getButtonName() {
    return name;
  }

  public Intent getTargetActivity() {
    return targetActivity;
  }

  public Context getButtonContext() {
    return context;
  }
}
