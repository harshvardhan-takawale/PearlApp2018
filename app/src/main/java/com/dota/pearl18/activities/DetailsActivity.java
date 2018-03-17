package com.dota.pearl18.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.pearl18.R;
import com.dota.pearl18.api.ApiClient;
import com.dota.pearl18.api.ClubInterface;
import com.dota.pearl18.api.EventAbout;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwik on 14-03-2018.
 */

public class DetailsActivity extends AppCompatActivity {

    private EventAbout event;
    private EventAbout model;
    private TextView eventdetails,starttime,eventname;
    private String TAG = DetailsActivity.class.getSimpleName();
    private boolean isnetwork = false;
    private Realm realm;
    private String id;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);
        eventdetails = findViewById(R.id.details);
        starttime = findViewById(R.id.starttime);
        eventname = findViewById(R.id.event_details_name);

        realm = Realm.getDefaultInstance();
        Realm.init(this);

        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");

        ClubInterface apiservice = ApiClient.getClient().create(ClubInterface.class);
        Call<EventAbout> call = apiservice.getEventListDetails(id);
        call.enqueue(new Callback<EventAbout>() {
            @Override
            public void onResponse(Call<EventAbout> call, Response<EventAbout> response) {
                event = response.body();
                addDatatoRealm(event);
                eventname.setText(event.getName());
                eventdetails.setText(event.getAbout());
                if (event.getStartTime().equals("")) {
                    starttime.setText("");
                } else {
                    time = getEventTime(event.getStartTime())[3] + ":" + getEventTime(event.getStartTime())[4] + " - " +
                            getEventTime(event.getEndTime())[3] + ":" + getEventTime(event.getEndTime())[4];
                    starttime.setText(time);
                }
            }

            @Override
            public void onFailure(Call<EventAbout> call, Throwable t) {
                getDatafromRealm(realm);
                Log.e(TAG,"error in connectivity");

            }
        });
    }
    private void addDatatoRealm(EventAbout details)
    {
        realm.beginTransaction();
        EventAbout model = realm.where(EventAbout.class).equalTo("id",id).findFirst();
        if(model==null)
        {
           EventAbout eventabout = realm.createObject(EventAbout.class);
           eventabout.setId(details.getId());
           eventabout.setName(details.getName());
           eventabout.setStartTime(details.getStartTime());
           eventabout.setEndTime(details.getEndTime());
        }
        else
        {
            model.setAbout(details.getAbout());
            model.setName(details.getName());
            model.setStartTime(details.getStartTime());
            model.setEndTime(details.getEndTime());
        }
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            EventAbout result = realm1.where(EventAbout.class).equalTo("id",id).findFirst();

            if(result == null)
            {
                Toast.makeText(this, "No Network...Get Connected", Toast.LENGTH_SHORT).show();
                starttime.setText("Please connect to network .... the App needs internet to load data for the first time");
            }

            if(result != null)
            {
                if( result.getStartTime() == null) {
                    Toast.makeText(this, "No Network...Get Connected", Toast.LENGTH_SHORT).show();
                    starttime.setText("Please connect to network .... the App needs internet to load data for the first time");
                }
                Toast.makeText(this,"No Network....Loading Offline Data",Toast.LENGTH_SHORT).show();
                eventname.setText(result.getName());
                eventdetails.setText(result.getAbout());
                if (result.getStartTime()!=null&&result.getStartTime().equals("")) {
                    starttime.setVisibility(View.GONE);
                } else if(result.getStartTime()!=null){
                    time = getEventTime(result.getStartTime())[3] + ":" + getEventTime(result.getStartTime())[4] + " - " +
                            getEventTime(result.getEndTime())[3] + ":" + getEventTime(result.getEndTime())[4];
                    starttime.setText(time);
                }
            }
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
//            Log.e(TAG,parts[0]);
            return parts;
        }

        return parts;

    }


}
