package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.EventAbout;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by pratd on 27-02-2018.
 */

public class EventAboutAdapter extends RecyclerView.Adapter<EventAboutAdapter.MyViewHolder>
{
    public List<EventAbout> list;
    public EventAboutAdapter(List<EventAbout> list)
    {
        this.list=list;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d("ImageLink",list.get(position).getThumbnail());
        holder.ename.setText(list.get(position).getName());
        holder.eprize.setText("₹ "+list.get(position).getPrize());
        Glide.with(holder.context)
                .load("https://bits-pearl.org"+list.get(position).getThumbnail())
                .placeholder(R.drawable.avatar_placeholder)
                .bitmapTransform(new CropCircleTransformation(holder.context))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.d("Glide","Failed to load image");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d("Glide","Image Loaded Successfully");
                        return false;
                    }
                })
                .into(holder.logo);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_details, parent, false));
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ename,eprize;
        public ImageView logo;
        public Context context;
        public MyViewHolder(View view) {
            super(view);
            context=view.getContext();
            ename = view.findViewById(R.id.tv_desc);
            eprize=view.findViewById(R.id.cell_content_prize);
            logo=view.findViewById(R.id.avatar);
        }
    }
}
