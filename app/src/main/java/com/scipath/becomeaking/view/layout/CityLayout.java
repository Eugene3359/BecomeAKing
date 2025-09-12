package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class CityLayout extends LinearLayout {

    TextView textViewCityName;


    public CityLayout(Context context) {
        super(context);
        init(context);
    }

    public CityLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CityLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.layout_city, this);

        textViewCityName = findViewById(R.id.text_view_city_name);
    }

    public void setName(int cityNameId) {
        textViewCityName.setText(cityNameId);
    }
}
