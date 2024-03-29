package com.dota.pearl18.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dota.pearl18.R;
import com.dota.pearl18.adapters.ScheduleAdapter;
import com.dota.pearl18.api.ApiClient;
import com.dota.pearl18.api.EventDetails;
import com.dota.pearl18.api.EventsInterface;

import java.util.ArrayList;
import java.util.Collections;
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
    private Context context;
    private boolean isnetwork = false;
    private int start;


  /*  public static ScheduleFragment newInstance(int page)
    {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("page_no",page);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        page = getArguments().getInt("page",0);
        start = getArguments().getInt("start",0);
//        Log.e(TAG,"start"+String.valueOf(start));
        recyclerView = view.findViewById(R.id.schedule_recyclerview);
        adapter = new ScheduleAdapter(realmlist,context,day);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

       /* switch (page) {
            case 0: CallApi();break;
        }*/
//       Log.e(TAG,"pagein:"+String.valueOf(page));
       switch (page) {
           case 0:
               day = "23";
               break;
           case 1:
               day = "24";
               break;
           case 2:
               day = "25";
               break;
       }
//       Log.e(TAG,"day:"+day);
        if(start==0)
        {CallApi();}
        else {
            getDatafromRealm(realm);
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
                isnetwork=true;
                getDatafromRealm(realm);
//                Log.e(TAG,"reach"+String.valueOf(list.size()));

            }

            @Override
            public void onFailure(Call<ArrayList<EventDetails>> call, Throwable t) {
                Log.e(TAG,"Error in Connectivity");
                isnetwork=false;
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
            event.setStarttime(getEventTime(details.getStarttime())[3] + ":"+getEventTime(details.getStarttime())[4]);
            event.setEventDescription(details.getEventDescription());
            event.setEventdate(getEventTime(details.getStarttime())[2]);
        }
        else
        {
            model.setEventname(details.getEventname());
            model.setStarttime(getEventTime(details.getStarttime())[3] + ":"+getEventTime(details.getStarttime())[4]);
            model.setEventDescription(details.getEventDescription());
            model.setEventdate(getEventTime(details.getStarttime())[2]);
        }
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            realmlist = new ArrayList<>();
            //RealmResults<EventDetails> results = realm1.where(EventDetails.class).findAll();
            RealmResults<EventDetails> results = realm1.where(EventDetails.class).equalTo("eventdate",day).findAll();
//            Log.e(TAG,"results="+String.valueOf(results.size()));

            if(results.size()==0)
            {
                if(isnetwork==false) {
                    Toast.makeText(context, "NO Internet", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if(isnetwork==false&&start==0)
                {
                    Toast.makeText(context,"No Network....Loading Offline Data",Toast.LENGTH_SHORT).show();
                }
            }
            for(int j=0;j<results.size();j++)
            {
                realmlist.add(results.get(j).getStarttime());
            }

            Set<String> set = new LinkedHashSet<>(realmlist);
            realmlist.clear();
            realmlist.addAll(set);
//            Log.e(TAG,"setsize"+String.valueOf(set.size())+""+realmlist.size());

            Collections.sort(realmlist);
            recyclerView.setAdapter(new ScheduleAdapter(realmlist,getContext(),day));
        }

    }

    public String[] getEventTime(String time)
    {

            // The format of the startTime string is yyyy-MM-dd-HH-mm
            // HH-mm is the time in 24 hour format. Use this after conversion to 12 hour format.

            String pattern = "\\d{4}(-\\d{2}){4}";
            String[] parts ={"","","","",""};
            // testdate corresponds to 10:05 AM (10:05 hours), 11th August 2018
            String testdate = "2018-08-11-10-05"; // replace with details.getStartTime()

            // validation condition. If false, do not parse the time, and have a default fallback option
            if (time.matches(pattern)){
                // Split the testdate String, to obtain the various parts of the time
                 parts = time.split("-");
                // wrt to testdate
                // parts[0] => yyyy => 2018
                // parts[1] => MM => 08
                // parts[2] => DD => 11
                // parts[3] => HH => 10
                // parts[4] => mm => 5
                 return parts;
            }

            return parts;

    }
}
