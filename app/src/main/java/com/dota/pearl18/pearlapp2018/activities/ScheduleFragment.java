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
    private List<EventDetails> list = new ArrayList<>();
    private List<EventDetails> page_list = new ArrayList<>();
    private  String TAG = "ScheduleFragment";
    private int page;
    private String day;
    public static ScheduleFragment newInstance(int page)
    {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("page_no",page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page_no",0);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.schedule_recyclerview);
        adapter = new ScheduleAdapter(page_list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        EventsInterface apiservice = ApiClient.getClient().create(EventsInterface.class);
        Call<ArrayList<EventDetails>> call = apiservice.getEventSchedule();
        call.enqueue(new Callback<ArrayList<EventDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                list = response.body();

                int i=0, n=0;
                switch(page){
                    case 0 : i=0;n=9;break;
                    case 1 : i=9;n=15;break;
                    case 2 : i=15;n=list.size();break;
                }
                for(int k=i;k<n;k++)
                {
                    page_list.add(list.get(k));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
            }
        });
    }
}
