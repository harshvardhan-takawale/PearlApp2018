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
    private TextView eventdetails;
    private String TAG = DetailsActivity.class.getSimpleName();
    private boolean isnetwork = false;
    private Realm realm;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);
        eventdetails = findViewById(R.id.details);

        realm = Realm.getDefaultInstance();
        realm.init(this);

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
            }
        }

    }
}
