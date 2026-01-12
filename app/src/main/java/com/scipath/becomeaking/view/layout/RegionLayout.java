package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class RegionLayout extends LinearLayout {

    ImageView imageRegion;


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

    private void init(Context context) {
        inflate(context, R.layout.layout_region, this);
        imageRegion = findViewById(R.id.image_region);
        setVisibility(GONE);
    }


    public void setWidth(int width) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageRegion.getLayoutParams();
        params.width = width;
        imageRegion.setLayoutParams(params);
    }

    public void setHeight(int height) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageRegion.getLayoutParams();
        params.height = height;
        imageRegion.setLayoutParams(params);
    }

    public void setImageSrc(int drawableId) {
        imageRegion.setImageResource(drawableId);
    }
}
