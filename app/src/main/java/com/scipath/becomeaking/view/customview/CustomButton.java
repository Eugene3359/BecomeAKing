package com.scipath.becomeaking.view.customview;

import static com.scipath.becomeaking.util.DrawableUtility.createBorderDrawable;
import static com.scipath.becomeaking.util.DrawableUtility.createTiledDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.scipath.becomeaking.R;


public class CustomButton extends AppCompatButton {

    // Constructor
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // Custom attributes initialization
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);

        int borderColor = typedArray.getColor(R.styleable.CustomButton_borderColor, 0x00000000); // Default: null
        int backgroundColor = typedArray.getColor(R.styleable.CustomButton_backgroundColor, 0x00000000); // Default: null
        int backgroundColorPressed = typedArray.getColor(R.styleable.CustomButton_backgroundColorPressed, backgroundColor); // Default: backgroundColor
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.CustomButton_backgroundDrawable);
        Drawable backgroundDrawablePressed = typedArray.getDrawable(R.styleable.CustomButton_backgroundDrawablePressed);
        if (backgroundDrawablePressed == null) backgroundDrawablePressed = backgroundDrawable;

        typedArray.recycle();

        // Make drawables repeatable
        if (backgroundDrawable != null) {
            backgroundDrawable = createTiledDrawable(context, backgroundDrawable);
        }
        if (backgroundDrawablePressed != null) {
            backgroundDrawablePressed = createTiledDrawable(context, backgroundDrawablePressed);
        }

        // Create default and pressed states
        Drawable defaultBackground = createBorderDrawable(context, borderColor, backgroundColor, backgroundDrawable);
        Drawable pressedBackground = createBorderDrawable(context, borderColor, backgroundColorPressed, backgroundDrawablePressed);

        // Create a selector using StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedBackground);
        stateListDrawable.addState(new int[]{}, defaultBackground); // Default state

        // Set the background to the layered drawable
        setBackground(stateListDrawable);
    }
}