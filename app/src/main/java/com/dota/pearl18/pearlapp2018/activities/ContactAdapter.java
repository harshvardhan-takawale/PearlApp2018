package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.pearl18.pearlapp2018.R;

import java.util.ArrayList;

/**
 * Created by Vineeth on 2/13/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    LayoutInflater mInflater;
    ArrayList<Contact> mArrayList;

    public ContactAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mArrayList = new ArrayList<>();
    }

    public void setArrayList(ArrayList<Contact> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.contact_item,parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.name.setText(mArrayList.get(position).getName());
        holder.designation.setText(mArrayList.get(position).getDesignation());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView designation;
        public ContactViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            designation = view.findViewById(R.id.designation);
        }
    }
}
