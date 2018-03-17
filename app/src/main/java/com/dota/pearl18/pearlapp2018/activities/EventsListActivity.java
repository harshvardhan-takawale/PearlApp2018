package com.dota.pearl18.pearlapp2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dota.pearl18.pearlapp2018.adapters.EventAboutAdapter;
import com.dota.pearl18.pearlapp2018.R;
import com.dota.pearl18.pearlapp2018.api.ApiClient;
import com.dota.pearl18.pearlapp2018.api.ClubInterface;
import com.dota.pearl18.pearlapp2018.api.EventAbout;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsListActivity extends AppCompatActivity {
    ArrayList<EventAbout> list = new ArrayList<>();
    ArrayList<EventAbout> realmlist = new ArrayList<>();
    EventRecyclerView recyclerView;
    EventAboutAdapter adapter;
    RecyclerView.ItemDecoration itemDecoration;
    private Realm realm;
    private  String TAG = EventsListActivity.class.getSimpleName();
    private String clubid;
    private boolean isnetwork = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        realm = Realm.getDefaultInstance();
        Realm.init(this);

        ImageView mainImage = findViewById(R.id.iv_background);

        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition));

        mainImage.setImageResource(getIntent().getIntExtra("img_res",R.drawable.ic_app_credits));
        mainImage.setTransitionName("background");

        Bundle bundle=getIntent().getExtras();
        clubid=bundle.getString("id");
//        Log.e(TAG,clubid);
        list=new ArrayList<EventAbout>();
        recyclerView=findViewById(R.id.event_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ClubInterface apiService = ApiClient.getClient().create(ClubInterface.class);
        Call<ArrayList<EventAbout>> call = apiService.getEventDetails();
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
               setAdapter();
            }

            @Override
            public void onFailure(Call<ArrayList<EventAbout>> call, Throwable t) {
                Log.e("Error:","Error in Connectivity");
                isnetwork = false;
                getDatafromRealm(realm);
                setAdapter();
            }
        });
    }
    public void goBack(View view)
    {
        super.onBackPressed();
    }

    private void addDatatoRealm(EventAbout details)
    {
        realm.beginTransaction();
        EventAbout model = realm.where(EventAbout.class).equalTo("id",details.getId()).findFirst();
        if(model==null)
        {
            EventAbout eventabout = realm.createObject(EventAbout.class);
            eventabout.setId(details.getId());
            eventabout.setName(details.getName());
//            eventabout.setAbout(details.getAbout());
            eventabout.setBody(details.getBody());
            eventabout.setPrize(details.getPrize());
            eventabout.setThumbnail(details.getThumbnail());
            eventabout.setType(details.getType());
            eventabout.setVenue(details.getVenue());
            eventabout.setTagline(details.getTagline());
        }
        else
        {
            model.setName(details.getName());
//            model.setAbout(details.getAbout());
            model.setBody(details.getBody());
            model.setPrize(details.getPrize());
            model.setThumbnail(details.getThumbnail());
            model.setType(details.getType());
            model.setVenue(details.getVenue());
            model.setTagline(details.getTagline());
        }
        realm.commitTransaction();

    }

    private void getDatafromRealm(Realm realm1)
    {
        if(realm1!=null)
        {
            realmlist = new ArrayList<>();
            RealmResults<EventAbout> results = realm1.where(EventAbout.class).equalTo("body",clubid).findAll();
//            Log.e(TAG,"results="+String.valueOf(results.size()));

            if(results.size()==0)
            {
                Toast.makeText(this,"NO Internet",Toast.LENGTH_SHORT).show();
            }
           else
            {
                for(int i=0;i<results.size();i++)
                {
                    if(results.get(i).getType().equals("Competition"))
                    {
                        realmlist.add(results.get(i));
                    }
                }
            }
//            Log.e(TAG,"realmlist="+String.valueOf(realmlist.size()));

        }

    }

    private void setAdapter()
    {
        adapter=new EventAboutAdapter(realmlist);
        recyclerView.setAdapter(adapter);
    }

}
