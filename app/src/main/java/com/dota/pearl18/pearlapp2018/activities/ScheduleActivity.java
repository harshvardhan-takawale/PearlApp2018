package com.dota.pearl18.pearlapp2018.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dota.pearl18.pearlapp2018.R;

public class ScheduleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabs;
    private String TAG = ScheduleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        viewPager = findViewById(R.id.schedule_viewpager);
        adapter = new viewpageradapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs = findViewById(R.id.schedule_tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public  class viewpageradapter extends FragmentStatePagerAdapter{

        public viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return ScheduleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Day"+String.valueOf(position+1) ;
        }
    }


}
