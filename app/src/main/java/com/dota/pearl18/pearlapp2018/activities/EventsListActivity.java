package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ClubInterface;
import com.dota.pearl18.pearlapp2018.api.EventAbout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsListActivity extends AppCompatActivity {
    ArrayList<EventAbout> list;
    RecyclerView recyclerView;
    EventAboutAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        Bundle bundle=getIntent().getExtras();
        final String id=bundle.getString("id");
        list=new ArrayList<EventAbout>();
        recyclerView=findViewById(R.id.event_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ClubInterface apiService = ApiClient.getClient().create(ClubInterface.class);
        Call<ArrayList<EventAbout>> call = apiService.getEventDetails();
        call.enqueue(new Callback<ArrayList<EventAbout>>() {
            @Override
            public void onResponse(Call<ArrayList<EventAbout>> call, Response<ArrayList<EventAbout>> response) {
                for(EventAbout e:response.body())
                {
                    Log.d("EventDetails",e.getName()+" id="+e.getId());
                    Log.d("EventBodyID",e.getBody());
                    if(e.getBody().equals(id))
                    {
                        Log.d("addingEvent",e.getName());
                        list.add(e);
                    }
                }
                adapter=new EventAboutAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<EventAbout>> call, Throwable t) {
                Log.e("Error:","Error in Connectivity");
            }
        });
    }
}
