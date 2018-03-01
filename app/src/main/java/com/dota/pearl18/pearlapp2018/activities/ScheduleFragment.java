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
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.EventDetails;
import com.dota.pearl18.pearlapp2018.api.EventsInterface;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<EventDetails> list = new ArrayList<>();
    private List<String> realmlist = new ArrayList<>();
    public Realm realm;
    private  String TAG = "ScheduleFragment";
    private int page;
    private String day;
    private int i ;

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
        realm.init(getContext());
        realm = Realm.getDefaultInstance();
        recyclerView = view.findViewById(R.id.schedule_recyclerview);
        adapter = new ScheduleAdapter(realmlist,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        switch (page) {
            case 0: CallApi();break;
        }
    }

    private  void CallApi()
    {
        EventsInterface apiservice = ApiClient.getClient().create(EventsInterface.class);
        Call<ArrayList<EventDetails>> call = apiservice.getEventSchedule();
        call.enqueue(new Callback<ArrayList<EventDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<EventDetails>> call, Response<ArrayList<EventDetails>> response) {
                list = response.body();

                for(i=0;i<list.size();i++)
                {
                    addDatatoRealm(list.get(i));

                }
                getDatafromRealm(realm);
                Log.e(TAG,"reach"+String.valueOf(list.size()));

            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
                getDatafromRealm(realm);
            }
        });
    }

    private void addDatatoRealm(EventDetails details)
    {
        realm.beginTransaction();
        EventDetails model = realm.where(EventDetails.class).equalTo("eventid",details.getEventid()).findFirst();
        if(model==null)
        {
            EventDetails event = realm.createObject(EventDetails.class);
            event.setEventid(details.getEventid());
            event.setEventname(details.getEventname());
            /*
            // The format of the startTime string is yyyy-MM-dd-HH-mm
            // HH-mm is the time in 24 hour format. Use this after conversion to 12 hour format.

            String pattern = "\\d{4}(-\\d{2}){4}";

            // testdate corresponds to 10:05 AM (10:05 hours), 11th August 2018
            String testdate = "2018-08-11-10-05"; // replace with details.getStartTime()

            // validation condition. If false, do not parse the time, and have a default fallback option
            if (testdate.matches(pattern)){
                // Split the testdate String, to obtain the various parts of the time
                String[] parts = testdate.split("-");
                // wrt to testdate
                // parts[0] => yyyy => 2018
                // parts[1] => MM => 08
                // parts[2] => DD => 11
                // parts[3] => HH => 10
                // parts[4] => mm => 5
            }
            */
            
            event.setStarttime(details.getStarttime());
            event.setEventDescription(details.getEventDescription());
        }
        else
        {
            model.setEventname(details.getEventname());
            model.setStarttime(details.getStarttime());
            model.setEventDescription(details.getEventDescription());

        }
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            realmlist = new ArrayList<>();
            RealmResults<EventDetails> results = realm1.where(EventDetails.class).findAll();
            Log.e(TAG,"results="+String.valueOf(results.size()));

            if(results.size()==0)
            {
                Toast.makeText(getContext(),"NO Internet",Toast.LENGTH_SHORT).show();
            }
            for(int j=0;j<results.size();j++)
            {
                realmlist.add(results.get(j).getStarttime());
            }
            Log.e(TAG,"realmlist="+String.valueOf(realmlist.size()));

            Set<String> set = new LinkedHashSet<>(realmlist);
            realmlist.clear();
            realmlist.addAll(set);
            Log.e(TAG,"setsize"+String.valueOf(set.size())+""+realmlist.size());

            recyclerView.setAdapter(new ScheduleAdapter(realmlist,getContext()));
        }

    }
}
