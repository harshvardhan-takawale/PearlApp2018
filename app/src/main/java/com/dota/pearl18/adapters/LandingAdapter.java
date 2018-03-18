package com.dota.pearl18.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dota.pearl18.R;
import com.dota.pearl18.activities.LandingButtonDetails;

import java.util.ArrayList;

/**
 * Created by harshvardhan on 3/11/2018.
 */

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.MyViewHolder> {
  private ArrayList<LandingButtonDetails> list;
  Context context;

  public LandingAdapter(ArrayList<LandingButtonDetails> list, Context context) {
    this.list = list;
    this.context = context;
  }

  @Override
  public LandingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_club_card, parent, false));
  }

  @Override
  public void onBindViewHolder(final LandingAdapter.MyViewHolder holder, final int position) {
//    Log.w("myApp", String.valueOf(position));
    final Activity activity = (Activity) context;

    holder.background.setImageResource(list.get(position).getImageRef());
    holder.intent = list.get(position).getTargetActivity();
    holder.background.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        list.get(position).getButtonContext().startActivity(holder.intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
      }
    });

  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView background;
    public Intent intent;

    public MyViewHolder(View itemView) {
      super(itemView);
      background = itemView.findViewById(R.id.club_bg);
    }
  }
}
