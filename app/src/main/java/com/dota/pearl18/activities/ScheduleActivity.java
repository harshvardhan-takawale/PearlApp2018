package com.dota.pearl18.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dota.pearl18.R;

public class ScheduleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabs;
    private String TAG = ScheduleActivity.class.getSimpleName();
    private int start = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        viewPager = findViewById(R.id.schedule_viewpager);
        adapter = new viewpageradapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs = findViewById(R.id.schedule_tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                start++;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public  class viewpageradapter extends FragmentPagerAdapter{

        public viewpageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


//            Log.e(TAG,"page:"+String.valueOf(position));
            ScheduleFragment scheduleFragment = new ScheduleFragment();
            Bundle args = new Bundle();
            args.putInt("page",position);
            args.putInt("start",start);
            scheduleFragment.setArguments(args);
            return scheduleFragment;
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
