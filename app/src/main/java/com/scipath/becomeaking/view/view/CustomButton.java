package com.scipath.becomeaking.view.view;

import static com.scipath.becomeaking.util.DrawableUtility.applyCornerRadius;
import static com.scipath.becomeaking.util.DrawableUtility.makeGradientDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.makeDrawableTiled;
import static com.scipath.becomeaking.util.DrawableUtility.mergeLayers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.scipath.becomeaking.R;


public class CustomButton extends AppCompatButton {

    // Constructor
    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        setIncludeFontPadding(false);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);

        int borderColor = typedArray.getColor(R.styleable.CustomButton_borderColor, Color.TRANSPARENT); // Default: TRANSPARENT
        float cornerRadius = typedArray.getDimension(R.styleable.CustomButton_cornerRadius, 0f); // Default: no rounding
        int backgroundColor = typedArray.getColor(R.styleable.CustomButton_backgroundColor, Color.TRANSPARENT); // Default: TRANSPARENT
        int backgroundColorPressed = typedArray.getColor(R.styleable.CustomButton_backgroundColorPressed, backgroundColor); // Default: backgroundColor
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomButton_backgroundDrawable);
        Drawable backgroundDrawablePressed = typedArray.getDrawable(R.styleable.CustomButton_backgroundDrawablePressed);
        if (backgroundDrawablePressed == null) backgroundDrawablePressed = backgroundDrawable;

        typedArray.recycle();

        if (backgroundDrawable != null) {
            backgroundDrawable = makeDrawableTiled(backgroundDrawable, context);
            backgroundDrawable = applyCornerRadius(backgroundDrawable, cornerRadius, context);
        }
        if (backgroundDrawablePressed != null) {
            backgroundDrawablePressed = makeDrawableTiled(backgroundDrawablePressed, context);
            backgroundDrawablePressed = applyCornerRadius(backgroundDrawablePressed, cornerRadius, context);
        }

        backgroundDrawable = mergeLayers(
                backgroundDrawable,
                makeGradientDrawable(backgroundColor, borderColor, cornerRadius, context),
                context);
        backgroundDrawablePressed = mergeLayers(
                backgroundDrawablePressed,
                makeGradientDrawable(backgroundColorPressed, borderColor, cornerRadius, context),
                context);

        // Create a selector using StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] { android.R.attr.state_pressed }, backgroundDrawablePressed);
        stateListDrawable.addState(new int[] {}, backgroundDrawable); // Default state

        setBackground(stateListDrawable);
    }
}