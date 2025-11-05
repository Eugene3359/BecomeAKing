package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class CarriageLayout extends LinearLayout {

    public enum Direction {
        Left,
        Right
    }


    ImageView imageViewCarriage;


    public CarriageLayout(Context context) {
        super(context);
        init(context);
    }

    public CarriageLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CarriageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_carriage, this);
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
