package com.dota.pearl18.pearlapp2018.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;

public class OrganisersFragment extends Fragment {

    private ArrayList<Contact> data;
    RecyclerView mRecyclerView;
    ContactAdapter mContactAdapter;

    private String[] names, designations, numbers;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources res = container.getResources();
        names = res.getStringArray(R.array.organiser_names);
        designations = res.getStringArray(R.array.organisers_designations);
        numbers = res.getStringArray(R.array.organisers_phone_numbers);
        return inflater.inflate(R.layout.fragment_organisers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.organiser_recycler);
        mContactAdapter = new ContactAdapter(getActivity());
        mRecyclerView.setAdapter(mContactAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        data = new ArrayList<>();
        feedData();
        mContactAdapter.setArrayList(data);
    }

    public void feedData(){
        for(int i = 0; i < 12 ; i++){
            data.add(new Contact(names[i],designations[i],numbers[i]));
        }
    }

}
