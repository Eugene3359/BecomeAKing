package com.scipath.becomeaking.view.view;

import static com.scipath.becomeaking.util.DrawableUtility.makeGradientDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.makeDrawableTiled;
import static com.scipath.becomeaking.util.DrawableUtility.mergeLayers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.scipath.becomeaking.R;


public class CustomRadioButton extends AppCompatRadioButton {

    // Fields
    private int borderColor;
    private int backgroundColor;
    private int backgroundColorSelected;
    private Drawable backgroundDrawable;
    private Drawable backgroundDrawableSelected;
    private Drawable defaultBackground;
    private Drawable selectedBackground;


    // Constructor
    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton);

        borderColor = typedArray.getColor(R.styleable.CustomRadioButton_borderColor, 0x00000000); // Default: null
        backgroundColor = typedArray.getColor(R.styleable.CustomRadioButton_backgroundColor, 0x00000000); // Default: null
        backgroundColorSelected = typedArray.getColor(R.styleable.CustomRadioButton_backgroundColorSelected, backgroundColor); // Default: backgroundColor
        backgroundDrawable = typedArray.getDrawable(R.styleable.CustomRadioButton_backgroundDrawable);
        backgroundDrawableSelected = typedArray.getDrawable(R.styleable.CustomRadioButton_backgroundDrawableSelected);
        if (backgroundDrawableSelected == null) backgroundDrawableSelected = backgroundDrawable;

        typedArray.recycle();

        // Make drawables repeatable
        if (backgroundDrawable != null) {
            backgroundDrawable = makeDrawableTiled(backgroundDrawable, context);
        }
        if (backgroundDrawableSelected != null) {
            backgroundDrawableSelected = makeDrawableTiled(backgroundDrawableSelected, context);
        }

        // Create default and selected states
        defaultBackground = mergeLayers(
                backgroundDrawable,
                makeGradientDrawable(backgroundColor, borderColor, 0, context),
                context);
        selectedBackground = mergeLayers(
                backgroundDrawableSelected,
                makeGradientDrawable(backgroundColorSelected, borderColor, 0, context),
                context);

        applyAttributes();
    }


    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        defaultBackground = mergeLayers(
                backgroundDrawable,
                makeGradientDrawable(backgroundColor, borderColor, 0, getContext()),
                getContext());
        applyAttributes();
    }

    public void setBackgroundColorSelected(int backgroundColorSelected) {
        this.backgroundColorSelected = backgroundColorSelected;
        selectedBackground = mergeLayers(
                backgroundDrawableSelected,
                makeGradientDrawable(backgroundColorSelected, borderColor, 0, getContext()),
                getContext());
        applyAttributes();
    }

    protected void applyAttributes() {
        // Create a selector using StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] { android.R.attr.state_checked }, selectedBackground);
        stateListDrawable.addState(new int[] {}, defaultBackground); // Default state

        setBackground(stateListDrawable);
    }
}
