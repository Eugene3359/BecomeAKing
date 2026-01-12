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
import androidx.appcompat.widget.AppCompatTextView;

import com.scipath.becomeaking.R;


public class CustomTextView extends AppCompatTextView {

    // Constructor
    public CustomTextView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

        int borderColor = typedArray.getColor(R.styleable.CustomTextView_borderColor, Color.TRANSPARENT); // Default: TRANSPARENT
        int backgroundColor = typedArray.getColor(R.styleable.CustomTextView_backgroundColor, Color.TRANSPARENT); // Default: TRANSPARENT
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomTextView_backgroundDrawable);

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
}
