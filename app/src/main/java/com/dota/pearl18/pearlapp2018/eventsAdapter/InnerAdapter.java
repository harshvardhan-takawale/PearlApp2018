package com.dota.pearl18.pearlapp2018.eventsAdapter;

//import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;
import java.util.List;


public class InnerAdapter extends com.ramotion.garlandview.inner.InnerAdapter<InnerItem> {

    private final List<InnerData> mData = new ArrayList<>();

    @Override
    public InnerItem onCreateViewHolder(ViewGroup parent, int viewType) {
        //final InnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        //return new InnerItem(binding.getRoot());
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new InnerItem(view);
    }

    @Override
    public void onBindViewHolder(InnerItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public void onViewRecycled(InnerItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.events_inner_item;
    }

    public void addData(@NonNull List<InnerData> innerDataList) {
        final int size = mData.size();
        mData.addAll(innerDataList);
        notifyItemRangeInserted(size, innerDataList.size());
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

}
