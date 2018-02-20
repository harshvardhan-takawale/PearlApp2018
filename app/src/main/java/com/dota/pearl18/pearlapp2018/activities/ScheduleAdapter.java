package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.EventDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwik on 20-02-2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    List<EventDetails> list = new ArrayList<>();
    Context context;


    public ScheduleAdapter(List<EventDetails> list,Context context)
    {
        this.list = list;
        this.context  =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_schedule_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.event_name.setText(list.get(position).getEventname());
    }

    @Override
    public int getItemCount() {
        if(list!=null){return list.size();}
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView event_name;
        public ViewHolder(View itemView) {
            super(itemView);
            event_name = itemView.findViewById(R.id.event_name);
        }
    }
}