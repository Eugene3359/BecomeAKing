package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class CarriageMarkerLayout extends LinearLayout {

    public enum Direction {
        Left,
        Right
    }


    ImageView imageViewCarriage;


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
        imageViewCarriage = findViewById(R.id.image_view_carriage);
    }


    public void setDirection(Direction direction) {
        if (direction == Direction.Left) {
            imageViewCarriage.setImageResource(R.drawable.icon_carriage_left);
        } else {
            imageViewCarriage.setImageResource(R.drawable.icon_carriage_right);
        }
    }
}
