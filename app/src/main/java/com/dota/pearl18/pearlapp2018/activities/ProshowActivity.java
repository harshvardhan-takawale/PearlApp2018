package com.dota.pearl18.pearlapp2018.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.text.util.LinkifyCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselChildSelectionListener;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.dota.pearl18.pearlapp2018.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.ViewConfigurator;

/**
 * Created by lenovo on 3/14/2018.
 */

public class ProshowActivity extends AppCompatActivity {

    String[] titles = new String[]{
            "Aron Chupa",
            "Tony Junior",
            "Amit Trivedi",
            "Ashish Shakya",
            "Tony Junior"
    };

    String[] talk_titles = new String[]{
            "Aron Chupa",
            "Tony Junior",
            "Amit Trivedi",
            "Ashish Trivedi"
    };

    String[] descriptions = new String[]{
            "<b>Date:</b> __/03/18<br/><b>Desc:</b> lorem ipsum xyz abc <br/><b>Time:</b> 8PM to 9PM<br/><b>Venue:</b> Stage 1 Lawns",
            "<b>Date:</b> __/03/18<br/><b>Desc:</b> lorem ipsum xyz abc <br/><b>Time:</b> 9PM to 12AM<br/><b>Venue:</b> Stage 1 Lawns",
            "<b>Date:</b> __/03/18<br/><b>Desc:</b> lorem ipsum xyz abc <br/><b>Time:</b> 7PM to 10PM<br/><b>Venue:</b> Stage 1 Lawns",
            "<b>Date:</b> __/03/18<br/><b>Desc:</b> lorem ipsum xyz abc <br/><b>Time:</b> 6PM to 7PM<br/><b>Venue:</b> Stage 1 Lawns",
            "<b>Date:</b> __/03/18<br/><b>Desc:</b> lorem ipsum xyz abc <br/><b>Time:</b> 7PM to 9PM<br/><b>Venue:</b> Stage 1 Lawns"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proshow);


        final CarouselLayoutManager mLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        final CarouselLayoutManager mLayoutManager2 = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        mLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        final RecyclerView mRecycler = findViewById(R.id.recycler_pro_show);
        final RecyclerView mRecyclerTalks = findViewById(R.id.recycler_talks);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true);
        mRecyclerTalks.setLayoutManager(mLayoutManager2);
        mRecyclerTalks.setHasFixedSize(true);

        new CarouselChildSelectionListener(mRecycler, mLayoutManager) {
            @Override
            protected void onCenterItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                new LovelyInfoDialog(ProshowActivity.this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setTopTitleColor(Color.WHITE)
                        .setTopTitle(titles[pos])
                        .setMessage(formatContent(descriptions[pos]))
                        .show();
            }

            @Override
            protected void onBackItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
                //bring that item to center
                recyclerView.smoothScrollToPosition(recyclerView.getChildAdapterPosition(v));
            }
        };

        new CarouselChildSelectionListener(mRecyclerTalks, mLayoutManager2){
            @Override
            protected void onCenterItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                new LovelyInfoDialog(ProshowActivity.this)
                        .setTopColorRes(R.color.colorPrimary)
                        .setTopTitleColor(Color.WHITE)
                        .setTopTitle(titles[pos])
                        .setMessage(formatContent(descriptions[pos]))
                        .show();
            }

            @Override
            protected void onBackItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
                //bring that item to center
                recyclerView.smoothScrollToPosition(recyclerView.getChildAdapterPosition(v));
            }
        };

        mRecycler.setAdapter(new ProShowAdapter());
        mRecycler.scrollToPosition(2);

        mRecyclerTalks.setAdapter(new TalksAdapter());
        mRecycler.scrollToPosition(2);
    }

    class ProShowAdapter extends RecyclerView.Adapter<ProShowAdapter.ProShowViewHolder> {
        int resId[] = new int[]{
                R.drawable.proshow_chupa,
                R.drawable.proshow_raghu,
                R.drawable.proshow_trivedi,
                R.drawable.proshow_shakya,
                R.drawable.proshow_tony
        };

        @Override
        public ProShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ProShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_show, parent, false));
        }

        @Override
        public void onBindViewHolder(ProShowViewHolder holder, int position) {
            holder.img.setImageResource(resId[position]);
        }

        @Override
        public int getItemCount() {
            return resId.length;
        }

        class ProShowViewHolder extends RecyclerView.ViewHolder {
            private ImageView img;

            ProShowViewHolder(View v) {
                super(v);
                img = v.findViewById(R.id.image_pro_show);
            }
        }

    }

    private Spanned formatContent(String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT);
        } else {
            //noinspection deprecation
            return Html.fromHtml(content);
        }
    }

    class TalksAdapter extends RecyclerView.Adapter<TalksAdapter.TalksViewHolder>{
        int resId[] = new int[]{
                R.drawable.proshow_chupa,
                R.drawable.proshow_raghu,
                R.drawable.proshow_trivedi,
                R.drawable.proshow_shakya,
                R.drawable.proshow_tony
        };

        @Override
        public TalksAdapter.TalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TalksViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_show, parent, false));
        }

        @Override
        public void onBindViewHolder(TalksViewHolder holder, int position) {
            holder.img.setImageResource(resId[position]);
        }

        @Override
        public int getItemCount() {
            return resId.length;
        }

        class TalksViewHolder extends RecyclerView.ViewHolder {
            private ImageView img;

            TalksViewHolder(View v) {
                super(v);
                img = v.findViewById(R.id.image_pro_show);
            }
        }
    }
}
