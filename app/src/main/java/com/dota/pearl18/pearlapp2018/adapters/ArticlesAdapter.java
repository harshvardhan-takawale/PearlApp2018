package com.dota.pearl18.pearlapp2018.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.activities.ArticleDisplayActivity;
import com.dota.pearl18.pearlapp2018.api.ArticleDetails;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    ArrayList<ArticleDetails> mList;
    Context mContext;

    public ArticlesAdapter(Context mContext, ArrayList<ArticleDetails> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        final ArticleDetails current = mList.get(position);
        holder.mTitle.setText(current.getTitle());
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ArticleDisplayActivity.class);
                i.putExtra("_id", current.getId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mList!= null) ? mList.size() : 0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.item_article_title);
        }
    }
}
