package com.dota.pearl18.pearlapp2018.activities;

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

public class ClubSenateFragment extends Fragment {

    private ArrayList<Contact> data;
    RecyclerView mRecyclerView;
    ContactAdapter mContactAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_senate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.club_senate_recycler);
        mContactAdapter = new ContactAdapter(getActivity());
        mRecyclerView.setAdapter(mContactAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        data = new ArrayList<>();
        feedData();
        mContactAdapter.setArrayList(data);
    }

    public void feedData(){
        for(int i = 1; i < 20 ; i++){
            data.add(new Contact("Name: " + i, "Designation: " + i));
        }
    }
}
