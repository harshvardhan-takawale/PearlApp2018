package com.dota.pearl18.pearlapp2018.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.EventDetails;

import java.util.List;


public class ScheduleFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<EventDetails> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.schedule_recyclerview);
        adapter = new ScheduleAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }
}
