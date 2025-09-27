package com.scipath.becomeaking.view.customview;

import static com.scipath.becomeaking.util.DrawableUtility.createBorderDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.createTiledDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.scipath.becomeaking.R;


public class CustomRadioButton extends AppCompatRadioButton {

    // Variables
    int borderColor;
    int backgroundColor;
    int backgroundColorSelected;
    Drawable backgroundDrawable;
    Drawable backgroundDrawableSelected;
    Drawable defaultBackground;
    Drawable selectedBackground;


    // Constructor
    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

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
            backgroundDrawable = createTiledDrawable(context, backgroundDrawable);
        }
        if (backgroundDrawableSelected != null) {
            backgroundDrawableSelected = createTiledDrawable(context, backgroundDrawableSelected);
        }

        // Create default and selected states
        defaultBackground = createBorderDrawable(context, borderColor, backgroundColor, backgroundDrawable);
        selectedBackground = createBorderDrawable(context, borderColor, backgroundColorSelected, backgroundDrawableSelected);

        applyAttributes();
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        defaultBackground = createBorderDrawable(getContext(), borderColor, backgroundColor, backgroundDrawable);
        applyAttributes();
    }

    public void setBackgroundColorSelected(int backgroundColorSelected) {
        this.backgroundColorSelected = backgroundColorSelected;
        selectedBackground = createBorderDrawable(getContext(), borderColor, backgroundColorSelected, backgroundDrawableSelected);
        applyAttributes();
    }

    protected void applyAttributes() {
        // Create a selector using StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, selectedBackground);
        stateListDrawable.addState(new int[]{}, defaultBackground); // Default state

        // Set the background to the layered drawable
        setBackground(stateListDrawable);
    }
}
