package com.scipath.becomeaking.view.view;

import static com.scipath.becomeaking.util.DrawableUtility.makeGradientDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.makeDrawableTiled;
import static com.scipath.becomeaking.util.DrawableUtility.mergeLayers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.scipath.becomeaking.R;


public class CustomImageView extends AppCompatImageView {

    // Fields
    private boolean isSquare;


    // Constructor
    public CustomImageView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);

        isSquare = typedArray.getBoolean(R.styleable.CustomLinearLayout_isSquare, false); // Default: false
        int borderColor = typedArray.getColor(R.styleable.CustomImageView_borderColor, Color.TRANSPARENT); // Default: TRANSPARENT
        int backgroundColor = typedArray.getColor(R.styleable.CustomImageView_backgroundColor, Color.TRANSPARENT); // Default: TRANSPARENT
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomImageView_backgroundDrawable);

        typedArray.recycle();

        Drawable background;
        if (backgroundDrawable != null) {
            backgroundDrawable = makeDrawableTiled(backgroundDrawable, context);
            background = mergeLayers(
                    backgroundDrawable,
                    makeGradientDrawable(backgroundColor, borderColor, 0f, context),
                    context);
        } else {
            background = makeGradientDrawable(backgroundColor, borderColor, 0f, context);
        }

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
}
