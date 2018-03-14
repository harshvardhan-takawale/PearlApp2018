package com.dota.pearl18.pearlapp2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.azoft.carousellayoutmanager.CarouselChildSelectionListener;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.dota.pearl18.pearlapp2018.R;

/**
 * Created by lenovo on 3/14/2018.
 */

public class ProshowActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_proshow);


    final CarouselLayoutManager mLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
    mLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
    final RecyclerView mRecycler = (RecyclerView) findViewById(R.id.recycler_pro_show);
    mRecycler.setLayoutManager(mLayoutManager);
    mRecycler.setHasFixedSize(true);
    new CarouselChildSelectionListener(mRecycler, mLayoutManager) {
      @Override
      protected void onCenterItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {

      }

      @Override
      protected void onBackItemClicked(@NonNull RecyclerView recyclerView, @NonNull CarouselLayoutManager carouselLayoutManager, @NonNull View v) {
        //bring that item to center
        recyclerView.smoothScrollToPosition(recyclerView.getChildAdapterPosition(v));
      }
    };
    mRecycler.setAdapter(new ProShowAdapter());
    mRecycler.scrollToPosition(0);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Intent i = new Intent(ProshowActivity.this, MainActivity.class);
    i.putExtra("scrollTo",1);
    startActivity(i);
    finish();
  }

  class ProShowAdapter extends RecyclerView.Adapter<ProShowAdapter.ProShowViewHolder> {
    int resId[] = new int[]{
        R.drawable.pearl,
        R.drawable.pearl,
        R.drawable.pearl,
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
        img = (ImageView) v.findViewById(R.id.image_pro_show);
      }
    }

  }
}
