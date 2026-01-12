package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Direction;


public class CarriageMarkerLayout extends LinearLayout {

    ImageView imageCarriage;


    public CarriageMarkerLayout(Context context) {
        super(context);
        init(context);
    }

    public CarriageMarkerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CarriageMarkerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_carriage_marker, this);
        imageCarriage = findViewById(R.id.image_carriage);
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.Left) {
            imageCarriage.setImageResource(R.drawable.marker_carriage_left);
        } else {
            imageCarriage.setImageResource(R.drawable.marker_carriage_right);
        }
    }
}
