package com.dota.pearl18.pearlapp2018.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.EventDetails;
import com.dota.pearl18.pearlapp2018.api.EventsInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<EventDetails> list;
    private  String TAG = "ScheduleFragment";
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        EventsInterface apiservice = ApiClient.getClient().create(EventsInterface.class);
        Call<ArrayList<EventDetails>> call = apiservice.getEventSchedule();
        call.enqueue(new Callback<ArrayList<EventDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                list = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
            }
        });
    }
}
