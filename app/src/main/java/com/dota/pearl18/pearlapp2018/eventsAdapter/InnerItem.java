package com.dota.pearl18.pearlapp2018.eventsAdapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dota.pearl18.pearlapp2018.R;

//import org.greenrobot.eventbus.EventBus;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class InnerItem extends com.ramotion.garlandview.inner.InnerItem {

    private final View mInnerLayout;

    public final TextView mEventName;
    public final TextView mDesc;
    public final TextView mDesc1;
    public final ImageView mLogo;
    public final View mAvatarBorder;
    public final View mLine;

    private InnerData mInnerData;

    public InnerItem(View itemView) {
        super(itemView);
        mInnerLayout = ((ViewGroup)itemView).getChildAt(0);

        mEventName = itemView.findViewById(R.id.tv_name);
        mDesc= itemView.findViewById(R.id.tv_desc);
        mDesc1 = itemView.findViewById(R.id.tv_desc1);
        mLogo = itemView.findViewById(R.id.logo);
        mAvatarBorder = itemView.findViewById(R.id.avatar_border);
        mLine = itemView.findViewById(R.id.line);

        /*mInnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(InnerItem.this);
            }
        });*/
    }

    @Override
    protected View getInnerLayout() {
        return mInnerLayout;
    }

    public InnerData getItemData() {
        return mInnerData;
    }

    public void clearContent() {
        Glide.clear(mLogo);
        mInnerData = null;
    }

    void setContent(InnerData data) {
        mInnerData = data;
        mEventName.setText(data.eventName);
        mDesc.setText(data.description);
        mDesc1.setText(data.description2);
        Glide.with(itemView.getContext())
                .load(data.logoUrl)
                .bitmapTransform(new CropCircleTransformation(itemView.getContext()))
                .into(mLogo);
    }

}
