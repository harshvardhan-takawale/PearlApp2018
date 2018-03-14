package com.dota.pearl18.pearlapp2018.activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.dota.pearl18.pearlapp2018.R;

/**
 * Created by Vineeth on 3/14/2018.
 */

public class EventRecyclerView extends RecyclerView {

    public static final String TAG = "EventRecyclerView";

    public EventRecyclerView(Context context) {
        super(context);
    }

    public EventRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();

        if (linearLayoutManager.findViewByPosition(0) == linearLayoutManager.getFocusedChild()) {

        }

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        // views on the screen
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        View lastView = linearLayoutManager.findViewByPosition(lastVisibleItemPosition);
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        View firstView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition);

        // distance we need to scroll
        int leftMargin = (screenWidth - lastView.getWidth()) / 2;
        int rightMargin = (screenWidth - firstView.getWidth()) / 2 + firstView.getWidth();
        int leftEdge = lastView.getLeft();
        int rightEdge = firstView.getRight();
        int scrollDistanceLeft = leftEdge - leftMargin;
        int scrollDistanceRight = rightMargin - rightEdge;

        if (Math.abs(velocityX) < 1000) {
            // The fling is slow -> stay at the current page if we are less than half through,
            // or go to the next page if more than half through

            if (leftEdge > screenWidth / 2) {
                // go to next page
                smoothScrollBy(-scrollDistanceRight, 0);
                Log.d(TAG, "smoothScrollBy(" + -scrollDistanceRight + "," + 0);
            } else if (rightEdge < screenWidth / 2) {
                // go to next page
                smoothScrollBy(scrollDistanceLeft, 0);
            } else {
                // stay at current page
                if (velocityX > 0) {
                    smoothScrollBy(-scrollDistanceRight, 0);
                    Log.d(TAG, "smoothScrollBy(" + -scrollDistanceRight + "," + 0);
                } else {
                    smoothScrollBy(scrollDistanceLeft, 0);
                }
            }
            return true;

        } else {
            // The fling is fast -> go to next page

            if (velocityX > 0) {
                smoothScrollBy(scrollDistanceLeft, 0);
            } else {
                smoothScrollBy(-scrollDistanceRight, 0);
            }
            return true;

        }

    }
}

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