package com.dota.pearl18.activities;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Vineeth on 3/16/2018.
 */

class DividerItemDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemPosition = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int dips = 310;

        int viewWidth = convertDipToPixels(dips);



        //first item
        if(itemPosition == 0){
            outRect.left = (screenWidth-viewWidth)/2;
        }

        else if(itemPosition>0 && itemPosition == itemCount-1){
            outRect.right = (screenWidth-viewWidth)/2;
        }
    }

    public static int convertDipToPixels(float dips)
    {
        return (int) (dips * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
