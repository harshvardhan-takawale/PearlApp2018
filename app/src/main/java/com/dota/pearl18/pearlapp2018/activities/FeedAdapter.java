package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.FeedDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Vineeth on 3/16/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ScoresFeedViewHolder>{
    List<FeedDetails> list = new ArrayList<>();
    private Context c ;

    public FeedAdapter(List<FeedDetails> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public ScoresFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_feed_content,parent,false);
        ScoresFeedViewHolder viewHolder = new ScoresFeedViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoresFeedViewHolder holder, int position) {
        FeedDetails currentPost = list.get(position);
        holder.title.setText(currentPost.getSport());
        holder.text.setText(currentPost.getScorestext());
        holder.time.setText(formatTimeString(currentPost.getCreatedtime()));
    }

    public static String formatTimeString (String utcTimeString) {
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone istTimeZone = TimeZone.getTimeZone("IST");
        iso8601Format.setTimeZone(istTimeZone);

        DateFormat outputFormat = new SimpleDateFormat("EEE, d MMM, hh:mm a");

        try {
//            Log.i("FeedAdapter", "formatTimeString: formatted = " + iso8601Format.parse(utcTimeString).toString());
            return outputFormat.format(iso8601Format.parse(utcTimeString));
        } catch (Exception e) {
//            Log.i("FeedAdapter", "formatTimeString: " + e.getMessage());
            return ""; // just to prevent crashes and stuff
        }
    }

    @Override
    public int getItemCount() {

        return (list!= null) ? list.size() : 0;
    }

    public void clearData() {
        int cur_size = list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, cur_size);
    }

    public class ScoresFeedViewHolder extends RecyclerView.ViewHolder{

        TextView title ,text ,time;

        public ScoresFeedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sport_title);
            text = itemView.findViewById(R.id.text_feed);
            time = itemView.findViewById(R.id.time_feed);
        }
    }
}
