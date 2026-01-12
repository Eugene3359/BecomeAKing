package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Direction;


public class PersonageMarkerLayout extends LinearLayout {

    ImageView imagePersonage;


    public PersonageMarkerLayout(Context context) {
        super(context);
        init(context);
    }

    public PersonageMarkerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PersonageMarkerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_personage_marker, this);
        imagePersonage = findViewById(R.id.image_personage);
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.Left) {
            imagePersonage.setImageResource(R.drawable.marker_personage_left);
        } else {
            imagePersonage.setImageResource(R.drawable.marker_personage_right);
        }
    }
}
