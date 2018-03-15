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

    ArrayList<EventAbout> list = new ArrayList<>();
    ArrayList<EventAbout> realmlist = new ArrayList<>();
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
        Call<ArrayList<EventAbout>> call = apiservice.getEventListDetails();
        call.enqueue(new Callback<ArrayList<EventAbout>>() {
            @Override
            public void onResponse(Call<ArrayList<EventAbout>> call, Response<ArrayList<EventAbout>> response) {
                list = response.body();
                for(int i=0;i<list.size();i++)
                {
                    addDatatoRealm(list.get(i));
                }
                isnetwork = true;
                getDatafromRealm(realm);
                eventdetails.setText(realmlist.get(0).getAbout());
            }

            @Override
            public void onFailure(Call<ArrayList<EventAbout>> call, Throwable t) {
                Log.e("Error:", "Error in Connectivity");
                isnetwork = false;
                getDatafromRealm(realm);
                if (realmlist.size() != 0) {
                    eventdetails.setText(realmlist.get(0).getAbout());
                }
            }
        });
    }
    private void addDatatoRealm(EventAbout details)
    {
        realm.beginTransaction();
        EventAbout model = realm.where(EventAbout.class).equalTo("id",details.getId()).findFirst();
        model.setAbout(details.getAbout());
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            realmlist = new ArrayList<>();
            RealmResults<EventAbout> results = realm1.where(EventAbout.class).equalTo("id",id).findAll();
            Log.e(TAG,"results="+String.valueOf(results.size()));

            if(results.size()==0)
            {
                Toast.makeText(this,"NO Internet",Toast.LENGTH_SHORT).show();
            }
            else
            {
                realmlist.addAll(results);
            }
            Log.e(TAG,"realmlist="+String.valueOf(realmlist.size()));

        }

    }
}
