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

    // Constructor
    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton);

        int borderColor = typedArray.getColor(R.styleable.CustomRadioButton_borderColor, 0x00000000); // Default: null
        int backgroundColor = typedArray.getColor(R.styleable.CustomRadioButton_backgroundColor, 0x00000000); // Default: null
        int backgroundColorSelecteds = typedArray.getColor(R.styleable.CustomRadioButton_backgroundColorSelected, backgroundColor); // Default: backgroundColor
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomRadioButton_backgroundDrawable);
        Drawable backgroundDrawableSelected = typedArray.getDrawable(R.styleable.CustomRadioButton_backgroundDrawableSelected);
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
        Drawable defaultBackground = createBorderDrawable(context, borderColor, backgroundColor, backgroundDrawable);
        Drawable selectedBackground = createBorderDrawable(context, borderColor, backgroundColorSelecteds, backgroundDrawableSelected);

        // Create a selector using StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, selectedBackground);
        stateListDrawable.addState(new int[]{}, defaultBackground); // Default state

        // Set the background to the layered drawable
        setBackground(stateListDrawable);
    }
}
