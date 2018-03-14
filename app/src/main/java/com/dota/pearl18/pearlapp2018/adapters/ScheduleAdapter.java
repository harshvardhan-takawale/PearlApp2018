package com.dota.pearl18.pearlapp2018.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.EventDetails;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ashwik on 20-02-2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    List<String> times = new ArrayList<>();

    Context context;
    private Realm realm;


    public ScheduleAdapter(List<String> times,Context context)
    {
        this.times=times;
        this.context  =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        realm.init(context);
        realm = Realm.getDefaultInstance();
        View v = LayoutInflater.from(context).inflate(R.layout.activity_schedule_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       /*if (position == times.size() - 1) {
           // holder.bottomLine.setVisibility(View.GONE);
            holder.circularBottom.setVisibility(View.GONE);
        } else {
          //  holder.bottomLine.setVisibility(View.VISIBLE);
            holder.circularBottom.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            holder.topLine.setVisibility(View.GONE);
            holder.circuarTop.setVisibility(View.GONE);
        } else {
            holder.topLine.setVisibility(View.VISIBLE);
            holder.circuarTop.setVisibility(View.VISIBLE);
        }
        holder.bottomLine.setVisibility(View.GONE);*/
 //       ****Here time conversion is needed for desplaying the TextView time****
        holder.time.setText(times.get(position));
        holder.bindRecycler(times.get(position));

    }

    @Override
    public int getItemCount() {
        if(times!=null){return times.size();}
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        View topLine, bottomLine;
        TextView time;
        View circuarTop, circularBottom;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            /*topLine = itemView.findViewById(R.id.topLine);
            bottomLine = itemView.findViewById(R.id.bottomLine1);*/
            linearLayout =  itemView.findViewById(R.id.sub_layout);
            /*circuarTop = itemView.findViewById(R.id.imageTop);
            circularBottom = itemView.findViewById(R.id.imageBottom);*/
        }

        public void bindRecycler(String time) {


            RealmResults<EventDetails> results = realm.where(EventDetails.class).equalTo("starttime",time).findAll();
            final List<EventDetails> sets = new ArrayList<>();
            sets.addAll(results);
            linearLayout.removeAllViews();

            for (int i = 0; i < sets.size(); i++) {
                final EventDetails set = sets.get(i);
                View v = LayoutInflater.from(context).inflate(R.layout.activity_schedule_sub_item,linearLayout,false);

                ((TextView) v.findViewById(R.id.event_name)).setText(set.getEventname());
                ((TextView) v.findViewById(R.id.event_tagline)).setText(set.getEventDescription());
                linearLayout.addView(v);
                if (i != sets.size() - 1) {
                    View divider = LayoutInflater.from(context).inflate(R.layout.divider, linearLayout, false);
                    linearLayout.addView(divider);
                }
            }

        }
    }
}