package com.dota.pearl18.pearlapp2018.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;

/**
 * Created by Vineeth on 2/19/2018.
 */

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> sponsor_url = new ArrayList<>();
    private ArrayList<String> sponsor_title = new ArrayList<>();

    public SponsorsAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        addPreviousSponsorUrls();
    }

    public void addPreviousSponsorUrls(){
        for(int i = 1; i < 11; i++){
            sponsor_url.add(String.format("https://bits-pearl.org/static/images/sponsors/%s.png", i));
        }
        sponsor_title.add("Title Sponsor");
        sponsor_title.add("Co- Title Sponsor");
        sponsor_title.add("In association with");
        sponsor_title.add("In association with");
        sponsor_title.add("Powered by");
        sponsor_title.add("Exclusive Partner Bollywood night");
        sponsor_title.add("Exclusive Partner-EDM Night");
        sponsor_title.add("Exclusive Partner-Fusion Night");
        sponsor_title.add("Exclusive Partner-Comedy Night");
        sponsor_title.add("Associate Sponsor");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.sponsors_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.sponsor_title.setText(sponsor_title.get(position));

        Glide.with(mContext).load(sponsor_url.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.sponsor_image);
    }

    @Override
    public int getItemCount() {
        return sponsor_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sponsor_title;
        ImageView sponsor_image;
        public MyViewHolder(View v){
            super(v);
            sponsor_title = v.findViewById(R.id.sponsor_title);
            sponsor_image = v.findViewById(R.id.main_events);
        }
    }

}
