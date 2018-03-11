package com.dota.pearl18.pearlapp2018.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartheek
 * On 22/2/18.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    List<ArticleDetails> list = new ArrayList<>();
    Context context;


    public ArticlesAdapter(List<ArticleDetails> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_article_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.article_text.setText(list.get(position).getContent());
        holder.article_time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        if(list!=null){return list.size();}
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView article_text,article_time;
        public ViewHolder(View itemView) {
            super(itemView);
            article_text = itemView.findViewById(R.id.article_text);
            article_time = itemView.findViewById(R.id.article_time);
        }
    }

    private String FormatTime(String time){
        // TODO: FORMAT TIME STRING
        return null;
    }
}