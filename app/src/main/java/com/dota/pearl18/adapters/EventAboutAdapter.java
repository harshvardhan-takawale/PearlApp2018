package com.dota.pearl18.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dota.pearl18.R;
import com.dota.pearl18.activities.DetailsActivity;
import com.dota.pearl18.api.EventAbout;
import java.util.List;
import java.util.Locale;

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
        //Log.d("ImageLink",list.get(position).getThumbnail());
        holder.ename.setText(list.get(position).getName());
        String price = list.get(position).getPrize();
        if(price == null) holder.eprize.setText("-     ");
        else if(price.equals("")) holder.eprize.setText("-     ");
        else holder.eprize.setText(String.format(Locale.US, "₹ %s", price));
        String venue = list.get(position).getVenue();
        if(venue==null){
            holder.evenue.setText("-     ");
        }else if(venue.equals("")){
            holder.evenue.setText("-     ");
        }else {
            holder.evenue.setText(venue);
        }
        holder.edecrip.setText(list.get(position).getTagline());
        holder.edetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailsActivity.class);
                i.putExtra("id",list.get(position).getId());
                view.getContext().startActivity(i);

            }
        });

        Glide.with(holder.context)
                .load(loadImage(holder.ename.getText().toString()))
                .bitmapTransform(new CropCircleTransformation(holder.context))
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
        public TextView ename,eprize,edetails,edecrip,evenue;
        public ImageView logo;
        public Context context;
        public RelativeLayout prize_layout;
        public LinearLayout venue_layout;

        public MyViewHolder(View view) {
            super(view);
            context=view.getContext();
            ename = view.findViewById(R.id.tv_desc);
            eprize=view.findViewById(R.id.cell_content_prize);
            prize_layout = view.findViewById(R.id.prize_layout);
            edecrip = view.findViewById(R.id.tv_info);
            logo=view.findViewById(R.id.avatar);
            edetails =view.findViewById(R.id.tv_status);
            evenue = view.findViewById(R.id.cell_content_venue);
            venue_layout = view.findViewById(R.id.content_location);
        }
    }

    public Integer loadImage(String eventName){
        String lowerCaseName = eventName.toLowerCase();
//        Log.i("EventAboutAdapter", "loadImage: testing against " + lowerCaseName);
        if (lowerCaseName.contains("abhivy")){
            return R.drawable.event_abhivyaktika;
        }else if (lowerCaseName.contains("album")){
            return R.drawable.event_album_art;
        }else if (lowerCaseName.contains("alfaaz")){
            return R.drawable.event_alfaaz;
        }else if (lowerCaseName.contains("antak")){
            return R.drawable.event_antakshari;
        }else if (lowerCaseName.contains("bull")){
            return R.drawable.event_bullseye;
        }else if (lowerCaseName.contains("caption")){
            return R.drawable.event_caption_it;
        }else if (lowerCaseName.contains("loaded")){
            return R.drawable.event_caricature;
        }else if (lowerCaseName.contains("charades")){
            return R.drawable.event_charades;
        }else if (lowerCaseName.contains("cinematic")){
            return R.drawable.event_cinematic_trailer;
        }else if (lowerCaseName.contains("debate")){
            return R.drawable.event_debate;
        }else if (lowerCaseName.contains("doodler")){
            return R.drawable.event_doodler;
        }else if (lowerCaseName.contains("ent")){
            return R.drawable.event_ent_quiz;
        }else if (lowerCaseName.contains("expressions")){
            return R.drawable.event_expressions;
        }else if (lowerCaseName.contains("face")){
            return R.drawable.event_face_painting;
        }else if (lowerCaseName.contains("football")){
            return R.drawable.event_football_manager;
        }else if (lowerCaseName.contains("fraglore")){
            return R.drawable.event_fraglore;
        }else if (lowerCaseName.contains("fx")){
            return R.drawable.event_fxed;
        }else if (lowerCaseName.contains("gandhaara")){
            return R.drawable.event_gandhaara;
        }else if (lowerCaseName.contains("general")){
            return R.drawable.event_general_quiz;
        }else if (lowerCaseName.contains("glitterati")){
            return R.drawable.event_glitterati;
        }else if (lowerCaseName.contains("what")){
            return R.drawable.event_guess_what;
        }else if (lowerCaseName.contains("hunt")){
            return R.drawable.event_ha_ha_hunt;
        }else if (lowerCaseName.contains("india")){
            return R.drawable.event_india_quiz;
        }else if (lowerCaseName.contains("jhankaar")){
            return R.drawable.event_jhankaar;
        }else if (lowerCaseName.contains("kaleidoscope")){
            return R.drawable.event_kaleidoscope;
        }else if (lowerCaseName.contains("masterstroke")){
            return R.drawable.event_masterstroke;
        }else if (lowerCaseName.contains("midnight")){
            return R.drawable.event_midnight_av;
        }else if (lowerCaseName.contains("minute")){
            return R.drawable.event_minute_to_win_it;
        }else if (lowerCaseName.contains("acting")){
            return R.drawable.event_mono_acting;
        }else if (lowerCaseName.contains("pearl")){
            if (lowerCaseName.contains("jam")){
               return R.drawable.event_pearl_jam;
            }else {
                return R.drawable.event_mr_ms_pearl;
            }
        }else if (lowerCaseName.contains("natya")){
            return R.drawable.event_natya_shastra;
        }else if (lowerCaseName.contains("nukkad")){
            return R.drawable.event_nukkad_natak;
        }else if (lowerCaseName.contains("pictionary")){
            return R.drawable.event_pictionary;
        }else if (lowerCaseName.contains("pitch")){
            return R.drawable.event_pitch_it_to_win_it;
        }else if (lowerCaseName.contains("retrospective")){
            return R.drawable.event_retrospective;
        }else if (lowerCaseName.contains("sherlocked")){
            return R.drawable.event_sherlocked;
        }else if (lowerCaseName.contains("soul")){
            return R.drawable.event_soul_o;
        }else if (lowerCaseName.contains("alert")){
            return R.drawable.event_spoiler_alert;
        }else if (lowerCaseName.contains("sports")){
            return R.drawable.event_sports_quiz;
        }else if (lowerCaseName.contains("synced")){
            return R.drawable.event_synced_in;
        }else if (lowerCaseName.contains("terpsichore")){
            return R.drawable.event_terpsichore;
        }else if (lowerCaseName.contains("deaf")){
            return R.drawable.event_till_deaf;
        }else if (lowerCaseName.contains("machine")){
            return R.drawable.event_time_machine;
        }else if (lowerCaseName.contains("torque")){
            return R.drawable.event_torque_a_wheeee;
        }else if (lowerCaseName.contains("living")){
            return R.drawable.event_travel_and_living_quiz;
        }else if (lowerCaseName.contains("travelogue")){
            return R.drawable.event_travelogue;
        }else if (lowerCaseName.contains("ukti")){
            return R.drawable.event_ukti;
        }else if (lowerCaseName.contains("feather")){
            return R.drawable.event_words_of_a_feather;
        }else if (lowerCaseName.contains("mahasabha")){
            return R.drawable.event_youth_mahasabha;
        }else {
            return R.drawable.circular_bg;
        }
    }

}
