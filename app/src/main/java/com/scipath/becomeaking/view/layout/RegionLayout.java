package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class RegionLayout extends LinearLayout {

    ImageView imageViewRegion;


    public RegionLayout(Context context) {
        super(context);
        init(context);
    }

    public RegionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RegionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.layout_region, this);
        imageViewRegion = findViewById(R.id.image_view_region);
        setVisibility(GONE);
    }


    public void setWidth(int width) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageViewRegion.getLayoutParams();
        params.width = width;
        imageViewRegion.setLayoutParams(params);
    }

    public void setHeight(int height) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageViewRegion.getLayoutParams();
        params.height = height;
        imageViewRegion.setLayoutParams(params);
    }

    public void setImageSrc(int drawableId) {
        imageViewRegion.setImageResource(drawableId);
    }
}
