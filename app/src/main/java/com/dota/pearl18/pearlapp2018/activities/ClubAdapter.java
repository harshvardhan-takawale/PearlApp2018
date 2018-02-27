package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ClubDetails;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by pratd on 26-02-2018.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder>
{
    private List<ClubDetails> clubs;
    public ClubAdapter(List<ClubDetails> club_list)
    {
        this.clubs=club_list;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cname.setText(clubs.get(position).getName());
        Glide.with(holder.context)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuW7X6D7YJbn0rcswQwrb_x-Cfq30lsyrJQhE7kRaLWLUFwcSS")
                .into(holder.background);
        //displays list of events for a particular body/club
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),EventsListActivity.class);
                intent.putExtra("id",clubs.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
        //holder.cprize.setText();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_club_card, parent, false));
    }
    @Override
    public int getItemCount(){
        return clubs.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cname,cprize;
        public ImageView background;
        public Context context;

        public MyViewHolder(View view) {
            super(view);
            context=view.getContext();
            cname = view.findViewById(R.id.club_name);
            cprize=view.findViewById(R.id.club_prize);
            background=view.findViewById(R.id.club_bg);
        }
    }
}
