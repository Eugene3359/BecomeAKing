package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class CurrentCityLayout extends LinearLayout {

    ImageView imageView;
    Button buttonCity;
    Button buttonMap;


    public CurrentCityLayout(Context context) {
        super(context);
        init(context);
    }

    public CurrentCityLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CurrentCityLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_current_city, this);
        imageView = findViewById(R.id.image_view);
        buttonCity = findViewById(R.id.button_city);
        buttonMap = findViewById(R.id.button_map);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurrentCityLayout);
        setImageResource(typedArray.getResourceId(R.styleable.CurrentCityLayout_src, R.drawable.img_logging));
        setText(typedArray.getResourceId(R.styleable.CurrentCityLayout_text, R.string.placeholder), context);
    }

    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public void setText(int resId, Context context) {
        buttonCity.setText(resId);
        imageView.setContentDescription(context.getText(resId));
    }

    public void setButtonCityOnClickListener(OnClickListener listener) {
        buttonCity.setOnClickListener(listener);
    }

    public void setButtonMapOnClickListener(OnClickListener listener) {
        buttonMap.setOnClickListener(listener);
    }
}
