package com.scipath.becomeaking.view.view;

import static com.scipath.becomeaking.util.DrawableUtility.createBorderDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.createTiledDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.util.DrawableUtility;


public class CustomLinearLayout extends LinearLayoutCompat {

    // Fields
    private boolean isSquare;
    private int borderColor;
    private int backgroundColor;
    private Drawable backgroundDrawable;


    // Constructor
    public CustomLinearLayout(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);

        isSquare = typedArray.getBoolean(R.styleable.CustomLinearLayout_isSquare, false); // Default: false
        borderColor = typedArray.getColor(R.styleable.CustomLinearLayout_borderColor, 0x00000000); // Default: null
        backgroundColor = typedArray.getColor(R.styleable.CustomTextView_backgroundColor, 0x00000000); // Default: null
        backgroundDrawable = typedArray.getDrawable(R.styleable.CustomLinearLayout_backgroundDrawable);

        typedArray.recycle();

        applyAttributes();
    }

    public void setBackgroundDrawable(int resId) {
        backgroundDrawable = AppCompatResources.getDrawable(getContext(), resId);
        applyAttributes();
    }

    protected void applyAttributes() {
        // Make background
        Drawable background;
        if (backgroundDrawable != null) {
            backgroundDrawable = createTiledDrawable(getContext(), backgroundDrawable);
            background = createBorderDrawable(getContext(), borderColor, backgroundColor, backgroundDrawable);
        } else {
            background = DrawableUtility.createBorderDrawable(getContext(), borderColor, backgroundColor);
        }

        // Set the background to the layered drawable
        setBackground(background);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isSquare) {
            int size = Math.max(widthMeasureSpec, heightMeasureSpec);
            super.onMeasure(size, size);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return 0;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return 0;
    }
}
