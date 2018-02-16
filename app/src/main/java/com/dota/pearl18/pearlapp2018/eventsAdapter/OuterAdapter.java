package com.dota.pearl18.pearlapp2018.eventsAdapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.garlandview.TailAdapter;
import com.dota.pearl18.pearlapp2018.R;

import java.util.List;

public class OuterAdapter extends TailAdapter<OuterItem> {

    private final int POOL_SIZE = 16;

    private final List<List<InnerData>> mData;
    private final RecyclerView.RecycledViewPool mPool;

    public OuterAdapter(List<List<InnerData>> data) {
        this.mData = data;

        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0, POOL_SIZE);
    }

    @Override
    public OuterItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OuterItem(view, mPool);
    }

    @Override
    public void onBindViewHolder(OuterItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public void onViewRecycled(OuterItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.events_outer_item;
    }

}
