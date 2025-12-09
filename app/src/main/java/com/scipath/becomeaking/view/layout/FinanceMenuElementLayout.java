package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.scipath.becomeaking.R;


public class FinanceMenuElementLayout extends LinearLayout {

    ImageView imageView1;
    ImageView imageView2;
    TextView textView;
    Button button;


    public FinanceMenuElementLayout(Context context) {
        super(context);
        init(context);
    }

    public FinanceMenuElementLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FinanceMenuElementLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_finance_menu_element, this);
        imageView1 = findViewById(R.id.image_view_1);
        imageView2 = findViewById(R.id.image_view_2);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FinanceMenuElementLayout);
        setImageResource(typedArray.getResourceId(R.styleable.FinanceMenuElementLayout_src, R.drawable.item_placeholder));
        setText(typedArray.getResourceId(R.styleable.FinanceMenuElementLayout_text, R.string.placeholder), context);
        setButtonText(typedArray.getResourceId(R.styleable.FinanceMenuElementLayout_buttonText, R.string.placeholder));
    }


    public void setImageResource(int resId) {
        imageView1.setImageResource(resId);
        imageView2.setImageResource(resId);
    }

    public void setText(int resId, Context context) {
        textView.setText(resId);
        imageView1.setContentDescription(context.getText(resId));
        imageView2.setContentDescription(context.getText(resId));
    }

    public void setButtonText(int resId) {
        button.setText(resId);
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        button.setOnClickListener(listener);
    }
}
