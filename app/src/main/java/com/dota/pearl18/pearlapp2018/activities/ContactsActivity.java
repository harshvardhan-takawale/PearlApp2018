package com.dota.pearl18.pearlapp2018.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.pearl18.pearlapp2018.R;

public class ContactsActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mPager = findViewById(R.id.container);
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            ContactsFragment contactsFragment = new ContactsFragment();
            switch(position){
                //Organisers Contact List
                case 0:
                    args.putInt(ContactsFragment.CONTACTS_SWITCH, 0);
                    break;
                //Club Senate Contact List
                case 1:
                    args.putInt(ContactsFragment.CONTACTS_SWITCH, 1);
                    break;
            }
            contactsFragment.setArguments(args);
            return contactsFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Organising Body";
                case 1:
                    return "Club Senate";
            }
            return null;
        }
    }
}