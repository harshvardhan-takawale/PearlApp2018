package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dota.pearl18.pearlapp2018.R;

/**
 * Created by Vineeth on 3/16/2018.
 */

class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    public DividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemPosition = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        //first item
        if(itemPosition == 0){
            outRect.left = mDivider.getMinimumWidth();
        }

        else if(itemPosition>0 && itemPosition == itemCount-1){
            outRect.right = mDivider.getMinimumWidth();
        }
    }
}
