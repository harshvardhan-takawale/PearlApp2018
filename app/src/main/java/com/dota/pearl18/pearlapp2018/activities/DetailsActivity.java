package com.dota.pearl18.pearlapp2018.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ClubInterface;
import com.dota.pearl18.pearlapp2018.api.EventAbout;
import com.dota.pearl18.pearlapp2018.api.EventDetails;
import com.dota.pearl18.pearlapp2018.api.EventsInterface;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashwik on 14-03-2018.
 */

public class DetailsActivity extends AppCompatActivity {

    private EventAbout event;
    private EventAbout model;
    private TextView eventdetails,starttime;
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
              eventdetails.setText(event.getAbout());
              time = getEventTime(event.getStartTime())[3] + ":"+getEventTime(event.getStartTime())[4]+" - "+
                      getEventTime(event.getEndTime())[3]+":"+getEventTime(event.getEndTime())[4];
              starttime.setText(time);
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
        model.setAbout(details.getAbout());
        model.setStartTime(details.getStartTime());
        model.setEndTime(details.getEndTime());
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            EventAbout result = realm1.where(EventAbout.class).equalTo("id",id).findFirst();

            if(result==null)
            {
                Toast.makeText(this,"NO Internet",Toast.LENGTH_SHORT).show();
            }
            else
            {
                eventdetails.setText(result.getAbout());
                time = getEventTime(result.getStartTime())[3] + ":"+getEventTime(result.getStartTime())[4]+" - "+
                        getEventTime(result.getEndTime())[3]+":"+getEventTime(result.getEndTime())[4];
                        starttime.setText(time);
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
            Log.e(TAG,parts[0]);
            return parts;
        }

        return parts;

    }


}
