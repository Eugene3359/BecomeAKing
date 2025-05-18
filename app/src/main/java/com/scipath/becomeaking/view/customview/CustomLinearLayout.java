package com.scipath.becomeaking.view.customview;

import static com.scipath.becomeaking.util.DrawableUtility.createBorderDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.createTiledDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.util.DrawableUtility;


public class CustomLinearLayout extends LinearLayoutCompat {

    // Constructor
    public CustomLinearLayout(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);

        int borderColor = typedArray.getColor(R.styleable.CustomLinearLayout_borderColor, 0x00000000); // Default: null
        int backgroundColor = typedArray.getColor(R.styleable.CustomTextView_backgroundColor, 0x00000000); // Default: null
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomLinearLayout_backgroundDrawable);

        typedArray.recycle();

        // Make background
        Drawable background;
        if (backgroundDrawable != null) {
            backgroundDrawable = createTiledDrawable(context, backgroundDrawable);
            background = createBorderDrawable(context, borderColor, backgroundColor, backgroundDrawable);
        } else {
            background = DrawableUtility.createBorderDrawable(context, borderColor, backgroundColor);
        }

        // Set the background to the layered drawable
        setBackground(background);
    }
}
